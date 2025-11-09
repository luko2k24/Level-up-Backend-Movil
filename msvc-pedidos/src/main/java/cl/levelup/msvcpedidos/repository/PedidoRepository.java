package cl.levelup.msvcpedidos.repository;

import cl.levelup.msvcpedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByUsuarioIdOrderByFechaCreacionDesc(Integer usuarioId);
}
