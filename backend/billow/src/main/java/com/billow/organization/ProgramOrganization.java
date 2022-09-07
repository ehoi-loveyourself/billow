package com.billow.organization;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
    private String broadCastingDay;

    @NotNull
    private String broadCastingTime;

    @NotNull
    private String broadCastingStation;

    @NotNull
    private String broadCastingType;

    @Builder
    public ProgramOrganization(Long id, String programTitle, String broadCastingDay, String broadCastingTime, String broadCastingStation, String broadCastingType ) {
        this.id = id;
        this.programTitle = programTitle;
        this.broadCastingDay = broadCastingDay;
        this.broadCastingTime = broadCastingTime;
        this.broadCastingStation = broadCastingStation;
        this.broadCastingType = broadCastingType;
    }

}
