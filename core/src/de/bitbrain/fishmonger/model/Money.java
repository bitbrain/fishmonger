package de.bitbrain.fishmonger.model;

import com.badlogic.gdx.audio.Sound;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.fishmonger.assets.Assets;
import de.bitbrain.fishmonger.progress.PlayerProgress;

public class Money {

   private int amount;

   public int getAmount() {
      return amount;
   }

   public void addAmount(int amount) {
      this.amount += amount;
      PlayerProgress.addMoney(amount);
      Sound sound = SharedAssetManager.getInstance().get(Assets.Sounds.COIN, Sound.class);
      sound.play();
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }
}
