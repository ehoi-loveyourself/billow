package com.billow.domain.entity.condition;

import com.billow.domain.entity.program.Program;
import com.billow.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_condition_program")
@Entity
public class ConditionProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "condition_program_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Program program;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "with_whom_id")
    private ConditionWithWhom conditionWithWhom;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private ConditionGenre conditionGenre;

    @Builder
    public ConditionProgram(Long id, User user, Program program, ConditionWithWhom conditionWithWhom, ConditionGenre conditionGenre) {
        this.id = id;
        this.user = user;
        this.program = program;
        this.conditionWithWhom = conditionWithWhom;
        this.conditionGenre = conditionGenre;
    }
}
