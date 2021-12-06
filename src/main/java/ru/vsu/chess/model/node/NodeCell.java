package ru.vsu.chess.model.node;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
import ru.vsu.chess.model.entity.CellType;

import java.util.List;
import java.util.Objects;

@Node
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NodeCell {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(value = "Connected", direction = Relationship.Direction.OUTGOING)
    private List<CellConnection> connections;

    @Property(name = "figureId")
    private Long figureId;

    @Property(name = "boardId")
    private Long boardId;

    @Property(name = "type")
    private CellType cellType;

    @Property(name = "isHouse")
    private boolean isHouse;

    @Property(name = "isBase")
    private boolean isBase;

    @Override
    public int hashCode() {
        return Objects.hash(id, figureId, cellType, isBase, isHouse);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        NodeCell nc = (NodeCell)obj;
        return id != null && Objects.equals(id, nc.id);
    }
}
