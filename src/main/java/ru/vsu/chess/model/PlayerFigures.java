package ru.vsu.chess.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "Playing")
public class PlayerFigures {

    @StartNode
    private Player player;

    @EndNode
    private Figure figure;
}
