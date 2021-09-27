package ru.vsu.chess.model.player;

public record Human(String name) implements Player {
    public String getName() {
        return name;
    }
}
