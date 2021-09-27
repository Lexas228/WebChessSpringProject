package ru.vsu.chess.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vsu.chess.model.figure.Figure;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.services.gameservice.GameService;
import ru.vsu.chess.services.movechecker.StandartBotMoveValidator;
import ru.vsu.chess.model.cell.Cell;
import ru.vsu.chess.model.game.Direction;
import ru.vsu.chess.model.cell.CellType;
import ru.vsu.chess.model.player.Bot;
import ru.vsu.chess.model.player.Human;
import ru.vsu.chess.model.player.Player;
import ru.vsu.chess.controller.playercontroller.BotController;
import ru.vsu.chess.spring.SpringConfiguration;

public class Usage {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class
        );
        Game game = Game.createGame();
        GameService gameService = context.getBean("gameService", GameService.class);
        BotController botController = context.getBean("botController", BotController.class);
        StandartBotMoveValidator botMoveValidator = context.getBean("standartBotMoveValidator", StandartBotMoveValidator.class);
        context.close();
        gameService.connectPlayer(game, new Bot("Ai"), botController, botMoveValidator);
        gameService.connectPlayer(game, new Human("Dima"), botController, botMoveValidator);
        gameService.startGame(game, Integer.MAX_VALUE, game1 -> {
            updateTwo(game);
        });

    }

    public static void updateTwo(Game game){
        Cell l = game.getLeftUpCell();
        System.out.println("Game Status: "+ game.getStatus());
        for(int i = 0; i < 10; i++){
            System.out.print("    " + i);
        }
        System.out.println();
        int pl = 0;
        while(l != null){
            Cell k = l;
            Cell copy = k;
            System.out.print("  ");
            for(int i = 0; i < 10; i++){
                String upU = crossed(Direction.NORTH, copy, game) ? "***" : "═══";
                String upL = crossed(Direction.WEST, copy, game) ? "*" : "╒";
                String upR = crossed(Direction.EAST, copy, game) ? "*" : "╕";
                System.out.print(upL + upU + upR);
                copy = copy.getCells().get(Direction.EAST);
            }
            System.out.println();
            System.out.print(pl + " ");
            pl++;
            while(k != null){
                String t = "   ";
                Figure f = game.getCellFigure().get(k);
                if(f != null){
                    t = game.getVisualFigure().get(getOwner(f, game)).get(f.getMyType());
                }
                String before = crossed(Direction.WEST, k, game) ? "*" : "|";
                String after = crossed(Direction.EAST, k, game) ? "*" : "|";
                System.out.print(before + t + after);
                k = k.getCells().get(Direction.EAST);
            }
            System.out.println();
            copy = l;
            System.out.print("  ");
            for(int p = 0; p < 10; p++){
                String downD = crossed(Direction.SOUTH, copy, game) ? "***" :"═══";
                String downL = crossed(Direction.WEST, copy, game) ? "*" : "╘";
                String downR = crossed(Direction.EAST, copy, game) ? "*" : "╛";
                System.out.print(downL + downD + downR);
                copy = copy.getCells().get(Direction.EAST);
            }
            l = l.getCells().get(Direction.SOUTH);
            System.out.println();
        }
    }

    private static Player getOwner(Figure fig, Game game){
        for(var s : game.getPlayerFigures().entrySet()){
            if(s.getValue().contains(fig)) return s.getKey();
        }
        return null;
    }

    private static boolean crossed(Direction dir, Cell curr, Game game){
        Cell need = curr.getCells().get(dir);
        if(need == null) return false;
        CellType one = game.getCells().get(curr);
        CellType two = game.getCells().get(need);
        return one != CellType.Center && one != CellType.Middle && two == CellType.Middle;
    }
}
