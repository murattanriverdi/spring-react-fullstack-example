package com.murattanriverdi.app.repository;

import com.murattanriverdi.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    Optional<User>  findByActivationToken(String activationToken);

}
