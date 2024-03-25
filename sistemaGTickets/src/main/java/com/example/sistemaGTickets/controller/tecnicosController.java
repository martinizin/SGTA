package com.example.sistemaGTickets.controller;

import com.example.sistemaGTickets.dominio.HttpResponse;
import com.example.sistemaGTickets.entity.clientes;
import com.example.sistemaGTickets.entity.tecnicos;
import com.example.sistemaGTickets.service.serviceImpl.clienteServiceImpl;
import com.example.sistemaGTickets.service.serviceImpl.tecnicosServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sistemaGTickets.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;

@RestController
@RequestMapping("api/v1/tecnicos")
public class tecnicosController {
    @Autowired
    private tecnicosServiceImpl tecnicosService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody tecnicos tecnicos)throws DataException {
        return new ResponseEntity<>(tecnicosService.save(tecnicos), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<tecnicos> listar() {
        return tecnicosService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<tecnicos> obtenerPorId(@PathVariable("id") int codigo) {
        return tecnicosService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<tecnicos> actualizarDatos(@PathVariable("id") int codigo, @RequestBody tecnicos tecnicos) throws DataException{


        return (ResponseEntity<tecnicos>) tecnicosService.getById(codigo).map(datosGuardados -> {

            datosGuardados.setNombre(tecnicos.getNombre().toUpperCase());
            datosGuardados.setApellido(tecnicos.getApellido().toUpperCase());
            datosGuardados.setCedula(tecnicos.getCedula());
            datosGuardados.setFecha_nacimiento(tecnicos.getFecha_nacimiento());
            datosGuardados.setGenero(tecnicos.getGenero());
            datosGuardados.setCiudad(tecnicos.getCiudad());
            datosGuardados.setDireccion(tecnicos.getDireccion());
            datosGuardados.setTelefono(tecnicos.getTelefono());
            datosGuardados.setEmail(tecnicos.getEmail());




            tecnicos datosActualizados = null;
            try{
                datosActualizados=tecnicosService.save(datosGuardados);
            }catch ( DataException e){
                return response(HttpStatus.BAD_REQUEST, e.getMessage().toString());
            }
            return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDatos(@PathVariable("id") Integer codigo) {
        tecnicos tecnicos = tecnicosService.getById(codigo).orElse(null);
        tecnicosService.delete(tecnicos);
        return response(HttpStatus.OK, REGISTRO_ELIMINADO_EXITO);
    }

    private ResponseEntity<HttpResponse>response(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message),httpStatus);
    }
}
