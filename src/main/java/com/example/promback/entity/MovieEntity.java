package com.example.promback.entity;

import java.util.Date;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Movies")
public class MovieEntity {
  @Id
  @Column
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "year")
  private Integer year;

  @Column(name = "director")
  private Integer director;

  @Column(name = "length")
  private String length;

  @Column(name = "rating")
  private Integer rating;


}
