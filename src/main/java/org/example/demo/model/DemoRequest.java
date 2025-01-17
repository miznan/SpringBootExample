package org.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DemoRequest {

    @NotBlank(message = "Data cannot be blank")
    private String data;

    private String name;
    private String description;
}
