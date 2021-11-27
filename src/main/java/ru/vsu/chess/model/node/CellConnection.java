package ru.vsu.chess.model.node;

import lombok.Data;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import ru.vsu.chess.model.entity.Direction;

@RelationshipEntity(type = "CONNECT")
@Data
public class CellConnection {
    @StartNode
    private NodeCell start;

    @EndNode
    private NodeCell end;

    @Property(name = "DIRECTION")
    private Direction direction;

    @Property(name = "LENGTH")
    private Integer length;


}
