package de.bitbrain.fishmonger;

import de.bitbrain.braingdx.BrainGdxGame;
import de.bitbrain.braingdx.assets.GameAssetLoader;
import de.bitbrain.braingdx.assets.SmartAssetLoader;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.i18n.Bundle;
import de.bitbrain.fishmonger.screens.IngameScreen;
import de.bitbrain.fishmonger.ui.Styles;

public class FishMongerGame extends BrainGdxGame {

	@Override
	protected GameAssetLoader getAssetLoader() {
		return new SmartAssetLoader(Assets.class);
	}

	@Override
	protected AbstractScreen<?> getInitialScreen() {
		Bundle.load();
		Styles.load();
		return new IngameScreen(this);
	}
}
