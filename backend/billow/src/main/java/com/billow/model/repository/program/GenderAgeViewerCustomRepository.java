package com.billow.model.repository.program;

import com.billow.domain.entity.addition.Rating;
import com.billow.domain.entity.program.Cast;
import com.billow.domain.entity.program.GenderAgeViewer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.billow.domain.entity.addition.QRating.rating;
import static com.billow.domain.entity.program.QCast.cast;

@Repository
public interface GenderAgeViewerCustomRepository {

    List<GenderAgeViewer> findTop5ByGenderAge(Integer age, String gender);
}