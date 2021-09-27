package ru.vsu.chess.model.game;

import ru.vsu.chess.model.figure.Figure;
import ru.vsu.chess.model.game.Move;
import ru.vsu.chess.model.player.Player;

public record Step(Move move, Player who, Figure bittenFigure) {

    public Move getMove() {
        return move;
    }

    public Player getWho() {
        return who;
    }

    public Figure bittenFigure(){return  bittenFigure;}
}
