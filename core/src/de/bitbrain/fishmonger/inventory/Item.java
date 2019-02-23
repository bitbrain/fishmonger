package de.bitbrain.fishmonger.inventory;

import com.badlogic.gdx.graphics.Texture;

public class Item {

   private String id;
   private Texture icon;
   private String name;
   private String description;

   public Item(String id, Texture icon, String name, String description) {
      this.id = id;
      this.icon = icon;
      this.name = name;
      this.description = description;
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
