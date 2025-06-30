package org.example.fz2hworkflow.service;


import jakarta.annotation.PostConstruct;
import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;


public abstract class BaseWorker implements ApplicationRunner {

    protected ExternalTaskClient client;

    @PostConstruct
    private void init() {
        client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000) // milliseconds
                .build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("baseWorker started");
//        ExternalTaskClient client = ExternalTaskClient.create()
//                .baseUrl("http://localhost:8080/engine-rest")
//                .asyncResponseTimeout(10000) // milliseconds
//                .build();
    }
}
