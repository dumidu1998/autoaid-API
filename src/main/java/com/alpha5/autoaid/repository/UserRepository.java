package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, Long> {
    UserData findByEmail(String email);

    UserData findByContactNo(String contactNo);

    UserData findByUserName(String username);

    UserData findByUserNameOrEmail(String username, String email);

}