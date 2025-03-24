package me.colingrimes.nightbank.config;

import me.colingrimes.midnight.config.annotation.Configuration;
import me.colingrimes.midnight.message.Message;

import static me.colingrimes.midnight.config.option.OptionFactory.message;

@Configuration("messages.yml")
public interface Messages {

	/**************************************************
	 *                 Success Messages               *
	 **************************************************/
	Message<?> WITHDRAW = message("success.withdraw", "&2&l✓ &aYou have withdrawn &l${amount}&a.");
	Message<?> WITHDRAW_CLAIM = message("success.withdraw-claim", "&2&l✓ &aYou have claimed &l${amount}&a.");
	Message<?> BOTTLE = message("success.bottle", "&2&l✓ &aYou have bottled up &l{amount} &aexperience.");
	Message<?> BOTTLE_CLAIM = message("success.bottle-claim", "&2&l✓ &aYou have claimed &l{amount} &aexperience.");

	/**************************************************
	 *                 Failure Messages               *
	 **************************************************/
	Message<?> INVENTORY_FULL = message("failure.inventory-full", "&4&l❌ &cYou do not have enough inventory space.");
	Message<?> NOT_ENOUGH_MONEY = message("failure.not-enough-money", "&4&l❌ &cYou do not have enough money.");
	Message<?> NOT_ENOUGH_EXPERIENCE = message("failure.not-enough-exp", "&4&l❌ &cYou do not have enough experience.");
	Message<?> WITHDRAW_MIN = message("failure.withdraw-min", "&4&l❌ &cThe minimum amount you can withdraw is &l${amount}&c.");
	Message<?> WITHDRAW_MAX = message("failure.withdraw-max", "&4&l❌ &cThe maximum amount you can withdraw is &l${amount}&c.");
	Message<?> BOTTLE_MIN = message("failure.bottle-min", "&4&l❌ &cThe minimum amount you can bottle up is &l{amount} &cexperience.");
	Message<?> BOTTLE_MAX = message("failure.bottle-ax", "&4&l❌ &cThe maximum amount you can bottle up is &l{amount} &cexperience.");

	/**************************************************
	 *                 Usage Messages                 *
	 **************************************************/
	Message<?> WITHDRAW_USAGE = message("usage.withdraw",
			"&eUsage: &a/withdraw <amount>",
			"&a► &7Withdraws the specified amount of money."
	);

	Message<?> BOTTLE_USAGE = message("usage.bottle",
			"&eUsage: &a/bottle <amount>",
			"&a► &7Bottles up the specified amount of experience."
	);
}