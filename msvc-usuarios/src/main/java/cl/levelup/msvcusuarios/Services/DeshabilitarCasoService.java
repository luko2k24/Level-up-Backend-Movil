package cl.levelup.msvcusuarios.Services;

public class DeshabilitarCasoService {

    public boolean deshabilitarCaso(boolean checkboxMarcado) {
        if (!checkboxMarcado) {
            throw new IllegalArgumentException("Debe marcar el check para deshabilitar el caso.");
        }
        System.out.println("Caso deshabilitado correctamente.");
        return true;
    }
}
