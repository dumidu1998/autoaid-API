package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByname(String name);

}
