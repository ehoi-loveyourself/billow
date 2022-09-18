package com.billow.model.repository.program;

import com.billow.domain.entity.program.GenderAgeViewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenderAgeViewerRepository extends JpaRepository<GenderAgeViewer, Long> {

    GenderAgeViewer findByProgramTitle(String programTitle);
}
