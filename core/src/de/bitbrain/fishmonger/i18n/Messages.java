package de.bitbrain.fishmonger.i18n;

public enum Messages {

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
   FISH_PIRANHA_DESCRIPTION("fish.piranha.description"),
   FISH_MACKEREL_NAME("fish.mackerel.name"),
   FISH_MACKEREL_DESCRIPTION("fish.mackerel.description");

   private final String key;

   Messages(String key) {
      this.key = key;
   }

   public String getKey() {
      return key;
   }
}
