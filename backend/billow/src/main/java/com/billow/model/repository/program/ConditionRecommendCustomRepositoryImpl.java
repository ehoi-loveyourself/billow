package com.billow.model.repository.program;

import com.billow.domain.entity.program.QConditionRecommend;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.billow.domain.entity.program.QProgram.program;
import static com.billow.domain.entity.program.QConditionRecommend.conditionRecommend;

@RequiredArgsConstructor
@Repository
public class ConditionRecommendCustomRepositoryImpl implements  ConditionRecommendCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    NumberPath<Long> count = Expressions.numberPath(Long.class, "program_id");

    @Override
    public List<Long> findTop3ByWithWhomAndGenre(String who, String genre) {
        return jpaQueryFactory.select(program.id)
                .from(conditionRecommend)
                .where(conditionRecommend.genre.eq(genre)
                        .and(conditionRecommend.who.eq(who)))
                .groupBy(program.id)
                .orderBy(program.id.count().desc())
                .limit(3)
                .fetch();
    }

    /*
    select program_id
from tb_condition_recommend
where genre = '우울'
and who = '혼자'
group by program_id
order by count(program_id) desc
limit 3;
     */
}
