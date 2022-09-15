package com.billow.model.service.program;

import com.billow.domain.entity.program.Cast;
import com.billow.domain.entity.program.Program;
import com.billow.model.repository.program.CastRepository;
import com.billow.model.repository.program.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public List<Program> findAll() {
        return programRepository.findAll();
    }
}
