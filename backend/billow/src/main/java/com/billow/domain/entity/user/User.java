
package com.billow.domain.entity.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String nickName;

    @NotNull
    private boolean gender;

    @NotNull
    private Integer age;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_img_id")
    private ProfileImg profileImg;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tv_carrier_id")
    private TvCarrier tvCarrier;

    @Builder
    public User(Long id, String email, String name, String nickName, boolean gender, Integer age, ProfileImg profileImg, Region region, TvCarrier tvCarrier) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.gender = gender;
        this.age = age;
        this.profileImg = profileImg;
        this.region = region;
        this.tvCarrier = tvCarrier;
    }
}
