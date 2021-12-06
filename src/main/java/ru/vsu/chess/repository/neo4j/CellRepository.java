package ru.vsu.chess.repository.neo4j;

import org.neo4j.driver.internal.shaded.reactor.core.publisher.Flux;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import ru.vsu.chess.model.entity.Direction;
import ru.vsu.chess.model.node.NodeCell;

import java.util.List;


@Repository
public interface CellRepository extends Neo4jRepository<NodeCell, Long> {

    @Query("MATCH (c:NodeCell) -[]-> (a:NodeCell) where id(c)=$id  return a")
    List<NodeCell> getAllAround(Long id);

    @Query("Match (c:NodeCell) where c.boardId=$boardId and c.isBase=false and c.figureId=-1 return c")
    List<NodeCell> getAllJumps(Long boardId);

    @Query("Match (f:NodeCell) where id(f)=$from " +
            "Match(t:NodeCell) where id(t)=$to " +
            "set t.figureId=f.figureId, f.figureId=-1")
    void doMove(Long from, Long to);

    @Query("match (start:NodeCell) where id(start)=$id " +
            "match (end:NodeCell) where id(end)<>id(start) and (end.figureId<>-1 or end.isHouse<>start.isHouse) " +
            "match path=shortestPath((start)-[:Connected*..10]-(end)) where all(x in relationships(path) where x.DIRECTION=$direction) " +
            "and all(n in nodes(path) where id(n)=id(start) or id(n)=id(end) or (n.figureId=-1 and n.isHouse=start.isHouse)) " +
            "unwind nodes(path) as nod " +
            "return collect(DISTINCT nod); ")
    List<NodeCell> getCellsToDirBeforeCrossing(Direction direction, Long id);


    @Query("match (start:NodeCell) where id(start)=$id " +
            "match (end:NodeCell) where id(end)<>id(start) and end.isHouse=start.isHouse " +
            "match path=(start)-[:Connected*]-(end) where all(x in relationships(path) where x.DIRECTION=$direction) " +
            "and all(n in nodes(path) where n.isHouse=start.isHouse and (id(n)=id(end) or n.figureId=-1)) " +
            "and length(path) <= $limit " +
            "unwind nodes(path) as nod " +
            "return collect(DISTINCT nod);")
    List<NodeCell> getCellsToDirAfterCrossing(Direction direction, Long id, int limit);


    @Query("match (start:NodeCell) where id(start)=$id "+
            "match (end:NodeCell) where id(end)<>id(start) and end.boardId=start.boardId " +
            "match path=shortestPath((start)-[:Connected*]->(end)) where all(x in relationships(path) where x.DIRECTION=$direction) and " +
            "length(path) <= $limit " +
            "unwind nodes(path) as n " +
            "return collect(distinct n) ")
    List<NodeCell> getSomeToDirection(Long id, Direction direction, int limit);

    @Query("match (c:NodeCell) where id(c)=$cellId set c.figureId=$figureId return c")
    List<NodeCell> setFigureId(Long cellId, Long figureId);

    @Query("match (start:NodeCell) where id(start)=$start " +
            "match (end:NodeCell) where id(end)=$end " +
            "match p=shortestPath((start)-[:Connected*0..10]->(end)) " +
            "return length(p);")
    Integer findDistance(Long start, Long end);
}
