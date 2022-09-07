package com.billow.domain.entity.condition;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_condition_with_whom")
@Entity
public class ConditionWithWhom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "with_whom_id")
    private Long id;

    @NotNull
    private String who;

    @Builder
    public ConditionWithWhom(Long id, String who) {
        this.id = id;
        this.who = who;
    }
}
