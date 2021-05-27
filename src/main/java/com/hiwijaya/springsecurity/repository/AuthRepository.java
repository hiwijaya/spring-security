package com.hiwijaya.springsecurity.repository;

import com.hiwijaya.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


/**
 * @author Happy Indra Wijaya
 */
public interface AuthRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
