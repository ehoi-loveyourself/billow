package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_poster_img")
@Entity
public class PosterImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poster_img_id")
    private Long id;

    private String saveFolder;

    private String originName;

    private String saveName;

    @Builder
    public PosterImg(Long id, String saveFolder, String originName, String saveName) {
        this.id = id;
        this.saveFolder = saveFolder;
        this.originName = originName;
        this.saveName = saveName;
    }
}
