package ru.vsu.chess.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.chess.model.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
