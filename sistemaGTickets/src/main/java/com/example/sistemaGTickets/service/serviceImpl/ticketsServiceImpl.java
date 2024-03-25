package com.example.sistemaGTickets.service.serviceImpl;

import com.example.sistemaGTickets.entity.tickets;
import com.example.sistemaGTickets.service.ticketsService;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ticketsServiceImpl implements ticketsService {
    @Override
    public tickets save(tickets tickets) throws DataException {
        return null;
    }

    @Override
    public List<tickets> getAll() {
        return null;
    }

    @Override
    public Optional<tickets> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void delete(tickets tickets) {

    }
}
