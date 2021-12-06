package ru.vsu.chess.model.entity;

public enum Roles {
    Playing("Playing"),
    Searching("Searching"),
    Nothing("Nothing");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

}
