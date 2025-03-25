package me.colingrimes.nightbank.command.nightbank;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.nightbank.config.Messages;

import javax.annotation.Nonnull;

public class NightBank implements Command<me.colingrimes.nightbank.NightBank> {

	@Override
	public void execute(@Nonnull me.colingrimes.nightbank.NightBank plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		if (args.isEqual(0, "reload")) {
			plugin.getConfigurationManager().reload();
			Messages.RELOADED.send(sender);
		} else {
			Messages.NIGHTBANK_USAGE.send(sender);
		}
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setUsage(Messages.NIGHTBANK_USAGE);
		properties.setPermission("nightbank.admin");
	}
}
