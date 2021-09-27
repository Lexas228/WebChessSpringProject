package ru.vsu.chess.services.figureservices;

import ru.vsu.chess.model.figure.Figure;
import ru.vsu.chess.model.figure.FigureType;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.model.cell.Cell;
import ru.vsu.chess.model.game.Direction;
import ru.vsu.chess.model.cell.CellType;
import ru.vsu.chess.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public interface FigureService {
    List<Cell> getAvailableMoves(Cell from, Game game, Player forWho);

    default boolean crossed(Cell o, Cell t, Game game){
        CellType one = game.getCells().get(o);
        CellType two = game.getCells().get(t);
        return one != two && !(one == CellType.Center && two == CellType.Middle || one == CellType.Middle && two == CellType.Center);
    }

    default List<Direction> getMyDirections(Cell c, FigureType ft, Game game){
        List<Direction> answer = new ArrayList<>();
        if(c != null && game != null){
            CellType toc = game.getCells().get(c);
            answer.addAll(game.getFiguresDirections().get(toc).get(ft));
        }
        return answer;
    }

    default boolean hasEnemyFigure(Cell cell, Game game, Player forWho){
        Figure f = game.getCellFigure().get(cell);
        return f != null && !game.getPlayerFigures().get(forWho).contains(f);
    }

    FigureType getType();
}
