package ru.vsu.chess.components.convector;

import org.springframework.stereotype.Component;
import ru.vsu.chess.model.dto.FigureDto;
import ru.vsu.chess.model.entity.Figure;

@Component
public class FigureConvector {
    public FigureDto toFigureDto(Figure figure){
        FigureDto figureDto = new FigureDto();
        figureDto.setFigureType(figure.getMyType());
        figureDto.setColor(figure.getColor());
        return figureDto;
    }
}
