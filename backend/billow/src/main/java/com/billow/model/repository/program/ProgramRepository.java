package com.billow.model.repository.program;

import com.billow.domain.entity.program.Cast;
import com.billow.domain.entity.program.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {


}
