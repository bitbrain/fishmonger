package de.bitbrain.fishmonger;

import de.bitbrain.braingdx.BrainGdxGame;
import de.bitbrain.braingdx.assets.GameAssetLoader;
import de.bitbrain.braingdx.assets.SmartAssetLoader;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.screens.IngameScreen;

public class FishMongerGame extends BrainGdxGame {

	@Override
	protected GameAssetLoader getAssetLoader() {
		return new SmartAssetLoader(Assets.class);
	}

	@Override
	protected AbstractScreen<?> getInitialScreen() {
		return new IngameScreen(this);
	}
}
