package com.example.simpleproject.repository;

import com.example.simpleproject.model.Employees;
import com.example.simpleproject.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    Optional<Employees> findByEmployeesIdAndDeletedAtIsNull(Integer userId);
@Query("select e from Employees e where " +
        "coalesce(:employeesId,e.employeesId)=e.employeesId ")


    Page<Employees> getBasicSearch(@Param("employeesId")Integer employeesId,
                                   Pageable pageable);


}
