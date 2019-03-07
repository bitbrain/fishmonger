package de.bitbrain.fishmonger.ui;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import de.bitbrain.braingdx.event.GameEventListener;
import de.bitbrain.fishmonger.event.InventoryClearedEvent;
import de.bitbrain.fishmonger.event.ItemAddedToInventoryEvent;
import de.bitbrain.fishmonger.progress.PlayerProgress;

import java.util.ArrayList;
import java.util.List;

public class InventoryUI extends Table {

   public final GameEventListener<InventoryClearedEvent> inventoryClearedEventListener = new GameEventListener<InventoryClearedEvent>() {

      @Override
      public void onEvent(InventoryClearedEvent event) {
         InventoryUI.this.clear();
         slots.clear();
         init();
      }
   };

   public final GameEventListener<ItemAddedToInventoryEvent> itemAddedToInventoryEventListener = new GameEventListener<ItemAddedToInventoryEvent>() {

      @Override
      public void onEvent(ItemAddedToInventoryEvent event) {
         ImageButton button = slots.get(event.getIndex());
         if (button == null) {
            return;
         }
         button.setStyle(Styles.INVENTORY_ICON_OCCUPIED);
         Sprite sprite = new Sprite(event.getItem().getIcon());
         Image image = new Image(sprite);
         image.setOrigin(Align.center);
         image.setScale(PlayerProgress.getInventorySlots() > 5 ? 6f : 8f);
         button.getImageCell().setActor(image);
      }
   };

   private final List<ImageButton> slots = new ArrayList<ImageButton>();

   public InventoryUI() {
      setFillParent(true);
      init();

   }

   private void init() {
      int size = 96;
      if (PlayerProgress.getInventorySlots() > 5f) {
         size = 72;
      }
      for (int i = 0; i < PlayerProgress.getInventorySlots(); ++i) {
         ImageButton button = new ImageButton(Styles.INVENTORY_ICON);
         Cell<ImageButton> cell = top().add(button).width(size).height(size).padTop(32f);
         if (i > 0f) {
            cell.padLeft(5f);
         }
         slots.add(button);
      }
   }


}
