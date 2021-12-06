package ru.vsu.chess.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.chess.model.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
