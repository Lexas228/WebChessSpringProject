package ru.vsu.chess.services.figureservices;

import org.springframework.stereotype.Service;
import ru.vsu.chess.model.entity.*;
import ru.vsu.chess.model.node.NodeCell;

import java.util.*;
import java.util.stream.Collectors;

/*
Galas move in general as kings in chess. Additionally, when a Gala is on one
of the four squares in the middle of the board, it may be moved in one move
to any empty square on the board with the exception of those squares that
contain a piece in the opening setup. Kings can only take when moving over
a deflection line.
 */

@Service
public class GalaService extends AbstractFigureService{
    @Override
    public Set<Long> getAvailableMoves(Long id, Game game, Player forWho) {
        Board board = game.getBoard();
        Optional<NodeCell> cell = cellRepository.findById(id);
        if(cell.isPresent()){
            NodeCell c = cell.get();
            if(c.getCellType() == CellType.Center){
                return cellRepository.getAllJumps(board.getId()).stream().map(NodeCell::getId).collect(Collectors.toSet());
            }else{
                return cellRepository.getAllAround(id).stream()
                        .filter(nodeCell -> {
                            if(nodeCell.getFigureId() < 0) return true;
                            return canBeatIt(c, nodeCell, board, forWho);
                        }).map(NodeCell::getId).collect(Collectors.toSet());
            }
        }
        return null;
    }

    @Override
    public FigureType getType() {
        return FigureType.GALA;
    }
}
