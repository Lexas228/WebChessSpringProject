package ru.vsu.chess.controller.playercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.vsu.chess.model.figure.Figure;
import ru.vsu.chess.model.figure.FigureType;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.model.game.Move;
import ru.vsu.chess.model.cell.Cell;
import ru.vsu.chess.model.player.Player;
import ru.vsu.chess.services.figureservices.FigureService;


import java.util.*;
@Controller
public class BotController implements PlayerController {
    private final Map<FigureType, FigureService> serviceMap;
    private static final Random rnd = new Random();
    @Autowired
    public BotController(Map<FigureType, FigureService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    @Override
    public Move getMove(Game game, Player forWho) {
        List<Figure> figures = new ArrayList<>(game.getPlayerFigures().get(forWho));
        Figure fig = getRandomFigure(figures, game);
        Cell from = game.getFigureCell().get(fig);
        List<Cell> ce = serviceMap.get(fig.getMyType()).getAvailableMoves(from, game, forWho);
        while(ce.size() < 1){
            fig = getRandomFigure(figures, game);
            from = game.getFigureCell().get(fig);
            ce = serviceMap.get(fig.getMyType()).getAvailableMoves(from, game, forWho);
        }
        return new Move(from, ce.get(rnd.nextInt(ce.size())));
    }


    private Figure getRandomFigure(List<Figure> figures, Game game){
        int s = rnd.nextInt(figures.size());
        while(game.getFigureCell().get(figures.get(s)) == null){
            s = rnd.nextInt(figures.size());
        }
        return figures.get(s);
    }

}
