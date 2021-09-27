package ru.vsu.chess.helper;

import org.springframework.stereotype.Component;
import ru.vsu.chess.model.game.Direction;
import ru.vsu.chess.model.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Helper {

    public static List<Direction> getSimpleDirections(){
        List<Direction> res = new ArrayList<>();
        res.add(Direction.WEST);
        res.add(Direction.EAST);
        res.add(Direction.SOUTH);
        res.add(Direction.NORTH);
        return res;
    }

    public static List<Direction> getCrossedDirections(){
        List<Direction> res = new ArrayList<>();
        res.add(Direction.NORTH_EAST);
        res.add(Direction.NORTH_WEST);
        res.add(Direction.SOUTH_EAST);
        res.add(Direction.SOUTH_WEST);
        return res;
    }

    public static List<Direction> listWith(Direction dr){
        List<Direction> res = new ArrayList<>();
        res.add(dr);
        return res;
    }

    public static void shuffle(Queue<Player> players){
        List<Player> list = new ArrayList<>();
        while(!players.isEmpty()){
            list.add(players.poll());
        }
        Random rnd = new Random();
        while(!list.isEmpty()){
            int s = rnd.nextInt(list.size());
            players.add(list.get(s));
            list.remove(s);
        }
    }
}
