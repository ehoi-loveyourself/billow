package com.billow.model.service.organization;

import com.billow.domain.dto.program.ProgramResponse;
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

    public List<ProgramResponse> selectProgramOrganization(Long programId) {
        System.out.println(programId);
        List<ProgramOrganization> programOrganizationList = programOrganizationRepository.findByProgram_Id(programId);
        System.out.println(programOrganizationList);
        //TODO : OrganizationResponse추가
        return null;
    }
}
