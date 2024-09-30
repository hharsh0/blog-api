package com.example.demo.model.dto.app;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HealthStatusDTO {
    private String status;
    private String message;
    private long latency;
}