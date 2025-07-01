package org.example.fz2hworkflow.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GatewaysTaskWorker extends BaseWorker {

    private static final Logger log = LoggerFactory.getLogger(GatewaysTaskWorker.class);

    @Override
    public void run(ApplicationArguments args) {
        String task1 = "gatewayEndpoint";
        String task2 = "gatewayTrueTask";
        String task3 = "gatewayFalseTask";
        String task4 = "gatewayJoinTask";
        String task5 = "";

        client.subscribe(task1)
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    String traceId = externalTask.getVariable("traceId");
                    Boolean flag = externalTask.getVariable("flag");
                    log.info("[{}] {}, flag:{}", traceId, task1, flag);

                    externalTaskService.complete(externalTask);
                })
                .open();

        client.subscribe(task2)
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    String traceId = externalTask.getVariable("traceId");
                    Boolean flag = externalTask.getVariable("flag");
                    log.info("[{}] {}, flag:{}", traceId, task2, flag);

                    externalTaskService.complete(externalTask);
                })
                .open();

        client.subscribe(task3)
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    String traceId = externalTask.getVariable("traceId");
                    Boolean flag = externalTask.getVariable("flag");
                    log.info("[{}] {}, flag:{}", traceId, task3, flag);

                    externalTaskService.complete(externalTask, Map.of("flag", !flag));
                })
                .open();

        client.subscribe(task4)
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {
                    String traceId = externalTask.getVariable("traceId");
                    Boolean flag = externalTask.getVariable("flag");
                    log.info("[{}] {}, flag:{}", traceId, task4, flag);

                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}
