package de.bitbrain.fishmonger.i18n;

public enum Messages {

   PLAYER_BALANCE("player.balance"),

   AND("and"),

   FISH_DELIVERY_EMPTY("fish.delivery.empty"),

   FISH_DELIVERY_01("fish.delivery.1"),
   FISH_DELIVERY_02("fish.delivery.2"),
   FISH_DELIVERY_03("fish.delivery.3"),
   FISH_DELIVERY_04("fish.delivery.4"),
   FISH_DELIVERY_05("fish.delivery.5"),
   FISH_DELIVERY_06("fish.delivery.6"),
   FISH_DELIVERY_07("fish.delivery.7"),
   FISH_DELIVERY_08("fish.delivery.8"),
   FISH_DELIVERY_09("fish.delivery.9"),
   FISH_DELIVERY_10("fish.delivery.10"),
   FISH_DELIVERY_11("fish.delivery.11"),
   FISH_DELIVERY_12("fish.delivery.12"),
   FISH_DELIVERY_13("fish.delivery.13"),
   FISH_DELIVERY_14("fish.delivery.14"),
   FISH_DELIVERY_15("fish.delivery.15"),
   FISH_DELIVERY_16("fish.delivery.16"),
   FISH_DELIVERY_17("fish.delivery.17"),
   FISH_DELIVERY_18("fish.delivery.18"),
   FISH_DELIVERY_19("fish.delivery.19"),

   FISH_PIRANHA_NAME("fish.piranha.name"),
   FISH_PIRANHA_NAME_PLURAL("fish.piranha.name.plural"),
   FISH_PIRANHA_DESCRIPTION("fish.piranha.description"),
   FISH_MACKEREL_NAME("fish.mackerel.name"),
   FISH_MACKEREL_NAME_PLURAL("fish.mackerel.name.plural"),
   FISH_MACKEREL_DESCRIPTION("fish.mackerel.description"),

   FISH_CAUGHT("fish.caught"),
   TIME_EXPIRED("time.expired"),
   INVENTORY_FULL("inventory.full"),

   LEVEL_1_NAME("level.1.name"),
   LEVEL_2_NAME("level.2.name"),
   LEVEL_3_NAME("level.3.name"),
   LEVEL_4_NAME("level.4.name"),

   GAME_OVER_EMPTY("game.over.empty"),
   GAME_OVER_INTRODUCTION("game.over.introduction"),
   GAME_OVER_OVERVIEW("game.over.overview"),
   GAME_OVER_RESULT("game.over.result"),
   GAME_OVER_INSUFFICIENT_CASH("game.over.insufficient"),
   GAME_OVER_SUFFICIENT("game.over.sufficient");

   private final String key;

   Messages(String key) {
      this.key = key;
   }

   public String getKey() {
      return key;
   }
}
