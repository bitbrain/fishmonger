package de.bitbrain.fishmonger.input.ingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.behavior.movement.Movement;
import de.bitbrain.braingdx.behavior.movement.Orientation;
import de.bitbrain.braingdx.util.Updateable;
import de.bitbrain.fishmonger.catching.FishingRod;
import de.bitbrain.fishmonger.screens.IngameScreen;
import de.bitbrain.fishmonger.screens.LevelSelectionScreen;

public class IngameKeyboardInput extends InputAdapter implements Updateable {

   private final Movement<Orientation> movement;
   private final FishingRod rod;
   private final GameContext context;
   private final IngameScreen ingameScreen;

   public IngameKeyboardInput(
         Movement<Orientation> movement,
         FishingRod rod,
         GameContext context,
         IngameScreen ingameScreen) {
      this.movement = movement;
      this.rod = rod;
      this.context = context;
      this.ingameScreen = ingameScreen;
   }

   @Override
   public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      rod.throwRod();
      return true;
   }

   @Override
   public boolean keyDown(int keycode) {
      if (keycode == Input.Keys.SPACE) {
         rod.throwRod();
         return true;
      }
      if (keycode == Input.Keys.ESCAPE) {
         ingameScreen.setGameOver();
         context.getScreenTransitions().out(new LevelSelectionScreen(ingameScreen.getGame()), 1f);
         return true;
      }
      return false;
   }

   @Override
   public void update(float delta) {
      if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
         movement.move(Orientation.UP);
      } else if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
         movement.move(Orientation.LEFT);
      } else if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
         movement.move(Orientation.DOWN);
      } else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
         movement.move(Orientation.RIGHT);
      }
   }
}
