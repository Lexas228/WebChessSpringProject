package ru.vsu.chess.model.node;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.util.List;

@NodeEntity
@Data
public class NodeCell {

    @Id
    @GeneratedValue
    private Long id;

    private List<CellConnection> connections;
}
