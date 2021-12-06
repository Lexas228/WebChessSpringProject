package ru.vsu.chess.components.movechecker;

import ru.vsu.chess.model.entity.Figure;
import ru.vsu.chess.model.entity.FigureType;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.entity.PlayerType;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.dto.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vsu.chess.services.figureservices.FigureService;


import java.util.Map;
@Component
public class UsualHumanMoveValidator implements MoveValidator {
    private Map<FigureType, FigureService> serviceMap;
    @Autowired
    public void setServiceMap(Map<FigureType, FigureService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    @Override
    public boolean canDoThisMove(Move move, Player who, Game game) {
        if(move == null) return false;
        Figure f = game.getBoard().getCellIdFigureMap().get(move.from());
        if(f == null || game.getBoard().getFigurePlayerMap().get(f) != who) return false;
        return serviceMap.get(f.getMyType()).getAvailableMoves(move.from(), game, who).contains(move.to());
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.Human;
    }
}
