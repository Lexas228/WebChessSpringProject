package ru.vsu.chess.services;

import org.springframework.transaction.annotation.Transactional;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.entity.PlayerType;
import ru.vsu.chess.model.entity.Game;

public interface PlayerService {
    PlayerType getPlayerType();
    @Transactional
    void notification(Player player, Game game);
}
