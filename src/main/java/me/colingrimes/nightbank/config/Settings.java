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
	Option<Double> WITHDRAW_MIN_AMOUNT = option("withdraw.min-amount", 1D);
	Option<Double> WITHDRAW_MAX_AMOUNT = option("withdraw.max-amount", 1000000000D);
	Option<String> WITHDRAW_CLAIM_SOUND = option("withdraw.claim-sound", "");
	Option<ItemStack> WITHDRAW_ITEM = option("withdraw", Items.of(Material.PAPER).name("&cInvalid Configuration.").build());

	// Bottle configurations.
	Option<Integer> BOTTLE_MIN_AMOUNT = option("bottle.min-amount", 1);
	Option<Integer> BOTTLE_MAX_AMOUNT = option("bottle.max-amount", 1000000);
	Option<String> BOTTLE_CLAIM_SOUND = option("bottle.claim-sound", "");
	Option<ItemStack> BOTTLE_ITEM = option("bottle", Items.of(Material.EXPERIENCE_BOTTLE).name("&cInvalid Configuration.").build());

	// Other configurations.
	Option<String> ADMIN_PLAYER = option("admin-player", "&cAdmin");
}
