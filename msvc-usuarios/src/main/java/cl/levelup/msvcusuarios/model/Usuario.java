package cl.levelup.msvcusuarios.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usuarios") // Asegúrate de que el nombre de la tabla sea correcto
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo; // Correo es único y debe ser índice
    private String contrasena; // Usar 'contrasena' para alineación con el móvil

    // --- CAMPOS DE NEGOCIO Y FIDELIZACIÓN ---
    private Integer edad;
    private Boolean esDuoc = false; // Descuento especial
    private Integer puntosLevelUp = 0;
    private Integer nivel = 1;
    private String codigoReferido = ""; // Código único para referir a otros
    private String referidoPor = ""; // Código de quien lo refirió
    private Integer totalCompras = 0;
    private Boolean sesionIniciada = false; // Usado por el móvil para estado de sesión

    // --- CAMPOS DE ADMINISTRACIÓN ---
    private String rol = "CLIENTE";
    // private boolean activo; // ELIMINADO: Usamos 'sesionIniciada'

    // Constructor vacío (necesario para JPA)
    public Usuario() {}

    // ------------------------------------------------------------------
    // Getters y Setters
    // ------------------------------------------------------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public Boolean getEsDuoc() { return esDuoc; }
    public void setEsDuoc(Boolean esDuoc) { this.esDuoc = esDuoc; }

    public Integer getPuntosLevelUp() { return puntosLevelUp; }
    public void setPuntosLevelUp(Integer puntosLevelUp) { this.puntosLevelUp = puntosLevelUp; }

    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }

    public String getCodigoReferido() { return codigoReferido; }
    public void setCodigoReferido(String codigoReferido) { this.codigoReferido = codigoReferido; }

    public String getReferidoPor() { return referidoPor; }
    public void setReferidoPor(String referidoPor) { this.referidoPor = referidoPor; }

    public Integer getTotalCompras() { return totalCompras; }
    public void setTotalCompras(Integer totalCompras) { this.totalCompras = totalCompras; }

    public Boolean getSesionIniciada() { return sesionIniciada; }
    public void setSesionIniciada(Boolean sesionIniciada) { this.sesionIniciada = sesionIniciada; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    // NOTA: En un proyecto real usarías Lombok para reducir boilerplate o Kotlin.
}