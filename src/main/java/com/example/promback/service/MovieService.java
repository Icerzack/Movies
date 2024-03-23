package com.example.promback.service;

import com.example.promback.dto.MovieDTO;
import com.example.promback.dto.request.AddMovieRequest;
import com.example.promback.dto.request.PatchMovieRequest;
import com.example.promback.dto.response.AddMovieResponse;
import com.example.promback.dto.response.GetMoviesResponse;
import com.example.promback.dto.response.PatchMovieResponse;
import com.example.promback.entity.MovieEntity;
import com.example.promback.exception.InternalServerException;
import com.example.promback.exception.NotFoundException;
import com.example.promback.repository.MovieRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  @Autowired
  MovieRepository movieRepository;

  public ResponseEntity<GetMoviesResponse> getAllMovies() {
    List<MovieEntity> list = movieRepository.findAll();
    if (!list.isEmpty()) {
      GetMoviesResponse getMoviesResponse = new GetMoviesResponse();
      List<MovieDTO> convertedList = new ArrayList<>();
      for (MovieEntity m : list) {
        MovieDTO movieDTO = convertEntityToDto(m);
        convertedList.add(movieDTO);
      }
      getMoviesResponse.setList(convertedList);
      return ResponseEntity.status(200).body(getMoviesResponse);
    } else {
      throw new NotFoundException("No any movies");
    }
  }

  public ResponseEntity<AddMovieResponse> addMovie(AddMovieRequest addMovieRequest) {
    try {
      MovieEntity m = convertDTOToEntity(addMovieRequest.getMovie());
      MovieEntity movieEntity = movieRepository.save(m);
      AddMovieResponse addMovieResponse = new AddMovieResponse();
      addMovieResponse.setMovie(convertEntityToDto(movieEntity));
      return ResponseEntity.status(200).body(addMovieResponse);
    } catch (Exception e) {
      throw new InternalServerException(e.getMessage());
    }
  }

  public ResponseEntity<PatchMovieResponse> updateMovie(PatchMovieRequest patchMovieRequest, int id) {
    Optional<MovieEntity> entity = movieRepository.findById(id);
    if (entity.isEmpty()) {
      throw new NotFoundException("movie not found");
    } else {
      MovieEntity m = convertDTOToEntity(patchMovieRequest.getMovie());
      movieRepository.deleteById(id);
      MovieEntity movieEntity = movieRepository.save(m);
      PatchMovieResponse patchMovieResponse = new PatchMovieResponse();
      patchMovieResponse.setMovie(convertEntityToDto(movieEntity));
      return ResponseEntity.status(200).body(patchMovieResponse);
    }
  }

  public ResponseEntity<String> deleteMovie(int movieId) {
    try {
      movieRepository.deleteById(movieId);
    } catch (Exception e) {
      throw new NotFoundException("no such movie");
    }
    return ResponseEntity.status(204).body("");
  }

  private MovieEntity convertDTOToEntity(MovieDTO md) {
    MovieEntity m = new MovieEntity();
    m.setId(md.getId());
    m.setTitle(md.getTitle());
    m.setYear(md.getYear());
    m.setDirector(md.getDirector());
    m.setLength(md.getLength());
    m.setRating(md.getRating());
    return m;
  }

  private MovieDTO convertEntityToDto(MovieEntity me) {
    MovieDTO m = new MovieDTO();
    m.setId(me.getId());
    m.setTitle(me.getTitle());
    m.setYear(me.getYear());
    m.setDirector(me.getDirector());
    m.setLength(me.getLength());
    m.setRating(me.getRating());
    return m;
  }
}
