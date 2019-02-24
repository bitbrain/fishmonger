package de.bitbrain.fishmonger.assets;

public interface Assets {

   interface Textures {
      String PLAYER = "textures/player.png";
      String AVATARS = "textures/avatars.png";
      String FISH = "textures/fish.png";
      String PANEL = "textures/panel.9.png";

      String HOOK = "textures/hook.png";

      String PIRANHA = "textures/piranha.png";
      String MACKEREL =  "textures/mackerel.png";
   }

   interface Sounds {

   }

   interface Musics {
      String OVERWORLD = "music/overworld.ogg";
   }

   interface Particles {

   }

   interface Fonts {
      String PIXELMIX = "fonts/pixelmix.ttf";
   }

   interface TiledMaps {
      String LEVEL_1 = "maps/level1.tmx";

      String GAME_OVER = "maps/gameover.tmx";
   }
}
