package com.minkon.board.repository;

import com.minkon.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {

    public boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);
}
