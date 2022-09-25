package com.billow.domain.entity.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_profile_img")
@Entity
public class ProfileImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_img_id")
    private Long id;

    private String saveFolder;

    private String imgName;

    @Builder
    public ProfileImg(Long id, String saveFolder, String imgName) {
        this.id = id;
        this.saveFolder = saveFolder;
        this.imgName = imgName;
    }
}
