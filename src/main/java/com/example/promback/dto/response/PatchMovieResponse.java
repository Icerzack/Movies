package com.example.promback.dto.response;

import com.example.promback.dto.MovieDTO;
import lombok.Data;

@Data
public class PatchMovieResponse {
  public MovieDTO movie;
}
