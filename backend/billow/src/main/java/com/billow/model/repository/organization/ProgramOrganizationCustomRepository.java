package com.billow.model.repository.organization;

import com.billow.domain.entity.organization.ProgramOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ProgramOrganizationCustomRepository {
    List<ProgramOrganization> findByProgram_IdAndBroadcastingTime(Long programId, LocalDate date);
}