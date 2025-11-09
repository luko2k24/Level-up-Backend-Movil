package cl.levelup.msvcusuarios.Services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class ReporteService {

    public File generarReporte() {
        File reporte = new File("reporte_simulado.txt");
        try (FileWriter writer = new FileWriter(reporte)) {
            writer.write("Reporte generado correctamente.");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo generar el reporte", e);
        }
        return reporte;
    }
}
