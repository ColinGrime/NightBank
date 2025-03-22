package me.colingrimes.nightbank.config;

import me.colingrimes.midnight.config.annotation.Configuration;
import me.colingrimes.midnight.config.option.Option;
import me.colingrimes.midnight.util.bukkit.Items;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static me.colingrimes.midnight.config.option.OptionFactory.option;

@Configuration
public interface Settings {

	// Banknote configurations.
	Option<Long> WITHDRAW_MIN_AMOUNT = option("withdraw.min-amount", 1L);
	Option<Long> WITHDRAW_MAX_AMOUNT = option("withdraw.max-amount", 1000000L);
	Option<ItemStack> WITHDRAW_ITEM = option("withdraw", Items.of(Material.PAPER).name("&cInvalid Configuration.").build());

	// Bottle configurations.
	Option<Long> BOTTLE_MIN_AMOUNT = option("bottle.min-amount", 1L);
	Option<Long> BOTTLE_MAX_AMOUNT = option("bottle.max-amount", 1000000L);
	Option<ItemStack> BOTTLE_ITEM = option("bottle", Items.of(Material.EXPERIENCE_BOTTLE).name("&cInvalid Configuration.").build());

	// Other configurations.
	Option<String> ADMIN_PLAYER = option("admin-player", "Admin");
}
