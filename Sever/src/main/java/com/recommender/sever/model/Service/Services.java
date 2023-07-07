package com.recommender.sever.model.Service;

import com.recommender.sever.model.DTOs.MovieDTO;
import com.recommender.sever.model.DTOs.UserPreferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Services {

    @Autowired
    private OpenAIService openAIService ;
    @Autowired
    private OmdbAPIService omdbAPIService;


    public List<MovieDTO> getRecomendation(UserPreferenceDTO userPreferenceDTO) {
        try {
            String prompt = DTOToPrompt(userPreferenceDTO) ;
            String openAIAnswer = openAIService.getOpenAISuggestion(prompt) ;
            List<String> moviesName =parseMovieNames(openAIAnswer) ;
            return omdbAPIService.getMoviesDetails( moviesName) ;
        }catch (Exception e){
            return null ;
        }

//        return null ;
    }
    public String DTOToPrompt(UserPreferenceDTO userPreferenceDTO) {
       return  "recommend me movie with" + userPreferenceDTO.genres() +
               "genre and "  +userPreferenceDTO.duration()  +
               " duration.send answer as Json lik >> { \"titles\" :{[ ]}" ;
    }

    public List<String> parseMovieNames( String openAIAnswer){
        String cleanAnswer = openAIAnswer.substring(openAIAnswer.indexOf("[")+1
        , openAIAnswer.indexOf("]")) ;
        return Arrays.stream(cleanAnswer.split(","))
                .map( line -> line.replace("\"", ""))
                .toList();
    }




}
