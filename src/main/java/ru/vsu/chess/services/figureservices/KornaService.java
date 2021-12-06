package ru.vsu.chess.services.figureservices;

import org.springframework.stereotype.Service;
import ru.vsu.chess.model.entity.*;
import ru.vsu.chess.model.node.NodeCell;

import java.util.List;
import java.util.Map;

/*
A Korna moves as a rook in chess when in a corner of the board, and as
a bishop in the middle of the board. It is also possible to combine these moves
in one turn. If the Korna has moved one or more squares before crossing
the deflection line, it may then only move one square. Kornas can only take
in moves where they move over a deflection line.
 */
@Service
public class KornaService extends HorsaKornaService{

    @Override
    protected FigureType getMyType() {
        return FigureType.KORNA;
    }

    @Override
    protected List<Direction> getInHome() {
        return List.of(Direction.NORTH, Direction.EAST);
    }

    @Override
    protected List<Direction> getInMiddle() {
        return List.of(Direction.NORTH_EAST, Direction.NORTH_WEST);
    }

    @Override
    protected boolean canBeatAfterCrossing(NodeCell which, Board board, Player forWho, Integer distance) {
        Map<Figure, Player> figurePlayerMap = board.getFigurePlayerMap();
        if(figurePlayerMap != null){
            Map<Long, Figure> figureIdMap = board.getCellIdFigureMap();
            if(figureIdMap != null){
                Figure figure = figureIdMap.get(which.getId());
                Player player = figurePlayerMap.get(figure);
                return forWho != player;
            }
        }
        return false;
    }
}
