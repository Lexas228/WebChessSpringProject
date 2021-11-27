package ru.vsu.chess.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.chess.model.entity.Cell;

import java.util.Optional;

public interface CellRepository extends JpaRepository<Cell, Long> {
    Optional<Cell> findByPosition(String position);
}
