package com.billow.model.repository.program;

import com.billow.domain.entity.program.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByTitleContaining(String word);
}
