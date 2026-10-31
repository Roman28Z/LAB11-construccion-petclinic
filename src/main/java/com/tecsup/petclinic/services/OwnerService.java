package com.tecsup.petclinic.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OwnerService {
    private final JdbcTemplate jdbc;

    public OwnerService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public int create(String firstName, String lastName, String address, String city, String telephone) {
        return new SimpleJdbcInsert(jdbc).withTableName("owners").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(Map.of("first_name", firstName, "last_name", lastName, "address", address, "city", city, "telephone", telephone)).intValue();
    }

    public Map<String, Object> findById(int id) { return jdbc.queryForMap("SELECT * FROM owners WHERE id=?", id); }

    public int update(int id, String firstName, String lastName, String address, String city, String telephone) {
        return jdbc.update("UPDATE owners SET first_name=?,last_name=?,address=?,city=?,telephone=? WHERE id=?", firstName, lastName, address, city, telephone, id);
    }

    public int delete(int id) { return jdbc.update("DELETE FROM owners WHERE id=?", id); }
}
