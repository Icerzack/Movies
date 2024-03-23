package com.example.promback.dto.request;

import com.example.promback.dto.MovieDTO;
import lombok.Data;

@Data
public class PatchMovieRequest {
  MovieDTO movie;
}
