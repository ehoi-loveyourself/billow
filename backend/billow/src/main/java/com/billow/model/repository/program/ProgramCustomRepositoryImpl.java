package com.billow.model.repository.program;

import com.billow.domain.entity.program.Program;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.billow.domain.entity.program.QProgram.program;

@Repository
public class ProgramCustomRepositoryImpl implements ProgramCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public ProgramCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Program> findPopularProgram() {
        return jpaQueryFactory.selectFrom(program)
                .where(program.ratingCnt.goe(5).and(program.bookmarkCnt.goe(5)))
                .orderBy(program.averageRating.asc())
                .limit(10)
                .fetch();
    }
}
