package com.murattanriverdi.app.repository;

import com.murattanriverdi.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
