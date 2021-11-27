package ru.vsu.chess.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vsu.chess.components.movechecker.MoveValidator;
import ru.vsu.chess.model.entity.FigureType;
import ru.vsu.chess.model.entity.PlayerType;
import ru.vsu.chess.services.PlayerService;
import ru.vsu.chess.services.figureservices.FigureService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SpringConfiguration {

    private List<PlayerService> playerServices;

    private List<MoveValidator> moveValidators;

    private List<FigureService> figureServices;

    @Autowired
    public void setFigureServices(List<FigureService> figureServices){
        this.figureServices = figureServices;
    }

    @Autowired
    public void setPlayerServices(List<PlayerService> playerServices){
        this.playerServices = playerServices;
    }

    @Autowired
    public void setMoveValidators(List<MoveValidator> moveValidators){
        this.moveValidators = moveValidators;
    }


    @Bean
    public Map<FigureType, FigureService> figureServiceMap(){
        Map<FigureType, FigureService> res = new HashMap<>();
        for(FigureService fs : figureServices){
            res.put(fs.getType(), fs);
        }
        return res;
    }

    @Bean
    public Map<PlayerType, PlayerService> playerServiceMap(){
        Map<PlayerType, PlayerService> res = new HashMap<>();
        playerServices.forEach(playerService -> {
            res.put(playerService.getPlayerType(), playerService);
        });
        return res;
    }

    @Bean
    public Map<PlayerType, MoveValidator> playerMoveValidatorMap(){
        Map<PlayerType, MoveValidator> res = new HashMap<>();
        moveValidators.forEach(moveValidator -> {
            res.put(moveValidator.getPlayerType(), moveValidator);
        });
        return res;
    }
}
