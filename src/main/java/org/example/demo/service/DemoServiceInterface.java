package org.example.demo.service;

import org.example.demo.model.DemoRequest;
import org.example.demo.model.DemoResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface DemoServiceInterface {

    public DemoResponse save(DemoRequest demoRequest);

    public DemoResponse update(UUID id, DemoRequest demoRequest);

    public Page<DemoResponse> getAll(int page, int size);

}
