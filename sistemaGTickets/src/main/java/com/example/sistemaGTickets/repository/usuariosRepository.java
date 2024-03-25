package com.example.sistemaGTickets.repository;

import com.example.sistemaGTickets.entity.usuarios;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface usuariosRepository extends CrudRepository<usuarios,Integer> {
    Optional<usuarios> findByEmail(String email);
}
