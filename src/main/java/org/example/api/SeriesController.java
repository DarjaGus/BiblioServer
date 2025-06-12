package org.example.api;

import org.example.model.Genre;
import org.example.model.Series;
import org.example.service.GenreService;
import org.example.service.SeriesService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SeriesController {

    private static final Logger logger = LoggerFactory.getLogger(SeriesController.class);

    private final SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping
    public ResponseEntity<List<Series>> getAllSeries() {
        logger.info("GET request to fetch all series");
        List<Series> series = seriesService.getAllSeries();
        return new ResponseEntity<>(series, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Series> getSeriesById(@PathVariable Integer id) {
        logger.info("GET request to fetch series with id: {}", id);
        Series series = seriesService.getSeriesById(id);
        return new ResponseEntity<>(series, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Series> createSeries(@Valid @RequestBody Series series) {
        logger.info("POST request to create series: {}", series.getSeriesName());
        Series savedSeries = seriesService.saveSeries(series);
        return new ResponseEntity<>(savedSeries, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Series> updateSeries(@PathVariable Integer id, @Valid @RequestBody Series seriesDetails) {
        logger.info("PUT request to update series with id: {}", id);
        Series updatedSeries = seriesService.updateSeries(id, seriesDetails);
        return new ResponseEntity<>(updatedSeries, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable Integer id) {
        logger.info("DELETE request to delete series with id: {}", id);
        seriesService.deleteSeries(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Series>> searchGenres(@RequestParam String name) {
        logger.info("GET request to search genres by name: {}", name);
        List<Series> genres = seriesService.findBySeriesName(name);
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }
}
