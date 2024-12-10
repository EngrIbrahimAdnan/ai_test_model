package chatgptTest.chatgptTest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;


@Service
public class HuggingFaceService {

    private final String API_URL = "https://api-inference.huggingface.co/models/Qwen/Qwen2.5-72B-Instruct";
    private final String API_TOKEN = "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";

    private final RestTemplate restTemplate;

    public HuggingFaceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateText(String prompt) {
        try {
            // Create a map for the JSON body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("inputs", prompt);

            // Add parameters to control the model's output
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("max_length", 50);
            parameters.put("temperature", 0.7);

            requestBody.put("parameters", parameters);

            // Convert the map to a JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

            // Log the JSON to check for errors
            System.out.println("Request Body: " + jsonRequestBody);

            // Set up headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_TOKEN);

            // Make the API call
            HttpEntity<String> entity = new HttpEntity<>(jsonRequestBody, headers);
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while generating text.";
        }
    }
}
