package com.billow.model.service.program;

import com.billow.domain.entity.program.GenderAgeViewer;
import com.billow.model.repository.program.GenderAgeViewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderAgeViewerService {
    @Autowired
    private GenderAgeViewerRepository genderAgeViewerRepository;

    public void save(GenderAgeViewer program) {
        genderAgeViewerRepository.save(program);
    }
}
