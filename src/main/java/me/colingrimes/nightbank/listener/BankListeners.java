package me.colingrimes.nightbank.listener;

import me.colingrimes.midnight.util.Common;
import me.colingrimes.midnight.util.bukkit.Experience;
import me.colingrimes.midnight.util.bukkit.Inventories;
import me.colingrimes.midnight.util.bukkit.NBT;
import me.colingrimes.nightbank.config.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.text.NumberFormat;

public class BankListeners implements Listener {

	@EventHandler
	public void onPlayerInteract(@Nonnull PlayerInteractEvent event) {
		if (!(event.getAction().name().startsWith("RIGHT_CLICK"))) {
			return;
		}

		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();

		// Depositing a banknote.
		double withdrawAmount = NBT.getTag(item, "withdraw", double.class).orElse(0.0);
		if (withdrawAmount > 0 && player.hasPermission("nightbank.withdraw.claim")) {
			event.setCancelled(true);
			withdrawAmount *= removeClaimedItems(player, item, "nightbank.withdraw.claim.stack");

			// Item was successfully redeemed.
			if (withdrawAmount > 0) {
				Common.economy().depositPlayer(player, withdrawAmount);
				Messages.WITHDRAW_CLAIM.replace("{amount}", NumberFormat.getInstance().format(withdrawAmount)).send(player);
			}
			return;
		}

		// Depositing an experience bottle.
		int bottleAmount = NBT.getTag(item, "bottle", int.class).orElse(0);
		if (bottleAmount > 0 && player.hasPermission("nightbank.bottle.claim")) {
			event.setCancelled(true);
			bottleAmount *= removeClaimedItems(player, item, "nightbank.bottle.claim.stack");

			// Item was successfully redeemed.
			if (bottleAmount > 0) {
				Experience.add(player, bottleAmount);
				Messages.BOTTLE_CLAIM.replace("{amount}", NumberFormat.getInstance().format(bottleAmount)).send(player);
			}
		}
	}

	/**
	 * If the player is shifting, and they have permission, the current stack of items will be claimed.
	 * Otherwise, only one item will be claimed.
	 *
	 * @param player the player to remove from
	 * @param item the item to remove
	 * @param claimStackPermission the permission to claim the stack
	 * @return the amount of items removed
	 */
	private int removeClaimedItems(@Nonnull Player player, @Nonnull ItemStack item, @Nonnull String claimStackPermission) {
		if (player.isSneaking() && player.hasPermission(claimStackPermission)) {
			return Inventories.remove(player.getInventory(), item);
		} else {
			return Inventories.removeSingle(player.getInventory(), item);
		}
	}
}
