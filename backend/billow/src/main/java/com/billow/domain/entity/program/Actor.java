package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long id;

    @Column(nullable = false)
    private String actorName;

    @Column(nullable = false)
    private String playName;

    private String saveFolder;

    private String saveName;

}
