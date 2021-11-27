package ru.vsu.chess.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ru.vsu.chess.model.entity.Game;

public interface GameRepository extends Neo4jRepository<Game, Long> {
}
