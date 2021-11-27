package ru.vsu.chess.services.figureservices;

import ru.vsu.chess.model.entity.Cell;
import ru.vsu.chess.model.entity.FigureType;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.entity.Player;
i

import java.util.List;

public interface FigureService {
    List<Cell> getAvailableMoves(Cell from, Game game, Player forWho);
    FigureType getType();
}
