package ru.vsu.chess.model.dto;

import lombok.Data;
import ru.vsu.chess.model.entity.Color;
import ru.vsu.chess.model.entity.FigureType;

@Data
public class FigureDto {
    private Color color;
    private FigureType figureType;
}
