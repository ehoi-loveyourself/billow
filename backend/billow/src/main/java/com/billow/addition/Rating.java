package com.billow.addition;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_rating")
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long id;

    @NotNull
    private float score;

    @Builder
    public Rating(Long id, float score) {
        this.id = id;
        this.score = score;
    }
}
