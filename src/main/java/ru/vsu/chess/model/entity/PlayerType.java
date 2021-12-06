package ru.vsu.chess.model.entity;

public enum PlayerType {
    Human("Human"),
    Bot("Bot");
    private final String type;
    PlayerType(String type){
        this.type = type;
    }
}
