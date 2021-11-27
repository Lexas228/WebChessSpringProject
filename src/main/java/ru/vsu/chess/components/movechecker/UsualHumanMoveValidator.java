package ru.vsu.chess.components.movechecker;

import ru.vsu.chess.model.Figure;
import ru.vsu.chess.model.FigureType;
import ru.vsu.chess.model.Player;
import ru.vsu.chess.model.PlayerType;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.model.game.Move;
import ru.vsu.chess.model.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vsu.chess.services.figureservices.FigureService;


import java.util.Map;
@Component
public class UsualHumanMoveValidator implements MoveValidator {
    private final  Map<FigureType, FigureService> serviceMap;
    @Autowired
    public UsualHumanMoveValidator(Map<FigureType, FigureService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    @Override
    public boolean canDoThisMove(Move move, Player who, Game game) {
        Figure f = game.getCellFigure().get(move.getFrom());
        if(f == null) return false;
        return serviceMap.get(f.getMyType()).getAvailableMoves(move.getFrom(), game, who).contains(move.getTo());
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.Human;
    }
}
