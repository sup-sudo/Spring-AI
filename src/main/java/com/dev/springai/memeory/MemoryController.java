package com.dev.springai.memeory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memory")
public class MemoryController {
    private final ChatClient chatClient;

    public MemoryController(ChatClient.Builder chatClient, ChatMemory chatMemory) {
        this.chatClient = chatClient.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }


    @GetMapping("/chat")
    public String chat(@RequestParam("text")String text) {
        return chatClient
                .prompt()
                .user(text)
                .call()
                .content();
    }
}
