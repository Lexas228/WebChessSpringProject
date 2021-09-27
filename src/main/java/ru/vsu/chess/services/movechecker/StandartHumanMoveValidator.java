package ru.vsu.chess.services.movechecker;

import ru.vsu.chess.model.figure.Figure;
import ru.vsu.chess.model.figure.FigureType;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.model.game.Move;
import ru.vsu.chess.model.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vsu.chess.services.figureservices.FigureService;


import java.util.Map;
@Component
public class StandartHumanMoveValidator implements MoveValidator {
    private final  Map<FigureType, FigureService> serviceMap;
    @Autowired
    public StandartHumanMoveValidator(Map<FigureType, FigureService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    @Override
    public boolean canDoThisMove(Move move, Player who, Game game) {
        Figure f = game.getCellFigure().get(move.getFrom());
        if(f == null) return false;
        return serviceMap.get(f.getMyType()).getAvailableMoves(move.getFrom(), game, who).contains(move.getTo());
    }
}
