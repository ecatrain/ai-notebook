package com.ainotebook.core.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotebookService {

    @Value("${openai.chatgpt.url}")
    private String OPEN_AI_URL;
    
    @Value("${openai.chatgpt.model}")
    private String OPEN_AI_MODEL;

    @Value("${openai.api.key}")
    private String OPEN_AI_API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public String obtainQuote() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPEN_AI_API_KEY);
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectMessage = new JSONObject();
        jsonObjectMessage.put("role", "user");
        jsonObjectMessage.put("content", "Send me a motivational quote please");
        ArrayList<JSONObject> messages = new ArrayList<>();
        messages.add(jsonObjectMessage);
        jsonObject.put("model", OPEN_AI_MODEL);
        jsonObject.put("messages", messages);

        HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
    
        String resultAsJsonStr = restTemplate.postForObject(OPEN_AI_URL, request, String.class);
        JsonNode root = objectMapper.readTree(resultAsJsonStr);
    
        log.info(root.toString());
        
        return root.get("choices").get(0).get("message").get("content").toString();
    }
    
}
