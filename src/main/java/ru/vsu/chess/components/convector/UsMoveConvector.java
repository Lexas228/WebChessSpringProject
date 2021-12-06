package ru.vsu.chess.components.convector;

import org.springframework.stereotype.Component;
import ru.vsu.chess.model.dto.MoveDto;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.dto.Move;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.entity.PlayerType;

@Component
public class UsMoveConvector implements MoveConvector {
    @Override
    public Move toEntity(MoveDto moveDto, Game game, Player player) {
        return new Move(Long.parseLong(moveDto.getFrom()), Long.parseLong(moveDto.getTo()));
    }

    @Override
    public PlayerType getMyType() {
        return PlayerType.Bot;
    }
}
