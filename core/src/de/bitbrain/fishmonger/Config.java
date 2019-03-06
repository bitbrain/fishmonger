package de.bitbrain.fishmonger;

public interface Config {

   String PLAYER_PREFERENCES_PATH = "fishmonger.player.config";
   String TOTAL_MONEY = "money.total";
   String INVENTORY_SLOTS = "inventory.slots";
   String FISHING_ROD_TYPE = "rod.type";

   int NUMBER_OF_INVENTORY_SLOTS = 3;
   int BONUS_REQUIRED_ITEMS = 4;
   int BONUS_DIFFERENCE_MULTIPLICATOR = 4;
   int BONUS_SAME_MULTIPLICATOR = 2;
   float MINIMUM_SELL_RANGE = 22f;
   float SOLD_RANGE = 32f;

   int FISHING_ROD_RANGE = 3;
   float FISHING_ROD_THROW_INTERVAL = 0.2f;
   float FISHING_ROD_PULL_INTERVAL = 0.1f;

   float GAME_DURATION_IN_SECONDS = 80f;

   float MENU_BUTTON_WIDTH = 400f;
   float MENU_BUTTON_HEIGHT = 90f;
   float MENU_BUTTON_PADDING = 20f;
   float MENU_BUTTON_ALPHA = 1f;
}
