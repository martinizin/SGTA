package com.example.sistemaGTickets.repository;

import com.example.sistemaGTickets.entity.clientes;
import com.example.sistemaGTickets.entity.usuarios;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface clientesRepository  extends CrudRepository<clientes, Integer> {
    Optional<clientes> findByEmail(String email);
}
