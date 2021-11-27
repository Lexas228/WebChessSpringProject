package ru.vsu.chess.model.game;

import ru.vsu.chess.model.Figure;
import ru.vsu.chess.model.Player;

public record Step(Move move, Player who, Figure bittenFigure) {

    public Move getMove() {
        return move;
    }

    public Player getWho() {
        return who;
    }

    public Figure bittenFigure(){return  bittenFigure;}
}
