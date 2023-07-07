package com.recommender.sever.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.recommender.sever.model.DTOs.MessageDTO;

import java.util.List;

public record OpenAIRequestDTO (String model ,@JsonProperty("messages") List<MessageDTO> messageDTOS){

}
