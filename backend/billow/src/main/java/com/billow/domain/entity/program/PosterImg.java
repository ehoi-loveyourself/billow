package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_poster_img")
public class PosterImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poster_img")
    private Long id;

    @NotNull
    private String saveFolder;

    @NotNull
    private String originName;

    @NotNull
    private String saveName;

    @Builder
    public PosterImg(Long id, String saveFolder, String originName, String saveName) {
        this.id = id;
        this.saveFolder = saveFolder;
        this.originName = originName;
        this.saveName = saveName;
    }
}
