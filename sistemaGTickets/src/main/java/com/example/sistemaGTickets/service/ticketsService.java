package com.example.sistemaGTickets.service;

import com.example.sistemaGTickets.entity.tickets;
import com.example.sistemaGTickets.entity.usuarios;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface ticketsService {
    tickets save (tickets tickets) throws DataException;

    List<tickets> getAll();

    Optional<tickets> getById(int id);



    void delete (tickets tickets);
}
