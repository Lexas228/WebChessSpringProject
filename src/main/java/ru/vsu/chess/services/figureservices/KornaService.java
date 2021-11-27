package ru.vsu.chess.services.figureservices;

import org.springframework.stereotype.Service;
import ru.vsu.chess.model.entity.FigureType;

/*
A Korna moves as a rook in chess when in a corner of the board, and as
a bishop in the middle of the board. It is also possible to combine these moves
in one turn. If the Korna has moved one or more squares before crossing
the deflection line, it may then only move one square. Kornas can only take
in moves where they move over a deflection line.
 */
@Service
public class KornaService extends HorsaKornaService implements FigureService{
    public KornaService() {
        super(FigureType.KORNA);
    }






}
