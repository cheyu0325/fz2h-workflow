package org.example.fz2hworkflow.service;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleTaskWorker implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000) // milliseconds
                .build();

        client.subscribe("doAutoShippingTask")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    System.out.println("Hello World from doAutoShippingTask");
                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}
