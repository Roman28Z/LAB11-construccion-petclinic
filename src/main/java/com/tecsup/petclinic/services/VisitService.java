package com.tecsup.petclinic.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class VisitService {
    private final JdbcTemplate jdbc;

    public VisitService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public int create(int petId, int vetId, LocalDate visitDate, String description, BigDecimal cost) {
        return new SimpleJdbcInsert(jdbc).withTableName("visits").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(Map.of("pet_id", petId, "vet_id", vetId, "visit_date", visitDate, "description", description, "cost", cost)).intValue();
    }

    public Map<String, Object> findById(int id) { return jdbc.queryForMap("SELECT * FROM visits WHERE id=?", id); }

    public int update(int id, int petId, int vetId, LocalDate visitDate, String description, BigDecimal cost) {
        return jdbc.update("UPDATE visits SET pet_id=?,vet_id=?,visit_date=?,description=?,cost=? WHERE id=?", petId, vetId, visitDate, description, cost, id);
    }

    public int delete(int id) { return jdbc.update("DELETE FROM visits WHERE id=?", id); }
}
