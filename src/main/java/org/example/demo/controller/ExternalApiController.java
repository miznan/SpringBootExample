package org.example.demo.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.model.DemoNestedResponse;
import org.example.demo.model.DemoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/external")
public class ExternalApiController {

    @PostMapping
    public ResponseEntity<DemoNestedResponse> callExternalApi(
            @Valid @RequestBody DemoRequest demoRequest
    ){
        try {
            log.info("nested api call with data: {}", demoRequest);
            DemoNestedResponse response = new DemoNestedResponse();
            response.setName("nested");
            response.setDemoResponse(demoRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Caught error : {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
}
