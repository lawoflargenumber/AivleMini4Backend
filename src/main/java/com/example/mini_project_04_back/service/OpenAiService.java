package com.example.mini_project_04_back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class OpenAiService {
    @Value("${openai.api-key}")
    private String openaiApiKey;


    public String generateImage(String prompt) {
        Map<String, Object> requestBody = Map.of(
                "prompt", prompt,
                "n", 1,
                "size", "512x512",
                "model", "dall-e-2"

        );
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/images/generations")
                .filter(logRequest())
                .filter(logResponse())
                .defaultHeader("Authorization", "Bearer " + openaiApiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();


        Map<String, Object> response = webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        List<Map<String, String>> data = (List<Map<String, String>>) response.get("data");
        return data.get(0).get("url");
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("=== [Request] ===");
            System.out.println(clientRequest.method() + " " + clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> System.out.println(name + ": " + value)));
            return Mono.just(clientRequest);
        });
    }

    private static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            System.out.println("=== [Response] ===");
            System.out.println("Status code: " + clientResponse.statusCode());
            clientResponse.headers().asHttpHeaders()
                    .forEach((name, values) -> values.forEach(value -> System.out.println(name + ": " + value)));
            return Mono.just(clientResponse);
        });
    }
}
