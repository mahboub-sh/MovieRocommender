package com.recommender.sever.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieDTO(  @JsonProperty("Title") String title,
                       @JsonProperty("Year") String year,
                       @JsonProperty("Poster")String poster,
                       @JsonProperty("Runtime") String runTime,
                       String imdbRating ,
                       String imdbVotes
                         ){ }
