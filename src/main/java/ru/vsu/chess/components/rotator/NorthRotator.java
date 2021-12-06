package ru.vsu.chess.components.rotator;

import org.springframework.stereotype.Component;
import ru.vsu.chess.components.others.Point;
import ru.vsu.chess.model.entity.Direction;


@Component
public class NorthRotator implements PositionRotator{
    @Override
    public void rotate(Point point) {
        point.setX(9- point.getX());
    }

    @Override
    public Direction getDirection() {
        return Direction.NORTH;
    }
}
