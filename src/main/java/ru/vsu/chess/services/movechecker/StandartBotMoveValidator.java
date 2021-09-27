package ru.vsu.chess.services.movechecker;

import org.springframework.stereotype.Component;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.model.game.Move;
import ru.vsu.chess.model.player.Player;
@Component
public class StandartBotMoveValidator implements MoveValidator {

    @Override
    public boolean canDoThisMove(Move move, Player who, Game game) {
        return true;
    }
}
