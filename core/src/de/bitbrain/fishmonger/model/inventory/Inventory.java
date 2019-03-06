package de.bitbrain.fishmonger.model.inventory;

import de.bitbrain.braingdx.event.GameEventManager;
import de.bitbrain.fishmonger.event.InventoryClearedEvent;
import de.bitbrain.fishmonger.event.ItemAddedToInventoryEvent;
import de.bitbrain.fishmonger.progress.PlayerProgress;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

   private List<Item> items = new ArrayList<Item>();
   private final GameEventManager eventManager;

   public Inventory(GameEventManager eventManager) {
      this.eventManager = eventManager;
   }

   public boolean isFull() {
      return items.size() == PlayerProgress.getInventorySlots();
   }

   public boolean addItem(Item item) {
      if (isFull()) {
         return false;
      }
      items.add(item);
      eventManager.publish(new ItemAddedToInventoryEvent(items.size() - 1, item));
      return true;
   }

   public boolean isEmpty() {
      return items.isEmpty();
   }

   public List<Item> getItems() {
      return items;
   }

   public List<Item> clearInventory() {
      List<Item> result = new ArrayList<Item>(items);
      items.clear();
      eventManager.publish(new InventoryClearedEvent(result));
      return result;
   }
}
