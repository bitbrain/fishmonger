package de.bitbrain.fishmonger.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.bitbrain.fishmonger.model.Money;

public class MoneyUI extends Label {

   private final Money money;

   public MoneyUI(Money money) {
      super("0$", Styles.INGAME_CASH);
      this.money = money;
   }

   @Override
   public void act(float delta) {
      setText(money.getAmount() + "$");
      super.act(delta);
   }
}
