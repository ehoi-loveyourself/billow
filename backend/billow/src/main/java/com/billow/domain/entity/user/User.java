
package com.billow.domain.entity.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@ToString
@Getter
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

    private String nickName;

    private String gender;

    private Integer age;

    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_img_id")
    private ProfileImg profileImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tv_carrier_id")
    private TvCarrier tvCarrier;

    @Builder
    public User(Long id, String email, String name, String nickName, String gender, Integer age, String refreshToken, ProfileImg profileImg, Region region, TvCarrier tvCarrier) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.gender = gender;
        this.age = age;
        this.refreshToken = refreshToken;
        this.profileImg = profileImg;
        this.region = region;
        this.tvCarrier = tvCarrier;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof User && this.getEmail().equals(((User) o).getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getEmail());
    }
}
