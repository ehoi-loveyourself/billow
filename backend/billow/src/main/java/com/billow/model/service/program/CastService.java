package com.billow.model.service.program;

import com.billow.domain.entity.program.Cast;
import com.billow.model.repository.program.CastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CastService {
    private final CastRepository castRepository;

    public void save(Cast cast) {
        castRepository.save(cast);
    }

    public Optional<Cast> findById(Long id) {
        return castRepository.findById(id);
    }
}
