package de.bitbrain.fishmonger.event.handler;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.badlogic.gdx.audio.Sound;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.event.GameEventListener;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import de.bitbrain.fishmonger.Config;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.event.InventoryClearedEvent;
import de.bitbrain.fishmonger.model.Money;
import de.bitbrain.fishmonger.model.inventory.Item;
import de.bitbrain.fishmonger.ui.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InventoryClearedHandler implements GameEventListener<InventoryClearedEvent> {

   private class CashValue {
      public final String description;
      public final int value;

      public CashValue(int value, String description) {
         this.value = value;
         this.description = description;
      }
   }

   private final Money money;

   public InventoryClearedHandler(Money money) {
      this.money = money;
   }

   @Override
   public void onEvent(InventoryClearedEvent event) {
      final List<CashValue> values = calculateCashValues(event.getItems());
      int totalAmount = 0;
      for (int i = 0; i < values.size(); ++i) {
         totalAmount += values.get(i).value;
         final int index = i;
         Tween.call(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
               Toast.getInstance().doToast(values.get(index).description);
               Sound sound = SharedAssetManager.getInstance().get(Assets.Sounds.COIN, Sound.class);
               sound.play();
            }
         }).delay(i * 0.6f)
           .start(SharedTweenManager.getInstance());
      }
      money.addAmount(totalAmount);
   }

   private List<CashValue> calculateCashValues(List<Item> items) {

      List<CashValue> cashValues = new ArrayList<CashValue>();

      // Default Value
      int defaultValue = 0;
      for (Item item : items) {
         defaultValue += item.getValue();
      }
      cashValues.add(new CashValue(defaultValue, "+" + defaultValue + "$"));

      // Bonus: all the same
      if (isAllTheSame(items)) {
         int bonus = Config.BONUS_SAME_MULTIPLICATOR * defaultValue - defaultValue;
         defaultValue *= Config.BONUS_SAME_MULTIPLICATOR;
         cashValues.add(new CashValue(bonus, "+" + bonus + "$ (x" + Config.BONUS_SAME_MULTIPLICATOR  + " bonus)"));
      }

      // Bonus: all different
      if (isAllDifferent(items)) {
         int bonus = Config.BONUS_DIFFERENCE_MULTIPLICATOR * defaultValue - defaultValue;
         cashValues.add(new CashValue(bonus, "+" + bonus + "$ (x" + Config.BONUS_DIFFERENCE_MULTIPLICATOR  + " bonus)"));
      }
      return cashValues;
   }

   private boolean isAllTheSame(List<Item> items) {
      if (items.size() < Config.BONUS_REQUIRED_ITEMS) {
         return false;
      }
      String previousType = null;
      int counter = 1;
      for (Item item : items) {
         if (previousType == null) {
            previousType = item.getId();
         } else {
            if (previousType.equals(item.getId())) {
               counter++;
            }
         }
      }
      return counter >= Config.BONUS_REQUIRED_ITEMS;
   }

   private boolean isAllDifferent(List<Item> items) {
      if (items.size() < Config.BONUS_REQUIRED_ITEMS) {
         return false;
      }
      Set<String> ids = new HashSet<String>();
      for (Item item : items) {
         ids.add(item.getId());
      }
      return ids.size() >= Config.BONUS_REQUIRED_ITEMS;
   }
}
