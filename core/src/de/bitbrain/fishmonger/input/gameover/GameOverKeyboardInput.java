package de.bitbrain.fishmonger.input.gameover;

import com.badlogic.gdx.InputAdapter;
import de.bitbrain.fishmonger.screens.GameOverScreen;

public class GameOverKeyboardInput extends InputAdapter {

   private final GameOverScreen screen;

   public GameOverKeyboardInput(GameOverScreen screen) {
      this.screen = screen;
   }

   @Override
   public boolean keyDown(int keycode) {
      screen.exit();
      return true;
   }
}
