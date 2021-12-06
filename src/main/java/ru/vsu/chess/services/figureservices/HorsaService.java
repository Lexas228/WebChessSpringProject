package ru.vsu.chess.services.figureservices;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.vsu.chess.model.entity.*;
import ru.vsu.chess.model.node.NodeCell;

import java.util.List;
import java.util.Map;

/*
Horsas are just the opposite of Kornas (with an additional small difference
in the taking rules): they move as bishops on the corner parts, and as rooks
on the middle part. Again it is possible to combine such moves. If the Horsa
has moved one or more squares before crossing the line, it may then only move
one square. Horsas only take in moves where they pass the deflection line,
but not when they move only one square in rookwise fashion.
 */
@Service
@Primary
public class HorsaService extends HorsaKornaService{
    @Override
    protected List<Direction> getInHome() {
        return List.of(Direction.NORTH_EAST, Direction.NORTH_WEST);
    }

    @Override
    protected List<Direction> getInMiddle() {
        return List.of(Direction.EAST, Direction.NORTH);
    }

    @Override
    protected FigureType getMyType() {
        return FigureType.HORSA;
    }

    @Override
    protected boolean canBeatAfterCrossing(NodeCell which, Board board, Player forWho, Integer distance) {
        Map<Figure, Player> figurePlayerMap = board.getFigurePlayerMap();
        if(figurePlayerMap != null){
            Map<Long, Figure> figureIdMap = board.getCellIdFigureMap();
            if(figureIdMap != null){
                Figure figure = figureIdMap.get(which.getId());
                Player player = figurePlayerMap.get(figure);
                return forWho != player && distance < 2;
            }
        }
        return false;
    }
}
