package com.example.sistemaGTickets.controller;

import com.example.sistemaGTickets.dominio.HttpResponse;
import com.example.sistemaGTickets.entity.clientes;
import com.example.sistemaGTickets.entity.tickets;
import com.example.sistemaGTickets.service.serviceImpl.clienteServiceImpl;
import com.example.sistemaGTickets.service.serviceImpl.ticketsServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sistemaGTickets.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;

@RestController
@RequestMapping("api/v1/tickets")
public class ticketsController {
    @Autowired
    private ticketsServiceImpl ticketsService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody tickets tickets)throws DataException {
        return new ResponseEntity<>(ticketsService.save(tickets), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<tickets> listar() {
        return ticketsService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<tickets> obtenerPorId(@PathVariable("id") int codigo) {
        return ticketsService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<tickets> actualizarDatos(@PathVariable("id") int codigo, @RequestBody tickets tickets) throws DataException{


        return (ResponseEntity<com.example.sistemaGTickets.entity.tickets>) ticketsService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setCodigo(tickets.getCodigo());
            datosGuardados.setDescripcion(tickets.getDescripcion());
            datosGuardados.setId_cliente(tickets.getId_cliente());
            datosGuardados.setId_tecnico(tickets.getId_tecnico());




            tickets datosActualizados = null;
            try{
                datosActualizados=ticketsService.save(datosGuardados);
            }catch ( DataException e){
                return response(HttpStatus.BAD_REQUEST, e.getMessage().toString());
            }
            return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDatos(@PathVariable("id") Integer codigo) {
        tickets tickets = ticketsService.getById(codigo).orElse(null);
        ticketsService.delete(tickets);
        return response(HttpStatus.OK, REGISTRO_ELIMINADO_EXITO);
    }

    private ResponseEntity<HttpResponse>response(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message),httpStatus);
    }
}
