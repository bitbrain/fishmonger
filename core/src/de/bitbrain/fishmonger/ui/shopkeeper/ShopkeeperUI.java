package de.bitbrain.fishmonger.ui.shopkeeper;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import de.bitbrain.fishmonger.shop.ShopItem;
import de.bitbrain.fishmonger.ui.Styles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopkeeperUI extends Table {

   private static final float PADDING = 30f;

   private Map<ShopItem, TextButton> buttons = new HashMap<ShopItem, TextButton>();

   public ShopkeeperUI(List<ShopItem> items) {
      setFillParent(true);
      for (final ShopItem item : items) {
         ImageButton icon = new ImageButton(Styles.INVENTORY_ICON);
         Image image = new Image(new SpriteDrawable(new Sprite(item.getIcon())));
         image.setScale(5f);
         image.setRotation(90f);
         icon.getImageCell().padRight(-46f).padBottom(-33).setActor(image);
         add(icon).padTop(PADDING).padRight(PADDING);


         add(new Label(item.getName() + " (" + item.getRarity().getName() + ")", Styles.LABEL_SHOPPINGLIST)).width(500f).padTop(PADDING);

         Label cost = new Label(item.getPrice() + "$", Styles.LABEL_SHOPPINGLIST);
         cost.setAlignment(Align.right);
         add(cost).width(250f).padTop(PADDING);

         String caption = item.isObtained() ? "Bought" : "Buy";
         TextButton button = new TextButton(caption, Styles.BUTTON_MENU);
         buttons.put(item, button);
         button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               item.getBuyRunnable().run();
               ShopkeeperUI.this.refresh();
            }
         });
         button.setDisabled(item.isObtained());
         add(button).padTop(PADDING).padLeft(PADDING);
         row();
      }
   }

   private void refresh() {
      for (Map.Entry<ShopItem, TextButton> entry : buttons.entrySet()) {
         String caption = entry.getKey().isObtained() ? "Bought" : "Buy";
         entry.getValue().setText(caption);
         entry.getValue().setDisabled(entry.getKey().isObtained());
      }
   }
}
