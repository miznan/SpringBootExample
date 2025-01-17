package org.example.demo.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.f4b6a3.uuid.UuidCreator;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.model.DemoEntity;
import org.example.demo.model.DemoRequest;
import org.example.demo.model.DemoResponse;
import org.example.demo.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Slf4j
public class DemoServiceImpl implements DemoServiceInterface {

    @Autowired
    private DemoRepository demoRepository;

    @Transactional
    @Override
    public DemoResponse save(DemoRequest demoRequest) {
        log.info("Saving data: {}", demoRequest);
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setData(demoRequest.getData());
        demoEntity.setId(UUID.randomUUID());
        demoEntity.setName(demoRequest.getName());
        demoEntity.setDescription(demoRequest.getDescription());
        var savedEntity = demoRepository.save(demoEntity);
        return new DemoResponse(savedEntity.getId(),savedEntity.getName(),savedEntity.getDescription(),savedEntity.getData());
    }

    @Transactional
    @Override
    public DemoResponse update(UUID id, DemoRequest demoRequest) {
        log.info("Updating data with ID: {} ", id);
        DemoEntity demoEntity = demoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));
        demoEntity.setData(demoRequest.getData());
        demoEntity.setName(demoRequest.getName());
        demoEntity.setDescription(demoRequest.getDescription());
        var updatedEntity = demoRepository.save(demoEntity);
        return new DemoResponse(updatedEntity.getId(),updatedEntity.getName(),updatedEntity.getDescription(),updatedEntity.getData());
    }

    @Transactional
    @Override
    public Page<DemoResponse> getAll(int page, int size) {
        log.info("Get all data for page: {}, with size: {}", page, size);
        return demoRepository.findAll(PageRequest.of(page,size))
                .map(demoEntity -> new DemoResponse(demoEntity.getId(), demoEntity.getName(),demoEntity.getDescription(),demoEntity.getData()));
    }
}
