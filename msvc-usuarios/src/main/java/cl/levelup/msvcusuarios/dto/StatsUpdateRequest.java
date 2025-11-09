package cl.levelup.msvcusuarios.dto;

public class StatsUpdateRequest {
    private Integer puntos;
    private Integer nivel;
    private Integer totalCompras;

    public Integer getPuntos() { return puntos; }
    public void setPuntos(Integer puntos) { this.puntos = puntos; }

    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }

    public Integer getTotalCompras() { return totalCompras; }
    public void setTotalCompras(Integer totalCompras) { this.totalCompras = totalCompras; }
}