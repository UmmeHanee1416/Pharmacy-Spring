package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query(value = "SELECT EXISTS (SELECT 1 FROM users WHERE username = :username)", nativeQuery = true)
    boolean existsByUsername(@Param("username") String username);

//    @Query(value = "SELECT EXISTS (SELECT 1 FROM users WHERE email = :email)", nativeQuery = true)
    boolean existsByEmail(@Param("email") String email);

    Optional<User> findByUsername(String userName);

    boolean existsByUsernameIgnoreCase(String userName);

    boolean existsByEmailIgnoreCase(String email);

    void deleteByUsername(String userName);
}
