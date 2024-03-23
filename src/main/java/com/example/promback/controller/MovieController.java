package com.example.promback.controller;

import com.example.promback.dto.request.AddMovieRequest;
import com.example.promback.dto.request.PatchMovieRequest;
import com.example.promback.dto.response.AddMovieResponse;
import com.example.promback.dto.response.GetMoviesResponse;
import com.example.promback.dto.response.PatchMovieResponse;
import com.example.promback.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

  @Autowired
  MovieService movieService;

  @GetMapping("/api/movies")
  public ResponseEntity<GetMoviesResponse> getAllMovies() {
    return movieService.getAllMovies();
  }

  @PostMapping("/api/movies")
  public ResponseEntity<AddMovieResponse> addNewMovie(@RequestBody AddMovieRequest dto) {
    return movieService.addMovie(dto);
  }

  @PatchMapping("/api/movies/{id}")
  public ResponseEntity<PatchMovieResponse> updateMovie(@RequestBody PatchMovieRequest dto, @PathVariable int id) {
    return movieService.updateMovie(dto, id);
  }

  @DeleteMapping("/api/movies/{id}")
  public ResponseEntity<String> deleteMovie(@PathVariable int id) {
    return movieService.deleteMovie(id);
  }
}
