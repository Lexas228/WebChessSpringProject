package ru.vsu.chess.services.figureservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.chess.components.DirectionHelper;
import ru.vsu.chess.model.*;
import ru.vsu.chess.model.game.Direction;
import ru.vsu.chess.model.game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public abstract class AbstractFigureService implements FigureService{
    private final DirectionHelper directionHelper;
    @Autowired
    public AbstractFigureService(DirectionHelper directionHelper){
        this.directionHelper = directionHelper;
    }
    protected boolean crossed(Cell o, Cell t){
        Optional<CellConnection> need = o.getConnections().stream().filter(cellConnection -> cellConnection.getEnd() == t).findAny();
        if(need.isPresent()){
            CellConnection cs = need.get();
            o.getConnections().stream().filter(cellConnection -> {

                return false;
            })

        }
        return false;
    }

    protected List<Direction> getMyDirections(Cell c, FigureType ft){
        List<Direction> answer = new ArrayList<>();
        if(c != null && game != null){
            CellType toc = game.getCells().get(c);
            answer.addAll(game.getFiguresDirections().get(toc).get(ft));
        }
        return answer;
    }

    protected boolean hasEnemyFigure(Cell cell, Player forWho){
        Figure f = cell.getFigure();
        return f != null && !forWho.getMyFigures().contains(f);
    }

}
