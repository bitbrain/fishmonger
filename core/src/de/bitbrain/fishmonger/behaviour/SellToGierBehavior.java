package de.bitbrain.fishmonger.behaviour;

import de.bitbrain.braingdx.behavior.BehaviorAdapter;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.braingdx.world.GameObjectUtils;
import de.bitbrain.fishmonger.Config;
import de.bitbrain.fishmonger.model.inventory.Inventory;
import de.bitbrain.fishmonger.model.inventory.Item;

import java.util.List;

public class SellToGierBehavior extends BehaviorAdapter {

   private final Inventory inventory;
   private final List<Item> delivered;
   private boolean sold;

   public SellToGierBehavior(Inventory inventory, List<Item> delivered) {
      this.inventory = inventory;
      this.delivered = delivered;
   }

   @Override
   public void update(GameObject source, GameObject target, float delta) {
      if (!source.getType().equals("PLAYER")) {
         return;
      }
      if (!target.getType().equals("GIER")) {
         return;
      }
      if (!sold && GameObjectUtils.distanceBetween(source, target) < Config.MINIMUM_SELL_RANGE) {
         sold = true;
         delivered.addAll(inventory.clearInventory());
      }
      if (sold && GameObjectUtils.distanceBetween(source, target) >= Config.SOLD_RANGE) {
         sold = false;
      }
   }
}
