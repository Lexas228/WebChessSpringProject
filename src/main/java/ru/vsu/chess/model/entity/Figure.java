package ru.vsu.chess.model.entity;

import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Figure")
public class Figure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Type")
    @Enumerated(EnumType.ORDINAL)
    private FigureType myType;
}
