package de.bitbrain.fishmonger.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import de.bitbrain.fishmonger.Colors;
import de.bitbrain.fishmonger.assets.Assets;

public class Styles {

   public static final ImageButton.ImageButtonStyle INVENTORY_ICON = new ImageButton.ImageButtonStyle();
   public static final Label.LabelStyle INGAME_CASH = new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_TOAST = new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_CREDITS =  new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_LOGO =  new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_DIALOG =  new Label.LabelStyle();
   public static final Label.LabelStyle LABEL_DIALOG_TITLE = new Label.LabelStyle();
   public static final TextButton.TextButtonStyle BUTTON_MENU = new TextButton.TextButtonStyle();

   public static void load() {
      INVENTORY_ICON.up = new NinePatchDrawable(
            GraphicsFactory.createNinePatch(SharedAssetManager.getInstance().get(Assets.Textures.PANEL, Texture.class), 8)
      );
      INGAME_CASH.font = bake(Assets.Fonts.PIXELMIX, 60, true);
      INGAME_CASH.fontColor = Colors.FOREGROUND;

      LABEL_TOAST.font = bake(Assets.Fonts.PIXELMIX, 40, true);
      LABEL_TOAST.fontColor = Colors.FOREGROUND;

      LABEL_CREDITS.font = bake(Assets.Fonts.PIXELMIX, 14, true);
      LABEL_CREDITS.fontColor = Colors.FOREGROUND;

      LABEL_LOGO.font = bake(Assets.Fonts.PIXELMIX, 50, true);
      LABEL_LOGO.fontColor = Colors.FOREGROUND;

      LABEL_DIALOG.font = bake(Assets.Fonts.PIXELMIX, 26);
      LABEL_DIALOG.fontColor = Colors.BORDER;

      LABEL_DIALOG_TITLE.font = bake(Assets.Fonts.PIXELMIX, 30);
      LABEL_DIALOG_TITLE.fontColor = Colors.FOREGROUND;

      BUTTON_MENU.font = bake(Assets.Fonts.PIXELMIX, 36);
      BUTTON_MENU.fontColor = Colors.BORDER;
      BUTTON_MENU.overFontColor = Colors.FOREGROUND;
      BUTTON_MENU.checkedFontColor = Colors.FOREGROUND;
      Texture ninePatchTexture = SharedAssetManager.getInstance().get(Assets.Textures.PANEL_MEDIUM, Texture.class);
      BUTTON_MENU.over = new NinePatchDrawable(GraphicsFactory.createNinePatch(ninePatchTexture, 22));
      Texture defaultPatchTexture = SharedAssetManager.getInstance().get(Assets.Textures.PANEL, Texture.class);
      BUTTON_MENU.checked = BUTTON_MENU.over;
      BUTTON_MENU.up = new NinePatchDrawable(GraphicsFactory.createNinePatch(defaultPatchTexture, 22));
   }

   private static BitmapFont bake(String fontPath, int size, boolean border) {
      FreeTypeFontGenerator generator = SharedAssetManager.getInstance().get(fontPath, FreeTypeFontGenerator.class);
      FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
      param.color = Color.WHITE;
      param.size = size;
      param.mono = true;
      if (border) {
         param.borderStraight = true;
         param.borderWidth = 10f;
         param.borderColor = Colors.BORDER;
      }
      return generator.generateFont(param);
   }

   private static BitmapFont bake(String fontPath, int size) {
      return bake(fontPath, size, false);
   }
}
