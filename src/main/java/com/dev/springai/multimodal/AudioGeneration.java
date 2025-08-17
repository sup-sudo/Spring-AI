package com.dev.springai.multimodal;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audio")
public class AudioGeneration {


    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;

    public AudioGeneration(OpenAiAudioSpeechModel openAiAudioSpeechModel) {
        this.openAiAudioSpeechModel = openAiAudioSpeechModel;
    }

    @GetMapping
    public ResponseEntity<byte[]> generateSpeech(@RequestParam(defaultValue = "Hi I am chat gpt", name = "text") String text) {

        OpenAiAudioSpeechOptions build = OpenAiAudioSpeechOptions.builder()
                .model("tts-1-hd")
                .voice(OpenAiAudioApi.SpeechRequest.Voice.SAGE)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .speed(1.0f)
                .build();
        SpeechPrompt speechPrompt = new SpeechPrompt(text, build);
        SpeechResponse call = openAiAudioSpeechModel.call(speechPrompt);
        byte[] output = call.getResult().getOutput();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE,"audio/mpeg")
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment: filename=\speech.mp3\"")
                .body(output);
    }
}
