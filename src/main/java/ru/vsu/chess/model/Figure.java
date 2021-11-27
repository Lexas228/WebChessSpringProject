package ru.vsu.chess.model;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.typeconversion.EnumStringConverter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
@NodeEntity
public class Figure {
    @Id
    @GeneratedValue
    private long id;

    @Property("Type")
    @Convert(EnumStringConverter.class)
    private FigureType myType;

}
