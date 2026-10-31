package com.tecsup.petclinic.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class VetSpecialtyServiceTest {
    @Autowired VetSpecialtyService vetSpecialtyService;

    @Test
    void assignFindBothDirectionsAndRemoveSpecialtyRelation() {
        assertEquals(1, vetSpecialtyService.assign(1, 3));

        List<String> specialties = vetSpecialtyService.findSpecialtiesByVet(1);
        assertTrue(specialties.contains("dentistry"));
        List<String> vets = vetSpecialtyService.findVetsBySpecialty(3);
        assertTrue(vets.contains("James"));

        assertEquals(1, vetSpecialtyService.remove(1, 3));
        assertEquals(List.of(), vetSpecialtyService.findSpecialtiesByVet(1));
        assertTrue(!vetSpecialtyService.findVetsBySpecialty(3).contains("James"));
    }
}
