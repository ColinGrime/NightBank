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
	Message<?> WITHDRAW_RECEIVE = message("success.withdraw-receive", "&2&l✓ &aYou have received a &l${amount} &abanknote.");
	Message<?> BOTTLE = message("success.bottle", "&2&l✓ &aYou have bottled up &l{amount} &aexperience.");
	Message<?> BOTTLE_CLAIM = message("success.bottle-claim", "&2&l✓ &aYou have claimed &l{amount} &aexperience.");
	Message<?> BOTTLE_RECEIVE = message("success.bottle-receive", "&2&l✓ &aYou have received &l{amount} &aexperience.");
	Message<?> RELOADED = message("success.reloaded", "&2&l✓ &a&lNightBank &ahas been reloaded.");

	/**************************************************
	 *                 Failure Messages               *
	 **************************************************/
	Message<?> INVENTORY_FULL = message("failure.inventory-full", "&4&l❌ &cYou do not have enough inventory space.");
	Message<?> NOT_ENOUGH_MONEY = message("failure.not-enough-money", "&4&l❌ &cYou do not have enough money.");
	Message<?> NOT_ENOUGH_EXPERIENCE = message("failure.not-enough-exp", "&4&l❌ &cYou do not have enough experience.");
	Message<?> WITHDRAW_MIN = message("failure.withdraw-min", "&4&l❌ &cThe minimum amount you can withdraw is &l${amount}&c.");
	Message<?> WITHDRAW_MAX = message("failure.withdraw-max", "&4&l❌ &cThe maximum amount you can withdraw is &l${amount}&c.");
	Message<?> WITHDRAW_CLAIM_FAILURE = message("failure.withdraw-claim", "&4&l❌ &cUnable to claim this banknote. It might be too much?");
	Message<?> BOTTLE_MIN = message("failure.bottle-min", "&4&l❌ &cThe minimum amount you can bottle up is &l{amount} &cexperience.");
	Message<?> BOTTLE_MAX = message("failure.bottle-max", "&4&l❌ &cThe maximum amount you can bottle up is &l{amount} &cexperience.");
	Message<?> NOT_ONLINE = message("failure.not-online", "&4&l❌ &cThe specified player is not online.");

	/**************************************************
	 *                 Usage Messages                 *
	 **************************************************/
	Message<?> NIGHTBANK_USAGE = message("usage.nightbank",
			"&7&m-------------&r &e&lNightBank &aCommands &7&m-------------",
			"&7- &a/nightbank reload &e: &7Reloads config files.",
			"&7- &a/withdraw give <player> <amount> &e: &7Gives a banknote.",
			"&7- &a/withdraw give all <amount> &e: &7Gives banknotes.",
			"&7- &a/bottle give <player> <amount> &e: &7Gives an XP bottle.",
			"&7- &a/bottle give all <amount> &e: &7Gives XP bottles.",
			"&7&m---------------------------------------------"
	);

	Message<?> WITHDRAW_USAGE = message("usage.withdraw",
			"&eUsage: &a/withdraw <amount>",
			"&a► &7Withdraws the specified amount of money."
	);

	Message<?> BOTTLE_USAGE = message("usage.bottle",
			"&eUsage: &a/bottle <amount>",
			"&a► &7Bottles up the specified amount of experience."
	);
}