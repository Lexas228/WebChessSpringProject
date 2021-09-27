package ru.vsu.chess.controller.playercontroller;

import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.model.game.Move;
import ru.vsu.chess.model.player.Player;

public interface PlayerController {
    Move getMove(Game game, Player forWho);

}
