package com.minkon.board.repository;

import com.minkon.board.entity.PopularSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopularSearchRepository extends JpaRepository<PopularSearchEntity, Integer> {

    public List<PopularSearchEntity> findTop10ByOrderByPopularSearchCountDesc();
}
