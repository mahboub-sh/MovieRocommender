package com.recommender.sever.model.Service;

import com.recommender.sever.model.DTOs.MessageDTO;
import com.recommender.sever.model.DTOs.OpenAIRequestDTO;
import com.recommender.sever.model.DTOs.OpenAIResponseDTO;
import com.recommender.sever.model.DTOs.UserPreferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenAIService {
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private  String model  ;

    @Value("${openai.api.url}")
    private  String url ;


    public String getOpenAISuggestion(String prompt) {
        List<MessageDTO>  messageDTOS= createRequestMessage( prompt) ;
        String result = chatWithOpenAI(messageDTOS) ;
        return result ;
    }

    public String  chatWithOpenAI( List<MessageDTO> messageDTOS){

        try {
            OpenAIRequestDTO request = new OpenAIRequestDTO(model, messageDTOS);
            OpenAIResponseDTO response = restTemplate.postForObject(url, request, OpenAIResponseDTO.class);
            if (response == null || response.choices() == null || response.choices().isEmpty()) {
                return null;
            }
            return response.choices().get(0).messageDTO().content();
        }catch (Exception e){
            return null ;
        }
    }

    public List<MessageDTO> createRequestMessage( String prompt){
        List<MessageDTO> messageDTOS = new ArrayList<>() ;
        messageDTOS.add( new MessageDTO("user" , prompt));
        return messageDTOS ;
    }


}
