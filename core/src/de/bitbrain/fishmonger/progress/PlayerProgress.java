package de.bitbrain.fishmonger.progress;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import de.bitbrain.fishmonger.Config;

public class PlayerProgress {

   private static Preferences preferences;

   public static void addMoney(int money) {
      int totalMoney = getTotalMoney();
      preferences.putInteger(Config.TOTAL_MONEY, totalMoney + money);
      preferences.flush();

   }

   public static int getTotalMoney() {
      if (preferences == null) {
         preferences = Gdx.app.getPreferences(Config.PLAYER_PREFERENCES_PATH);
      }
      return preferences.getInteger(Config.TOTAL_MONEY, 0);
   }
}
