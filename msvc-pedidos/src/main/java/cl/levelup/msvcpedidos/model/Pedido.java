package cl.levelup.msvcpedidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer usuarioId;
    private Integer montoTotal;
    private Integer montoDescuento;
    private Integer montoFinal;
    private String estado;
    private Long fechaCreacion;

    @Lob
    private String itemsJson;

    public Pedido() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public Integer getMontoTotal() { return montoTotal; }
    public void setMontoTotal(Integer montoTotal) { this.montoTotal = montoTotal; }

    public Integer getMontoDescuento() { return montoDescuento; }
    public void setMontoDescuento(Integer montoDescuento) { this.montoDescuento = montoDescuento; }

    public Integer getMontoFinal() { return montoFinal; }
    public void setMontoFinal(Integer montoFinal) { this.montoFinal = montoFinal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Long fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getItemsJson() { return itemsJson; }
    public void setItemsJson(String itemsJson) { this.itemsJson = itemsJson; }
}
