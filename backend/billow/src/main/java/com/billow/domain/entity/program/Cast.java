package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_cast")
@Entity
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cast_id")
    private Long id;

    @NotNull
    private String actorName;

    @NotNull
    private String playName;

    private String saveFolder;

    private String saveName;

    @Builder
    public Cast(Long id, String actorName, String playName, String saveFolder, String saveName){
        this.id = id;
        this.actorName = actorName;
        this.playName = playName;
        this.saveFolder = saveFolder;
        this.saveName = saveName;
    }
}
