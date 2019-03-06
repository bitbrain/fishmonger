package de.bitbrain.fishmonger.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.bitbrain.braingdx.BrainGdxGame;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tweens.ActorTween;
import de.bitbrain.fishmonger.Colors;
import de.bitbrain.fishmonger.animation.Animations;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.i18n.Messages;
import de.bitbrain.fishmonger.progress.PlayerProgress;
import de.bitbrain.fishmonger.ui.ButtonMenu;
import de.bitbrain.fishmonger.ui.Styles;
import de.bitbrain.fishmonger.ui.Toast;

import static de.bitbrain.fishmonger.i18n.Bundle.get;
import static de.bitbrain.fishmonger.i18n.Messages.PLAYER_BALANCE;

public class LevelSelectionScreen extends AbstractScreen<BrainGdxGame> {

   private ButtonMenu buttonMenu;

   private boolean exiting = false;

   public LevelSelectionScreen(BrainGdxGame game) {
      super(game);
   }

   @Override
   protected void onCreate(GameContext context) {
      setupUI(context);
      setBackgroundColor(Colors.BACKGROUND);
      context.getScreenTransitions().in(0.4f);
   }

   void setupUI(final GameContext context) {
      Table layout = new Table();
      layout.setFillParent(true);

      Label logo = new Label("Fishmonger", Styles.LABEL_LOGO);
      layout.add(logo).padBottom(50f).padTop(20f);
      layout.row();

      Label earnings = new Label(Bundle.get(PLAYER_BALANCE, PlayerProgress.getTotalMoney()), Styles.LABEL_EARNINGS);
      layout.add(earnings).padBottom(50f).padTop(20f);
      layout.row();

      Tween.to(logo, ActorTween.ALPHA, 1f)
            .target(0.7f)
            .repeatYoyo(Tween.INFINITY, 0f)
            .ease(TweenEquations.easeInOutSine)
            .start(context.getTweenManager());

      buttonMenu = new ButtonMenu(context.getTweenManager());
      buttonMenu.add(get(Messages.LEVEL_1_NAME), new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (!exiting) {
            context.getScreenTransitions().out(new IngameScreen(getGame(), Assets.TiledMaps.LEVEL_1), 0.5f);
               exiting = true;
            }
         }
      });
      buttonMenu.add(get(Messages.LEVEL_2_NAME), new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (!exiting) {
            context.getScreenTransitions().out(new IngameScreen(getGame(), Assets.TiledMaps.LEVEL_2), 0.5f);
               exiting = true;
            }
         }
      });
      buttonMenu.add(get(Messages.LEVEL_3_NAME), new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (!exiting) {
            context.getScreenTransitions().out(new IngameScreen(getGame(), Assets.TiledMaps.LEVEL_3), 0.5f);
               exiting = true;
            }
         }
      });
      buttonMenu.add(get(Messages.LEVEL_4_NAME), new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (!exiting) {
               context.getScreenTransitions().out(new IngameScreen(getGame(), Assets.TiledMaps.LEVEL_4), 0.5f);
               exiting = true;
            }
         }
      });

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
            if (!exiting) {
               context.getScreenTransitions().out(new HelpScreen(getGame()), 0.2f);
               exiting = true;
            }
         }
      });
      helpButton.setSize(additionalButtonSize, additionalButtonSize);
      helpButton.setPosition(Gdx.graphics.getWidth() - 32f - additionalButtonSize, 32f);
      context.getStage().addActor(helpButton);

      ImageButton shopButton = new ImageButton(Styles.BUTTON_SHOP);
      shopButton.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (!exiting) {
               context.getScreenTransitions().out(new ShopkeeperScreen(getGame()), 0.2f);
               exiting = true;
            }
         }
      });
      shopButton.setSize(additionalButtonSize, additionalButtonSize);
      shopButton.setPosition(32f, 32f);
      context.getStage().addActor(shopButton);
   }
}
