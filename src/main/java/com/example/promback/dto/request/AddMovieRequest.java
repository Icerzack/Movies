package com.example.promback.dto.request;

import com.example.promback.dto.MovieDTO;
import lombok.Data;

@Data
public class AddMovieRequest {
  public MovieDTO movie;
}
