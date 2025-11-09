package cl.levelup.msvcpedidos.repository;

import cl.levelup.msvcpedidos.model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoRepository extends JpaRepository<CarritoItem, Integer> {
    List<CarritoItem> findByUsuarioId(Integer usuarioId);
}
