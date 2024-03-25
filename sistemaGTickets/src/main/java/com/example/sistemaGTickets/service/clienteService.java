package com.example.sistemaGTickets.service;

import com.example.sistemaGTickets.entity.clientes;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface clienteService {
    clientes save (clientes clientes) throws DataException;

    List<clientes> getAll();

    Optional<clientes> getById(int id);
    Optional<clientes> getByEmail(String email);


    void delete (clientes clientes);
}
