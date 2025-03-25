package me.colingrimes.nightbank.listener;

import me.colingrimes.midnight.util.Common;
import me.colingrimes.midnight.util.bukkit.Experience;
import me.colingrimes.midnight.util.bukkit.Inventories;
import me.colingrimes.midnight.util.bukkit.NBT;
import me.colingrimes.midnight.util.bukkit.Players;
import me.colingrimes.midnight.util.text.Parser;
import me.colingrimes.nightbank.config.Messages;
import me.colingrimes.nightbank.config.Settings;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.text.NumberFormat;
import java.util.Optional;

public class BankListeners implements Listener {

	@EventHandler
	public void onPlayerInteract(@Nonnull PlayerInteractEvent event) {
		if (!(event.getAction().name().startsWith("RIGHT_CLICK")) || event.getHand() != EquipmentSlot.HAND) {
			return;
		}

		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		int originalAmount = item.getAmount();

		// Depositing a banknote.
		double withdrawAmount = NBT.getTag(item, "withdraw", double.class).orElse(0.0);
		if (withdrawAmount > 0 && player.hasPermission("nightbank.withdraw.claim")) {
			event.setCancelled(true);
			withdrawAmount *= removeClaimedItems(player, item, "nightbank.withdraw.claim.stack");

			// Attempt to redeem the item -- could still fail if the economy plugin sends back a bad response.
			if (withdrawAmount == 0) {
				return;
			} else if (Common.economy().depositPlayer(player, withdrawAmount).type == EconomyResponse.ResponseType.SUCCESS) {
				Messages.WITHDRAW_CLAIM.replace("{amount}", NumberFormat.getInstance().format(withdrawAmount)).send(player);
				Optional.ofNullable(Parser.parseSound(Settings.WITHDRAW_CLAIM_SOUND.get())).ifPresent(s -> Players.sound(player, s));
			} else {
				item.setAmount(originalAmount);
				player.getInventory().setItemInMainHand(item);
				Messages.WITHDRAW_CLAIM_FAILURE.send(player);
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
				Optional.ofNullable(Parser.parseSound(Settings.BOTTLE_CLAIM_SOUND.get())).ifPresent(s -> Players.sound(player, s));
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
