package com.billow.domain.entity.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_user_profile_img")
@Entity
public class ProfileImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_img_id")
    private Long id;

    private String saveFolder;
    private String originName;
    private String saveName;

    @Builder
    public ProfileImg(Long id, String saveFolder, String originName, String saveName) {
        this.id = id;
        this.saveFolder = saveFolder;
        this.originName = originName;
        this.saveName = saveName;
    }
}
