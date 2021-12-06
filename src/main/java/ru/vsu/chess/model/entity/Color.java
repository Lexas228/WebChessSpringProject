package ru.vsu.chess.model.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Color {
    White("White"),
    Black("Black");

    private String color;
    Color(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public Optional<Color> fromString(String string){
        return  Arrays.stream(Color.values()).filter(color -> color.getColor().equals(string)).findAny();
    }
}
