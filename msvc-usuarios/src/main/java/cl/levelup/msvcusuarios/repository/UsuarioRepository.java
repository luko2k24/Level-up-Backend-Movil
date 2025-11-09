package cl.levelup.msvcusuarios.repository;


import cl.levelup.msvcusuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // --- Búsqueda por Correo (para Login y Registro) ---
    Optional<Usuario> findByCorreo(String correo);

    // --- Búsqueda por Código de Referido ---
    Optional<Usuario> findByCodigoReferido(String codigoReferido);

    // --- Gestión de Sesión ---
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.sesionIniciada = :sesionIniciada WHERE u.id = :id")
    void updateSesionIniciada(@Param("id") Long id, @Param("sesionIniciada") Boolean sesionIniciada);

    // --- Actualización de Fidelización (Llamado por MSVC-Pedidos) ---
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.puntosLevelUp = :puntos, u.nivel = :nivel WHERE u.id = :id")
    void updateNivelAndPuntos(@Param("id") Long id, @Param("puntos") Integer puntos, @Param("nivel") Integer nivel);

    // --- Actualización de Compras (Llamado por MSVC-Pedidos) ---
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.totalCompras = :totalCompras WHERE u.id = :id")
    void updateTotalCompras(@Param("id") Long id, @Param("totalCompras") Integer totalCompras);
}