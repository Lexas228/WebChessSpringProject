package ru.vsu.chess.dto;

import lombok.Data;

@Data
public class MoveDto {
    private Long playerId;
    private Long gameId;
    private String move;
}
