
package com.billow.domain.entity.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_user")
@Entity
public class User implements UserDetails {

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

    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Builder
    public User(Long id, String email, String name, String nickName, String gender, Integer age, String refreshToken,
                ProfileImg profileImg, Region region, TvCarrier tvCarrier, List<String> roles) {
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
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof User && this.getEmail().equals(((User) o).getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getEmail());
    }

    public void saveRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void deleteRefreshToken() {
        this.refreshToken = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
