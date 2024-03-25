package com.example.sistemaGTickets.controller;

import com.example.sistemaGTickets.dominio.HttpResponse;
import com.example.sistemaGTickets.entity.clientes;
import com.example.sistemaGTickets.service.serviceImpl.clienteServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sistemaGTickets.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;

@RestController
@RequestMapping("api/v1/clientes")
public class clientesController {
    @Autowired
    private clienteServiceImpl clientesService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody clientes clientes)throws DataException {
        return new ResponseEntity<>(clientesService.save(clientes), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<clientes> listar() {
        return clientesService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<clientes> obtenerPorId(@PathVariable("id") int codigo) {
        return clientesService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<clientes> actualizarDatos(@PathVariable("id") int codigo, @RequestBody clientes clientes) throws DataException{


        return (ResponseEntity<clientes>) clientesService.getById(codigo).map(datosGuardados -> {
            datosGuardados.setCedula(clientes.getCedula());
            datosGuardados.setNombre(clientes.getNombre().toUpperCase());
            datosGuardados.setApellido(clientes.getApellido().toUpperCase());
            datosGuardados.setCiudad(clientes.getCiudad());
            datosGuardados.setEmail(clientes.getEmail());
            datosGuardados.setDireccion(clientes.getDireccion());
            datosGuardados.setTelefono(clientes.getTelefono());
            datosGuardados.setFecha_nacimiento(clientes.getFecha_nacimiento());
            datosGuardados.setDependencia(clientes.getDependencia());



            clientes datosActualizados = null;
            try{
                datosActualizados=clientesService.save(datosGuardados);
            }catch ( DataException e){
                return response(HttpStatus.BAD_REQUEST, e.getMessage().toString());
            }
            return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDatos(@PathVariable("id") Integer codigo) {
        clientes clientes = clientesService.getById(codigo).orElse(null);
        clientesService.delete(clientes);
        return response(HttpStatus.OK, REGISTRO_ELIMINADO_EXITO);
    }

    private ResponseEntity<HttpResponse>response(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message),httpStatus);
    }

}
