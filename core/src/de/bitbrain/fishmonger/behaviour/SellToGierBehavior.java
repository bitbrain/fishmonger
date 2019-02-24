package de.bitbrain.fishmonger.behaviour;

import de.bitbrain.braingdx.behavior.BehaviorAdapter;
import de.bitbrain.braingdx.world.GameObject;
import de.bitbrain.braingdx.world.GameObjectUtils;
import de.bitbrain.fishmonger.Config;
import de.bitbrain.fishmonger.animation.Animations;
import de.bitbrain.fishmonger.i18n.Messages;
import de.bitbrain.fishmonger.model.inventory.Inventory;
import de.bitbrain.fishmonger.model.inventory.Item;
import de.bitbrain.fishmonger.ui.DialogManager;
import de.bitbrain.fishmonger.ui.dialog.DialogPool;

import java.util.List;

public class SellToGierBehavior extends BehaviorAdapter {

   private final Inventory inventory;
   private final List<Item> delivered;
   private final DialogManager dialogManager;
   private boolean sold;

   public SellToGierBehavior(Inventory inventory, List<Item> delivered, DialogManager dialogManager) {
      this.inventory = inventory;
      this.delivered = delivered;
      this.dialogManager = dialogManager;
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
         if (inventory.isEmpty()) {
            dialogManager.addDialog("Richard Gier", Messages.FISH_DELIVERY_EMPTY, Animations.createGierAvatar(), true);
            dialogManager.nextDialog();
         } else {
            delivered.addAll(inventory.clearInventory());
            dialogManager.addDialog("Richard Gier", DialogPool.getRandomDeliveryMessage(), Animations.createGierAvatar(), true);
            dialogManager.nextDialog();
         }
      }
      if (sold && GameObjectUtils.distanceBetween(source, target) >= Config.SOLD_RANGE) {
         sold = false;
      }
   }
}
