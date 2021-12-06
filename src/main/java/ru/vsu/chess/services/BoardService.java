package ru.vsu.chess.services;

import org.hibernate.type.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.chess.components.DirectionHelper;
import ru.vsu.chess.components.others.BasicPosition;
import ru.vsu.chess.components.others.Point;
import ru.vsu.chess.model.entity.*;
import ru.vsu.chess.model.node.CellConnection;
import ru.vsu.chess.model.node.NodeCell;
import ru.vsu.chess.repository.jpa.BoardRepository;
import ru.vsu.chess.repository.jpa.FigureRepository;
import ru.vsu.chess.repository.neo4j.CellRepository;

import java.util.*;

@Service
public class BoardService {

    private final CellRepository cellRepository;
    private final BoardRepository boardRepository;
    private BasicPosition basicPosition;
    private Map<Point, Direction> directionMap;
    private final FigureRepository figureRepository;

    @Autowired
    public BoardService(CellRepository cellRepository, BoardRepository boardRepository, FigureRepository figureRepository) {
        this.cellRepository = cellRepository;
        this.boardRepository = boardRepository;
        this.figureRepository = figureRepository;
    }

    @Autowired
    public void setDirectionMap(Map<Point, Direction> directionMap) {
        this.directionMap = directionMap;
    }

    @Autowired
    public void setBasicPosition(BasicPosition basicPosition) {
        this.basicPosition = basicPosition;
    }

    public Board createBoard(Map<Player, Direction> playerDirectionMap){
        Board board = new Board();
        boardRepository.save(board);
        NodeCell[][] nodeCells = new NodeCell[10][10];
        for(int i = 0; i < nodeCells.length; i++){
            for(int j = 0; j < nodeCells[i].length; j++){
                NodeCell n = new NodeCell();
                nodeCells[i][j] = n;
                n.setConnections(new ArrayList<>());
                n.setCellType(getTypeFor(i, j));
                n.setHouse(n.getCellType() != CellType.Middle && n.getCellType() != CellType.Center);
                n.setBase(false);
                n.setFigureId(-1L);
                n.setBoardId(board.getId());
            }
        }
        createConnections(nodeCells);
        Map<Long, Figure> map = setFigures(board, playerDirectionMap, nodeCells);
        cellRepository.save(nodeCells[0][0]);
        createMaps(nodeCells, board, map);
        return board;
    }

    private CellType getTypeFor(int i, int j){
        if(i < 4 && j < 4) return CellType.LU_House;
        else if(i < 4 && j > 4)return CellType.RU_House;
        else if(i > 5 && j < 4) return CellType.LD_House;
        else if(i > 5 && j > 5) return CellType.RD_House;
        else if(i > 4 && j > 4 && i < 6 && j < 6) return CellType.Center;
        return CellType.Middle;
    }

    private boolean can(int i, int j, NodeCell[][] cells){
        return i >= 0 && j >= 0 && i < cells.length && j < cells[i].length && cells[i][j] != null;
    }

    private void createConnections(NodeCell[][] nodeCells){
        for(int i = 0; i < nodeCells.length; i++){
            for(int j = 0; j < nodeCells[i].length; j++){
                NodeCell n = nodeCells[i][j];
                for(var l : directionMap.entrySet()){
                    if(can(i+l.getKey().getX(), j+l.getKey().getY(), nodeCells)){
                        CellConnection cs = new CellConnection();
                        NodeCell target = nodeCells[i+l.getKey().getX()][j+l.getKey().getY()];
                        cs.setEnd(target);
                        cs.setDirection(l.getValue());
                        n.getConnections().add(cs);
                        n.getConnections().add(cs);
                    }
                }
            }
        }
    }

    private Map<Long, Figure> setFigures(Board board, Map<Player, Direction> playerDirectionMap, NodeCell[][] cells){
        Map<Figure, Player> figurePlayerMap = new HashMap<>();
        Map<Long, Figure> idFigureMap = new HashMap<>();
        Map<Player, Color> playerColorMap= createPlayerColorMap(playerDirectionMap.keySet());
        playerDirectionMap.forEach((key, value) -> basicPosition.getBasicPositionFor(value).forEach((point, figureType) ->{
            Figure figure = new Figure();
            figure.setMyType(figureType);
            figure.setColor(playerColorMap.get(key));
            figureRepository.save(figure);
            NodeCell need = cells[point.getY()][point.getX()];
            need.setBase(true);
            need.setFigureId(figure.getId());
            figurePlayerMap.put(figure, key);
            idFigureMap.put(figure.getId(), figure);
        }));
        board.setFigurePlayerMap(figurePlayerMap);
        return idFigureMap;
    }

        private Map<Player, Color> createPlayerColorMap(Set<Player> players){
            List<Color> colors = new ArrayList<>(List.of(Color.values()));
            Map<Player, Color> answer = new HashMap<>();
            Collections.shuffle(colors);
            int count = 0;
            for(Player p : players){
                answer.put(p, colors.get(count));
                count++;
            }
            return answer;
        }

    private void createMaps(NodeCell[][] cells, Board board, Map<Long, Figure> idFigureMap) {
        Map<Figure, Long> figureIntegerMap = new HashMap<>();
        Map<Long, Figure> integerFigureMap = new HashMap<>();
        Map<String, Long> positionCellMap = new HashMap<>();
        Map<Long, String> cellPositionMap = new HashMap<>();
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                NodeCell curr = cells[i][j];
                if(curr.getFigureId() >= 0){
                    Figure f = idFigureMap.get(curr.getFigureId());
                    figureIntegerMap.put(f, curr.getId());
                    integerFigureMap.put(curr.getId(), f);
                }else{
                    integerFigureMap.put(curr.getFigureId(), null);
                }
                positionCellMap.put(i+""+j, curr.getId());
                cellPositionMap.put(curr.getId(), i+""+j);

            }
        }
        board.setFigureCellIdMap(figureIntegerMap);
        board.setCellIdFigureMap(integerFigureMap);
        board.setPositionCellMap(positionCellMap);
        board.setCellPositionMapping(cellPositionMap);
    }



}
