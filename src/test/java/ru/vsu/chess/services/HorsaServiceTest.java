package ru.vsu.chess.services;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.repository.jpa.GameRepository;
import ru.vsu.chess.repository.jpa.PlayerRepository;
import ru.vsu.chess.repository.neo4j.CellRepository;
import ru.vsu.chess.services.figureservices.HorsaKornaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HorsaServiceTest {

    @Autowired
    private HorsaKornaService horsaService;


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CellRepository cellRepository;

    @Test
    public void availableMovesTest(){
        Game game = gameRepository.getById(1L);
        Player player = playerRepository.getById(1L);
        System.out.println("start");
        horsaService.getAvailableMoves(94L, game, player).forEach(id ->{
            System.out.println(id + " ");
        });
        System.out.println("end");
    }
}
