package com.billow.domain.entity.organization;

import com.billow.domain.entity.program.Program;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_program_organization")
@Entity
public class ProgramOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_organization_id")
    private Long id;

    @NotNull
    private String programTitle;

    @NotNull
    private String broadcastingDay;

    @NotNull
    private String broadcastingTime;

    @NotNull
    private String broadcastingStation;

    @NotNull
    private String broadcastingType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Program program;

    @Builder
    public ProgramOrganization(Long id, String programTitle, String broadcastingDay, String broadcastingTime, String broadcastingStation, String broadcastingType, Program program) {
        this.id = id;
        this.programTitle = programTitle;
        this.broadcastingDay = broadcastingDay;
        this.broadcastingTime = broadcastingTime;
        this.broadcastingStation = broadcastingStation;
        this.broadcastingType = broadcastingType;
        this.program = program;
    }
}
