package org.example.demo.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.model.DemoEntity;
import org.example.demo.model.DemoRequest;
import org.example.demo.model.DemoResponse;
import org.example.demo.service.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class DemoController {

    @Autowired
    private DemoServiceImpl service;

    @PostMapping("/create")
    public ResponseEntity<DemoResponse> create(
            @Valid @RequestBody DemoRequest demo
    ) {
        log.info("Post request: {}", demo);
        try {
            var savedEntity = service.save(demo);
            log.info("Post response: {}", savedEntity);
            return ResponseEntity.status(HttpStatus.OK).body(savedEntity);
        } catch (Exception e){
            log.error("Error when create: {} ", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemoResponse> updateData(
            @PathVariable String id,
            @Valid @RequestBody DemoRequest demoRequest
    ) {
       log.info("update data for id: {} , with data: {}", id, demoRequest);
       try {

           var response = service.update(UUID.fromString(id),demoRequest);
           log.info("update response: {} ", response);
           return ResponseEntity.ok(response);
       } catch (Exception e){
           log.error("Error when update: {} ", e.getMessage());
           return ResponseEntity.badRequest().body(null);
       }
    }

    @GetMapping("/allData")
    public ResponseEntity<Page<DemoResponse>> getAllData(
            @RequestParam int page,
            @RequestParam int size
    ) {
        try {
            log.info("Get all data Request for pagination: page={}, size={}", page, size);
            Page<DemoResponse> entities = service.getAll(page, size);
            log.info("Get all data Response: {}", entities.getContent());
            return ResponseEntity.ok(entities);
        } catch (Exception e){
            log.error("Error when update: {} ", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

}
