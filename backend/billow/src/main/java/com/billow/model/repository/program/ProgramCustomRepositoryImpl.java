package com.billow.model.repository.program;

import com.billow.domain.entity.program.Program;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.billow.domain.entity.program.QProgram.program;
import static com.billow.domain.entity.addition.QRating.rating;
import static com.billow.domain.entity.user.QUser.user;

@RequiredArgsConstructor
@Repository
public class ProgramCustomRepositoryImpl implements ProgramCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Program> findPopularProgram() {
        return jpaQueryFactory.selectFrom(program)
                .where(program.ratingCnt.goe(5).and(program.bookmarkCnt.goe(5)))
                .orderBy(program.averageRating.desc())
                .fetch();
    }

    @Override
    public List<Program> findGenderAgeRecommend(Long userId, Integer age, String gender, Long id, Long id1, Long id2, Long id3, Long id4) {
        return jpaQueryFactory.selectFrom(program)
                .join(rating).on(program.id.eq(rating.program.id))
                .where(program.id.in(
                        jpaQueryFactory.select(program.id).from(rating)
                                .where(rating.user.id.in(
                                                jpaQueryFactory.select(user.id).from(user)
                                                        .where(user.age.eq(age).and(user.gender.eq(gender)))
                                        )
                                        .and(rating.user.id.in(
                                                jpaQueryFactory.select(user.id).from(rating)
                                                        .where(rating.program.id.eq(id).or(rating.program.id.eq(id1)).or(rating.program.id.eq(id2)).or(rating.program.id.eq(id3)).or(rating.program.id.eq(id4)))
                                        ))
                                        .and(rating.user.id.ne(userId)))
                ))
                .groupBy(program.id)
                .orderBy(rating.score.sum().desc())
                .limit(20)
                .fetch();
    }
}