package ru.vsu.chess.repositories;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vsu.chess.model.entity.CellType;
import ru.vsu.chess.model.entity.Direction;
import ru.vsu.chess.model.node.NodeCell;
import ru.vsu.chess.repository.neo4j.CellRepository;

import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CellRepositoryTest {

    @Autowired
    private CellRepository cellRepository;

    @Test
    public void firstTest(){
        cellRepository.setFigureId(94L, -1L);
        cellRepository.getCellsToDirBeforeCrossing(Direction.NORTH_WEST, 94L).forEach(nodeCell -> {
            System.out.print(nodeCell.getId() + " ");
        });
        cellRepository.setFigureId(94L, 18L);
    }

    @Test
    public void distanceTest(){
        Integer l = cellRepository.findDistance(490L, 436L);
        System.out.println(l);
    }

    @Test
    public void getAllJumps(){
        cellRepository.getAllJumps(1L).stream().map(NodeCell::getId).forEach(System.out::println);
    }

    @Test
    public void getAllAround(){
        cellRepository.getAllAround(453L).stream().map(NodeCell::getId).forEach(System.out::println);
    }



}
