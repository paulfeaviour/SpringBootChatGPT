package com.savvy.demo.restservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

@RestController
public class GenerateController {

    private static final String SYSTEM_TASK_MESSAGE = """
            You are an API server that responds in a JSON format.
            Don't say anything else. Respond only with the JSON.

            The user have provided the following context, %s! use this for the creation of an email.
            """;

    @Value("${openai.key}")
    private String apiKey;

    @PostMapping("/generateEmail")
    public GenerateResponse generate(@RequestBody GenerateRequest request) {
        String prompt = String.format(SYSTEM_TASK_MESSAGE, request.getUserPrompt());

        OpenAiService service = new OpenAiService(apiKey);

        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(
                ChatMessageRole.SYSTEM.value(), prompt);

        messages.add(systemMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(200)
                .logitBias(new HashMap<>())
                .build();

        ChatCompletionResult res = service.createChatCompletion(chatCompletionRequest);

        return new GenerateResponse(res.getChoices().toString());
    }

}