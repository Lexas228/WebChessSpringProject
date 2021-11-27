package ru.vsu.chess.services.figureservices;

import org.springframework.stereotype.Service;
import ru.vsu.chess.model.entity.FigureType;

/*
Horsas are just the opposite of Kornas (with an additional small difference
in the taking rules): they move as bishops on the corner parts, and as rooks
on the middle part. Again it is possible to combine such moves. If the Horsa
has moved one or more squares before crossing the line, it may then only move
one square. Horsas only take in moves where they pass the deflection line,
but not when they move only one square in rookwise fashion.
 */
@Service
public class HorsaService extends HorsaKornaService implements FigureService{

    public HorsaService() {
        super(FigureType.HORSA);
    }

    @Override
    protected boolean canBeatIt(boolean wasCrossed, int numOfMovesBefore) {
        return wasCrossed && numOfMovesBefore > 1;
    }
}
