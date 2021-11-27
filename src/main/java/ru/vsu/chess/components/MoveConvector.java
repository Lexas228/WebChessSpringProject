package ru.vsu.chess.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vsu.chess.dto.MoveDto;
import ru.vsu.chess.model.entity.Cell;
import ru.vsu.chess.model.entity.Move;
import ru.vsu.chess.repository.jpa.CellRepository;

import java.util.Optional;

@Component
public class MoveConvector {
    private final CellRepository cellRepository;

    @Autowired
    public MoveConvector(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

    public Move toEntity(MoveDto dto){
      String move = dto.getMove();
      if(move.length() != 4){
          return null;
      }
      String from = Character.toString(move.charAt(0) + move.charAt(1));
      String to = Character.toString(move.charAt(2) + move.charAt(3));


      Optional<Cell> ocf =  cellRepository.findByPosition(from);
      if(ocf.isPresent()){
          Cell cf = ocf.get();
          Optional<Cell> oct = cellRepository.findByPosition(to);
          if(oct.isPresent()){
              Cell ct = oct.get();
              return new Move(cf, ct);
          }
      }
      return null;
    }


}
