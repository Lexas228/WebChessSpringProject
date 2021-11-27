package ru.vsu.chess.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "Stand")
public class FigureCellConnection {

    @StartNode
    private Cell cell;

    @EndNode
    private Figure figure;
}
