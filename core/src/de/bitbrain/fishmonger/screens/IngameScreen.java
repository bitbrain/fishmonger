package de.bitbrain.fishmonger.screens;

import com.badlogic.gdx.controllers.Controllers;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.fishmonger.Colors;
import de.bitbrain.fishmonger.FishMongerGame;
import de.bitbrain.fishmonger.input.ingame.IngameControllerInput;
import de.bitbrain.fishmonger.input.ingame.IngameKeyboardInput;

public class IngameScreen extends AbstractScreen<FishMongerGame> {

   public IngameScreen(FishMongerGame game) {
      super(game);
   }

   @Override
   public void dispose() {
      super.dispose();
      Controllers.clearListeners();
   }

   @Override
   protected void onCreate(GameContext context) {
      setBackgroundColor(Colors.BACKGROUND);
      setupWorld(context);
      setupInput(context);
      setupShaders(context);
   }

   private void setupInput(GameContext context) {
      context.getInput().addProcessor(new IngameKeyboardInput());
      Controllers.addListener(new IngameControllerInput());
   }

   private void setupWorld(GameContext context) {
      // TODO
   }

   private void setupShaders(GameContext context) {
      // TODO
   }
}
