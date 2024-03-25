package com.example.sistemaGTickets.service;

import com.example.sistemaGTickets.entity.clientes;
import com.example.sistemaGTickets.entity.usuarios;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface usuariosService {
    usuarios save (usuarios usuarios) throws DataException;

    List<usuarios> getAll();

    Optional<usuarios> getById(int id);
    Optional<usuarios> getByEmail(String email);


    void delete (usuarios usuarios);
}
