package com.billow.model.repository.program;

import com.billow.domain.entity.addition.Rating;
import com.billow.domain.entity.program.Cast;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.billow.domain.entity.addition.QRating.rating;
import static com.billow.domain.entity.program.QCast.cast;

@Repository
public class CastCustomRepositoryImpl implements CastCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public CastCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public String findMaxCountByProgram_Id(List<Rating> ratingList) {
        List<String> actorNameList = new ArrayList<>();
        for (Rating rating : ratingList) {
            List<String> actorName = jpaQueryFactory.select(cast.actorName)
                    .from(cast)
                    .where(cast.program.id.eq(rating.getProgram().getId()))
                    .fetch();
            actorNameList.addAll(actorName);
        }

        int max = 0;
        String actor = null;
        Set<String> set = new HashSet<>(actorNameList);
        for (String str : set) {
            if (max <= Collections.frequency(actorNameList, str)) {
                max = Collections.frequency(actorNameList, str);
                actor = str;
            }
        }
        return actor;
    }

    @Override
    public List<Cast> findByActorName(Long userId, String actorName) {
        return jpaQueryFactory.select(cast)
                .from(cast)
                .where(cast.actorName.eq(actorName)
                        .and(cast.program.id.notIn(
                                jpaQueryFactory.select(rating.program.id)
                                        .from(rating)
                                        .where(rating.user.id.eq(userId))
                        )))
                .fetch();
    }
}