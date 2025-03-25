package me.colingrimes.nightbank.command.bottle;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.bukkit.Experience;
import me.colingrimes.midnight.util.bukkit.Inventories;
import me.colingrimes.midnight.util.bukkit.Items;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.nightbank.NightBank;
import me.colingrimes.nightbank.config.Messages;
import me.colingrimes.nightbank.config.Settings;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.text.NumberFormat;

public class Bottle implements Command<NightBank> {

	@Override
	public void execute(@Nonnull NightBank plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		Player player = sender.player();
		if (args.getInt(0).isEmpty() && !args.isEqual(0, "all")) {
			Messages.BOTTLE_USAGE.send(player);
			return;
		} else if (args.isEqual(0, "all") && !sender.hasPermission("nightbank.bottle.all")) {
			sender.message(Text.color("&4&l❌ &cYou lack the required permission for this command."));
			return;
		}


		int amount = args.getInt(0).orElse(Math.min(Experience.fromPlayer(player), Settings.BOTTLE_MAX_AMOUNT.get()));
		ItemStack bottleItem = Items.of(Settings.BOTTLE_ITEM.get().clone())
				.placeholder("{amount}", NumberFormat.getInstance().format(amount))
				.placeholder("{player}", player.getName())
				.nbt("bottle", amount)
				.build();

		if (amount < Settings.BOTTLE_MIN_AMOUNT.get()) {
			Messages.BOTTLE_MIN.replace("{amount}", NumberFormat.getInstance().format(Settings.BOTTLE_MIN_AMOUNT.get())).send(player);
		} else if (amount > Settings.BOTTLE_MAX_AMOUNT.get()) {
			Messages.BOTTLE_MAX.replace("{amount}", NumberFormat.getInstance().format(Settings.BOTTLE_MAX_AMOUNT.get())).send(player);
		} else if (Experience.fromPlayer(player) < amount) {
			Messages.NOT_ENOUGH_EXPERIENCE.send(player);
		} else if (!Inventories.canFit(player.getInventory(), bottleItem)) {
			Messages.INVENTORY_FULL.send(player);
		} else {
			Experience.remove(player, amount);
			player.getInventory().addItem(bottleItem);
			Messages.BOTTLE.replace("{amount}", NumberFormat.getInstance().format(amount)).send(player);
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
