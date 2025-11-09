package cl.levelup.msvcproductos.repository;

import cl.levelup.msvcproductos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoriaOrderByNombreAsc(String categoria);
    List<Producto> findByDestacadoTrueOrderByValoracionDesc();
}
