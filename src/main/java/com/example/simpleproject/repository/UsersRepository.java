package com.example.simpleproject.repository;

import com.example.simpleproject.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {


    Optional<Users> findByUsersIdAndDeletedAtIsNull(Integer userId);

    boolean existsByPhoneNumber(String phoneNumber);


    @Query("select u from  Users u where " +
            "coalesce(:usersId,u.usersId)=u.usersId AND " +
            "coalesce(:employeesId,u.employeesId)=u.employeesId AND " +
            "coalesce(:firstName,u.firstName)=u.firstName AND " +
            "coalesce(:lastName,u.lastName)=u.lastName AND " +
            "coalesce(:middleName,u.middleName)=u.middleName AND " +
            "coalesce(:userName,u.userName)=u.userName AND " +
            "coalesce(:borrowName,u.borrowName)=u.borrowName AND " +
            "coalesce(:phoneNumber,u.phoneNumber)=u.phoneNumber AND " +
            "coalesce(:passportSerial,u.passportSerial)=u.passportSerial AND " +
            "coalesce(:firstAddress,u.firstAddress)=u.firstAddress AND " +
            "coalesce(:secondAddress,u.secondAddress)=u.secondAddress AND " +
            "coalesce(:monthlyPrice,u.monthlyPrice)=u.monthlyPrice"
    )
    Page<Users> getBasicSearch(@Param("usersId")Integer usersId,
                                  @Param("employeesId")Integer employeesId,
                                  @Param("firstName")String firstName,
                                  @Param("lastName")String lastName,
                                  @Param("middleName")String middleName,
                                  @Param("userName")String userName,
                                  @Param("borrowName")String borrowName,
                                  @Param("phoneNumber")String phoneNumber,
                                  @Param("passportSerial")String passportSerial,
                                  @Param("firstAddress")String firstAddress,
                                  @Param("secondAddress")String secondAddress,
                                  @Param("monthlyPrice")Integer monthlyPrice,
                               Pageable pageable);
}
