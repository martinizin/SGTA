package com.example.sistemaGTickets.service.serviceImpl;

import com.example.sistemaGTickets.entity.usuarios;
import com.example.sistemaGTickets.repository.usuariosRepository;
import com.example.sistemaGTickets.service.usuariosService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class usuariosServiceImpl implements usuariosService {
    @Autowired
    private usuariosRepository usuariosRepository;
    @Override
    public usuarios save(usuarios usuarios) throws DataException {
        return usuariosRepository.save(usuarios);
    }

    @Override
    public List<usuarios> getAll() {
        return (List<usuarios>) usuariosRepository.findAll();
    }

    @Override
    public Optional<usuarios> getById(int id) {
        return usuariosRepository.findById(id);
    }

    @Override
    public Optional<usuarios> getByEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }

    @Override
    public void delete(usuarios usuarios) {
    usuariosRepository.delete(usuarios);
    }
}
