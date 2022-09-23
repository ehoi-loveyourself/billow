package com.billow.domain.entity.addition;

import com.billow.domain.entity.program.Program;
import com.billow.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_broadcasting_alarm")
@Entity
public class BroadcastingAlarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcasting_alarm_id")
    private Long id;

    @NotNull
    private Date dateTime;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Program program;

    @Builder
    public BroadcastingAlarm(Long id, Date dateTime, User user, Program program) {
        this.id = id;
        this.dateTime = dateTime;
        this.user = user;
        this.program = program;
    }
}
