package chatgptTest.chatgptTest.controller;

import chatgptTest.chatgptTest.service.HuggingFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/qwen")
public class basicController {

    private final HuggingFaceService huggingFaceService;

    public basicController(HuggingFaceService huggingFaceService) {
        this.huggingFaceService = huggingFaceService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateText(@RequestBody String prompt) {

        System.out.println("in here");
        String generatedText = huggingFaceService.generateText(prompt);
        return ResponseEntity.ok(generatedText);
    }
}
