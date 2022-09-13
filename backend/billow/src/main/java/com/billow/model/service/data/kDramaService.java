package com.billow.model.service.data;

import com.billow.domain.dto.program.GenderAgeViewerInformation;
import com.billow.model.repository.data.kDramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class kDramaService {
    @Autowired
    private kDramaRepository kDramaRepository;

    public List<GenderAgeViewerInformation> getData() {
        return kDramaRepository.getData();
    }
}
