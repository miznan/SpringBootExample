package org.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DemoResponse {

    private UUID id;
    private String name;
    private String description;
    private String data;

}
