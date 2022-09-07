package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_poster_img")
public class PosterImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poster_img")
    private Long id;

    @Column(nullable = false)
    private String saveFolder;

    @Column(nullable = false)
    private String originName;

    @Column(nullable = false)
    private String saveName;
}
