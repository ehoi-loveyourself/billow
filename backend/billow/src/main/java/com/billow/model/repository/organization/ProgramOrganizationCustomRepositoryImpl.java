package com.billow.model.repository.organization;

import com.billow.domain.entity.organization.ProgramOrganization;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static com.billow.domain.entity.organization.QProgramOrganization.programOrganization;

@RequiredArgsConstructor
@Repository
public class ProgramOrganizationCustomRepositoryImpl implements ProgramOrganizationCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProgramOrganization> findByBroadcastingTimeBetween(Date date, Date now) {
        return jpaQueryFactory.selectFrom(programOrganization)
                .where(programOrganization.broadcastingTime.between(date, now))
                .orderBy(programOrganization.broadcastingTime.desc())
                .fetch();
    }
}