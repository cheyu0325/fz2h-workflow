package org.example.fz2hworkflow.service;


import jakarta.annotation.PostConstruct;
import org.camunda.bpm.client.ExternalTaskClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


public abstract class BaseWorker implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(BaseWorker.class);
    protected ExternalTaskClient client;

    @PostConstruct
    private void init() {
        log.info("Initializing external task client");
        client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000) // milliseconds
                .build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Running external task client");
    }

    protected void doPost(String url, Map<String, Object> payload) {
//        String url = "http://localhost:8080/engine-rest/process-definition/key/Process_0horfzd/start";
//
//        // 準備 body payload
//        Map<String, Object> payload = Map.of(
//                "variables", Map.of(
//                        "traceId", Map.of("value", 12345678, "type", "Integer"),
//                        "customerId", Map.of("value", 9999, "type", "Integer")
//                )
//        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("Response: " + response.getBody());
    }
}
