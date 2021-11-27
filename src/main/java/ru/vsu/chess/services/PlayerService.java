package ru.vsu.chess.services;

import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.entity.PlayerType;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.entity.Move;

public interface PlayerService {
    Move getMove(Game game, Player forWho);
    PlayerType getPlayerType();
}
