package ru.vsu.chess.components.convector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vsu.chess.components.others.Point;
import ru.vsu.chess.components.rotator.PositionRotator;
import ru.vsu.chess.model.dto.FigureDto;
import ru.vsu.chess.model.dto.GameStatusDto;
import ru.vsu.chess.model.entity.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameConvector {
    private final Map<Direction, PositionRotator> positionRotatorMap;

    @Autowired
    public GameConvector(Map<Direction, PositionRotator> positionRotatorMap) {
        this.positionRotatorMap = positionRotatorMap;
    }

    public GameStatusDto toGst(Game game, Player player){
        GameStatusDto gsd = new GameStatusDto();
        gsd.setGameId(game.getId());
        gsd.setGameStatus(game.getGameStatus());
        gsd.setActionPlayerId(game.getActivePlayer().getId());
        Map<String, FigureDto> res = new HashMap<>();
        Direction playerDirection = game.getPlayerDirectionMap().get(player);
        PositionRotator need = positionRotatorMap.get(playerDirection);
        Board b = game.getBoard();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                Point curr = new Point(i, j);
                need.rotate(curr);
                Long cell = b.getPositionCellMap().get(curr.toString());
                Figure figure = b.getCellIdFigureMap().get(cell);
                FigureDto fdt = figure == null ? null : new FigureDto();
                if(fdt != null){
                    fdt.setColor(figure.getColor());
                    fdt.setFigureType(figure.getMyType());
                }
                res.put(i+""+j, fdt);
            }
        }
        gsd.setState(res);
        return gsd;
    }

}
