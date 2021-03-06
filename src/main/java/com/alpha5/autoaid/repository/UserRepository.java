package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.enums.UserType;
import com.alpha5.autoaid.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserData, Long> {
    UserData findByEmail(String email);
    UserData findByIdAndUserName(long id,String userName);
    UserData findByContactNo(String contactNo);

    UserData findByUserName(String username);
    UserData findByIdAndUserType(long userId, UserType userType);

    UserData findByUserNameOrEmail(String username, String email);

    List<UserData> findAllByUserType(UserType userType);

}