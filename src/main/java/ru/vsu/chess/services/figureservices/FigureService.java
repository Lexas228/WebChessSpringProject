package ru.vsu.chess.services.figureservices;

import ru.vsu.chess.model.*;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.model.game.Direction;
import ru.vsu.chess.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public interface FigureService {
    List<Cell> getAvailableMoves(Cell from, Game game, Player forWho);
    FigureType getType();
}
