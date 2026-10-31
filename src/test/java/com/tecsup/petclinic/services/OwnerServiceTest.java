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
class OwnerServiceTest {
    @Autowired OwnerService ownerService;

    @Test
    void createUpdateFindAndDeleteOwner() {
        int id = ownerService.create("TestOwner", "Olanda", "123 Test St", "Lima", "999333444");
        assertEquals("TestOwner", ownerService.findById(id).get("first_name"));

        assertEquals(1, ownerService.update(id, "TestOwner", "Olanda", "123 Test St", "Arequipa", "999333444"));
        assertEquals("Arequipa", ownerService.findById(id).get("city"));

        assertEquals(1, ownerService.delete(id));
        assertThrows(DataAccessException.class, () -> ownerService.findById(id));
    }
}
