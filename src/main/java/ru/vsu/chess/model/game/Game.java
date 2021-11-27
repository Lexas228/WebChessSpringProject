package ru.vsu.chess.model.game;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import ru.vsu.chess.model.Cell;
import ru.vsu.chess.model.Player;
import ru.vsu.chess.model.PlayerFigures;

import java.util.List;
import java.util.Queue;

@Data
@Node
public class Game {

    private Long id;

    //Something like board
    @Relationship(type = "HAS")
    private Cell leftUpCell;

    @Relationship
    private List<PlayerFigures> playerFigures;

    //all players settings
    @Relationship(type = "PLAYED")
    private Queue<Player> players;

    //game properties
    private GameStatus status;

    @Relationship(type = "WAS")
    private List<Step> steps;
}
