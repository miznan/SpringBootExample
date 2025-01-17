package org.example.demo.model;

import lombok.Data;

@Data
public class DemoNestedResponse {

    private String name;
    private DemoRequest demoResponse;
}
