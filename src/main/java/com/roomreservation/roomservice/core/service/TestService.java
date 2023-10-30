package com.roomreservation.roomservice.core.service;

import com.roomreservation.roomservice.core.domain.TestEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TestService {

    public void saveRecord() {
        TestEntity.persist(new TestEntity());
        TestEntity.flush();
    }

    public Object readData() {
        List<?> all = TestEntity.listAll();
        return all.stream().skip(all.size() - 1).findFirst().orElse(null);
    }

}
