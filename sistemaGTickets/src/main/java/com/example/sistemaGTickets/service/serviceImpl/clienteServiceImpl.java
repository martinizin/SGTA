package com.example.sistemaGTickets.service.serviceImpl;

import com.example.sistemaGTickets.entity.clientes;
import com.example.sistemaGTickets.repository.clientesRepository;
import com.example.sistemaGTickets.service.clienteService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class clienteServiceImpl implements clienteService {

    @Autowired
    private clientesRepository clientesRepository;
    @Override
    public clientes save(clientes clientes) throws DataException {
        return clientesRepository.save(clientes);
    }

    @Override
    public List<clientes> getAll() {
        return (List<clientes>) clientesRepository.findAll();
    }

    @Override
    public Optional<clientes> getById(int id) {
        return clientesRepository.findById(id);
    }

    @Override
    public Optional<clientes> getByEmail(String email) {
        return clientesRepository.findByEmail(email);
    }

    @Override
    public void delete(clientes clientes) {
    clientesRepository.delete(clientes);
    }
}
