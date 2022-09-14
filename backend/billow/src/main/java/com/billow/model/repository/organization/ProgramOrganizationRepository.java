package com.billow.model.repository.organization;

import com.billow.domain.entity.organization.ProgramOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramOrganizationRepository extends JpaRepository<ProgramOrganization, Long> {

    void deleteByBroadcastingDayStartingWith(String yesterDay);
}
