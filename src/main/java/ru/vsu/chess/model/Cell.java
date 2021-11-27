package ru.vsu.chess.model;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.typeconversion.EnumArrayStringConverter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import ru.vsu.chess.model.game.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@NodeEntity
@Data
public class Cell {

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "Connected")
    private List<CellConnection> connections;

    @Relationship(type = "HAS")
    private Figure figure;

    @Convert(EnumArrayStringConverter.class)
    private Set<CellType> myTypes;


}
