package ru.vsu.chess.model.game;

import ru.vsu.chess.model.cell.Cell;

public record Move(Cell from, Cell to) {

    public Cell getFrom() {
        return from;
    }

    public Cell getTo() {
        return to;
    }
}
