package com.billow.model.repository.program;

import com.billow.domain.entity.program.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CastRepository extends JpaRepository<Cast, Long>, CastCustomRepository {
    Optional<List<Cast>> findByActorName(String actorName);

    Optional<List<Cast>> findByProgram_Id(Long id);
}
