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
    private String broadcastingDay;

    @NotNull
    private String broadcastingTime;

    @NotNull
    private String broadcastingStation;

    @NotNull
    private String broadcastingType;

    @Builder
    public ProgramOrganization(Long id, String programTitle, String broadcastingDay, String broadcastingTime, String broadcastingStation, String broadcastingType ) {
        this.id = id;
        this.programTitle = programTitle;
        this.broadcastingDay = broadcastingDay;
        this.broadcastingTime = broadcastingTime;
        this.broadcastingStation = broadcastingStation;
        this.broadcastingType = broadcastingType;
    }
}
