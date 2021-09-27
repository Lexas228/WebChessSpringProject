package ru.vsu.chess.services.figureservices;

import ru.vsu.chess.model.figure.Figure;
import ru.vsu.chess.model.figure.FigureType;
import ru.vsu.chess.model.game.Game;
import ru.vsu.chess.model.cell.Cell;
import ru.vsu.chess.model.game.Direction;
import ru.vsu.chess.model.player.Player;

import java.util.ArrayList;
import java.util.List;
/*
Horsa and Kornd do actually very similar things but with opposite sides. As u could read a
notation in horsa and korna services they can move in opposite way at home and in the middle so
I use it to avoid duplicating
 */
public abstract class HorsaKornaService implements FigureService{
    private final FigureType figureType;

    public HorsaKornaService(FigureType figureType) {
        this.figureType = figureType;
    }

    @Override
    public List<Cell> getAvailableMoves(Cell from, Game game, Player forWho) {
        List<Cell> answer = new ArrayList<>();
        findAvailableMoves(from, 0, false, answer, game, forWho);
        return answer;
    }

    private void findAvailableMoves(Cell current, int numOfMovesBefore, boolean wasCrossed, List<Cell> available, Game game, Player forWho){
        List<Direction> directions = getMyDirections(current, figureType, game);
        finder(directions, available, current, wasCrossed, numOfMovesBefore, game, forWho);
    }

    private void finder(List<Direction> directions, List<Cell> answer, Cell current, boolean wasCrossed, int numOfMovesBefore, Game game, Player forWho){
        for(Direction dr : directions) {
            Cell nCell = current.getCells().get(dr);
            int tmp = numOfMovesBefore+1;
            while(nCell != null){
                if (!crossed(current, nCell, game) || !wasCrossed) {
                    Figure f = game.getCellFigure().get(nCell);
                    if (f == null || hasEnemyFigure(nCell, game, forWho) && canBeatIt(wasCrossed, numOfMovesBefore)) {
                        answer.add(nCell);
                        if(wasCrossed && numOfMovesBefore > 1) break;
                        if(crossed(current, nCell, game)){
                            findAvailableMoves(nCell, tmp, true, answer, game, forWho);
                            break;
                        }
                    }else{
                        break;
                    }
                }else{
                    break;
                }
                nCell = nCell.getCells().get(dr);
                tmp++;
            }
        }
    }

    protected boolean canBeatIt(boolean wasCrossed, int numOfMovesBefore){
        return wasCrossed;
    }

    @Override
    public FigureType getType() {
        return figureType;
    }
}
