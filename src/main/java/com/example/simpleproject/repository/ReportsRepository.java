package com.example.simpleproject.repository;

import com.example.simpleproject.model.Reports;
import com.example.simpleproject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ReportsRepository extends JpaRepository<Reports, Integer> {
   Optional<Reports> findByReportsIdAndDeletedAtIsNull(Integer reportsId);
}
