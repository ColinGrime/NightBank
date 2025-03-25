package me.colingrimes.nightbank.command.withdraw;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.Common;
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

public class Withdraw implements Command<NightBank> {

	@Override
	public void execute(@Nonnull NightBank plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		Player player = sender.player();
		if (args.getDouble(0).isEmpty() && !args.isEqual(0, "all")) {
			Messages.WITHDRAW_USAGE.send(player);
			return;
		} else if (args.isEqual(0, "all") && !sender.hasPermission("nightbank.withdraw.all")) {
			Messages.NO_PERMISSION.send(sender);
			return;
		}

		double amount = args.getDouble(0).orElse(Math.min(Common.economy().getBalance(player), Settings.WITHDRAW_MAX_AMOUNT.get()));
		ItemStack withdrawItem = Items.of(Settings.WITHDRAW_ITEM.get().clone())
				.placeholder("{amount}", NumberFormat.getInstance().format(amount))
				.placeholder("{player}", player.getName())
				.nbt("withdraw", amount)
				.build();

		if (amount < Settings.WITHDRAW_MIN_AMOUNT.get()) {
			Messages.WITHDRAW_MIN.replace("{amount}", NumberFormat.getInstance().format(Settings.WITHDRAW_MIN_AMOUNT.get())).send(player);
		} else if (amount > Settings.WITHDRAW_MAX_AMOUNT.get()) {
			Messages.WITHDRAW_MAX.replace("{amount}", NumberFormat.getInstance().format(Settings.WITHDRAW_MAX_AMOUNT.get())).send(player);
		} else if (Common.economy().getBalance(player) < amount) {
			Messages.NOT_ENOUGH_MONEY.send(player);
		} else if (!Inventories.canFit(player.getInventory(), withdrawItem)) {
			Messages.INVENTORY_FULL.send(player);
		} else {
			Common.economy().withdrawPlayer(player, amount);
			player.getInventory().addItem(withdrawItem);
			Messages.WITHDRAW.replace("{amount}", NumberFormat.getInstance().format(amount)).send(player);
		}
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setUsage(Messages.WITHDRAW_USAGE);
		properties.setPermission("nightbank.withdraw");
		properties.setArgumentsRequired(1);
		properties.setPlayerRequired(true);
	}
}
