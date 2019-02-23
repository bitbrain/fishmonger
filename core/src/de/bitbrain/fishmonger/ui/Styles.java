package de.bitbrain.fishmonger.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import de.bitbrain.fishmonger.assets.Assets;

public class Styles {

   public static final ImageButton.ImageButtonStyle INVENTORY_ICON = new ImageButton.ImageButtonStyle();

   public static void load() {
      INVENTORY_ICON.up = new NinePatchDrawable(
            GraphicsFactory.createNinePatch(SharedAssetManager.getInstance().get(Assets.Textures.PANEL, Texture.class), 8)
      );
   }
}
