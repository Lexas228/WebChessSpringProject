package ru.vsu.chess.model.cell;

import ru.vsu.chess.model.game.Direction;

import java.util.HashMap;
import java.util.Map;

public class Cell {
    private Map<Direction, Cell> cells;

    public Cell(){
        setCells(new HashMap<>());
        for(Direction dr : Direction.values()){
            getCells().put(dr, null);
        }
    }

    public Map<Direction, Cell> getCells() {
        return cells;
    }

    public void setCells(Map<Direction,  Cell> cells) {
        this.cells = cells;
    }

}
