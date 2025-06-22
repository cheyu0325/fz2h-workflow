# fz2h-workflow
This is a personal workflow learning project.

## Simple Flow - Subscribe the topic with SimpleTaskWorker
```curl command
# Camunda setting with multi-tenancy practice 
curl -X POST http://localhost:8080/engine-rest/process-definition/key/Process_05fjmez/tenant-id/admin/start 
 \ -H "Content-Type: application/json" 
 \ -d '{}'
```

## Multitasks Flow - Subscribe the topic with MultiTasksWorker
```curl command
curl -X POST http://localhost:8080/engine-rest/process-definition/key/Process_0horfzd/start -H "Content-Type: application/json" -d '{ "variables": {
"traceId": { "value": "12345678", "type": "Integer" },
"customerId": { "value": 9999, "type": "Integer" }
} }'
```