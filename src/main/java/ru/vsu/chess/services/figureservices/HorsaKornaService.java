package ru.vsu.chess.services.figureservices;

import org.w3c.dom.Node;
import ru.vsu.chess.model.entity.*;
import ru.vsu.chess.model.node.NodeCell;

import java.util.*;

/*
Horsa and Korna do actually very similar things but with opposite sides. As u could read a
notation in horsa and korna services they can move in opposite way at home and in the middle so
I use it to avoid duplicating
 */
public abstract class HorsaKornaService extends AbstractFigureService{

    @Override
    public Set<Long> getAvailableMoves(Long id, Game game, Player forWho) {
        Set<Long> answer = new HashSet<>();
        Optional<NodeCell> from = cellRepository.findById(id);
        if(from.isPresent()){
            NodeCell n = from.get();
            addBeforeCrossing(n, game, forWho, answer);
        }
        return answer;
    }

    private void addBeforeCrossing(NodeCell from, Game game, Player forWho, Set<Long> answer) {
        Board board = game.getBoard();
        List<Direction> directions = from.isHouse() ? getInHome() : getInMiddle();
        for (Direction d : directions) {
            cellRepository.getCellsToDirBeforeCrossing(d, from.getId()).forEach(nodeCell -> {
                if(nodeCell.getFigureId() >= 0){
                    if(canBeatIt(from, nodeCell, board, forWho)){
                        answer.add(nodeCell.getId());
                    }
                }else{
                    if(nodeCell.isHouse() != from.isHouse()){
                    Integer distance = cellRepository.findDistance(from.getId(), nodeCell.getId());
                    addAfterCrossing(nodeCell, game, forWho, answer, distance);
                    }else{
                        answer.add(nodeCell.getId());
                    }
                }
            });
        }
    }

    private void addAfterCrossing(NodeCell from, Game game, Player forWho, Set<Long> answer, Integer numOfMovesBefore){
        int limit = numOfMovesBefore < 2 ? 10 : 1;
        List<Direction> directions = from.isHouse() ? getInHome() : getInMiddle();
        for(Direction d : directions){
            cellRepository.getCellsToDirAfterCrossing(d, from.getId(), limit).stream()
                    .filter(nodeCell -> {
                        if(nodeCell == from) return false;
                        if(nodeCell.getFigureId() < 0) return true;
                        return canBeatAfterCrossing(nodeCell, game.getBoard(), forWho, numOfMovesBefore);
                    }).map(NodeCell::getId).forEach(answer::add);
        }
    }

    protected abstract List<Direction> getInHome();
    protected abstract List<Direction> getInMiddle();
    protected abstract FigureType getMyType();
    protected abstract boolean canBeatAfterCrossing(NodeCell which, Board board, Player forWho, Integer distance);

    @Override
    public FigureType getType() {
        return getMyType();
    }
}
