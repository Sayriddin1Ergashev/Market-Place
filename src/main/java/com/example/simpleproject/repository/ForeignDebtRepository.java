package com.example.simpleproject.repository;

import com.example.simpleproject.model.ForeignDebt;
import com.example.simpleproject.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ForeignDebtRepository extends JpaRepository<ForeignDebt, Integer> {

   Optional<ForeignDebt> findByForeignDebtIdAndDeletedAtIsNull(Integer foreignDebtId);


}
