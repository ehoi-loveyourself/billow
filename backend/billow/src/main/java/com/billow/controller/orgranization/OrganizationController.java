package com.billow.controller.orgranization;

import com.billow.domain.dto.organization.OrganizationListResponse;
import com.billow.domain.dto.organization.OrganizationResponse;
import com.billow.domain.dto.program.ProgramResponse;
import com.billow.model.repository.organization.ProgramOrganizationRepository;
import com.billow.model.service.organization.ProgramOrganozationService;
import com.billow.util.JwtUtil;
import com.billow.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final ProgramOrganozationService programOrganozationService;

    @GetMapping("/{programId}")
    public ResponseEntity<Object> selectProgramOrganization(@PathVariable("programId") Long programId) {
        log.info("편성표 조회 API 호출");
        List<OrganizationListResponse> responses = programOrganozationService.selectProgramOrganization(programId);
        log.info("편성표 조회 API 성공");
        return ResponseEntity.ok()
                .body(responses);
    }
}
