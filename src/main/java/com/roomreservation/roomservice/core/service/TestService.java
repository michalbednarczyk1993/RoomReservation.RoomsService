package com.roomreservation.roomservice.core.service;

import com.roomreservation.roomservice.core.domain.TestEntity;
import com.roomreservation.roomservice.exceptions.DefaultExceptionMapper;
import com.roomreservation.roomservice.exceptions.NoContentException;
import io.quarkus.arc.profile.UnlessBuildProfile;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@UnlessBuildProfile("prod")
public class TestService {

    private static final Logger LOGGER = Logger.getLogger(DefaultExceptionMapper.class.getName());


    @Transactional
    public void saveRecord() {
        TestEntity entity = new TestEntity();

        entity.text = "save record";
        entity.persist();
    }

    public String readData(String text) {
        List<TestEntity> all = TestEntity.listAll();
        LOGGER.log(Level.INFO, "Size of TestEntity: " + all.size());

        if (!all.isEmpty()) {
            TestEntity entity = TestEntity
                    .findByTextOptional(text)
                    .orElseThrow(NoContentException::new);
            return entity.text;
        }
        return "There are no entities";
    }

    public void throwException(String exception) {
        switch (exception) {
            case "ConstraintViolationException" ->
                    throw new ConstraintViolationException("ConstraintViolationException", null);
            case "NoContentException" -> throw new NoContentException();
            case "DefaultException" -> throw new IllegalArgumentException();
        }
    }
}
