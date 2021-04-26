package com.github.fdehghan4013.reporting.repository;

import com.github.fdehghan4013.reporting.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
