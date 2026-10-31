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
class SpecialityServiceTest {
    @Autowired SpecialityService specialityService;

    @Test
    void createUpdateFindAndDeleteSpeciality() {
        int id = specialityService.create("test-specialty", "Office A", 8, 17);
        assertEquals("Office A", specialityService.findById(id).get("office"));

        assertEquals(1, specialityService.update(id, "test-specialty", "Office B", 8, 17));
        assertEquals("Office B", specialityService.findById(id).get("office"));

        assertEquals(1, specialityService.delete(id));
        assertThrows(DataAccessException.class, () -> specialityService.findById(id));
    }
}
