package com.recommender.sever.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.recommender.sever.model.DTOs.MessageDTO;

import java.util.List;

public record OpenAIResponseDTO(List<Choice> choices){
    public record Choice(int index ,@JsonProperty("message") MessageDTO messageDTO){ }
}
