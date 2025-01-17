package org.example.demo.repository;

import org.example.demo.model.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DemoRepository extends JpaRepository<DemoEntity, UUID> {
}
