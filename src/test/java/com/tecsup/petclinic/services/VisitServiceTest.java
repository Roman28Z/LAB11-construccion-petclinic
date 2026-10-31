package com.tecsup.petclinic.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.dao.DataAccessException;

@SpringBootTest
@Transactional
class VisitServiceTest {
    @Autowired VisitService visitService;

    @Test
    void createUpdateFindAndDeleteVisit() {
        int id = visitService.create(1, 1, LocalDate.of(2026, 10, 31), "test-visit", new BigDecimal("25.00"));
        assertEquals(new BigDecimal("25.00"), visitService.findById(id).get("cost"));

        assertEquals(1, visitService.update(id, 1, 1, LocalDate.of(2026, 10, 31), "updated-visit", new BigDecimal("30.00")));
        assertEquals("updated-visit", visitService.findById(id).get("description"));

        assertEquals(1, visitService.delete(id));
        assertThrows(DataAccessException.class, () -> visitService.findById(id));
    }
}
