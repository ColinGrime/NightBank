package me.colingrimes.nightbank.command.bottle;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.nightbank.NightBank;

import javax.annotation.Nonnull;

public class Bottle implements Command<NightBank> {

	@Override
	public void execute(@Nonnull NightBank plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {

	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setPermission("nightbank.bottle");
		properties.setArgumentsRequired(1);
		properties.setPlayerRequired(true);
	}
}
