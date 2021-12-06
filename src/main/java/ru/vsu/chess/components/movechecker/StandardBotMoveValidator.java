package ru.vsu.chess.components.movechecker;

import org.springframework.stereotype.Component;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.entity.Move;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.entity.PlayerType;
@Component
public class StandardBotMoveValidator implements MoveValidator {

    @Override
    public boolean canDoThisMove(Move move, Player who, Game game) {
        return true;
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.Bot;
    }
}
