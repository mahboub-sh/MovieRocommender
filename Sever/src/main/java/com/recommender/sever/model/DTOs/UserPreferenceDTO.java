package com.recommender.sever.model.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record UserPreferenceDTO(String genres,
                                String duration,
                                String minReleaseYear,
                                String maxReleaseYear,
                                String minimumRate) {
}
