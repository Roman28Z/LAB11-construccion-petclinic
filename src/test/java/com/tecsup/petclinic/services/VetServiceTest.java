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
class VetServiceTest {
    @Autowired VetService vetService;

    @Test
    void createUpdateFindAndDeleteVet() {
        int id = vetService.create("TestVet", "Olanda", "vet-test@example.test", "999111222", true);
        assertEquals("TestVet", vetService.findById(id).get("first_name"));

        assertEquals(1, vetService.update(id, "UpdatedVet", "Olanda", "vet-test@example.test", "999111222", true));
        assertEquals("UpdatedVet", vetService.findById(id).get("first_name"));

        assertEquals(1, vetService.delete(id));
        assertThrows(DataAccessException.class, () -> vetService.findById(id));
    }
}
