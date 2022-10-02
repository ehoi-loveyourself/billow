package com.billow.model.repository.organization;

import com.billow.domain.entity.organization.ProgramOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ProgramOrganizationRepository extends JpaRepository<ProgramOrganization, Long>, ProgramOrganizationCustomRepository {

    @Transactional
    void deleteByBroadcastingDayStartingWith(String yesterDay);

    List<ProgramOrganization> findByProgram_Id(Long id);

    List<ProgramOrganization> findByBroadcastingTimeBetween(Date date, Date now);

//    @Transactional
//    void deleteByBroadcastingTimeBefore(Date date);

    List<ProgramOrganization> findByBroadcastingTimeBefore(Date date);
}