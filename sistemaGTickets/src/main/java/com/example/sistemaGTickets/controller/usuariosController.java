package com.example.sistemaGTickets.controller;

import com.example.sistemaGTickets.dominio.HttpResponse;
import com.example.sistemaGTickets.entity.clientes;
import com.example.sistemaGTickets.entity.usuarios;
import com.example.sistemaGTickets.service.serviceImpl.clienteServiceImpl;
import com.example.sistemaGTickets.service.serviceImpl.usuariosServiceImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sistemaGTickets.constantes.MensajesConst.REGISTRO_ELIMINADO_EXITO;

@RestController
@RequestMapping("api/v1/usuarios")
public class usuariosController {
    @Autowired
    private usuariosServiceImpl usuariosService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> guardar(@RequestBody usuarios usuarios)throws DataException {
        return new ResponseEntity<>(usuariosService.save(usuarios), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public List<usuarios> listar() {
        return usuariosService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<usuarios> obtenerPorId(@PathVariable("id") int codigo) {
        return usuariosService.getById(codigo).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<usuarios> actualizarDatos(@PathVariable("id") int codigo, @RequestBody usuarios usuarios) throws DataException{


        return (ResponseEntity<usuarios>) usuariosService.getById(codigo).map(datosGuardados -> {

            datosGuardados.setNombre(usuarios.getNombre().toUpperCase());
            datosGuardados.setApellido(usuarios.getApellido().toUpperCase());

            datosGuardados.setEmail(usuarios.getEmail());
            datosGuardados.setPassword(usuarios.getPassword());




            usuarios datosActualizados = null;
            try{
                datosActualizados=usuariosService.save(datosGuardados);
            }catch ( DataException e){
                return response(HttpStatus.BAD_REQUEST, e.getMessage().toString());
            }
            return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDatos(@PathVariable("id") Integer codigo) {
        usuarios usuarios = usuariosService.getById(codigo).orElse(null);
        usuariosService.delete(usuarios);
        return response(HttpStatus.OK, REGISTRO_ELIMINADO_EXITO);
    }

    private ResponseEntity<HttpResponse>response(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message),httpStatus);
    }

}
