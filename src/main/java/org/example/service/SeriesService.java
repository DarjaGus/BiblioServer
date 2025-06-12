package org.example.service;

import org.example.model.Series;
import org.example.repository.SeriesRepository;
import org.example.config.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {

    private static final Logger logger = LoggerFactory.getLogger(SeriesService.class);

    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<Series> getAllSeries() {
        logger.info("Fetching all series");
        return seriesRepository.findAll();
    }

    public Series getSeriesById(Integer id) {
        logger.info("Fetching series by id: {}", id);
        return seriesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Series not found with id: " + id));
    }

    @Transactional
    public Series saveSeries(Series series) {
        logger.info("Saving series: {}", series.getSeriesName());
        return seriesRepository.save(series);
    }

    @Transactional
    public Series updateSeries(Integer id, Series seriesDetails) {
        logger.info("Updating series with id: {}", id);
        Series series = seriesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Series not found with id: " + id));

        series.setSeriesName(seriesDetails.getSeriesName());

        return seriesRepository.save(series);
    }

    public void deleteSeries(Integer id) {
        logger.info("Deleting series with id: {}", id);
        Series genre = seriesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Series not found with id: " + id));

        seriesRepository.delete(genre);
    }

    public List<Series> findBySeriesName(String seriesName) {
        logger.info("Searching series by name: {}", seriesName);
        return seriesRepository.findBySeriesNameContainingIgnoreCase(seriesName);
    }
}
