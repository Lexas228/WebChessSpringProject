package ru.vsu.chess.services.figureservices;

import org.springframework.stereotype.Service;
import ru.vsu.chess.model.Figure;
import ru.vsu.chess.model.FigureType;
import ru.vsu.chess.model.Cell;
import ru.vsu.chess.model.game.Game;

import ru.vsu.chess.model.game.Direction;
import ru.vsu.chess.model.CellType;
import ru.vsu.chess.model.player.Player;

import java.util.*;

/*
Kampas move diagonally towards the middle of the board until they are in the
middle part or in the opponents corners. It is allowed to move two squares
if the deflection line isn't passed with the first move of the Kampa.
When the Kampa is in the middle part or in one of the opponents corners,
then it may move one square in any direction. When a Kampa is moved back
to a corner of the owning player, it can only move diagonally forward again.
Kampas only can take in the move after they have crossed a deflection line.
Kampa can't move to "nobile" squares.
 */

@Service
public class KampaService implements FigureService{
    @Override
    public List<Cell> getAvailableMoves(Cell from, Game game, Player forWho) {
        List<Cell> answer = new ArrayList<>();
        CellType r = game.getCells().get(from);
        List<Direction> list = game.getFiguresDirections().get(r).get(FigureType.KAMPA);
        for (Direction dr : list) {
            Cell need = from.getCells().get(dr);
            if (need != null) {
                Figure f = game.getCellFigure().get(need);
                boolean cross = crossed(from, need, game);
                CellType tc = game.getCells().get(need);
                if ((hasEnemyFigure(need, game, forWho) && cross || f == null) && tc != CellType.Middle) {
                    answer.add(need);
                    if(atHome(from, game)){
                        Cell oneMore = need.getCells().get(dr);
                        if(oneMore != null) {
                            CellType ct = game.getCells().get(oneMore);
                            Figure g = game.getCellFigure().get(oneMore);
                            if (!cross && (g == null || hasEnemyFigure(oneMore, game, forWho) && crossed(need, oneMore, game) && ct != CellType.Middle)){
                                answer.add(oneMore);
                            }
                        }
                    }
                }

            }
        }
    return answer;
    }

    private boolean atHome(Cell from, Game game){
        Figure f = game.getCellFigure().get(from);
        CellType type = game.getCells().get(from);
        Cell startPos = game.getBasicFigureCellPosition().get(f);
        CellType toc = game.getCells().get(startPos);
        Map<CellType, Set<CellType>> ls = game.getGalasHomeHelper();
        return ls != null && ls.get(toc) != null && ls.get(toc).contains(type);
    }


    @Override
    public FigureType getType() {
        return FigureType.KAMPA;
    }
}
