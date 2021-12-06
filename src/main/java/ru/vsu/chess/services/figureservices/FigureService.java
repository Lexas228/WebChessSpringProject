package ru.vsu.chess.services.figureservices;

import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Node;
import ru.vsu.chess.model.entity.FigureType;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.node.NodeCell;

import java.util.List;
import java.util.Set;

public interface FigureService {
    Set<Long> getAvailableMoves(Long id, Game game, Player forWho);
    FigureType getType();
}
