package ru.vsu.chess.services;

import ru.vsu.chess.model.node.NodeCell;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.entity.PlayerType;
import ru.vsu.chess.model.entity.Direction;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.entity.Move;

import java.util.Scanner;

public class HumanService implements PlayerService{
    @Override
    public Move getMove(Game game, Player forWho) {
        System.out.println("Enter move (Example: 0122): ");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int x = Integer.parseInt(Character.toString(s.charAt(0)));
        int y = Integer.parseInt(Character.toString(s.charAt(1)));
        NodeCell chosen = findCell(x, y, game.getLeftUpCell());
        int x1 = Integer.parseInt(Character.toString(s.charAt(2)));
        int y1 = Integer.parseInt(Character.toString(s.charAt(3)));
        NodeCell to = findCell(x1, y1, game.getLeftUpCell());
        return new Move(chosen, to);
    }

    private NodeCell findCell(int x, int y, NodeCell leftUp){
        int k = 0;
        NodeCell need = leftUp;
        while(k < x){
            need = need.getCells().get(Direction.EAST);
            k++;
        }
        int s = 0;
        while(s < y){
            need = need.getCells().get(Direction.SOUTH);
            s++;
        }
        return need;
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.Human;
    }
}
