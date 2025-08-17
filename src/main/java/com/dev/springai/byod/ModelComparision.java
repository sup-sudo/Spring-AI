package com.dev.springai.byod;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/model-comparision")
public class ModelComparision
{
    private final ChatClient chatClient;

    public ModelComparision(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat() {
        return chatClient
                .prompt()
                .user("can you give me an up to date list popular large language models and their current context windows?")
                .call()
                .content();
    }


    @GetMapping("/chat-stuffed")
    public String chatWithPromptStuffing() {
        String userMsg = "{\n" +
                "  \"OpenAI\": [\n" +
                "    {\n" +
                "      \"name\": \"GPT-3.5 Turbo\",\n" +
                "      \"context_window_tokens\": 4096\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"GPT-4 (standard)\",\n" +
                "      \"context_window_tokens\": 8192\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"GPT-4 (extended)\",\n" +
                "      \"context_window_tokens\": 32768\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"GPT-4 Turbo / GPT-4o\",\n" +
                "      \"context_window_tokens\": 128000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"GPT-4.5 Preview\",\n" +
                "      \"context_window_tokens\": 128000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"GPT-4.1 (Full / Mini / Nano)\",\n" +
                "      \"context_window_tokens\": 1000000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"o3 / o3-mini / o3-pro\",\n" +
                "      \"context_window_tokens\": 200000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"GPT-5 (main / mini / nano / thinking)\",\n" +
                "      \"context_window_tokens\": 256000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"GPT-5 Turbo\",\n" +
                "      \"context_window_tokens\": 1000000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"gpt-oss-120B / gpt-oss-20B\",\n" +
                "      \"context_window_tokens\": 128000\n" +
                "    }\n" +
                "  ],\n" +
                "  \"Google\": [\n" +
                "    {\n" +
                "      \"name\": \"Gemini 2.5 Pro / Flash (MAX mode)\",\n" +
                "      \"context_window_tokens\": {\n" +
                "        \"input\": 1048576,\n" +
                "        \"output\": 65536\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Gemini 2.0 Flash\",\n" +
                "      \"context_window_tokens\": {\n" +
                "        \"input\": 1048576,\n" +
                "        \"output\": 8192\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Gemini 1.5 Pro\",\n" +
                "      \"context_window_tokens\": 2000000\n" +
                "    }\n" +
                "  ],\n" +
                "  \"Anthropic\": [\n" +
                "    {\n" +
                "      \"name\": \"Claude 3 (Opus, Sonnet, Haiku)\",\n" +
                "      \"context_window_tokens\": 200000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Claude Sonnet 4 (Enterprise)\",\n" +
                "      \"context_window_tokens\": 1000000\n" +
                "    }\n" +
                "  ],\n" +
                "  \"xAI\": [\n" +
                "    {\n" +
                "      \"name\": \"Grok-4\",\n" +
                "      \"context_window_tokens\": 256000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Grok-3\",\n" +
                "      \"context_window_tokens\": 1000000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Grok-1.5\",\n" +
                "      \"context_window_tokens\": 131072\n" +
                "    }\n" +
                "  ],\n" +
                "  \"Meta\": [\n" +
                "    {\n" +
                "      \"name\": \"LLaMA 3 (70B)\",\n" +
                "      \"context_window_tokens\": 8000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"LLaMA 3 (400B enterprise variant)\",\n" +
                "      \"context_window_tokens\": 32000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"LLaMA 4 (rumored 2025 release)\",\n" +
                "      \"context_window_tokens\": 128000\n" +
                "    }\n" +
                "  ],\n" +
                "  \"Alibaba\": [\n" +
                "    {\n" +
                "      \"name\": \"Qwen 2.5 (72B)\",\n" +
                "      \"context_window_tokens\": 128000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Qwen 2.5 Max\",\n" +
                "      \"context_window_tokens\": 1000000\n" +
                "    }\n" +
                "  ],\n" +
                "  \"Mistral\": [\n" +
                "    {\n" +
                "      \"name\": \"Mixtral 8x22B\",\n" +
                "      \"context_window_tokens\": 64000\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Mistral Large (2025)\",\n" +
                "      \"context_window_tokens\": 128000\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
        return chatClient
                .prompt()
                .user("can you give me an up to date list popular large language models and their current context windows?")
                .system(userMsg)
                .call()
                .content();
    }
}
