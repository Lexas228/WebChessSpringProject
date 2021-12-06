package ru.vsu.chess.services;

import org.springframework.transaction.annotation.Transactional;
import ru.vsu.chess.model.dto.GameStatusDto;
import ru.vsu.chess.model.entity.Player;
import ru.vsu.chess.model.entity.PlayerType;
import ru.vsu.chess.model.entity.Game;
import ru.vsu.chess.model.entity.Move;

public interface PlayerService {
    PlayerType getPlayerType();
    @Transactional
    void notification(Player player, Game game);
}
