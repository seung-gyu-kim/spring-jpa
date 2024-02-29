package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Integer> {
    List<Point> findAllByUserId(String userId);
    int countByPointId(Integer pointId);
}
