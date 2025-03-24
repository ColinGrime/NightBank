package me.colingrimes.nightbank.command.bottle;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.bukkit.Experience;
import me.colingrimes.midnight.util.bukkit.Items;
import me.colingrimes.nightbank.NightBank;
import me.colingrimes.nightbank.config.Messages;
import me.colingrimes.nightbank.config.Settings;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class Bottle implements Command<NightBank> {

	@Override
	public void execute(@Nonnull NightBank plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		Player player = sender.player();
		if (args.getInt(0).isEmpty()) {
			Messages.BOTTLE_USAGE.send(player);
			return;
		}

		int amount = args.getInt(0).get();
		if (amount < Settings.BOTTLE_MIN_AMOUNT.get()) {
			Messages.BOTTLE_MIN.replace("{amount}", Settings.BOTTLE_MIN_AMOUNT.get()).send(player);
		} else if (amount > Settings.BOTTLE_MAX_AMOUNT.get()) {
			Messages.BOTTLE_MAX.replace("{amount}", Settings.BOTTLE_MAX_AMOUNT.get()).send(player);
		} else if (Experience.fromPlayer(player) < amount) {
			Messages.NOT_ENOUGH_EXPERIENCE.send(player);
		} else if (player.getInventory().firstEmpty() == -1) {
			Messages.INVENTORY_FULL.send(player);
		} else {
			Experience.remove(player, amount);
			ItemStack bottleItem = Items.of(Settings.BOTTLE_ITEM.get())
					.placeholder("{amount}", amount)
					.placeholder("{player}", player.getName())
					.nbt("bottle", amount)
					.build();
			player.getInventory().addItem(bottleItem);
			Messages.BOTTLE.replace("{amount}", amount).send(player);
		}
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setUsage(Messages.BOTTLE_USAGE);
		properties.setPermission("nightbank.bottle");
		properties.setArgumentsRequired(1);
		properties.setPlayerRequired(true);
	}
}
