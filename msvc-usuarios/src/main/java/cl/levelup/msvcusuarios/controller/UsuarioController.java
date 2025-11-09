package cl.levelup.msvcusuarios.controller;


import cl.levelup.msvcusuarios.Services.UsuarioService;
import cl.levelup.msvcusuarios.dto.LoginRequest;
import cl.levelup.msvcusuarios.dto.StatsUpdateRequest;
import cl.levelup.msvcusuarios.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @GetMapping
    public List<Usuario> listar() { return service.listar(); }

    @GetMapping("/{id}")
    // CORREGIDO: Se especifica explícitamente el nombre de la variable de ruta ("id")
    public ResponseEntity<Usuario> obtener(@PathVariable("id") Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    // CORREGIDO
    public ResponseEntity<Usuario> actualizar(@PathVariable("id") Long id, @RequestBody Usuario obj) {
        return service.actualizar(id, obj)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    // CORREGIDO
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody Usuario obj) {
        return service.guardar(obj);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequest request) {
        return service.autenticar(request.getCorreo(), request.getContrasena())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }

    @PutMapping("/{id}/sesion")
    // CORREGIDO
    public ResponseEntity<Usuario> actualizarEstadoSesion(
            @PathVariable("id") Long id,
            @RequestParam("iniciada") boolean iniciada) {
        return service.actualizarEstadoSesion(id, iniciada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/stats")
    // CORREGIDO
    public ResponseEntity<Usuario> actualizarStats(
            @PathVariable("id") Long id,
            @RequestBody StatsUpdateRequest stats) {
        return service.actualizarStats(id, stats)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/descuento")
    // CORREGIDO
    public ResponseEntity<Integer> obtenerDescuento(@PathVariable("id") Long id) {
        return service.obtenerPorcentajeDescuento(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}