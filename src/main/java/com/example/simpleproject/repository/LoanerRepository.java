package com.example.simpleproject.repository;

import com.example.simpleproject.model.Loaner;
import com.example.simpleproject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LoanerRepository extends JpaRepository<Loaner, Integer> {

    //Optional<Users> findByLoanerIdAndDeletedAtIsNull(Integer userId);
}
