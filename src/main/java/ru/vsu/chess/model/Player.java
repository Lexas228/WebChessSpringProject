package ru.vsu.chess.model;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@NodeEntity
@Data
public class Player {
    @Id
    @GeneratedValue
    private Long id;

    private PlayerType myType;
}
