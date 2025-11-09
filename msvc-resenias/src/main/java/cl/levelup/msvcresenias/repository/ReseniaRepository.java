package cl.levelup.msvcresenias.repository;

import cl.levelup.msvcresenias.model.Resenia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReseniaRepository extends JpaRepository<Resenia, Integer> {
    List<Resenia> findByProductoIdOrderByFechaCreacionDesc(Integer productoId);
}
