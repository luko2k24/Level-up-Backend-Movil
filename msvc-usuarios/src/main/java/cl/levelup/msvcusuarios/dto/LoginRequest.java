package cl.levelup.msvcusuarios.dto;

public class LoginRequest {

    private String correo;
    private String contrasena; // Debe coincidir con el JSON

    public LoginRequest() {} // NECESARIO

    // --------------------------------------------------
    // Getters y Setters (¡CRUCIALES!)
    // --------------------------------------------------

    public String getCorreo() { // <--- ¿Están estos métodos?
        return correo;
    }

    public void setCorreo(String correo) { // <--- ¿Están estos métodos?
        this.correo = correo;
    }

    public String getContrasena() { // <--- ¿Están estos métodos?
        return contrasena;
    }

    public void setContrasena(String contrasena) { // <--- ¿Están estos métodos?
        this.contrasena = contrasena;
    }
}