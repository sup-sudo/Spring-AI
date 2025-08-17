package com.dev.springai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple")
public class SimpleController {
    private final ChatClient chatClient;

    SimpleController(@Qualifier("chatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/posts/new")
    public String newPost(@RequestParam(name = "topic", defaultValue = "JDK virtual Threads") String topic) {
        String systemMessage = """
                You are a professional blog writer AI. 
                Your task is to generate clear, engaging, and well-structured blog posts based on the topic provided by the user. 
                The blog should:
                - Be informative and easy to read.
                - Stay under 500 words.
                - Have a logical flow with an introduction, body, and conclusion.
                - Avoid repetition and filler content.
                - Maintain a professional but approachable tone.

                Only return the blog content. Do not include any extra commentary or instructions.
                """;

        return chatClient.prompt()
                .system(systemMessage)
                .user("Write a blog post about " + topic)
                .call()
                .content();
    }

    @GetMapping("/unstructured")
    public String unstructured(@RequestParam(name = "location") String location){
        String systemInstruction = """
                You are a professional travel agent AI.
                Your task is to generate clear, engaging, and well-structured travel plans based on the location provided by the user.
                The travel plan should:
                - Be informative and easy to read.
                - Stay under 200 words.
                - Have a logical flow with an introduction, body, and conclusion.
                - Avoid repetition and filler content.
                - Maintain a professional but approachable tone.

                Only return the travel plan content. Do not include any extra commentary or instructions.
                """;
        return chatClient.prompt()
                .system(systemInstruction)
                .user("Write a travel plan to " + location)
                .call()
                .content();
    }
    @GetMapping("/structured")
    public Itinerary structured(@RequestParam(name = "location") String location){
        String systemInstruction = """
                You are a professional travel agent AI.
                Your task is to generate clear, engaging, and well-structured travel plans based on the location provided by the user.
                The travel plan should:
                - Be informative and easy to read.
                - Stay under 200 words.
                - Have a logical flow with an introduction, body, and conclusion.
                - Avoid repetition and filler content.
                - Maintain a professional but approachable tone.

                Only return the travel plan content. Do not include any extra commentary or instructions.
                """;
        return chatClient.prompt()
                .system(systemInstruction)
                .user("Write a travel plan to " + location)
                .call()
                .entity(Itinerary.class);
    }
}
