package ru.vsu.chess.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Direction {
    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West"),
    NORTH_WEST("North-West"),
    SOUTH_WEST("South-West"),
    SOUTH_EAST("South-East"),
    NORTH_EAST("North-East");
    private final String direction;
    Direction (String direction){
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public Optional<Direction> fromString(String dir){
        return Arrays.stream(Direction.values()).filter(direction -> direction.getDirection().equals(dir)).findAny();
    }
}
