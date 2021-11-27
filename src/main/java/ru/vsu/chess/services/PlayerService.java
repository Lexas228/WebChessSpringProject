package ru.vsu.chess.services;

import ru.vsu.chess.model.Player;
import ru.vsu.chess.model.PlayerType;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.model.game.Move;

public interface PlayerService {
    Move getMove(Game game, Player forWho);
    PlayerType getPlayerType();
}
