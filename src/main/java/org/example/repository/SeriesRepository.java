package org.example.repository;

import org.example.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface SeriesRepository extends JpaRepository<Series, Integer> {
    List<Series> findBySeriesNameContainingIgnoreCase(String seriesName);
}
