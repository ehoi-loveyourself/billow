package com.billow.model.repository.program;

import com.billow.domain.entity.program.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastRepository extends JpaRepository<Cast, Long> {


}
