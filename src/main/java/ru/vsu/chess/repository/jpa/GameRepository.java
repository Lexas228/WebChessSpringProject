package ru.vsu.chess.repository.jpa;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.chess.model.entity.Game;


public interface GameRepository extends JpaRepository<Game, Long> {
}
