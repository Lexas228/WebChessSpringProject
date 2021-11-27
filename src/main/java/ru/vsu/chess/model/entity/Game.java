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
@Table(name = "Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Player_figures",
            joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "figure_id")
    private Map<Figure, Player> figurePlayerMap;

    @OneToMany
    @JoinTable(name = "Cell_figure",
            joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "figure_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "cell_id")
    private Map<Cell, Figure> cellFigureMap;

    @OneToMany
    @JoinTable(name = "Figure_Cell",
            joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "cell_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "figure_id")
    private Map<Figure, Cell> figureCellMap;

    @OneToMany
    @JoinTable(name = "Enemies",
            joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "enemy_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "player_id")
    private Map<Player, Player> enemyMap;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "active_player_id")
    private Player activePlayer;

    @Column(name = "Status")
    @Enumerated(EnumType.ORDINAL)
    private GameStatus gameStatus;




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
