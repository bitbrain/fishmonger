package de.bitbrain.fishmonger.model.inventory;

import com.badlogic.gdx.graphics.Texture;

public class Item {

   private final String id;
   private final Texture icon;
   private final  String name;
   private final String description;
   private final int value;

   public Item(String id, Texture icon, String name, String description, int value) {
      this.id = id;
      this.icon = icon;
      this.name = name;
      this.description = description;
      this.value = value;
   }

   public String getId() {
      return id;
   }

   public Texture getIcon() {
      return icon;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public int getValue() {
      return value;
   }

   @Override
   public String toString() {
      return "Item{" +
            "id='" + id + '\'' +
            ", icon='" + icon + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
   }
}
