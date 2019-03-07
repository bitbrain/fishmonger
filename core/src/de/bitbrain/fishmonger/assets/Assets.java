package de.bitbrain.fishmonger.assets;

public interface Assets {

   interface Textures {
      String MINIBEANSJAM_LOGO = "textures/minibeansjam.png";

      String PLAYER = "textures/player.png";
      String AVATARS = "textures/avatars.png";
      String FISH = "textures/fish.png";
      String PANEL = "textures/panel.9.png";
      String PANEL_MEDIUM = "textures/panel-medium.9.png";
      String PANEL_DARK = "textures/panel-dark.9.png";

      String HOOK = "textures/hook.png";
      String HOOK_RARE = "textures/hook-rare.png";
      String HOOK_EPIC = "textures/hook-epic.png";
      String BAG_SMALL = "textures/bag-small.png";
      String BAG_MEDIUM = "textures/bag-medium.png";
      String BAG_LARGE = "textures/bag.png";
      String BAG_LEGENDARY = "textures/bag-legendary.png";

      String PIRANHA = "textures/piranha.png";
      String MACKEREL =  "textures/mackerel.png";
      String GIANT_PIRANHA =  "textures/giant-piranha.png";
      String EEL = "textures/eel.png";
      String STRIDER = "textures/strider.png";

      String HELP = "textures/help.png";
      String HELP_XBOX = "textures/help-xbox.png";
      String MONEY = "textures/money.png";
      String QUESTION = "textures/question.png";
   }

   interface Sounds {
      String COIN = "sfx/coin.ogg";
      String BITE = "sfx/bite.ogg";
      String THROW = "sfx/throw.ogg";
      String THROW_FAIL = "sfx/throw_fail.ogg";
      String GAME_OVER = "sfx/gameover.ogg";
   }

   interface Musics {
      String OVERWORLD = "music/overworld.ogg";
      String OVERWORLD2 = "music/overworld2.ogg";
      String MAIN_MENU = "music/mainmenu.ogg";
      String RICHARD_GIER = "music/richardgier.ogg";
   }

   interface Particles {

   }

   interface Fonts {
      String PIXELMIX = "fonts/pixelmix.ttf";
   }

   interface TiledMaps {
      String LEVEL_1 = "maps/level1.tmx";
      String LEVEL_2 = "maps/level2.tmx";
      String LEVEL_3 = "maps/level3.tmx";
      String LEVEL_4 = "maps/level4.tmx";

      String GAME_OVER = "maps/gameover.tmx";
      String SHOP = "maps/shop.tmx";
   }
}
