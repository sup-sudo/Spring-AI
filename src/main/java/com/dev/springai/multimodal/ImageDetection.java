package com.dev.springai.multimodal;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/image")
public class ImageDetection {


    private final ChatClient chatClient;
    @Value("classpath:/images/imageForDetection.jpg")
    Resource sampleImage;

    private final OpenAiImageModel openAiImageModel;

    ImageDetection(@Qualifier("chatClient") ChatClient chatClient, OpenAiImageModel openAiImageModel) {
        this.chatClient = chatClient;
        this.openAiImageModel = openAiImageModel;
    }

    @GetMapping("/detect")
    public String detectImage() {
        return chatClient
                .prompt()
                .user(u -> {
                    u.text("Can you describe what you see in the image and list out any products you can recognize?");
                    u.media(MediaType.IMAGE_JPEG, sampleImage);
                })
                .call()
                .content();
    }

    @GetMapping("/generate")
    public ResponseEntity<Map<String,String>> generateImage(@RequestParam(name = "prompt", defaultValue = "Sunset in Paris") String prompt ) {
        ImageOptions imageOptions = OpenAiImageOptions.builder()
                .model("dall-e-3")
                .width(1024)
                .height(1024)
                .quality("hd")
                .style("vivid")
                .build();
        ImagePrompt imagePrompt = new ImagePrompt(prompt, imageOptions);
        ImageResponse call = openAiImageModel.call(imagePrompt);
        String url = call.getResult().getOutput().getUrl();
        return ResponseEntity.ok(Map.of("url", url));
    }
}
