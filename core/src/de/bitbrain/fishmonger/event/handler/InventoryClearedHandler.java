package de.bitbrain.fishmonger.event.handler;

import de.bitbrain.braingdx.event.GameEventListener;
import de.bitbrain.fishmonger.Config;
import de.bitbrain.fishmonger.event.InventoryClearedEvent;
import de.bitbrain.fishmonger.model.Money;
import de.bitbrain.fishmonger.model.inventory.Item;
import de.bitbrain.fishmonger.ui.Toast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InventoryClearedHandler implements GameEventListener<InventoryClearedEvent> {

   private final Money money;

   public InventoryClearedHandler(Money money) {
      this.money = money;
   }

   @Override
   public void onEvent(InventoryClearedEvent event) {
      int totalAmount = calculateTotalValue(event.getItems());
      money.addAmount(totalAmount);
   }

   private int calculateTotalValue(List<Item> items) {
      int totalValue = 0;
      for (Item item : items) {
         totalValue += item.getValue();
      }

      if (isAllTheSame(items)) {
         totalValue *= Config.BONUS_SAME_MULTIPLICATOR;
         Toast.getInstance().doToast("+" + totalValue + "$ (x" + Config.BONUS_SAME_MULTIPLICATOR  + " bonus)");
         return totalValue;
      }

      if (isAllDifferent(items)) {
         totalValue *= Config.BONUS_DIFFERENCE_MULTIPLICATOR;
         Toast.getInstance().doToast("+" + totalValue + "$ (x" + Config.BONUS_DIFFERENCE_MULTIPLICATOR + " bonus)");
         return totalValue;
      }
      Toast.getInstance().doToast("+" + totalValue + "$");
      return totalValue;
   }

   private boolean isAllTheSame(List<Item> items) {
      String previousType = null;
      for (Item item : items) {
         if (previousType == null) {
            previousType = item.getId();
         } else {
            if (!previousType.equals(item.getId())) {
               return false;
            }
         }
      }
      return true;
   }

   private boolean isAllDifferent(List<Item> items) {
      Set<String> ids = new HashSet<String>();
      for (Item item : items) {
         ids.add(item.getId());
      }
      return items.size() == ids.size();
   }
}
