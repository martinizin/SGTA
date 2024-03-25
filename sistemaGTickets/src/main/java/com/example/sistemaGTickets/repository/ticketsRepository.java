package com.example.sistemaGTickets.repository;

import com.example.sistemaGTickets.entity.tickets;
import org.springframework.data.repository.CrudRepository;

public interface ticketsRepository extends CrudRepository<tickets,Integer> {
}
