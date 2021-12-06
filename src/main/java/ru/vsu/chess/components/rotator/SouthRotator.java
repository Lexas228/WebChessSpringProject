package ru.vsu.chess.components.rotator;

import org.springframework.stereotype.Component;
import ru.vsu.chess.components.others.Point;
import ru.vsu.chess.model.entity.Direction;

@Component
public class SouthRotator implements PositionRotator{
    @Override
    public void rotate(Point point) {
        //actually nothing
    }

    @Override
    public Direction getDirection() {
        return Direction.SOUTH;
    }
}
