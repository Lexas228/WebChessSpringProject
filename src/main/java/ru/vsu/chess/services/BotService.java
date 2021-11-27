package ru.vsu.chess.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vsu.chess.model.entity.*;
import ru.vsu.chess.model.node.NodeCell;
import ru.vsu.chess.services.figureservices.FigureService;

import java.util.*;

public class BotService implements PlayerService{

    private final Map<FigureType, FigureService> serviceMap;
    private static final Random rnd = new Random();
    @Autowired
    public BotService(Map<FigureType, FigureService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    @Override
    public Move getMove(Game game, Player forWho) {
        Set<Figure> figures = forWho.getMyFigures();
        Figure fig = getRandomFigure(figures, game);
        NodeCell from = fig.getCell();
        List<NodeCell> ce = serviceMap.get(fig.getMyType()).getAvailableMoves(from, game, forWho);
        while(ce.size() < 1){
            fig = getRandomFigure(figures, game);
            from = game.getFigureCell().get(fig);
            ce = serviceMap.get(fig.getMyType()).getAvailableMoves(from, game, forWho);
        }
        return new Move(from, ce.get(rnd.nextInt(ce.size())));
    }


    private Figure getRandomFigure(Set<Figure> figures, Game game){
        Figure f = null;
        while(f == null){
            int s = rnd.nextInt(figures.size());
            int step = 0;
            for(Figure figure : figures){
                step++;
                if(step >= s) {
                    f = figure;
                    break;
                }
            }
        }
        return f;
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.Bot;
    }
}
