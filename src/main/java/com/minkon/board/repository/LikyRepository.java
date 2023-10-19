package com.minkon.board.repository;

import com.minkon.board.entity.LikyEntity;
import com.minkon.board.entity.PopularSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, Integer> {
}
