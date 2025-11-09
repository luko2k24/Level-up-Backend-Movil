package cl.levelup.msvcusuarios.Services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ValidacionService {

    /**
     * Calcula el nivel del usuario en base a sus puntos acumulados.
     * Implementa la lógica de Validacion.kt: 500->N2, 2000->N3, 5000->N4, 10000->N5
     */
    public int calcularNivel(int puntos) {
        if (puntos >= 10000) return 5;
        if (puntos >= 5000) return 4;
        if (puntos >= 2000) return 3;
        if (puntos >= 500) return 2;
        return 1;
    }

    /**
     * Obtiene el porcentaje de descuento asociado al nivel del usuario.
     * Implementa la lógica de Validacion.kt
     */
    public int obtenerPorcentajeDescuento(int nivel) {
        switch (nivel) {
            case 5: return 15;
            case 4: return 12;
            case 3: return 10;
            case 2: return 7;
            case 1: return 5;
            default: return 0;
        }
    }

    /**
     * Genera un código de referido único (ej: LUK4123).
     */
    public String generarCodigoReferido(String nombre) {
        String nombreLimpio = nombre.replaceAll("\\s+", "").toUpperCase();
        String prefijo = nombreLimpio.length() >= 3 ? nombreLimpio.substring(0, 3) : "USR";
        int sufijoAleatorio = 1000 + new Random().nextInt(9000);
        return prefijo + sufijoAleatorio;
    }
}