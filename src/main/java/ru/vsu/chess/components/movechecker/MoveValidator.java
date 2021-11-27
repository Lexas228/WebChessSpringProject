package ru.vsu.chess.components.movechecker;

import ru.vsu.chess.model.Player;
import ru.vsu.chess.model.PlayerType;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.model.game.Move;
import ru.vsu.chess.model.player.Player;

public interface MoveValidator {
    boolean canDoThisMove(Move move, Player who, Game game);
    PlayerType getPlayerType();
}
