package ru.vsu.chess.model.player;

public record Bot(String name) implements Player {
    public String getName() {
        return name;
    }
}
