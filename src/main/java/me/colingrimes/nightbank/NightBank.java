package me.colingrimes.nightbank;

import me.colingrimes.midnight.Midnight;
import me.colingrimes.midnight.util.Common;
import me.colingrimes.midnight.util.io.Logger;
import me.colingrimes.nightbank.listener.BankListeners;
import org.bukkit.event.Listener;

import javax.annotation.Nonnull;
import java.util.List;

public class NightBank extends Midnight {

	@Override
	protected void enable() {
		if (!Common.hasEconomy()) {
			Logger.severe(this, "No Vault dependency found. Disabling plugin...");
			Common.disable(this);
			return;
		}
	}

	@Override
	protected void registerListeners(@Nonnull List<Listener> listeners) {
		listeners.add(new BankListeners());
	}
}
