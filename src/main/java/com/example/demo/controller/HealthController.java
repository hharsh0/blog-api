package com.example.demo.controller;

import com.example.demo.model.dto.app.HealthStatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/health")
    public ResponseEntity<HealthStatusDTO> healthCheck() {
        long startTime = System.currentTimeMillis();
        HealthStatusDTO healthStatus = new HealthStatusDTO("UP", "Application is running!",0);
        long latency = System.currentTimeMillis() - startTime;
        healthStatus.setLatency(latency);
        return ResponseEntity.ok(healthStatus);
    }
}
