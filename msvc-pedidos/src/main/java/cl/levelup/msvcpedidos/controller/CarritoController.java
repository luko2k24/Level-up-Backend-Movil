package cl.levelup.msvcpedidos.controller;

import cl.levelup.msvcpedidos.model.CarritoItem;
import cl.levelup.msvcpedidos.repository.CarritoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    private final CarritoRepository repo;

    public CarritoController(CarritoRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<CarritoItem> listarPorUsuario(@PathVariable Integer usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public ResponseEntity<CarritoItem> agregar(@RequestBody CarritoItem item) {
        CarritoItem creado = repo.save(item);
        return ResponseEntity.created(URI.create("/api/carrito/" + creado.getId()))
                .body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarritoItem> actualizar(@PathVariable Integer id, @RequestBody CarritoItem item) {
        return repo.findById(id)
                .map(ex -> {
                    item.setId(id);
                    return ResponseEntity.ok(repo.save(item));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
