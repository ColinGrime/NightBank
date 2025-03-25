package me.colingrimes.nightbank.command.withdraw.give;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.bukkit.Items;
import me.colingrimes.midnight.util.bukkit.Players;
import me.colingrimes.nightbank.NightBank;
import me.colingrimes.nightbank.config.Messages;
import me.colingrimes.nightbank.config.Settings;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.text.NumberFormat;
import java.util.Collection;

public class WithdrawGive implements Command<NightBank> {

	@Override
	public void execute(@Nonnull me.colingrimes.nightbank.NightBank plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		if (args.getDouble(1).isEmpty()) {
			Messages.NIGHTBANK_USAGE.send(sender);
			return;
		}

		Collection<? extends Player> players = args.getPlayer(0).stream().toList();
		if (args.isEqual(0, "all")) {
			players = Players.all();
		} else if (players.isEmpty()) {
			Messages.NOT_ONLINE.send(sender);
			return;
		}

		double amount = args.getDouble(1).get();
		ItemStack withdrawItem = Items.of(Settings.WITHDRAW_ITEM.get().clone())
				.placeholder("{amount}", NumberFormat.getInstance().format(amount))
				.placeholder("{player}", Settings.ADMIN_PLAYER.get())
				.nbt("withdraw", amount)
				.build();
		for (Player player : players) {
			player.getInventory().addItem(withdrawItem);
			Messages.WITHDRAW_RECEIVE.replace("{amount}", NumberFormat.getInstance().format(amount)).send(player);
		}
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setUsage(Messages.NIGHTBANK_USAGE);
		properties.setPermission("nightbank.admin");
		properties.setArgumentsRequired(2);
	}
}
