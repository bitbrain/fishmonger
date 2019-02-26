package de.bitbrain.fishmonger;

public interface Config {

   String PLAYER_PREFERENCES_PATH = "fishmonger.player.config";
   String TOTAL_MONEY = "money.total";

   int NUMBER_OF_INVENTORY_SLOTS = 4;
   int BONUS_DIFFERENCE_MULTIPLICATOR = 4;
   int BONUS_SAME_MULTIPLICATOR = 2;
   float MINIMUM_SELL_RANGE = 22f;
   float SOLD_RANGE = 32f;

   float FISHING_ROD_RANGE = 6;
   float FISHING_ROD_THROW_INTERVAL = 0.1f;
   float FISHING_ROD_PULL_INTERVAL = 0.03f;

   float GAME_DURATION_IN_SECONDS = 80f;

   float MENU_BUTTON_WIDTH = 400f;
   float MENU_BUTTON_HEIGHT = 90f;
   float MENU_BUTTON_PADDING = 20f;
   float MENU_BUTTON_ALPHA = 1f;
}
