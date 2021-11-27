package ru.vsu.chess.model.others;

import ru.vsu.chess.model.FigureType;

import java.util.Map;

public interface BasicPosition {
    public Map<Point, FigureType> getBasicUp();
    public Map<Point, FigureType> getBasicDown();
}
