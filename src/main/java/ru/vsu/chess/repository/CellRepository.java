package ru.vsu.chess.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import ru.vsu.chess.model.node.NodeCell;

import java.util.List;

public interface CellRepository extends Neo4jRepository<NodeCell, Long> {

    @Query("Match")
    List<NodeCell> getByDirectionsAndLimit();
}
