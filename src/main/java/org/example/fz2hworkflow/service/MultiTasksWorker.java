package org.example.fz2hworkflow.service;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MultiTasksWorker extends BaseWorker {

    @Override
    public void run(ApplicationArguments args) {
        client.subscribe("action1")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    Integer traceId = externalTask.getVariable("traceId");
                    Integer customerId = externalTask.getVariable("customerId");
                    System.out.println("[Action1] CustomerId received, traceId: " + traceId + ", customerId: "+ customerId);
                    externalTaskService.complete(externalTask,
                            Map.of("customerId", customerId + 1));
                })
                .open();

        client.subscribe("action2")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    Integer traceId = externalTask.getVariable("traceId");
                    Integer customerId = externalTask.getVariable("customerId");
                    System.out.println("[Action1] CustomerId received, traceId: " + traceId + ", customerId: "+ customerId);
                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}
