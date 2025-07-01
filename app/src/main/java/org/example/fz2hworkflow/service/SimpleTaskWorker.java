package org.example.fz2hworkflow.service;

import org.camunda.bpm.client.ExternalTaskClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleTaskWorker extends BaseWorker {

    private static final Logger log = LoggerFactory.getLogger(SimpleTaskWorker.class);

    @Override
    public void run(ApplicationArguments args) {
        client.subscribe("doAutoShippingTask")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    log.info("Hello World from doAutoShippingTask");
                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}
