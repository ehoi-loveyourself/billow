package com.billow.domain.entity.condition;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_condition_program")
@Entity
public class ConditionProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "condition_program_id")
    private Long id;

    @Builder
    public ConditionProgram(Long id) {
        this.id = id;
    }
}
