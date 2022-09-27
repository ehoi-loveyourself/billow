package com.billow.model.service.program;

import com.billow.domain.entity.program.Cast;
import com.billow.model.repository.program.CastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CastService {
    private final CastRepository castRepository;

    public void save(Cast cast) {
        castRepository.save(cast);
    }


    public List<Cast> findByProgram_Id(Long id) {
        return castRepository.findByProgram_Id(id);
    }
}
