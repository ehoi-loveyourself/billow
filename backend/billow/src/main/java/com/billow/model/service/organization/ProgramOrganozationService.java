package com.billow.model.service.organization;

import com.billow.domain.entity.organization.ProgramOrganization;
import com.billow.model.repository.organization.ProgramOrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProgramOrganozationService {

    private final ProgramOrganizationRepository programOrganizationRepository;

    public void save(ProgramOrganization programOrganization) {
        programOrganizationRepository.save(programOrganization);
    }

    public void deleteByBroadcastingDayStartingWith(String yesterDay) {
        programOrganizationRepository.deleteByBroadcastingDayStartingWith(yesterDay);
    }

    public List<ProgramOrganization> findByProgram_Id(Long id) {
        return programOrganizationRepository.findByProgram_Id(id);
    }
}
