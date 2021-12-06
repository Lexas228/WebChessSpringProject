package ru.vsu.chess.services.figureservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.chess.components.DirectionHelper;
import ru.vsu.chess.model.entity.Board;
import ru.vsu.chess.model.entity.Figure;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.node.NodeCell;
import ru.vsu.chess.repository.neo4j.CellRepository;

import java.util.Map;

@Service
public abstract class AbstractFigureService implements FigureService{
    protected DirectionHelper directionHelper;
    protected CellRepository cellRepository;

    @Autowired
    public void setDirectionHelper(DirectionHelper directionHelper) {
        this.directionHelper = directionHelper;
    }

    @Autowired
    public void setCellRepository(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

    protected boolean canBeatIt(NodeCell from, NodeCell to, Board board, Player who){
        Map<Figure, Player> figurePlayerMap = board.getFigurePlayerMap();
        if(figurePlayerMap != null){
            Map<Long, Figure> figureIdMap = board.getCellIdFigureMap();
            if(figureIdMap != null){
                Figure figure = figureIdMap.get(to.getId());
                Player player = figurePlayerMap.get(figure);
                return from.isHouse() != to.isHouse() && who != player;
            }
        }
        return false;
    }


}
