package com.billow.model.entity.condition;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_condition_genre")
@Entity
public class ConditionGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @NotNull
    private String genre;

    @Builder
    public ConditionGenre(Long id, String genre) {
        this.id = id;
        this.genre = genre;
    }
}
