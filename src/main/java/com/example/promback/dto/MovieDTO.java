package com.example.promback.dto;

import lombok.Data;

@Data
public class MovieDTO {
  public int id;
  public String title;
  public int year;
  public int director;
  public String length;
  public int rating;
}
