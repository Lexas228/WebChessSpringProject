package ru.vsu.chess.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private PlayerType myType;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles myRole;
}
