package com.billow.domain.entity.program;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Program program;

    @Builder
    public Cast(Long id, String actorName, String playName, String saveFolder, String saveName, Program program) {
        this.id = id;
        this.actorName = actorName;
        this.playName = playName;
        this.saveFolder = saveFolder;
        this.saveName = saveName;
        this.program = program;
    }
}
