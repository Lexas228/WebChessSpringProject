package ru.vsu.chess.controller.playercontroller;

import org.springframework.stereotype.Controller;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.model.game.Move;
import ru.vsu.chess.model.cell.Cell;
import ru.vsu.chess.model.game.Direction;
import ru.vsu.chess.model.player.Player;

import java.util.Scanner;
@Controller
public class HumanController implements PlayerController {
    @Override
    public Move getMove(Game game, Player forWho) {
        System.out.println("Enter move (Example: 0122): ");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int x = Integer.parseInt(Character.toString(s.charAt(0)));
        int y = Integer.parseInt(Character.toString(s.charAt(1)));
        Cell chosen = findCell(x, y, game.getLeftUpCell());
        int x1 = Integer.parseInt(Character.toString(s.charAt(2)));
        int y1 = Integer.parseInt(Character.toString(s.charAt(3)));
        Cell to = findCell(x1, y1, game.getLeftUpCell());
        return new Move(chosen, to);
    }

    private Cell findCell(int x, int y, Cell leftUp){
        int k = 0;
        Cell need = leftUp;
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
}
