package de.bitbrain.fishmonger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class HelpScreen extends AbstractScreen<BrainGdxGame> {

   private boolean exiting = false;
   private GameContext context;

   public HelpScreen(BrainGdxGame game) {
      super(game);
   }

   @Override
   protected void onCreate(GameContext context) {
      setBackgroundColor(Colors.BORDER);
      context.getScreenTransitions().in(0.2f);
      this.context = context;
      setupUI(context);
   }

   @Override
   protected void onUpdate(float delta) {
      if (exiting) {
         return;
      }
      if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY) || Gdx.input.isTouched()) {
         context.getScreenTransitions().out(new LevelSelectionScreen(getGame()), 0.3f);
         exiting = true;
      }
   }

   private void setupUI(GameContext context) {
      Table layout = new Table();
      layout.setFillParent(true);

      Image image = new Image(new SpriteDrawable(new Sprite(SharedAssetManager.getInstance().get(Assets.Textures.HELP, Texture.class))));


      layout.center().add(image).width(512 * 3f).height(256 * 3f);

      context.getStage().addActor(layout);
   }
}
