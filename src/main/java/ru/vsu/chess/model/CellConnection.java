package ru.vsu.chess.model;

import lombok.Data;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.neo4j.core.schema.Relationship;
import ru.vsu.chess.model.game.Direction;

@RelationshipEntity(type = "CONNECT")
@Data
public class CellConnection {
    @StartNode
    private Cell start;

    @EndNode
    private Cell end;

    @Property(name = "DIRECTION")
    private Direction direction;

    @Property(name = "LENGTH")
    private Integer length;


}
