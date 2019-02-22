package de.bitbrain.fishmonger.screens;

import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.fishmonger.Colors;
import de.bitbrain.fishmonger.FishMongerGame;

public class IngameScreen extends AbstractScreen<FishMongerGame> {

   public IngameScreen(FishMongerGame game) {
      super(game);
   }

   @Override
   protected void onCreate(GameContext context) {
      setBackgroundColor(Colors.BACKGROUND);
   }
}
