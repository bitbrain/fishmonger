package de.bitbrain.fishmonger.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import de.bitbrain.braingdx.BrainGdxGame;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tweens.ActorTween;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import de.bitbrain.fishmonger.Colors;
import de.bitbrain.fishmonger.animation.Animations;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;
import de.bitbrain.fishmonger.input.ingame.IngameControllerInput;
import de.bitbrain.fishmonger.input.ingame.IngameKeyboardInput;
import de.bitbrain.fishmonger.input.levelselection.LevelSelectionControllerInput;
import de.bitbrain.fishmonger.input.levelselection.LevelSelectionKeyboardInput;
import de.bitbrain.fishmonger.progress.PlayerProgress;
import de.bitbrain.fishmonger.ui.ButtonMenu;
import de.bitbrain.fishmonger.ui.Styles;
import de.bitbrain.fishmonger.ui.Toast;

import static de.bitbrain.fishmonger.i18n.Bundle.get;
import static de.bitbrain.fishmonger.i18n.Messages.PLAYER_BALANCE;

public class LevelSelectionScreen extends AbstractScreen<BrainGdxGame> {

   private ButtonMenu buttonMenu;

   private boolean exiting = false;
   private GameContext context;

   public LevelSelectionScreen(BrainGdxGame game) {
      super(game);
   }

   @Override
   protected void onCreate(GameContext context) {
      this.context = context;
      setupUI(context);
      setupInput(context);
      setBackgroundColor(Colors.BACKGROUND);
      context.getScreenTransitions().in(0.4f);
   }

   @Override
   public void dispose() {
      super.dispose();
      Controllers.clearListeners();
   }

   public void helpScreen() {
      if (!exiting) {
         context.getScreenTransitions().out(new HelpScreen(getGame()), 0.2f);
         exiting = true;
      }
   }

   public void shopScreen() {
      if (!exiting) {
         context.getScreenTransitions().out(new ShopkeeperScreen(getGame()), 0.2f);
         exiting = true;
      }
   }

   private void setupInput(GameContext context) {
      context.getInput().addProcessor(new LevelSelectionKeyboardInput(buttonMenu, this));
      Controllers.addListener(new LevelSelectionControllerInput(buttonMenu, this));
   }

   void setupUI(final GameContext context) {
      Table layout = new Table();
      layout.setFillParent(true);


      layout.add(createAnimatedLogo("fishmonger")).padBottom(90f).padTop(150f);
      layout.row();

      Label earnings = new Label(Bundle.get(PLAYER_BALANCE, PlayerProgress.getTotalMoney()), Styles.LABEL_EARNINGS);
      earnings.setPosition(Gdx.graphics.getWidth() - 35f - earnings.getPrefWidth(), Gdx.graphics.getHeight() - 45f - earnings.getPrefHeight());
      context.getStage().addActor(earnings);

      buttonMenu = new ButtonMenu(context.getTweenManager());
      buttonMenu.add(get(Messages.LEVEL_1_NAME), new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (!exiting) {
            context.getScreenTransitions().out(new IngameScreen(getGame(), Assets.TiledMaps.LEVEL_1), 0.5f);
               exiting = true;
            }
         }
      }).left();
      buttonMenu.add(get(Messages.LEVEL_2_NAME), new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (!exiting) {
            context.getScreenTransitions().out(new IngameScreen(getGame(), Assets.TiledMaps.LEVEL_2), 0.5f);
               exiting = true;
            }
         }
      }).left();
      buttonMenu.add(get(Messages.LEVEL_3_NAME), new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (!exiting) {
            context.getScreenTransitions().out(new IngameScreen(getGame(), Assets.TiledMaps.LEVEL_3), 0.5f);
               exiting = true;
            }
         }
      }).left();
      buttonMenu.add(get(Messages.LEVEL_4_NAME), new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (!exiting) {
               context.getScreenTransitions().out(new IngameScreen(getGame(), Assets.TiledMaps.LEVEL_4), 0.5f);
               exiting = true;
            }
         }
      }).left();

      buttonMenu.checkNext();

      layout.center().add(buttonMenu);

      context.getStage().addActor(layout);

      Label credits = new Label("a game by k0stnix and bitbrain", Styles.LABEL_CREDITS);
      layout.row();
      layout.add(credits).padTop(70f);

      Toast.getInstance().init(context.getStage());

      Image imageA = new Image(Animations.gierAnimationDrawable());
      imageA.setSize(300f, 300f);
      imageA.setPosition(Gdx.graphics.getWidth() / 2f - 150f - Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 2f - 150);
      context.getStage().addActor(imageA);
      Image imageB = new Image(Animations.gierAnimationDrawable());
      imageB.setSize(300f, 300f);
      imageB.setPosition(Gdx.graphics.getWidth() / 2f - 150f + Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 2f - 150);
      context.getStage().addActor(imageB);

      float additionalButtonSize = 98f;

      ImageButton helpButton = new ImageButton(Styles.BUTTON_HELP);
      helpButton.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            helpScreen();
         }
      });
      helpButton.setSize(additionalButtonSize, additionalButtonSize);
      helpButton.setPosition(Gdx.graphics.getWidth() - 64f - additionalButtonSize, 32f);
      context.getStage().addActor(helpButton);

      decorateWithArrowTooltip(getHelpText(), helpButton, context);

      ImageButton shopButton = new ImageButton(Styles.BUTTON_SHOP);
      shopButton.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            shopScreen();
         }
      });
      shopButton.setSize(additionalButtonSize, additionalButtonSize);
      shopButton.setPosition(64f, 32f);
      context.getStage().addActor(shopButton);

      decorateWithArrowTooltip(getShopText(), shopButton, context);
   }

   private String getHelpText() {
      String description = Bundle.get(Messages.HELP);
      String key = "E";
      for (Controller controller : Controllers.getControllers()) {
         if (Xbox.isXboxController(controller)) {
            key = "START";
            break;
         }
      }
      return description + " (" + key + ")";
   }

   private String getShopText() {
      String description = Bundle.get(Messages.SHOP);
      String key = "Q";
      for (Controller controller : Controllers.getControllers()) {
         if (Xbox.isXboxController(controller)) {
            key = "BACK";
            break;
         }
      }
      return description + " (" + key + ")";
   }

   private void decorateWithArrowTooltip(String text, Actor target, GameContext context) {
      decorateWithArrowTooltip(new Label(text, Styles.LABEL_CREDITS), target, context);
   }

   private void decorateWithArrowTooltip(Actor descriptor, Actor target, GameContext context) {
      VerticalGroup tooltip = new VerticalGroup();

      tooltip.addActor(descriptor);

      Image arrow = new Image(new SpriteDrawable(new Sprite(SharedAssetManager.getInstance().get(Assets.Textures.ARROW, Texture.class))));
      arrow.setSize(32f, 32f);

      tooltip.addActor(arrow);

      float initialOffsetX = target.getX() + target.getWidth() / 2f - tooltip.getWidth() / 2f;
      float initialOffsetY = target.getY() + target.getHeight() + tooltip.getHeight() + 25f + 32f;
      tooltip.setPosition(initialOffsetX, initialOffsetY);

      Tween.to(tooltip, ActorTween.POPUP, 1f)
            .target(initialOffsetY + 20)
            .ease(TweenEquations.easeInBounce)
            .repeatYoyo(Tween.INFINITY, 0f)
            .start(SharedTweenManager.getInstance());

      context.getStage().addActor(tooltip);
   }

   private Actor createAnimatedLogo(String text) {
      HorizontalGroup logoGroup = new HorizontalGroup();
      for (int i = 0; i < text.length(); ++i) {
         Label character = new Label(text.charAt(i) + "", Styles.LABEL_LOGO);

         Tween.to(character, ActorTween.ALPHA, 0.5f)
               .delay(0.3f * i)
               .target(0.9f)
               .repeatYoyo(Tween.INFINITY, 0f)
               .ease(TweenEquations.easeInOutSine)
               .start(context.getTweenManager());

         Tween.to(character, ActorTween.SCALE, 0.5f)
               .delay(0.3f * i)
               .target(0.8f)
               .repeatYoyo(Tween.INFINITY, 0f)
               .ease(TweenEquations.easeInOutSine)
               .start(context.getTweenManager());
         logoGroup.addActor(character);
      }

      return logoGroup;
   }
}
