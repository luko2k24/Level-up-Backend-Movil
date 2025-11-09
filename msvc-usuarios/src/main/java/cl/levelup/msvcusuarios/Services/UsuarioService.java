package cl.levelup.msvcusuarios.Services;

import cl.levelup.msvcusuarios.dto.StatsUpdateRequest;
import cl.levelup.msvcusuarios.model.Usuario;
import cl.levelup.msvcusuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    // Se cambió la inyección de campo por la inyección de constructor (la forma más robusta)
    private final UsuarioRepository usuarioRepository;
    private final ValidacionService validacionService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ValidacionService validacionService) {
        this.usuarioRepository = usuarioRepository;
        this.validacionService = validacionService;
    }

    // --- CRUD BÁSICO ---

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Usado internamente por 'registrar' del Controller
    @Transactional
    public Usuario guardar(Usuario usuario) {
        // Lógica de REGISTRO (Solo se ejecuta al crear un nuevo usuario)
        if (usuario.getId() == null) {
            usuario.setPuntosLevelUp(0);
            usuario.setNivel(1);
            usuario.setTotalCompras(0);
            usuario.setSesionIniciada(true);
            usuario.setRol(usuario.getRol() != null ? usuario.getRol() : "CLIENTE");

            // Generar código de referido único
            if (usuario.getCodigoReferido() == null || usuario.getCodigoReferido().isEmpty()) {
                String codigo = validacionService.generarCodigoReferido(usuario.getNombre());
                usuario.setCodigoReferido(codigo);
            }
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Optional<Usuario> actualizar(Long id, Usuario usuarioDetalles) {
        return usuarioRepository.findById(id).map(u -> {
            u.setNombre(usuarioDetalles.getNombre());
            u.setCorreo(usuarioDetalles.getCorreo());
            u.setContrasena(usuarioDetalles.getContrasena());
            u.setRol(usuarioDetalles.getRol());
            u.setEdad(usuarioDetalles.getEdad());
            u.setEsDuoc(usuarioDetalles.getEsDuoc());
            // No actualizamos stats de fidelización aquí, sino vía /stats endpoint
            return usuarioRepository.save(u);
        });
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // --- MÉTODOS DE NEGOCIO (REQUERIDOS POR EL MÓVIL) ---

    // 1. AUTENTICACIÓN (Login)
    @Transactional
    public Optional<Usuario> autenticar(String correo, String contrasena) {
        return usuarioRepository.findByCorreo(correo)
                .filter(u -> u.getContrasena().equals(contrasena))
                .map(u -> {
                    usuarioRepository.updateSesionIniciada(u.getId(), true);
                    u.setSesionIniciada(true);
                    return u;
                });
    }

    // 2. ACTUALIZAR ESTADO DE SESIÓN (Logout/Login manual)
    @Transactional
    public Optional<Usuario> actualizarEstadoSesion(Long id, boolean iniciada) {
        return usuarioRepository.findById(id).map(u -> {
            usuarioRepository.updateSesionIniciada(id, iniciada);
            u.setSesionIniciada(iniciada);
            return u;
        });
    }

    // 3. ACTUALIZAR ESTADÍSTICAS (Llamado por MSVC-Pedidos)
    @Transactional
    public Optional<Usuario> actualizarStats(Long id, StatsUpdateRequest stats) {
        return usuarioRepository.findById(id).map(u -> {
            usuarioRepository.updateNivelAndPuntos(id, stats.getPuntos(), stats.getNivel());
            usuarioRepository.updateTotalCompras(id, stats.getTotalCompras());

            u.setPuntosLevelUp(stats.getPuntos());
            u.setNivel(stats.getNivel());
            u.setTotalCompras(stats.getTotalCompras());
            return u;
        });
    }

    // 4. OBTENER PORCENTAJE DE DESCUENTO (Lógica Defensiva y Null-Safe)
    public Optional<Integer> obtenerPorcentajeDescuento(Long id) {
        return usuarioRepository.findById(id).map(u -> {

            // LÓGICA DEFENSIVA: Proporciona un valor por defecto si el campo es null.
            boolean esDuocSeguro = u.getEsDuoc() != null ? u.getEsDuoc() : false;

            // Si el campo es null en la base de datos, usamos '1'.
            int nivelSeguro = u.getNivel() != null ? u.getNivel() : 1;

            if (esDuocSeguro) {
                return 20;
            }
            // Usamos el nivel seguro, evitando la NPE
            return validacionService.obtenerPorcentajeDescuento(nivelSeguro);
        });
    }
}