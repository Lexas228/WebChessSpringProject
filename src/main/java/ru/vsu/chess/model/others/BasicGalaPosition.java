package ru.vsu.chess.model.others;

import ru.vsu.chess.model.figure.FigureType;
import ru.vsu.chess.model.player.Player;

import java.util.HashMap;
import java.util.Map;

public class BasicGalaPosition implements BasicPosition{

    private final Map<Point, FigureType> basicUp;
    private final Map<Point, FigureType> basicDown;

    public BasicGalaPosition(){
        basicUp = new HashMap<>();
        basicDown = new HashMap<>();
        basicUp.put(new Point(0, 0), FigureType.GALA);
        basicUp.put(new Point(1, 0), FigureType.KORNA);
        basicUp.put(new Point(0, 1), FigureType.KORNA);
        basicUp.put(new Point(2, 0), FigureType.HORSA);
        basicUp.put(new Point(1, 1), FigureType.HORSA);
        basicUp.put(new Point(0, 2), FigureType.HORSA);
        basicUp.put(new Point(3, 0), FigureType.KAMPA);
        basicUp.put(new Point(2, 1), FigureType.KAMPA);
        basicUp.put(new Point(1, 2), FigureType.KAMPA);
        basicUp.put(new Point(0, 3), FigureType.KAMPA);
        //down_right
        basicDown.put(new Point(9, 9), FigureType.GALA);
        basicDown.put(new Point(9, 8), FigureType.KORNA);
        basicDown.put(new Point(8, 9), FigureType.KORNA);
        basicDown.put(new Point(9, 7), FigureType.HORSA);
        basicDown.put(new Point(8, 8), FigureType.HORSA);
        basicDown.put(new Point(7, 9), FigureType.HORSA);
        basicDown.put(new Point(9, 6), FigureType.KAMPA);
        basicDown.put(new Point(8, 7), FigureType.KAMPA);
        basicDown.put(new Point(7, 8), FigureType.KAMPA);
        basicDown.put(new Point(6, 9), FigureType.KAMPA);
        //down_left
        basicDown.put(new Point(0, 9), FigureType.GALA);
        basicDown.put(new Point(0, 8), FigureType.HORSA);
        basicDown.put(new Point(1, 9), FigureType.HORSA);
        basicDown.put(new Point(0, 7), FigureType.KORNA);
        basicDown.put(new Point(1, 8), FigureType.KORNA);
        basicDown.put(new Point(2, 9), FigureType.KORNA);
        basicDown.put(new Point(0, 6), FigureType.KAMPA);
        basicDown.put(new Point(1, 7), FigureType.KAMPA);
        basicDown.put(new Point(2, 8), FigureType.KAMPA);
        basicDown.put(new Point(3, 9), FigureType.KAMPA);
        //up_right
        basicUp.put(new Point(9, 0), FigureType.GALA);
        basicUp.put(new Point(8, 0), FigureType.HORSA);
        basicUp.put(new Point(9, 1), FigureType.HORSA);
        basicUp.put(new Point(7, 0), FigureType.KORNA);
        basicUp.put(new Point(8, 1), FigureType.KORNA);
        basicUp.put(new Point(9, 2), FigureType.KORNA);
        basicUp.put(new Point(6, 0), FigureType.KAMPA);
        basicUp.put(new Point(7, 1), FigureType.KAMPA);
        basicUp.put(new Point(8, 2), FigureType.KAMPA);
        basicUp.put(new Point(9, 3), FigureType.KAMPA);
    }


    public Map<Point, FigureType> getBasicUp(){
        return basicUp;
    }

    public Map<Point, FigureType> getBasicDown(){
        return basicDown;
    }


}
