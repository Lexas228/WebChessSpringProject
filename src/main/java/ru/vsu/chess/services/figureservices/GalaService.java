package ru.vsu.chess.services.figureservices;

import org.springframework.stereotype.Service;
import ru.vsu.chess.model.*;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.model.game.Direction;
import ru.vsu.chess.model.player.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Galas move in general as kings in chess. Additionally, when a Gala is on one
of the four squares in the middle of the board, it may be moved in one move
to any empty square on the board with the exception of those squares that
contain a piece in the opening setup. Kings can only take when moving over
a deflection line.
 */

@Service
public class GalaService implements FigureService{
    @Override
    public List<Cell> getAvailableMoves(Cell from, Game game, Player forWho) {
        List<Cell> answer = new ArrayList<>();
        CellType toc = game.getCells().get(from);
            List<Direction> directions = getMyDirections(from, FigureType.GALA, game);
            if (toc == CellType.Center) {
                finder(new HashSet<>(), from, game, answer, directions);
            } else {
                for (Direction dr : directions) {
                    Cell need = from.getCells().get(dr);
                    if (need != null) {
                        Figure f = game.getCellFigure().get(need);
                        if (f == null || hasEnemyFigure(need, game, forWho) && crossed(from, need, game)) {
                            answer.add(need);
                        }
                    }
                }
            }
        return answer;
    }

    private void finder(Set<Cell> visited, Cell curr, Game game, List<Cell> answer, List<Direction> directions){
        for(Direction dr : directions){
            Cell need = curr.getCells().get(dr);
            if(need != null){
                Figure f = game.getCellFigure().get(need);
                Figure k = game.getBasicCellFigurePosition().get(need);
                if(f == null && k == null && !visited.contains(need)){
                    answer.add(need);
                    visited.add(need);
                    finder(visited, need, game, answer, directions);
                }
            }
        }
    }

    @Override
    public FigureType getType() {
        return FigureType.GALA;
    }
}
