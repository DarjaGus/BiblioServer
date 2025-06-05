package org.example.service;

import org.example.model.Series;
import org.example.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {
    @Autowired
    private SeriesRepository seriesRepository;

    public List<Series> findAllSeries() {
        return seriesRepository.findAll();
    }

    public Series findSeriesById(Long id) {
        return seriesRepository.findById(id).orElse(null);
    }

    public Series saveSeries(Series series) {
        return seriesRepository.save(series);
    }

    public void deleteSeries(Long id) {
        seriesRepository.deleteById(id);
    }
}
