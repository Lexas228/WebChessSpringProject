package ru.vsu.chess.components.others;

import ru.vsu.chess.model.entity.FigureType;

import java.util.Map;

public interface BasicPosition {
    public Map<Point, FigureType> getBasicUp();
    public Map<Point, FigureType> getBasicDown();
}
