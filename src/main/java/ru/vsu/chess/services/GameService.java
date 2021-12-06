package ru.vsu.chess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.chess.components.convector.MoveConvector;
import ru.vsu.chess.components.movechecker.MoveValidator;
import ru.vsu.chess.model.dto.Move;
import ru.vsu.chess.model.dto.MoveDto;
import ru.vsu.chess.model.entity.*;
import ru.vsu.chess.model.node.NodeCell;
import ru.vsu.chess.repository.jpa.GameRepository;
import ru.vsu.chess.repository.jpa.PlayerRepository;
import ru.vsu.chess.repository.neo4j.CellRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private Map<PlayerType, MoveValidator> playerMoveValidatorMap;
    private Map<PlayerType, PlayerService> playerServiceMap;
    private final BoardService boardService;
    private Map<PlayerType, MoveConvector> moveConvectorsMap;
    private CellRepository cellRepository;


    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository, BoardService boardService){
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.boardService = boardService;
    }

    @Autowired
    public void setPlayerServiceMap(Map<PlayerType, PlayerService> playerServiceMap) {
        this.playerServiceMap = playerServiceMap;
    }

    @Autowired
    public void setCellRepository(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }
    @Autowired
    public void setMoveConvectorsMap(Map<PlayerType, MoveConvector> moveConvectorsMap) {
        this.moveConvectorsMap = moveConvectorsMap;
    }

    @Autowired
    public void setPlayerMoveValidatorMap(Map<PlayerType, MoveValidator> playerMoveValidatorMap) {
        this.playerMoveValidatorMap = playerMoveValidatorMap;
    }


    public Game createGameWithBot(Long playerId){
        Map<Player, Direction> playerDirectionMap = new HashMap<>();
        Player player = playerRepository.getById(playerId);
        Game game = new Game();
        Player bot = new Player();
        bot.setMyType(PlayerType.Bot);
        Map<Player, Player> enemyMap = new HashMap<>();
        playerDirectionMap.put(player, Direction.SOUTH);
        playerDirectionMap.put(bot, Direction.NORTH);
        enemyMap.put(player, bot);
        enemyMap.put(bot, player);
        game.setActivePlayer(player);
        game.setEnemyMap(enemyMap);
        game.setBoard(boardService.createBoard(playerDirectionMap));
        game.setGameStatus(GameStatus.OK);
        game.setPlayerDirectionMap(playerDirectionMap);
        gameRepository.save(game);
        sendResult(game);
        return game;
    }

    public Game createGameBotVsBot(){
        Player player = new Player();
        player.setMyType(PlayerType.Bot);
        playerRepository.save(player);
        return createGameWithBot(player.getId());
    }




    @Transactional
    public void doMove(MoveDto dtoMove){
        Optional<Game> og = gameRepository.findById(dtoMove.getGameId());
        if(og.isPresent()){
            Game game = og.get();
            Optional<Player> player = playerRepository.findById(dtoMove.getPlayerId());
            if(player.isPresent()){
                Player p = player.get();
                if(game.getActivePlayer() == p){
                    MoveValidator mv = playerMoveValidatorMap.get(p.getMyType());
                    MoveConvector need = moveConvectorsMap.get(p.getMyType());
                    Board b = game.getBoard();
                    Move m = need.toEntity(dtoMove, game, p);
                    if(mv.canDoThisMove(m, p, game)){
                        doMove(m, p, game);
                        updateStatus(game);
                        game.setActivePlayer(game.getEnemyMap().get(game.getActivePlayer()));
                        System.out.println("move was done, now active player is " + game.getActivePlayer().getId());
                        sendResult(game);
                    }
                }
            }
        }
    }

    private void sendResult(Game game){
        game.getEnemyMap().keySet().forEach(player -> {
            PlayerService pl = playerServiceMap.get(player.getMyType());
            pl.notification(player, game);
        });
    }

    private void doMove(Move move, Game game){
        cellRepository.doMove(move.from(), move.to());
        Board board = game.getBoard();
        Figure active = board.getCellIdFigureMap().get(move.from());
        board.getFigurePlayerMap().put(active, null);
        board.getCellIdFigureMap().put(move.to(), active);
        board.getCellIdFigureMap().put(move.from(), null);
        board.getFigureCellIdMap().put(active, move.to());
    }

    private void updateStatus(Game game){
        Board board = new Board();
        List<Player> players = new ArrayList<>();
        game.getEnemyMap().keySet().forEach(player -> {
            List<Figure> galas = board.getFigurePlayerMap().entrySet().stream()
                    .filter((entry)-> entry.getKey().getMyType() == FigureType.GALA && Objects.equals(entry.getValue(), player))
                    .map(Map.Entry::getKey).collect(Collectors.toList());
            if(galas.isEmpty())game.setGameStatus(GameStatus.END);
            else{
                if(galas.size() > 1){
                   long count = galas.stream().filter(figure -> {
                        Long cellId = board.getFigureCellIdMap().get(figure);
                        if(cellId != null && cellId >= 0){
                            Optional<NodeCell> cell = cellRepository.findById(cellId);
                            if(cell.isPresent()){
                                NodeCell nodeCell = cell.get();
                                return nodeCell.getCellType() == CellType.Middle;
                            }
                        }
                        return false;
                    }).count();
                   if(count > 1) game.setGameStatus(GameStatus.END);
                }else{
                    players.add(player);
                }
            }
        });
        if(players.size() >= game.getEnemyMap().keySet().size()){
            game.setGameStatus(GameStatus.DRAW);
        }
    }




}
