package ru.vsu.chess.model.entity;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "step")
public class Step{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "from")
    private Long from;

    @Column(name = "to")
    private Long to;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player who;

    @OneToOne
    @JoinColumn(name = "bitten_figure_id")
    private Figure bittenFigure;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date")
    private Date date;

}
