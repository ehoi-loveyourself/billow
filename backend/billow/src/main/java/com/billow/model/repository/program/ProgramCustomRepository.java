package com.billow.model.repository.program;

import com.billow.domain.entity.program.Program;

import java.util.List;

public interface ProgramCustomRepository {

    List<Program> findPopularProgram();
}
