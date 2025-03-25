<div align="center">
    <img src="images/NightBank.png" alt="NightBank" width="300" height="300" />
</div>

<div align="center">
    <h3>NightBank</h3>
    <p>Withdraw currency (<b>$</b>) into banknotes, and experience (<b>XP</b>) into bottles.</p>
</div>

## Features
* `Banknotes` - Withdraw your **$** into banknotes.
* `XP Bottles` - Withdraw your **XP** into experience bottles.
* `Withdraw All` - Withdraw all **$** / **XP** in a single command.
* `Claim Stack` - Shift to claim all banknotes / XP bottles of the same stack.
* `100% Customization` - Always :)

## Configurations:
* [`config.yml`](https://github.com/ColinGrime/NightBank/blob/master/src/main/resources/config.yml) - Allows you to change the various settings associated with this plugin.
* [`messages.yml`](https://github.com/ColinGrime/NightBank/blob/master/src/main/resources/messages.yml) - Allows you to change practically all messages used in this plugin.

## Commands:
* `/withdraw [amount]` (nightbank.withdraw)
* `/withdraw all` (nightbank.withdraw.all)
* `/bottle [amount]` (nightbank.bottle)
* `/bottle all` (nightbank.bottle.all)

## Admin Commands:
* `/nightbank reload` (nightbank.admin)
* `/withdraw give [player] [amount]` (nightbank.admin)
* `/withdraw give all [amount]` (nightbank.admin)
* `/bottle give [player] [amount]` (nightbank.admin)
* `/bottle give all [amount]` (nightbank.admin)

## Permissions:
* `nightbank.admin` - Access to all Admin commands.
* `nightbank.bottle` - Withdraw **XP** into XP bottles.
* `nightbank.bottle.all` - Withdraw all **XP** into a single bottle.
* `nightbank.bottle.claim` - Claim XP bottles.
* `nightbank.bottle.claim.stack` - Shift to claim all XP bottles of the same stack.
* `nightbank.withdraw` - Withdraw **$** into banknotes.
* `nightbank.withdraw.all` - Withdraw all **$** into a single banknote.
* `nightbank.withdraw.claim` - Claim banknotes.
* `nightbank.withdraw.claim.stack` - Shift to claim all banknotes of the same stack.
