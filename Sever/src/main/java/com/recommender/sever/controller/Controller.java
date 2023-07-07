package com.recommender.sever.controller;


import com.recommender.sever.model.DTOs.MovieDTO;
import com.recommender.sever.model.DTOs.UserPreferenceDTO;
import com.recommender.sever.model.Service.OmdbAPIService;
import com.recommender.sever.model.Service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class Controller {

    @Autowired
    Services services;
    @Autowired
    OmdbAPIService omdbAPIService ;
    @PostMapping
    public ResponseEntity<List<MovieDTO>> getRecommendedMovies(@RequestBody UserPreferenceDTO userPreferenceDTO) {
        // don't forget error handling
        List<MovieDTO> movieDTOS = services.getRecomendation(userPreferenceDTO) ;
        if( movieDTOS == null || movieDTOS.isEmpty()){
            ResponseEntity.notFound().build() ;
        }
        return ResponseEntity.ok(movieDTOS) ;
    }

}

