package chatgptTest.chatgptTest.controller;

import chatgptTest.chatgptTest.service.HuggingFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/gpt")
public class basicController {

    private final HuggingFaceService huggingFaceService;

    @Autowired
    public basicController(HuggingFaceService huggingFaceService) {
        this.huggingFaceService = huggingFaceService;
    }

    @PostMapping("/generate")
    public String generateText(@RequestBody String prompt) {
        return huggingFaceService.generateText(prompt);
    }


}
