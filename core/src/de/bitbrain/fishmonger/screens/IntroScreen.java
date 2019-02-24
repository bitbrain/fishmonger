package de.bitbrain.fishmonger.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import de.bitbrain.braingdx.BrainGdxGame;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.fishmonger.Colors;
import de.bitbrain.fishmonger.assets.Assets;

public class IntroScreen extends AbstractScreen<BrainGdxGame> {


   public IntroScreen(BrainGdxGame game) {
      super(game);
   }

   @Override
   protected void onCreate(final GameContext context) {
      setBackgroundColor(Colors.BORDER);
      context.getScreenTransitions().in(0.5f);
      setupUI(context);

      Tween.call(new TweenCallback() {
         @Override
         public void onEvent(int type, BaseTween<?> source) {
            context.getScreenTransitions().out(new LevelSelectionScreen(getGame()), 0.5f);
         }
      }).delay(2f).start(context.getTweenManager());
   }

   void setupUI(GameContext context) {
      Table layout = new Table();
      layout.setFillParent(true);

      Image logo = new Image(new SpriteDrawable(new Sprite(SharedAssetManager.getInstance().get(Assets.Textures.MINIBEANSJAM_LOGO, Texture.class))));

      layout.center().add(logo);

      context.getStage().addActor(layout);
   }
}
