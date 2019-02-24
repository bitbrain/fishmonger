package de.bitbrain.fishmonger.event.handler;

import de.bitbrain.braingdx.event.GameEventListener;
import de.bitbrain.fishmonger.event.InventoryClearedEvent;
import de.bitbrain.fishmonger.model.Money;
import de.bitbrain.fishmonger.model.inventory.Item;
import de.bitbrain.fishmonger.ui.Toast;

import java.util.List;

public class InventoryClearedHandler implements GameEventListener<InventoryClearedEvent> {

   private final Money money;

   public InventoryClearedHandler(Money money) {
      this.money = money;
   }

   @Override
   public void onEvent(InventoryClearedEvent event) {
      int totalAmount = calculateTotalValue(event.getItems());
      money.addAmount(totalAmount);
      Toast.getInstance().doToast("+" + totalAmount + "$");
   }

   private int calculateTotalValue(List<Item> items) {
      int totalValue = 0;
      for (Item item : items) {
         totalValue += item.getValue();
      }
      return totalValue;
   }
}
