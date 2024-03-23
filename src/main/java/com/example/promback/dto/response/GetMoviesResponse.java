package com.example.promback.dto.response;

import com.example.promback.dto.MovieDTO;
import java.util.List;
import lombok.Data;

@Data
public class GetMoviesResponse {
  public List<MovieDTO> list;
}
