package ru.vsu.chess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.chess.components.convector.GameConvector;
import ru.vsu.chess.model.dto.FigureDto;
import ru.vsu.chess.model.dto.GameStatusDto;
import ru.vsu.chess.model.dto.MoveDto;
import ru.vsu.chess.model.entity.*;
import ru.vsu.chess.model.node.NodeCell;
import ru.vsu.chess.services.figureservices.FigureService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service
public class HumanService implements PlayerService{
    private GameConvector gameConvector;
    private GameService gameService;
    private Map<FigureType, FigureService> serviceMap;

    @Autowired
    public void setServiceMap(Map<FigureType, FigureService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    private final static Scanner sc = new Scanner(System.in);

    @Autowired
    public void setGameConvector(GameConvector gameConvector) {
        this.gameConvector = gameConvector;
    }

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.Human;
    }

    @Override
    public void notification(Player player, Game game) {
        GameStatusDto gst = gameConvector.toGst(game, player);
        draw(gst);
        while(game.getActivePlayer() == player){
            String from = getFrom();
            showAvailable(from, game, player);
            String to = getTo();
            MoveDto moveDto = new MoveDto();
            moveDto.setPlayerId(player.getId());
            moveDto.setGameId(game.getId());
            moveDto.setFrom(from);
            moveDto.setTo(to);
            gameService.doMove(moveDto);
        }
    }

    private String getFrom(){
        System.out.println("From is: ");
        return sc.next();
    }

    private String getTo(){
        System.out.println("To is: ");
        return sc.next();
    }

    private void draw(GameStatusDto gameStatusDto){
        Map<String, FigureDto> board = gameStatusDto.getState();
        for(int i = 0; i < 10; i++){
            for(int j =0; j < 10; j++){
                FigureDto f = board.get(i+""+j);
                System.out.print(f==null? " " : f+" ");
            }
            System.out.println();
        }
    }

    private void showAvailable(String from, Game game, Player player){
        Long cellId = Long.parseLong(from);
        System.out.println("cell id is " + cellId);
        Board board = game.getBoard();
        Figure figure = board.getCellIdFigureMap().get(cellId);
        while(figure == null){
            from = getFrom();
            cellId = Long.parseLong(from);
            figure = board.getCellIdFigureMap().get(cellId);
        }
        System.out.println(serviceMap.get(figure.getMyType()).getAvailableMoves(cellId, game, player));
    }

}
