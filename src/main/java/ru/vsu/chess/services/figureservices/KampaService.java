package ru.vsu.chess.services.figureservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.chess.model.entity.*;
import ru.vsu.chess.model.node.NodeCell;

import java.util.*;
import java.util.stream.Collectors;

/*
Kampas move diagonally towards the middle of the board until they are in the
middle part or in the opponents corners. It is allowed to move two squares
if the deflection line isn't passed with the first move of the Kampa.
When the Kampa is in the middle part or in one of the opponents corners,
then it may move one square in any direction. When a Kampa is moved back
to a corner of the owning player, it can only move diagonally forward again.
Kampas only can take in the move after they have crossed a deflection line.
Kampa can't move to "nobile" squares.
 */

@Service
public class KampaService extends AbstractFigureService{
    private Map<Direction, Set<CellType>> map;
    private Map<CellType, Direction> helpMap;

    @Autowired
    public void setHelpMap(Map<CellType, Direction> helpMap) {
        this.helpMap = helpMap;
    }

    @Autowired
    public void setMap(Map<Direction, Set<CellType>> map) {
        this.map = map;
    }

    @Override
    public Set<Long> getAvailableMoves(Long id, Game game, Player forWho) {
        Optional<NodeCell> node = cellRepository.findById(id);
        Board board = game.getBoard();
        Direction dir = game.getPlayerDirectionMap().get(forWho);
        Set<CellType> cellTypes = map.get(dir);
        if(node.isPresent()){
            NodeCell nodeCell = node.get();
            if(!nodeCell.isHouse() || !cellTypes.contains(nodeCell.getCellType())){
                return cellRepository.getAllAround(id).stream().filter(nc ->{
                   if(nc.getCellType() == CellType.Center) return false;
                   if(nc.getFigureId() >= 0 && canBeatIt(nodeCell, nc, board, forWho)) return true;
                   return nc.getFigureId() < 0;
                }).map(NodeCell::getId).collect(Collectors.toSet());
            }else{
                Set<Long> answer = new HashSet<>();
                Direction direction = helpMap.get(nodeCell.getCellType());
                List<NodeCell> nodeCells = cellRepository.getSomeToDirection(id, direction, 2);
                for(int i = 1; i < nodeCells.size(); i++){
                    NodeCell curr = nodeCells.get(i);
                    if(curr.isHouse() != nodeCell.isHouse()){
                        if(curr.getFigureId() < 0 || canBeatIt(nodeCell, curr, board, forWho)){
                            answer.add(curr.getId());
                            break;
                        }
                    }
                    if(curr.getFigureId() > 0 ) break;
                    answer.add(curr.getId());
                }
                return answer;
            }
        }
        return null;
    }

    @Override
    public FigureType getType() {
        return FigureType.KAMPA;
    }
}
