package ru.vsu.chess.components.convector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vsu.chess.components.others.Point;
import ru.vsu.chess.components.rotator.PositionRotator;
import ru.vsu.chess.model.dto.MoveDto;
import ru.vsu.chess.model.entity.*;

import java.util.Map;

@Component
public class HumanMoveConvector implements MoveConvector{
    private final Map<Direction, PositionRotator> positionRotatorMap;

    @Autowired
    public HumanMoveConvector(Map<Direction, PositionRotator> positionRotatorMap) {
        this.positionRotatorMap = positionRotatorMap;
    }

    @Override
    public Move toEntity(MoveDto moveDto, Game game, Player player) {
        return new Move(Long.parseLong(moveDto.getFrom()), Long.parseLong(moveDto.getTo()));
    }

    private Point toPoint(String string){
        if(string.length() < 3) return null;
        int x = Integer.parseInt(Character.toString(string.charAt(0)));
        int y = Integer.parseInt(Character.toString(string.charAt(1)));
        return new Point(x, y);
    }

    @Override
    public PlayerType getMyType() {
        return PlayerType.Human;
    }
}
