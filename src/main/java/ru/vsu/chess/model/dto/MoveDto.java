package ru.vsu.chess.model.dto;

import lombok.Data;

@Data
public class MoveDto {
    private Long playerId;
    private Long gameId;
    private String from;
    private String to;
}
