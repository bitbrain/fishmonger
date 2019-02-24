package de.bitbrain.fishmonger.ui;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import de.bitbrain.braingdx.tweens.ActorTween;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import de.bitbrain.braingdx.tweens.ValueTween;
import de.bitbrain.braingdx.ui.AnimationDrawable;
import de.bitbrain.braingdx.util.DeltaTimer;
import de.bitbrain.braingdx.util.ValueProvider;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.ui.dialog.Dialog;

public class DialogUI extends Actor {

   private static final float INNER_PADDING_Y = 30f;
   private static final float MARGIN = 10f;
   private static final float AVATAR_SCALE = 2f;
   private static final float TITLE_PADDING = 40f;

   private static final float AUTO_CLOSE_DURATION = 4f;

   private Dialog dialog;

   private final DialogManager dialogManager;
   private final TweenManager tweenManager = SharedTweenManager.getInstance();

   private Label text;
   private Label title;
   private ValueProvider offsetProvider = new ValueProvider();
   private boolean currentlyClosing;
   private final NinePatch dialogBackground;
   private final NinePatch titleBackground;

   private final DeltaTimer autoCloseTimer = new DeltaTimer();

   static {
      Tween.registerAccessor(ValueProvider.class, new ValueTween());
   }

   public DialogUI(DialogManager dialogManager) {
      this.dialogManager = dialogManager;
      // Create a nice background so font is readable
      Texture buttonNinePatchTexture = SharedAssetManager.getInstance().get(Assets.Textures.PANEL, Texture.class);
      Texture labelNinePatchTexture = SharedAssetManager.getInstance().get(Assets.Textures.PANEL_DARK, Texture.class);
      dialogBackground = GraphicsFactory.createNinePatch(buttonNinePatchTexture, 20);
      titleBackground =  GraphicsFactory.createNinePatch(labelNinePatchTexture, 15);
   }

   @Override
   public void act(float delta) {
      if (!currentlyClosing && dialog != null && dialog.isAutoClose()) {
         autoCloseTimer.update(delta);
      }
      if (autoCloseTimer.reached(AUTO_CLOSE_DURATION) && dialog != null && dialog.isAutoClose()) {
         autoCloseTimer.reset();
         dialogManager.nextDialog();
         unsetDialog(dialog, new TweenCallback() {
            @Override
            public void onEvent(int arg0, BaseTween<?> arg1) {
               setDialog(dialogManager.getCurrentDialog());
            }
         });
      }
      if (!currentlyClosing && (dialog == null || dialog != dialogManager.getCurrentDialog())) {
         unsetDialog(dialog, new TweenCallback() {
            @Override
            public void onEvent(int arg0, BaseTween<?> arg1) {
               setDialog(dialogManager.getCurrentDialog());
            }
         });
      }
   }

   @Override
   public float getX() {
      return super.getX();
   }

   @Override
   public float getY() {
      return MARGIN + offsetProvider.getValue();
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      parentAlpha *= getColor().a;
      if (dialog != null) {
         dialogBackground.draw(batch, getX(), getY(), getWidth() - MARGIN * 2f, getHeight());
         AnimationDrawable avatar = dialog.getAvatar();
         avatar.setAlpha(getColor().a);
         avatar.draw(batch,
               getX() - getHeight() * AVATAR_SCALE,
               0f,
               getHeight() * AVATAR_SCALE,
               getHeight() * AVATAR_SCALE
         );
      }
      if (text != null) {
         text.setPosition(getX() + 30f, getY() + getHeight() - text.getHeight() - INNER_PADDING_Y);
         text.draw(batch, parentAlpha);
      }
      if (title != null) {
         title.setX(getTitleX());
         title.setY(getTitleY());
         titleBackground.getColor().a = title.getColor().a;
         titleBackground.draw(batch, getTitleBackgroundX(), getTitleBackgroundY(), getTitleBackgroundWidth(), getTitleBackgroundHeight());
         title.draw(batch, 1f);
      }
   }

   private void unsetDialog(Dialog dialog, TweenCallback finishCallback) {
      if (dialog != null) {
         currentlyClosing = true;
         Tween.to(text, ActorTween.ALPHA, 0.5f)
               .target(0f)
               .ease(TweenEquations.easeInCubic)
               .start(tweenManager);
         Tween.to(title, ActorTween.ALPHA, 0.5f)
               .target(0f)
               .ease(TweenEquations.easeInCubic)
               .start(tweenManager);
         Tween.to(this, ActorTween.ALPHA, 0.5f)
               .delay(0.3f)
               .target(0f)
               .ease(TweenEquations.easeInCubic)
               .start(tweenManager);
         Tween.to(offsetProvider, ValueTween.VALUE, 0.5f)
               .target(getFadeOutYPosition())
               .ease(TweenEquations.easeInCubic)
               .setCallbackTriggers(TweenCallback.COMPLETE)
               .setCallback(finishCallback)
               .start(tweenManager);
      } else {
         finishCallback.onEvent(0, null);
      }
   }

   private void setDialog(Dialog dialog) {
      currentlyClosing = false;
      if (dialog != null) {

         this.dialog = dialog;
         this.text = new Label(dialog.getText(), Styles.LABEL_DIALOG);
         this.title = new Label(dialog.getTitle(), Styles.LABEL_DIALOG_TITLE);
         text.setColor(dialog.getColor());
         text.setWrap(true);
         text.setWidth(getWidth() - getHeight() -  MARGIN * 2f - 50f);
         text.setAlignment(Align.top | Align.left);
         text.setHeight(getHeight() -  MARGIN);
         getColor().a = 0f;
         Tween.to(this, ActorTween.ALPHA, 0.8f)
               .delay(0.3f)
               .target(1f)
               .ease(TweenEquations.easeInCubic)
               .start(tweenManager);
         text.getColor().a = 0f;
         Tween.to(text, ActorTween.ALPHA, 0.4f)
               .delay(0.6f)
               .target(1f)
               .ease(TweenEquations.easeInCubic)
               .start(tweenManager);
         Tween.to(title, ActorTween.ALPHA, 0.6f)
               .target(1f)
               .ease(TweenEquations.easeInCubic)
               .start(tweenManager);
         offsetProvider.setValue(getFadeOutYPosition());
         Tween.to(offsetProvider, ValueTween.VALUE, 0.5f)
               .target(0f)
               .ease(TweenEquations.easeInCubic)
               .start(tweenManager);
      }
   }

   private float getTitleBackgroundX() {
      return title.getX() - TITLE_PADDING;
   }

   private float getTitleBackgroundY() {
      return title.getY() - (TITLE_PADDING / 2f);
   }

   private float getTitleBackgroundWidth() {
      return title.getPrefWidth() + TITLE_PADDING * 2f;
   }

   private float getTitleBackgroundHeight() {
      return title.getPrefHeight() + (TITLE_PADDING / 2f) * 2f;
   }

   private float getTitleY() {
      return  getY() + getHeight() + (TITLE_PADDING / 2f) - 5f;
   }

   private float getTitleX() {
      return getX() + TITLE_PADDING;
   }

   private float getFadeOutYPosition() {
      return -getHeight() - MARGIN - TITLE_PADDING * 4f;
   }

}