package ru.vsu.chess.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ru.vsu.chess.model.Cell;

public interface CellRepository extends Neo4jRepository<Cell, Long> {
}
