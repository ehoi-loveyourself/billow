package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_ott")
@Entity
public class Ott {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ott_id")
    private Long id;

    @Builder
    public Ott(Long id) {
        this.id = id;
    }
}
