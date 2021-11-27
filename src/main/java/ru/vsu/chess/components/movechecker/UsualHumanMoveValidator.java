package ru.vsu.chess.components.movechecker;

import ru.vsu.chess.model.entity.Figure;
import ru.vsu.chess.model.entity.FigureType;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.entity.PlayerType;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.entity.Move;
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
        Figure f = game.getCellFigureMap().get(move.from());
        if(f == null) return false;
        return serviceMap.get(f.getMyType()).getAvailableMoves(move.from(), game, who).contains(move.to());
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.Human;
    }
}
