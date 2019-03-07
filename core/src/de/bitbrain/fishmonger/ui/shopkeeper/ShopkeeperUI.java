package de.bitbrain.fishmonger.ui.shopkeeper;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import de.bitbrain.braingdx.tweens.ActorTween;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import de.bitbrain.fishmonger.Colors;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;
import de.bitbrain.fishmonger.progress.PlayerProgress;
import de.bitbrain.fishmonger.shop.Rarity;
import de.bitbrain.fishmonger.shop.ShopItem;
import de.bitbrain.fishmonger.ui.ButtonMenu;
import de.bitbrain.fishmonger.ui.Styles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopkeeperUI extends ButtonMenu {

   private static final float PADDING = 30f;
   private final Label credits;

   private Map<ShopItem, TextButton> buttons = new HashMap<ShopItem, TextButton>();

   public ShopkeeperUI(List<ShopItem> items, ButtonMenuStyle style) {
      super(SharedTweenManager.getInstance(), style);
      setFillParent(true);
      Color bgColor = Colors.BACKGROUND.cpy();
      bgColor.a = 0.45f;
      Drawable backgroundImage = new SpriteDrawable(new Sprite(GraphicsFactory.createTexture(2, 2, bgColor)));
      setBackground(backgroundImage);
      add();
      Label logo = new Label("Shop", Styles.LABEL_LOGO);
      add(logo).left().padBottom(PADDING);
      row();

      for (final ShopItem item : items) {
         ImageButton icon = new ImageButton(Styles.INVENTORY_ICON);
         Image image = new Image(new SpriteDrawable(new Sprite(item.getIcon())));
         image.setScale(5f);
         image.setRotation(90f);
         icon.getImageCell().padRight(-46f).padBottom(-33).setActor(image);
         add(icon).padTop(PADDING).padRight(PADDING);


         add(new Label(item.getName(), Styles.LABEL_SHOPPINGLIST)).width(600f).padTop(PADDING);

         add(createRarityActor(item.getRarity())).width(200).padTop(PADDING);

         Label cost = new Label(item.getPrice() + "$", Styles.LABEL_SHOPPINGLIST);
         cost.setAlignment(Align.right);
         add(cost).width(250f).padTop(PADDING);
         Cell<TextButton> cell = add("", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               if (!item.isObtained() && PlayerProgress.getTotalMoney() >= item.getPrice()) {
                  item.getBuyRunnable().run();
                  PlayerProgress.removeMoney(item.getPrice());
                  ShopkeeperUI.this.refresh();
               }
            }
         }).padTop(PADDING).padLeft(PADDING);
         buttons.put(item, cell.getActor());
      }
      credits = new Label( PlayerProgress.getTotalMoney() + "$", Styles.LABEL_LOGO);
      credits.setAlignment(Align.left);
      add();
      add(credits).left().padTop(PADDING * 2);
      refresh();
   }

   public void refresh() {
      for (Map.Entry<ShopItem, TextButton> entry : buttons.entrySet()) {
         boolean hasMoney = PlayerProgress.getTotalMoney() >= entry.getKey().getPrice();
         if (hasMoney || entry.getKey().isObtained()) {
            String caption = entry.getKey().isObtained() ? Bundle.get(Messages.SHOP_BOUGHT) : Bundle.get(Messages.SHOP_BUY);
            entry.getValue().setText(caption);
         } else {
            entry.getValue().setText(Bundle.get(Messages.SHOP_NO_MONEY));
         }
         entry.getValue().setDisabled(entry.getKey().isObtained() || !hasMoney);
      }
      credits.setText(PlayerProgress.getTotalMoney() + "$");
   }

   private Actor createRarityActor(Rarity rarity) {
      if (rarity != Rarity.LEGENDARY) {
         return new Label(rarity.getName(), Styles.LABEL_SHOPPINGLIST);
      }
      HorizontalGroup group = new HorizontalGroup();
      new Label(rarity.getName(), Styles.LABEL_SHOPPINGLIST);
      for (int i = 0; i < rarity.getName().length(); ++i) {
         Label character = new Label(rarity.getName().charAt(i) + "", Styles.LABEL_SHOPPINGLIST_RARITY);

         Tween.to(character, ActorTween.ALPHA, 0.5f)
               .delay(0.3f * i)
               .target(0.9f)
               .repeatYoyo(Tween.INFINITY, 0f)
               .ease(TweenEquations.easeInOutSine)
               .start(SharedTweenManager.getInstance());

         Tween.to(character, ActorTween.SCALE, 0.5f)
               .delay(0.3f * i)
               .target(0.9f)
               .repeatYoyo(Tween.INFINITY, 0f)
               .ease(TweenEquations.easeInOutSine)
               .start(SharedTweenManager.getInstance());

         group.addActor(character);
      }
      return group;
   }
}
