package com.example.sistemaGTickets.repository;

import com.example.sistemaGTickets.entity.tecnicos;
import com.example.sistemaGTickets.entity.usuarios;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface tecnicosRepository extends CrudRepository<tecnicos, Integer> {
    Optional<tecnicos> findByEmail(String email);
}
