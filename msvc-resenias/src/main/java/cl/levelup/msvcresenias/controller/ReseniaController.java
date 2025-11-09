package cl.levelup.msvcresenias.controller;

import cl.levelup.msvcresenias.model.Resenia;
import cl.levelup.msvcresenias.repository.ReseniaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/resenias")
@CrossOrigin(origins = "*")
public class ReseniaController {

    private final ReseniaRepository repo;

    public ReseniaController(ReseniaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Resenia> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resenia> obtener(@PathVariable Integer id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Resenia> crear(@RequestBody Resenia r) {
        r.setFechaCreacion(Instant.now().toEpochMilli());
        Resenia creada = repo.save(r);
        return ResponseEntity.created(URI.create("/api/resenias/" + creada.getId()))
                .body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resenia> actualizar(@PathVariable Integer id, @RequestBody Resenia r) {
        return repo.findById(id)
                .map(ex -> {
                    r.setId(id);
                    return ResponseEntity.ok(repo.save(r));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/producto/{productoId}")
    public List<Resenia> porProducto(@PathVariable Integer productoId) {
        return repo.findByProductoIdOrderByFechaCreacionDesc(productoId);
    }
}
