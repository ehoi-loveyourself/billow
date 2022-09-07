package com.billow.model.entity.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_region")
@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    @NotNull
    private String region;

    @Builder
    public Region(Long id, String region) {
        this.id = id;
        this.region = region;
    }
}
