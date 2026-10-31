package com.tecsup.petclinic.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class TypeServiceTest {
    @Autowired TypeService typeService;

    @Test
    void createUpdateFindAndDeleteType() {
        int id = typeService.create("test-type", "Test type", true, "small", 5, "low");
        assertEquals("Test type", typeService.findById(id).get("description"));

        assertEquals(1, typeService.update(id, "test-type", "Updated type", true, "small", 5, "low"));
        assertEquals("Updated type", typeService.findById(id).get("description"));

        assertEquals(1, typeService.delete(id));
        assertThrows(DataAccessException.class, () -> typeService.findById(id));
    }
}
