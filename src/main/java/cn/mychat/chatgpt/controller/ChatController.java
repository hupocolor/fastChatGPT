package cn.mychat.chatgpt.controller;

import cn.mychat.chatgpt.pojo.ChatInput;
import cn.mychat.chatgpt.pojo.ChatRequest;
import cn.mychat.chatgpt.pojo.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ChatController {

    @Value("${openai.api.key}")
    private String apiKey;

    @PostMapping("/chat")
    public String chatWithGPT(@RequestBody String userInput) {
        WebClient webClient = WebClient.builder().baseUrl("https://api.openai.com/v1").defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey).build();

        ChatInput chatInput = new ChatInput(userInput);
        ChatRequest chatRequest = new ChatRequest(chatInput);

        ChatResponse chatResponse = webClient.post()
                .uri("/engines/davinci-codex/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(chatRequest))
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();

        if (chatResponse != null && chatResponse.getChoices() != null && !chatResponse.getChoices().isEmpty()) {
            return chatResponse.getChoices().get(0).getText();
        }

        return "ChatGPT 未响应";
    }
}
