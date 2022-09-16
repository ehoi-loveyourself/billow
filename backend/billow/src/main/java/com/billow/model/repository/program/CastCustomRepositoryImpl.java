package com.billow.model.repository.program;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.billow.domain.entity.program.QCast.cast;

@Repository
public class CastCustomRepositoryImpl implements CastCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public CastCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<String> findMaxCountByProgram_Id(Long id, Long id1, Long id2, Long id3, Long id4) {
        return jpaQueryFactory.select(cast.actorName)
                .from(cast)
                .where(cast.id.eq(id).or(cast.id.eq(id1)).or(cast.id.eq(id2)).or(cast.id.eq(id3)).or(cast.id.eq(id4)))
                .groupBy(cast.actorName)
                .orderBy(cast.actorName.count().desc())
                .fetch();
    }
}
