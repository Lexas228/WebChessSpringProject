package ru.vsu.chess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.chess.model.Player;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.repository.GameRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public Game createGame(List<Integer> peopleId){
        Game game = new Game();
        Queue<Player> players = new LinkedList<>();

    }


}
