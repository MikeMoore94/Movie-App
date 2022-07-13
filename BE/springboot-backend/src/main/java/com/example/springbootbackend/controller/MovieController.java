package com.example.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.springbootbackend.exception.ResoucresNotFoundEx;
import com.example.springbootbackend.model.Movie;
import com.example.springbootbackend.repository.MoviesRepo;

// @CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class MovieController {

    @Autowired
    private MoviesRepo movieRepo;

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    @PostMapping("/movies")
        public Movie createMovie(@RequestBody Movie movie) {
            return movieRepo.save(movie);
    }

    @GetMapping("/movies/{id}")
	public ResponseEntity<Movie> getEmployeeById(@PathVariable Long id) {
		Movie employee = movieRepo.findById(id)
				.orElseThrow(() -> new ResoucresNotFoundEx("Movie not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails){
        Movie movie = movieRepo.findById(id)
        .orElseThrow(() -> new ResoucresNotFoundEx("Movie not exist with id :" + id));

            movie.setTitle(movieDetails.getTitle());
            movie.setRating(movieDetails.getRating());
            movie.setDuration(movieDetails.getDuration());
            movie.setGenre(movieDetails.getGenre());

            Movie updatedMovie = movieRepo.save(movie);
            return ResponseEntity.ok(updatedMovie);

    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMovie(@PathVariable Long id) {
        Movie movie = movieRepo.findById(id)
        .orElseThrow(() -> new ResoucresNotFoundEx("Movie not exist with id :" + id));

        movieRepo.delete(movie);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    
}