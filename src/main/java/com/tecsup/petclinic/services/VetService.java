package com.tecsup.petclinic.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VetService {
    private final JdbcTemplate jdbc;

    public VetService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public int create(String firstName, String lastName, String email, String phone, boolean active) {
        return new SimpleJdbcInsert(jdbc).withTableName("vets").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(Map.of("first_name", firstName, "last_name", lastName, "email", email, "phone", phone, "active", active)).intValue();
    }

    public Map<String, Object> findById(int id) {
        return jdbc.queryForMap("SELECT * FROM vets WHERE id=?", id);
    }

    public int update(int id, String firstName, String lastName, String email, String phone, boolean active) {
        return jdbc.update("UPDATE vets SET first_name=?,last_name=?,email=?,phone=?,active=? WHERE id=?", firstName, lastName, email, phone, active, id);
    }

    public int delete(int id) { return jdbc.update("DELETE FROM vets WHERE id=?", id); }
}
