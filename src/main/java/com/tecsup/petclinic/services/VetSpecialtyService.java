package com.tecsup.petclinic.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetSpecialtyService {
    private final JdbcTemplate jdbc;

    public VetSpecialtyService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public int assign(int vetId, int specialtyId) {
        return jdbc.update("INSERT INTO vet_specialties(vet_id,specialty_id) VALUES (?,?)", vetId, specialtyId);
    }

    public List<String> findSpecialtiesByVet(int vetId) {
        return jdbc.queryForList("SELECT s.name FROM specialties s JOIN vet_specialties vs ON vs.specialty_id=s.id WHERE vs.vet_id=? ORDER BY s.id", String.class, vetId);
    }

    public List<String> findVetsBySpecialty(int specialtyId) {
        return jdbc.queryForList("SELECT v.first_name FROM vets v JOIN vet_specialties vs ON vs.vet_id=v.id WHERE vs.specialty_id=? ORDER BY v.id", String.class, specialtyId);
    }

    public int remove(int vetId, int specialtyId) {
        return jdbc.update("DELETE FROM vet_specialties WHERE vet_id=? AND specialty_id=?", vetId, specialtyId);
    }
}
