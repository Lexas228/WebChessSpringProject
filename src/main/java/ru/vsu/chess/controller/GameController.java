package ru.vsu.chess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.entity.PlayerType;
import ru.vsu.chess.repository.jpa.PlayerRepository;
import ru.vsu.chess.services.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    private final PlayerRepository playerRepository;

    @Autowired
    public GameController(GameService gameService, PlayerRepository playerRepository) {
        this.gameService = gameService;
        this.playerRepository = playerRepository;
    }

    @GetMapping()
    public void play(){
        Player player = new Player();
        player.setMyType(PlayerType.Human);
        playerRepository.save(player);
        Game game = gameService.createGameWithBot(player.getId());
        //playerRepository.delete(player);
    }
}
