package ru.vsu.chess.components.others;

import ru.vsu.chess.model.entity.Direction;
import ru.vsu.chess.model.entity.FigureType;

import java.util.Map;

public interface BasicPosition {
    Map<Point, FigureType> getBasicPositionFor(Direction direction);
}
