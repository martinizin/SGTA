package com.example.sistemaGTickets.service;

import com.example.sistemaGTickets.entity.tecnicos;
import com.example.sistemaGTickets.entity.usuarios;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface tecnicosService {
    tecnicos save (tecnicos tecnicos) throws DataException;

    List<tecnicos> getAll();

    Optional<tecnicos> getById(int id);
    Optional<tecnicos> getByEmail(String email);


    void delete (tecnicos tecnicos);
}
