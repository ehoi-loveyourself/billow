package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_ott_info")
public class OttInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ott_info_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String url;

    @NotNull
    private String saveFolder;

    @NotNull
    private String saveName;


}
