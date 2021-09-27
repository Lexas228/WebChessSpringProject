package ru.vsu.chess.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.vsu.chess.model.figure.FigureType;
import ru.vsu.chess.services.figureservices.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan("ru.vsu.chess.services")
@ComponentScan("ru.vsu.chess.controller.playercontroller")
public class SpringConfiguration {

    private List<FigureService> figureServices;

    @Autowired
    public void setFigureServices(List<FigureService> figureServices){
        this.figureServices = figureServices;
    }

    @Bean
    public Map<FigureType, FigureService> figureServiceMap(){
        Map<FigureType, FigureService> res = new HashMap<>();
        for(FigureService fs : figureServices){
            res.put(fs.getType(), fs);
        }
        return res;
    }
}
