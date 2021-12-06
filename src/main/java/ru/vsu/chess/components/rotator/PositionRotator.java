package ru.vsu.chess.components.rotator;

import ru.vsu.chess.components.others.Point;
import ru.vsu.chess.model.entity.Direction;

public interface PositionRotator {
    void rotate(Point point);
    Direction getDirection();
}
