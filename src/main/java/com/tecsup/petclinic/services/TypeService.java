package com.tecsup.petclinic.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TypeService {
    private final JdbcTemplate jdbc;

    public TypeService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public int create(String name, String description, boolean active, String sizeCategory, int averageLifespan, String careLevel) {
        return new SimpleJdbcInsert(jdbc).withTableName("types").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(Map.of("name", name, "description", description, "active", active, "size_category", sizeCategory,
                        "average_lifespan", averageLifespan, "care_level", careLevel)).intValue();
    }

    public Map<String, Object> findById(int id) { return jdbc.queryForMap("SELECT * FROM types WHERE id=?", id); }

    public int update(int id, String name, String description, boolean active, String sizeCategory, int averageLifespan, String careLevel) {
        return jdbc.update("UPDATE types SET name=?,description=?,active=?,size_category=?,average_lifespan=?,care_level=? WHERE id=?",
                name, description, active, sizeCategory, averageLifespan, careLevel, id);
    }

    public int delete(int id) { return jdbc.update("DELETE FROM types WHERE id=?", id); }
}
