package org.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "t_demo")
public class DemoEntity {

    @Id
    private UUID id;

    private String name;

    private String description;

    @NotBlank(message = "Data cannot be blank")
    private String data;

}
