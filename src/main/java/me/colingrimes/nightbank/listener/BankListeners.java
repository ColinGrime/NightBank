package me.colingrimes.nightbank.listener;

import me.colingrimes.midnight.util.Common;
import me.colingrimes.midnight.util.bukkit.Experience;
import me.colingrimes.midnight.util.bukkit.NBT;
import me.colingrimes.midnight.util.bukkit.Players;
import me.colingrimes.nightbank.config.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Optional;

public class BankListeners implements Listener {

	@EventHandler
	public void onPlayerInteract(@Nonnull PlayerInteractEvent event) {
		if (!(event.getAction().name().startsWith("RIGHT_CLICK"))) {
			return;
		}

		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();

		// Depositing a banknote.
		Optional<Double> withdrawAmount = NBT.getTag(item, "withdraw", double.class);
		if (withdrawAmount.isPresent()) {
			event.setCancelled(true);
			if (Players.removeItem(player, item, 1)) {
				Common.economy().depositPlayer(player, withdrawAmount.get());
				Messages.WITHDRAW_CLAIM.replace("{amount}", withdrawAmount.get()).send(player);
			}
		}

		// Depositing an experience bottle.
		Optional<Integer> bottleAmount = NBT.getTag(item, "bottle", int.class);
		if (bottleAmount.isPresent()) {
			event.setCancelled(true);
			if (Players.removeItem(player, item, 1)) {
				Experience.add(player, bottleAmount.get());
				Messages.BOTTLE_CLAIM.replace("{amount}", bottleAmount.get()).send(player);
			}
		}
	}
}
