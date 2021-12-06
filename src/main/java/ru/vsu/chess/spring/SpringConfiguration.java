package ru.vsu.chess.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import ru.vsu.chess.components.convector.MoveConvector;
import ru.vsu.chess.components.movechecker.MoveValidator;
import ru.vsu.chess.components.others.Point;
import ru.vsu.chess.components.rotator.PositionRotator;
import ru.vsu.chess.model.entity.CellType;
import ru.vsu.chess.model.entity.Direction;
import ru.vsu.chess.model.entity.FigureType;
import ru.vsu.chess.model.entity.PlayerType;
import ru.vsu.chess.services.PlayerService;
import ru.vsu.chess.services.figureservices.FigureService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class SpringConfiguration {

    private List<PlayerService> playerServices;

    private List<MoveValidator> moveValidators;

    private List<FigureService> figureServices;

    private List<PositionRotator> positionRotators;

    private List<MoveConvector> moveConvectors;

    @Autowired @Lazy
    public void setFigureServices(List<FigureService> figureServices){
        this.figureServices = figureServices;
    }

    @Autowired @Lazy
    public void setPlayerServices(List<PlayerService> playerServices){
        this.playerServices = playerServices;
    }

    @Autowired @Lazy
    public void setMoveValidators(List<MoveValidator> moveValidators){
        this.moveValidators = moveValidators;
    }

    @Autowired @Lazy
    public void setPositionRotators(List<PositionRotator> positionRotators) {
        this.positionRotators = positionRotators;
    }

    @Autowired @Lazy
    public void setMoveConvectors(List<MoveConvector> moveConvectors) {
        this.moveConvectors = moveConvectors;
    }

    @Bean @Lazy
    public Map<FigureType, FigureService> figureServiceMap(){
        Map<FigureType, FigureService> res = new HashMap<>();
        for(FigureService fs : figureServices){
            res.put(fs.getType(), fs);
        }
        return res;
    }

    @Bean @Lazy
    public Map<PlayerType, PlayerService> playerServiceMap(){
        Map<PlayerType, PlayerService> res = new HashMap<>();
        playerServices.forEach(playerService -> {
            res.put(playerService.getPlayerType(), playerService);
        });
        return res;
    }

    @Bean @Lazy
    public Map<PlayerType, MoveValidator> playerMoveValidatorMap(){
        Map<PlayerType, MoveValidator> res = new HashMap<>();
        moveValidators.forEach(moveValidator -> {
            res.put(moveValidator.getPlayerType(), moveValidator);
        });
        return res;
    }

    @Bean @Lazy
    public Map<Direction, PositionRotator> positionRotatorMap(){
        Map<Direction, PositionRotator> positionRotatorMap = new HashMap<>();
        positionRotators.forEach(positionRotator -> {
            positionRotatorMap.put(positionRotator.getDirection(), positionRotator);
        });
        return positionRotatorMap;
    }

    @Bean @Lazy
    public Map<Point, Direction> pointDirectionMap(){
        return Map.of(
                new Point(-1, 0), Direction.NORTH,
                new Point(-1, -1), Direction.NORTH_WEST,
                new Point(0, -1), Direction.WEST,
                new Point(1, -1), Direction.SOUTH_WEST,
                new Point(1, 0), Direction.SOUTH,
                new Point(1, 1), Direction.SOUTH_EAST,
                new Point(0, 1), Direction.EAST,
                new Point(-1, 1), Direction.NORTH_EAST
        );
    }

    @Bean @Lazy
    public Map<Direction, Set<CellType>> kampaMap(){
        return Map.of(
                Direction.NORTH, Set.of(CellType.LU_House, CellType.RU_House),
                Direction.SOUTH, Set.of(CellType.LD_House, CellType.RD_House)
        );
    }

    @Bean @Lazy
    public Map<CellType, Direction> kampaHelperMap(){
        return Map.of(
                CellType.LD_House, Direction.NORTH_EAST,
                CellType.LU_House, Direction.SOUTH_EAST,
                CellType.RD_House, Direction.NORTH_WEST,
                CellType.RU_House, Direction.NORTH_EAST
        );
    }

    @Bean @Lazy
    public Map<PlayerType, MoveConvector> moveConvectorMap(){
        Map<PlayerType, MoveConvector> res = new HashMap<>();
        moveConvectors.forEach(moveConvector -> {
            res.put(moveConvector.getMyType(), moveConvector);
        });
        return res;
    }
}
