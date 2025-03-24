package me.colingrimes.nightbank.command.withdraw;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.Common;
import me.colingrimes.midnight.util.bukkit.Items;
import me.colingrimes.nightbank.NightBank;
import me.colingrimes.nightbank.config.Messages;
import me.colingrimes.nightbank.config.Settings;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class Withdraw implements Command<NightBank> {

	@Override
	public void execute(@Nonnull NightBank plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		Player player = sender.player();
		if (args.getDouble(0).isEmpty()) {
			Messages.WITHDRAW_USAGE.send(player);
			return;
		}

		double amount = args.getDouble(0).get();
		if (amount < Settings.WITHDRAW_MIN_AMOUNT.get()) {
			Messages.WITHDRAW_MIN.replace("{amount}", Settings.WITHDRAW_MIN_AMOUNT.get()).send(player);
		} else if (amount > Settings.WITHDRAW_MAX_AMOUNT.get()) {
			Messages.WITHDRAW_MAX.replace("{amount}", Settings.WITHDRAW_MAX_AMOUNT.get()).send(player);
		} else if (Common.economy().getBalance(player) < amount) {
			Messages.NOT_ENOUGH_MONEY.send(player);
		} else if (player.getInventory().firstEmpty() == -1) {
			Messages.INVENTORY_FULL.send(player);
		} else {
			Common.economy().withdrawPlayer(player, amount);
			ItemStack withdrawItem = Items.of(Settings.WITHDRAW_ITEM.get())
					.placeholder("{amount}", amount)
					.placeholder("{player}", player.getName())
					.nbt("withdraw", amount)
					.build();
			player.getInventory().addItem(withdrawItem);
			Messages.WITHDRAW.replace("{amount}", amount).send(player);
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
