package de.bitbrain.fishmonger;

public interface Config {

   int NUMBER_OF_INVENTORY_SLOTS = 4;
   float MINIMUM_SELL_RANGE = 22f;
   float SOLD_RANGE = 32f;

   float FISHING_ROD_RANGE = 6;
   float FISHING_ROD_THROW_INTERVAL = 0.1f;
   float FISHING_ROD_PULL_INTERVAL = 0.03f;

   float GAME_DURATION_IN_SECONDS = 60f;

   float MENU_BUTTON_WIDTH = 500f;
   float MENU_BUTTON_HEIGHT = 120f;
   float MENU_BUTTON_PADDING = 30f;
   float MENU_BUTTON_ALPHA = 1f;
}
