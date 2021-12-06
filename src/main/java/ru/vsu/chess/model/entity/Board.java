package ru.vsu.chess.model.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.type.EntityType;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "player_figures",
            joinColumns = {@JoinColumn(name = "board_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "figure_id")
    @ToString.Exclude
    private Map<Figure, Player> figurePlayerMap;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "figure_cell_id_mapping",
            joinColumns = {@JoinColumn(name = "board_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "figure_id", referencedColumnName = "id")
    @Column(name = "cell_id")
    @ToString.Exclude
    private Map<Figure, Long> figureCellIdMap;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "cell_id_figure_mapping",
            joinColumns = {@JoinColumn(name = "board_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "figure_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "cell_id")
    @ToString.Exclude
    private Map<Long, Figure> cellIdFigureMap;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "position_cell_id_mapping",
            joinColumns = {@JoinColumn(name = "board_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "position")
    @Column(name = "cell_id")
    @ToString.Exclude
    private Map<String, Long> positionCellMap;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cell_id_position_mapping",
            joinColumns = {@JoinColumn(name = "board_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "cell_id")
    @Column(name = "position")
    @ToString.Exclude
    private Map<Long, String> cellPositionMapping;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Board board = (Board) o;
        return id != null && Objects.equals(id, board.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


