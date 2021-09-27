package ru.vsu.chess.services.gameservice;

import org.springframework.stereotype.Service;
import ru.vsu.chess.controller.Updater;
import ru.vsu.chess.helper.Helper;
import ru.vsu.chess.model.cell.Cell;
import ru.vsu.chess.model.cell.CellType;
import ru.vsu.chess.model.figure.Figure;
import ru.vsu.chess.model.figure.FigureType;
import ru.vsu.chess.model.game.*;
import ru.vsu.chess.model.player.Player;
import ru.vsu.chess.controller.playercontroller.PlayerController;
import ru.vsu.chess.services.movechecker.MoveValidator;
import ru.vsu.chess.model.others.*;

import java.util.*;
@Service
public class GameService {

    public void connectPlayer(Game game, Player player, PlayerController playerService, MoveValidator validator){
        game.getPlayers().add(player);
        game.getPlayerServiceMap().put(player, playerService);
        game.getPlayerMoveValidatorMap().put(player, validator);
    }

    public void startGame(Game game, int numOfStep, Updater updater, BasicPosition position){
        Cell leftUp = new Cell();
        //creating and setSettings
        creatingBoard(leftUp);
        setGameSettings(leftUp, game, position);
        doOtherSettings(game);
        updater.update(game);
        Helper.shuffle(game.getPlayers());
        int step = 0;
        //actually main logic of game
        while(game.getStatus() == GameStatus.OK && step < numOfStep){
            Queue<Player> players = game.getPlayers();
            Player curr = players.poll();
            PlayerController ps = game.getPlayerServiceMap().get(curr);
            MoveValidator mv = game.getPlayerMoveValidatorMap().get(curr);
            Move m;
            do{
                 m = ps.getMove(game, curr);
            }while(!mv.canDoThisMove(m, curr, game));
            doStep(m, game, curr);
            players.add(curr);
            updateStatus(game);
            updater.update(game);
            step++;
        }

    }


    private void doStep(Move m, Game game, Player curr){
        Figure bittenFigure = game.getCellFigure().get(m.getTo());
        Figure chosenFigure = game.getCellFigure().get(m.getFrom());
        if(bittenFigure != null){
            game.getFigureCell().put(bittenFigure, null);
            for(Player pl : game.getPlayerFigures().keySet()){
                if(game.getPlayerFigures().get(pl).contains(bittenFigure)){
                    game.getPlayerFigures().get(pl).remove(bittenFigure);
                    break;
                }
            }
        }
        game.getFigureCell().put(chosenFigure, m.getTo());
        game.getCellFigure().put(m.getTo(), chosenFigure);
        game.getCellFigure().put(m.getFrom(), null);
        game.getSteps().add(new Step(m, curr, bittenFigure));
    }

    private void creatingBoard(Cell leftUp){
        List<Cell> up = new ArrayList<>();
        up.add(leftUp);
        Cell prev = leftUp;
        for(int i = 1; i <= 9; i++){
            Cell c = new Cell();
            prev.getCells().put(Direction.EAST, c);
            c.getCells().put(Direction.WEST, prev);
            prev = c;
            up.add(c);
        }
        for(int i = 1; i <= 9; i++){
            List<Cell> u = new ArrayList<>();
            for(int j = 0; j <= 9; j++){
                Cell c = new Cell();
                c.getCells().put(Direction.NORTH, up.get(j));
                up.get(j).getCells().put(Direction.SOUTH, c);
                if(j >= 1) {
                    c.getCells().put(Direction.NORTH_WEST, up.get(j-1));
                    up.get(j-1).getCells().put(Direction.SOUTH_EAST, c);
                    c.getCells().put(Direction.WEST, prev);
                    prev.getCells().put(Direction.EAST, c);
                }
                if(j+1 <= 9){
                    c.getCells().put(Direction.NORTH_EAST, up.get(j+1));
                    up.get(j+1).getCells().put(Direction.SOUTH_WEST, c);
                }
                u.add(c);
                prev = c;
            }
            up = u;
        }
    }

    private void doOtherSettings(Game game){ //just for separating of settings. Here we will set directions for every CellType
        //okey.. lets start
        Map<CellType, Map<FigureType, List<Direction>>> res = new HashMap<>();
        Map<FigureType, List<Direction>> one = new HashMap<>();
        //for houses
        one.put(FigureType.HORSA, Helper.getCrossedDirections());
        one.put(FigureType.KORNA, Helper.getSimpleDirections());
        one.put(FigureType.GALA, Arrays.stream(Direction.values()).toList());
        one.put(FigureType.KAMPA, Helper.listWith(Direction.NORTH_EAST));
        res.put(CellType.RD_House, one);
        Map<FigureType, List<Direction>> two = new HashMap<>(one);
        two.put(FigureType.KAMPA, Helper.listWith(Direction.NORTH_WEST));
        res.put(CellType.LD_House, two);
        Map<FigureType, List<Direction>> three = new HashMap<>(one);
        three.put(FigureType.KAMPA, Helper.listWith(Direction.SOUTH_WEST));
        res.put(CellType.RU_House, three);
        Map<FigureType, List<Direction>> four = new HashMap<>(three);
        four.put(FigureType.KAMPA, Helper.listWith(Direction.SOUTH_EAST));
        res.put(CellType.LU_House, four);
        //for middle and center
        Map<FigureType, List<Direction>> five = new HashMap<>();
        five.put(FigureType.HORSA, Helper.getSimpleDirections());
        five.put(FigureType.KORNA, Helper.getCrossedDirections());
        five.put(FigureType.GALA, Arrays.stream(Direction.values()).toList());
        five.put(FigureType.KAMPA, Arrays.stream(Direction.values()).toList());
        res.put(CellType.Middle, five);
        res.put(CellType.Center, five);
        game.setFiguresDirections(res);

        //also let's set galas helper

        Map<CellType, Set<CellType>> galas = new HashMap<>();
        Set<CellType> l = new HashSet<>();
        l.add(CellType.LU_House);
        l.add(CellType.RU_House);
        galas.put(CellType.LU_House, l);
        galas.put(CellType.RU_House, l);

        Set<CellType> r = new HashSet<>();
        r.add(CellType.RD_House);
        r.add(CellType.LD_House);
        galas.put(CellType.RD_House, r);
        galas.put(CellType.LD_House, r);

        game.setGalasHomeHelper(galas);
    }


    private void setGameSettings(Cell leftUp, Game game, BasicPosition position){
        List<Player> pl = new ArrayList<>(game.getPlayers());
        Player one = pl.get(0);
        Player two = pl.get(1);
        Map<Cell, CellType> cells = new HashMap<>();
        List<Figure> figureForPlayerOne = new ArrayList<>();
        List<Figure> figureForPlayerTwo = new ArrayList<>();
        Map<Cell, Figure> cellFigureMap = new HashMap<>();
        Map<Figure, Cell> figureCellMap = new HashMap<>();
        Map<Player, List<Figure>> playerSetMap = new HashMap<>();
        playerSetMap.put(one, figureForPlayerOne);
        playerSetMap.put(two, figureForPlayerTwo);
        Figure f = new Figure(FigureType.GALA);
        figureForPlayerOne.add(f);
        cellFigureMap.put(leftUp, f);
        cells.put(leftUp, CellType.LU_House);
        figureCellMap.put(f, leftUp);
        Cell cop = leftUp;
        for(int i = 0; i < 10; i++){
            Cell cl = cop;
            for(int j = 0; j < 10; j++){
                if(i < 5){
                    FigureType ft = position.getBasicUp().getOrDefault(new Point(j, i), null);
                    if(ft != null){
                        Figure ftp = new Figure(ft);
                        figureForPlayerOne.add(ftp);
                        cellFigureMap.put(cl, ftp);
                        figureCellMap.put(ftp, cl);
                    }else{
                        cellFigureMap.put(cl, null);
                    }
                }else {
                    FigureType ft = position.getBasicDown().getOrDefault(new Point(j, i), null);
                    if (ft != null) {
                        Figure ftp = new Figure(ft);
                        figureForPlayerTwo.add(ftp);
                        cellFigureMap.put(cl, ftp);
                        figureCellMap.put(ftp, cl);
                    } else {
                        cellFigureMap.put(cl, null);
                    }
                }
                CellType tp = CellType.Middle;
                if(j <= 3 && i <= 3) tp = CellType.LU_House;
                else if(j > 5 && i <= 3) tp = CellType.RU_House;
                else if(j <= 3 && i > 5) tp = CellType.LD_House;
                else if(j > 5 && i > 5) tp = CellType.RD_House;
                else{
                    if(i > 3 && j > 3 && i < 6 && j < 6) tp = CellType.Center;
                }
                cells.put(cl, tp);
                cl = cl.getCells().get(Direction.EAST);
            }
            cop = cop.getCells().get(Direction.SOUTH);
        }
        game.setCells(cells);
        game.setLeftUpCell(leftUp);
        game.setBasicCellFigurePosition(cellFigureMap);
        game.setBasicFigureCellPosition(figureCellMap);
        game.setCellFigure(new HashMap<>(cellFigureMap));
        game.setFigureCell(new HashMap<>(figureCellMap));
        game.setPlayerFigures(playerSetMap);
        Map<FigureType, String> forOne = new HashMap<>();
        forOne.put(FigureType.HORSA, " ♗ ");
        forOne.put(FigureType.KAMPA, " ♙ ");
        forOne.put(FigureType.GALA, " ♔ ");
        forOne.put(FigureType.KORNA, " ♖ ");
        Map<FigureType, String> forTwo = new HashMap<>();
        forTwo.put(FigureType.HORSA, " ♝ ");
        forTwo.put(FigureType.KAMPA, " ♟ ");
        forTwo.put(FigureType.GALA, " ♚ " );
        forTwo.put(FigureType.KORNA, " ♜ ");
        game.getVisualFigure().put(one, forOne);
        game.getVisualFigure().put(two, forTwo);
    }

    private void updateStatus(Game game){
        List<Player> players = new ArrayList<>(game.getPlayers());
        List<Integer> ints = new ArrayList<>();
        for(Player pl : players){
            List<Figure> list = new ArrayList<>();
            for(Figure f : game.getPlayerFigures().get(pl)){
                if(f.getMyType() == FigureType.GALA){
                    list.add(f);
                }
            }
            if(list.isEmpty()){
                game.setStatus(GameStatus.END);
                return;
            }
            if(list.size() >= 2 && allInCenter(list, game)){
                game.setStatus(GameStatus.END);
                return;
            }
            int size = list.size();
            System.out.print(size + " ");
            ints.add(size);
        }
        System.out.println();
        for(Integer k : ints){
            if(k != 1)return;
        }
        game.setStatus(GameStatus.DRAW);
    }

    private boolean allInCenter(List<Figure> figure, Game game){
        for(Figure f : figure){
            Cell c = game.getFigureCell().get(f);
            if(f == null || game.getCells().get(c) != CellType.Center) return false;
        }
        return true;
    }
}
