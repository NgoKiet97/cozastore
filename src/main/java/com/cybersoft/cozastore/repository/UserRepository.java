package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    List<UserEntity> findByUsernameAndPassword(String username, String password);
    UserEntity findByUsername(String username);
}
