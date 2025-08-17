package com.dev.springai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prompt")
public class BankController{

    private final ChatClient chatClient;

    BankController(@Qualifier("chatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping
    public String prompt(@RequestParam(name = "message") String message) {

        // Guard rail to prevent the AI to only answer a question based on a particular topic based on The application of the Enterprise
        String systemInstruction = "You are a bank assistant. " +
                "You are to answer questions about banking and financial services. " +
                "Account Balances and Transactions" +
                "General banking services" +
                "If asked about anything non related to Banking please device a unique message to infrom the user to ask questions only regarding to bank";
        return chatClient
                .prompt()
                .user(message)
                .system(systemInstruction)
                .call()
                .content();
    }

}

