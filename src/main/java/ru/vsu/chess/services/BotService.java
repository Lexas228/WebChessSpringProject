package ru.vsu.chess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.vsu.chess.model.dto.Move;
import ru.vsu.chess.model.dto.MoveDto;
import ru.vsu.chess.model.entity.*;
import ru.vsu.chess.repository.jpa.GameRepository;
import ru.vsu.chess.services.figureservices.FigureService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BotService implements PlayerService{

    private Map<FigureType, FigureService> serviceMap;
    private GameService gameService;
    private GameRepository gameRepository;
    private static final Random rnd = new Random();
    @Autowired @Lazy
    public void setServiceMap(Map<FigureType, FigureService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    @Autowired
    public void setGameService(GameService gameService){
        this.gameService = gameService;
    }

    public void setGameRepository(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    private Move getMove(Game game, Player player){
        Board board = game.getBoard();
       List<Figure> myFigures = getMyFigures(game, player);
       Collections.shuffle(myFigures);
        for (Figure curr : myFigures) {
            Long cellId = board.getFigureCellIdMap().get(curr);
            FigureService figureService = serviceMap.get(curr.getMyType());
            Set<Long> available = figureService.getAvailableMoves(cellId, game, player);
            if (!available.isEmpty()) {
                System.out.println("for " + cellId);
                System.out.println(available);
                int count = 0;
                int max = rnd.nextInt(available.size());
                Long need = null;
                for(Long l : available){
                    if(count >= max){
                        need = l;
                    }
                    count++;
                }
                return new Move(cellId, need);
            }
        }
       return null;
    }

    private List<Figure> getMyFigures(Game game, Player player){
        Board board = game.getBoard();
        return game.getBoard().getFigurePlayerMap().entrySet().stream().filter((entry) -> {
            Figure f = entry.getKey();
            Player p = entry.getValue();
            if(p != player) return false;
            return board.getFigureCellIdMap().get(f) != null;
        }).map(Map.Entry::getKey).collect(Collectors.toList());
    }




    @Override
    public void notification(Player player, Game game) {
        if(game.getActivePlayer() == player){
            Move m = getMove(game, player);
            if(m != null) {
                MoveDto dto = new MoveDto();
                dto.setFrom(Long.toString(m.from()));
                dto.setTo(Long.toString(m.to()));
                dto.setGameId(game.getId());
                dto.setPlayerId(player.getId());
                gameService.doMove(dto);
            }
        }
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.Bot;
    }
}
