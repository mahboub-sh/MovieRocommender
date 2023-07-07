package com.recommender.sever.model.Service;

import com.recommender.sever.model.DTOs.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OmdbAPIService {

    @Qualifier("omdbapiRestTemplate")
    @Autowired
    RestTemplate restTemplate ;

    @Value("${omdbapi.url}")
    private String url ;

    @Value("${omdbapi.key}")
    private String key ;

    public List<MovieDTO> getMoviesDetails(List<String> moviesName){

        return moviesName.stream()
                .filter( name -> moviesName!=null)
                .map( name -> getMoviesDetails(name)).toList() ;
    }

    public MovieDTO getMoviesDetails( String name ){

        MovieDTO body = restTemplate.getForEntity(
                "%s/?t=%s&apikey=%s".formatted(url, name, key), MovieDTO.class).getBody();
        if( body.poster() == null){
            return null ;
        }
        return body ;
    }


}
