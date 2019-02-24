package de.bitbrain.fishmonger.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.bitbrain.braingdx.BrainGdxGame;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tweens.GameCameraTween;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.i18n.Messages;
import de.bitbrain.fishmonger.ui.ButtonMenu;
import de.bitbrain.fishmonger.ui.Toast;

import static de.bitbrain.fishmonger.i18n.Bundle.get;

public class LevelSelectionScreen extends AbstractScreen<BrainGdxGame> {

   private ButtonMenu buttonMenu;

   public LevelSelectionScreen(BrainGdxGame game) {
      super(game);
   }

   @Override
   protected void onCreate(GameContext context) {
      setupUI(context);
   }

   void setupUI(final GameContext context) {
      Table layout = new Table();
      layout.setFillParent(true);
      buttonMenu = new ButtonMenu(context.getTweenManager());
      buttonMenu.add(get(Messages.LEVEL_1_NAME), new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            context.getScreenTransitions().out(new IngameScreen(getGame(), Assets.TiledMaps.LEVEL_1), 0.5f);
         }
      });
      buttonMenu.add(get(Messages.LEVEL_2_NAME), new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            context.getScreenTransitions().out(new IngameScreen(getGame(), Assets.TiledMaps.LEVEL_2), 0.5f);
         }
      });
      buttonMenu.add(get(Messages.LEVEL_3_NAME), new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            context.getScreenTransitions().out(new IngameScreen(getGame(), Assets.TiledMaps.LEVEL_3), 0.5f);
         }
      });
      buttonMenu.add(get(Messages.LEVEL_4_NAME), new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            context.getScreenTransitions().out(new IngameScreen(getGame(), Assets.TiledMaps.LEVEL_4), 0.5f);
         }
      });

      buttonMenu.checkNext();

      layout.center().add(buttonMenu);

      context.getStage().addActor(layout);

      Toast.getInstance().init(context.getStage());
   }
}
