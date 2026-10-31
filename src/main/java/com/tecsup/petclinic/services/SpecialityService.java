package com.tecsup.petclinic.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SpecialityService {
    private final JdbcTemplate jdbc;

    public SpecialityService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public int create(String name, String office, int opensAt, int closesAt) {
        return new SimpleJdbcInsert(jdbc).withTableName("specialties").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(Map.of("name", name, "office", office, "h_open", opensAt, "h_close", closesAt)).intValue();
    }

    public Map<String, Object> findById(int id) { return jdbc.queryForMap("SELECT * FROM specialties WHERE id=?", id); }

    public int update(int id, String name, String office, int opensAt, int closesAt) {
        return jdbc.update("UPDATE specialties SET name=?,office=?,h_open=?,h_close=? WHERE id=?", name, office, opensAt, closesAt, id);
    }

    public int delete(int id) { return jdbc.update("DELETE FROM specialties WHERE id=?", id); }
}
