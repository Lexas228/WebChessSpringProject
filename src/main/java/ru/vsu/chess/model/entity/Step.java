package ru.vsu.chess.model.entity;

public record Step(Move move, Player who, Figure bittenFigure) {

    public Move getMove() {
        return move;
    }

    public Player getWho() {
        return who;
    }

    public Figure bittenFigure(){return  bittenFigure;}
}
