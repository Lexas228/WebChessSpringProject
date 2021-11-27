package ru.vsu.chess.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "Cell")
public class Cell {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ElementCollection(targetClass = CellType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "Cell_types", joinColumns = @JoinColumn(name = "cell_id"))
    @Enumerated(EnumType.STRING)
    private Set<CellType> cellType;

    @Column(name = "position")
    private String position;
}
