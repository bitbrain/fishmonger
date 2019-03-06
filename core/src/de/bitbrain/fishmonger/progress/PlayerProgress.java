package de.bitbrain.fishmonger.progress;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import de.bitbrain.fishmonger.Config;
import de.bitbrain.fishmonger.catching.HookType;

public class PlayerProgress {

   private static Preferences preferences;

   public static void addMoney(int money) {
      int totalMoney = getTotalMoney();
      preferences.putInteger(Config.TOTAL_MONEY, totalMoney + money);
      preferences.flush();
   }

   public static void removeMoney(int money) {
      int totalMoney = getTotalMoney();
      preferences.putInteger(Config.TOTAL_MONEY, totalMoney - money);
      preferences.flush();
   }

   public static int getTotalMoney() {
      if (preferences == null) {
         preferences = Gdx.app.getPreferences(Config.PLAYER_PREFERENCES_PATH);
      }
      return preferences.getInteger(Config.TOTAL_MONEY, 0);
   }

   public static void setInventorySlots(int slots) {
      if (preferences == null) {
         preferences = Gdx.app.getPreferences(Config.PLAYER_PREFERENCES_PATH);
      }
      preferences.putInteger(Config.INVENTORY_SLOTS, slots);
      preferences.flush();
   }

   public static int getInventorySlots() {
      if (preferences == null) {
         preferences = Gdx.app.getPreferences(Config.PLAYER_PREFERENCES_PATH);
      }
      return preferences.getInteger(Config.INVENTORY_SLOTS, Config.NUMBER_OF_INVENTORY_SLOTS);
   }

   public static void setHookType(HookType hookType) {
      if (preferences == null) {
         preferences = Gdx.app.getPreferences(Config.PLAYER_PREFERENCES_PATH);
      }
      preferences.putString(Config.HOOK_TYPE, hookType.toString());
      preferences.flush();
   }

   public static HookType getHookType() {
      if (preferences == null) {
         preferences = Gdx.app.getPreferences(Config.PLAYER_PREFERENCES_PATH);
      }
      return HookType.valueOf(preferences.getString(Config.HOOK_TYPE, HookType.DEFAULT.toString()));
   }
}
