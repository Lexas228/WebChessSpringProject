package ru.vsu.chess.model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@NodeEntity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String login;
}
