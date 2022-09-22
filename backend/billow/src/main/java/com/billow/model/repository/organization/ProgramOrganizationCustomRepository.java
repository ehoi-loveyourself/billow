package com.billow.model.repository.organization;

import com.billow.domain.entity.organization.ProgramOrganization;

import java.util.Date;
import java.util.List;

public interface ProgramOrganizationCustomRepository {
    List<ProgramOrganization> findByBroadcastingTimeBetween(Date date, Date now);
}