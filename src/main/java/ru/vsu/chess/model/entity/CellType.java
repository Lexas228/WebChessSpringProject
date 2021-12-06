package ru.vsu.chess.model.entity;

import java.util.Arrays;
import java.util.Optional;

public enum CellType {
    Middle("Middle"),
    Center("Center"),
    LD_House("LD_House"),
    RD_House("RD_House"),
    LU_House("LU_House"),
    RU_House("RU_House");
    private final String type;

    CellType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public Optional<CellType> fromString(String string){
       return  Arrays.stream(CellType.values()).filter(cellType -> cellType.getType().equals(string)).findAny();
    }
}
