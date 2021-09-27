package ru.vsu.chess.model.figure;

public class Figure {
    private FigureType myType;

    public Figure(FigureType myType) {
        this.myType = myType;
    }

    public FigureType getMyType() {
        return myType;
    }

    public void setMyType(FigureType myType) {
        this.myType = myType;
    }
}
