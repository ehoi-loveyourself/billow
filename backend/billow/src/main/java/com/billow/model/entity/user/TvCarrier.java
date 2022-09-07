package com.billow.model.entity.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_tv_carrier")
@Entity
public class TvCarrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tv_carrier_id")
    private Long id;

    @NotNull
    private String company;

    @NotNull
    public TvCarrier(Long id, String company) {
        this.id = id;
        this.company = company;
    }
}
