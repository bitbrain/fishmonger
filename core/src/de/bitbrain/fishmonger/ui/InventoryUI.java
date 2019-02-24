package de.bitbrain.fishmonger.ui;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import de.bitbrain.braingdx.event.GameEventListener;
import de.bitbrain.fishmonger.Config;
import de.bitbrain.fishmonger.event.InventoryClearedEvent;
import de.bitbrain.fishmonger.event.ItemAddedToInventoryEvent;

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
         image.setScale(8f);
         button.getImageCell().setActor(image);
      }
   };

   private final List<ImageButton> slots = new ArrayList<ImageButton>();

   public InventoryUI() {
      setFillParent(true);
      init();

   }

   private void init() {
      for (int i = 0; i < Config.NUMBER_OF_INVENTORY_SLOTS; ++i) {
         ImageButton button = new ImageButton(Styles.INVENTORY_ICON);
         Cell<ImageButton> cell = top().add(button).width(96).height(96).padTop(32f);
         if (i > 0f) {
            cell.padLeft(15f);
         }
         slots.add(button);
      }
   }


}
