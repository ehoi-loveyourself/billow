package com.billow.model.service.organization;

import com.billow.domain.entity.organization.ProgramOrganization;
import com.billow.model.repository.organization.ProgramOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramOrganozationService {

    @Autowired
    private ProgramOrganizationRepository programOrganizationRepository;

    public void save(ProgramOrganization programOrganization) {
        programOrganizationRepository.save(programOrganization);
    }

    public void deleteByBroadcastingDayStartingWith(String yesterDay) {
        programOrganizationRepository.deleteByBroadcastingDayStartingWith(yesterDay);
    }
}
