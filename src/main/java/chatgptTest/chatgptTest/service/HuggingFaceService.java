package chatgptTest.chatgptTest.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


@Service
public class HuggingFaceService {


    @Value("${huggingface.api.url}")
    private String apiUrl;

    @Value("${huggingface.api.token}")
    private String apiToken;

    private final RestTemplate restTemplate;

    public HuggingFaceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateText(String prompt) {
        // Set up request headers with the API token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.set("Content-Type", "application/json");

        // Create request payload
        String requestBody = "{\"inputs\": \"" + prompt + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send POST request to the Hugging Face API
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return response.getBody(); // Return the API response
    }
}
