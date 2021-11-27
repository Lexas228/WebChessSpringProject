package ru.vsu.chess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.chess.components.MoveConvector;
import ru.vsu.chess.components.movechecker.MoveValidator;
import ru.vsu.chess.dto.MoveDto;
import ru.vsu.chess.model.entity.*;
import ru.vsu.chess.repository.GameRepository;
import ru.vsu.chess.repository.jpa.PlayerRepository;

import java.util.*;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private Map<PlayerType, MoveValidator> playerMoveValidatorMap;
    private MoveConvector moveConvector;


    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository){
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @Autowired
    public void setPlayerMoveValidatorMap(Map<PlayerType, MoveValidator> playerMoveValidatorMap) {
        this.playerMoveValidatorMap = playerMoveValidatorMap;
    }

    @Autowired
    public void setMoveConvector(MoveConvector moveConvector) {
        this.moveConvector = moveConvector;
    }

    public Game createGameWithBot(Long playerId){
        Player player = playerRepository.getById(playerId);
        Game game = new Game();
        Player bot = new Player();
        bot.setMyType(PlayerType.Bot);
        Map<Player, Player> enemyMap = new HashMap<>();
        enemyMap.put(player, bot);
        enemyMap.put(bot, player);
        game.setActivePlayer(player);
        game.setEnemyMap(enemyMap);
        setMaps(game);
    }


    private void setMaps(Game game){
        Map<Figure, Cell> figureCellMap = new HashMap<>();
        Map<Cell, Figure> CellFigureMap = new HashMap<>();
        Cell[][] cells = new Cell[11][11];
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){

            }
        }
    }

    @Transactional
    public boolean doMove(MoveDto dtoMove){
        Optional<Game> og = gameRepository.findById(dtoMove.getGameId());
        if(og.isPresent()){
            Game game = og.get();
            Optional<Player> player = playerRepository.findById(dtoMove.getPlayerId());
            if(player.isPresent()){
                Player p = player.get();
                if(game.getActivePlayer() != p){
                    return false;
                }
                MoveValidator mv = playerMoveValidatorMap.get(p.getMyType());
                Move m = moveConvector.toEntity(dtoMove);
                if(mv.canDoThisMove(m, p, game)){

                }
            }
        }
    }


}
