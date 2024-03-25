package com.example.sistemaGTickets.service.serviceImpl;

import com.example.sistemaGTickets.entity.tecnicos;
import com.example.sistemaGTickets.repository.tecnicosRepository;
import com.example.sistemaGTickets.service.tecnicosService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class tecnicosServiceImpl implements tecnicosService {

    @Autowired
    private tecnicosRepository tecnicosRepository;
    @Override
    public tecnicos save(tecnicos tecnicos) throws DataException {
        return tecnicosRepository.save(tecnicos);
    }

    @Override
    public List<tecnicos> getAll() {
        return (List<tecnicos>) tecnicosRepository.findAll();
    }

    @Override
    public Optional<tecnicos> getById(int id) {
        return tecnicosRepository.findById(id);
    }

    @Override
    public Optional<tecnicos> getByEmail(String email) {
        return tecnicosRepository.findByEmail(email);
    }

    @Override
    public void delete(tecnicos tecnicos) {
    tecnicosRepository.delete(tecnicos);
    }
}
