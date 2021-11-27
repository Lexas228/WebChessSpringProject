package ru.vsu.chess.components;

import org.springframework.stereotype.Component;
import ru.vsu.chess.model.Player;
import ru.vsu.chess.model.game.Direction;

import java.util.*;

@Component
public class DirectionHelper {

    private Map<Direction, Direction> oppositeDirections;
    public DirectionHelper(){
        oppositeDirections = Map.of(
                Direction.NORTH, Direction.SOUTH,
                Direction.SOUTH, Direction.NORTH,
                Direction.NORTH_WEST, Direction.SOUTH_EAST,
                Direction.SOUTH_EAST, Direction.NORTH_WEST,
                Direction.WEST, Direction.EAST,
                Direction.EAST, Direction.WEST,
                Direction.SOUTH_WEST, Direction.NORTH_EAST,
                Direction.NORTH_EAST, Direction.SOUTH_WEST
        );
    }

    public List<Direction> getSimpleDirections(){
        List<Direction> res = new ArrayList<>();
        res.add(Direction.WEST);
        res.add(Direction.EAST);
        res.add(Direction.SOUTH);
        res.add(Direction.NORTH);
        return res;
    }

    public List<Direction> getCrossedDirections(){
        List<Direction> res = new ArrayList<>();
        res.add(Direction.NORTH_EAST);
        res.add(Direction.NORTH_WEST);
        res.add(Direction.SOUTH_EAST);
        res.add(Direction.SOUTH_WEST);
        return res;
    }

    public List<Direction> listWith(Direction dr){
        List<Direction> res = new ArrayList<>();
        res.add(dr);
        return res;
    }

    public void shuffle(Queue<Player> players){
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
