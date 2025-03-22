package me.colingrimes.nightbank.command.withdraw;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.message.Placeholders;
import me.colingrimes.midnight.util.Common;
import me.colingrimes.midnight.util.bukkit.Items;
import me.colingrimes.nightbank.NightBank;
import me.colingrimes.nightbank.config.Settings;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Optional;

public class Withdraw implements Command<NightBank> {

	@Override
	public void execute(@Nonnull NightBank plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		if (args.getLong(0).isEmpty()) {
			return;
		}

		long amount = args.getLong(0).get();
		Player player = sender.player();
		ItemStack withdrawItem = Items.of(Settings.WITHDRAW_ITEM.get())
				.placeholder("{amount}", amount)
				.placeholder("{player}", player.getName())
				.build();
		player.getInventory().addItem(withdrawItem);
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setPermission("nightbank.withdraw");
		properties.setArgumentsRequired(1);
		properties.setPlayerRequired(true);
	}
}
