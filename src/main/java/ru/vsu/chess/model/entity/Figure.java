package ru.vsu.chess.model.entity;

import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "figure")
public class Figure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private FigureType myType;

    @Column(name = "color")
    @Enumerated(EnumType.ORDINAL)
    private Color color;

}
