package com.billow.model.service.data;

import com.billow.domain.dto.program.GenderAgeViewerInformation;
import com.billow.model.repository.data.kPopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class kPopService {
    @Autowired
    private kPopRepository kPopRepository;

    public List<GenderAgeViewerInformation> getData() {
        return kPopRepository.getData();
    }
}
