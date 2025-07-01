package org.example.fz2hworkflow.service;

import org.camunda.bpm.client.ExternalTaskClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MultiTasksWorker extends BaseWorker {

    private static final Logger log = LoggerFactory.getLogger(MultiTasksWorker.class);

    @Override
    public void run(ApplicationArguments args) {
        client.subscribe("action1")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    String traceId = externalTask.getVariable("traceId");
                    Integer customerId = externalTask.getVariable("customerId");
                    log.info("[Action1] CustomerId received, traceId: " + traceId + ", customerId: "+ customerId);
                    externalTaskService.complete(externalTask,
                            Map.of("customerId", customerId + 1));
                })
                .open();

        client.subscribe("action2")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    String traceId = externalTask.getVariable("traceId");
                    Integer customerId = externalTask.getVariable("customerId");
                    log.info("[Action2] CustomerId received, traceId: " + traceId + ", customerId: "+ customerId);
                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}
