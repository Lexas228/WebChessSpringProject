package ru.vsu.chess.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.chess.model.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
