package ru.vsu.chess.model.entity;

public enum FigureType {
   KAMPA("Kampa"),
   KORNA("Korna"),
   HORSA("Horsa"),
   GALA("Gala");
   private final String type;
   FigureType(String type){
      this.type = type;
   }
}
