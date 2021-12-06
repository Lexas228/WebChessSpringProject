package ru.vsu.chess.components.convector;

import ru.vsu.chess.model.dto.MoveDto;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.dto.Move;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.entity.PlayerType;

public interface MoveConvector {
    Move toEntity(MoveDto moveDto, Game game, Player player);
    PlayerType getMyType();
}
