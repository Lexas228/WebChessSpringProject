package ru.vsu.chess.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "enemies",
            joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "enemy_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "player_id")
    private Map<Player, Player> enemyMap;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "active_player_id")
    private Player activePlayer;

    @Column(name = "Status")
    @Enumerated(EnumType.ORDINAL)
    private GameStatus gameStatus;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "player_direction_mapping",
            joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "player_id")
    @Column(name = "direction")
    @ToString.Exclude
    private Map<Player, Direction> playerDirectionMap;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Game game = (Game) o;
        return id != null && Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
