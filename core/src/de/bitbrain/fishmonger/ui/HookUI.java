package de.bitbrain.fishmonger.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import de.bitbrain.fishmonger.catching.FishingRod;
import de.bitbrain.fishmonger.catching.HookType;
import de.bitbrain.fishmonger.progress.PlayerProgress;

public class HookUI extends Actor {

   private static final float SIZE = 128f;

   private final FishingRod rod;
   private final Label name;
   private final ImageButton icon;

   public HookUI(FishingRod rod) {
      this.rod = rod;
      HookType type = PlayerProgress.getHookType();
      name = new Label(type.getName(), Styles.LABEL_ROD);
      icon = new ImageButton(Styles.INVENTORY_ICON);
      Image image = new Image(new SpriteDrawable(new Sprite(type.getTexture())));
      image.setScale(10f);
      image.setRotation(90f);
      icon.getImageCell().padRight(-86f).padBottom(-73).setActor(image);
   }

   @Override
   public float getWidth() {
      return SIZE;
   }

   @Override
   public float getHeight() {
      return SIZE;
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      icon.setStyle(rod.isUsable() ? Styles.INVENTORY_ICON : Styles.INVENTORY_ICON_OCCUPIED);
      icon.setPosition(getX(), getY() + name.getPrefHeight() + 16f);
      icon.setSize(SIZE, SIZE);
      name.setPosition(getX() - (name.getPrefWidth() - SIZE), getY());
      icon.draw(batch, parentAlpha);
      name.draw(batch, parentAlpha);
   }
}
