package ru.vsu.chess.model.dto;

import lombok.Data;
import ru.vsu.chess.model.entity.GameStatus;

import java.util.Map;

@Data
public class GameStatusDto {
    private Long gameId;
    private Long actionPlayerId;
    private Map<String, FigureDto> state;
    private GameStatus gameStatus;
}
