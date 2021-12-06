package ru.vsu.chess.model.node;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
import ru.vsu.chess.model.entity.Direction;

import java.util.Objects;

@RelationshipProperties
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CellConnection {

    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private NodeCell end;

    @Property(name = "DIRECTION")
    private Direction direction;

    @Override
    public int hashCode() {
        return Objects.hash(id, direction, end);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
       CellConnection nc = (CellConnection)obj;
        return id != null && Objects.equals(id, nc.id);
    }


}
