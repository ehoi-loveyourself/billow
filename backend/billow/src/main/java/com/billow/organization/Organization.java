package com.billow.organization;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_organization")
@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
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
    public Organization(Long id, String programTitle, String broadCastingDay, String broadCastingTime, String broadCastingStation, String broadCastingType ) {
        this.id = id;
        this.programTitle = programTitle;
        this.broadCastingDay = broadCastingDay;
        this.broadCastingTime = broadCastingTime;
        this.broadCastingStation = broadCastingStation;
        this.broadCastingType = broadCastingType;
    }

}
