package com.dev.springai.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @PostMapping
    public String chat(@RequestParam(name = "message") String message) {
        return chatClient.prompt().user(message).call().content();
    }

    @GetMapping
    public Flux<String> stream(@RequestParam(name = "message") String message){
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }
}

