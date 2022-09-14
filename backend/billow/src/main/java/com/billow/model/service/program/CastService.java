package com.billow.model.service.program;

import com.billow.domain.entity.program.Cast;
import com.billow.model.repository.program.CastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CastService {
    @Autowired
    private CastRepository castRepository;

    public void save(Cast cast) {
        castRepository.save(cast);
    }
}
