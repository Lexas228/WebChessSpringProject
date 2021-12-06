package ru.vsu.chess.components.movechecker;

import org.springframework.transaction.annotation.Transactional;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.dto.Move;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.entity.PlayerType;

public interface MoveValidator {
    @Transactional
    boolean canDoThisMove(Move move, Player who, Game game);
    PlayerType getPlayerType();
}
