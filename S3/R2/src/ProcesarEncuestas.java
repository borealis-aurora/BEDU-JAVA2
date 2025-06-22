import java.util.*;

public class ProcesarEncuestas {
    public static void main(String[] args) {
        // 📋 Lista de sucursales con sus encuestas
        List<Sucursal> sucursales = List.of(
                new Sucursal("Greenwich", List.of(
                        new Encuesta("Luis XVI", null, 5),
                        new Encuesta("Elisabeth I", "No tenían maquillaje base plomo", 1)
                )),
                new Sucursal("GrandLine", List.of(
                        new Encuesta("Robin", "Buena atención, pero no tenían material de lectura", 4),
                        new Encuesta("Chopper", "No tenían el medicamento correctamente etiquetado", 2)
                )),
                new Sucursal("Sorajima", List.of(
                        new Encuesta("Margarite", null, 4),
                        new Encuesta("Sandersonia", "No encontré el medicamento que necesitaba", 1)
                ))
        );

        System.out.println("📋 Seguimiento a pacientes insatisfechos:");

        sucursales.stream()
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                .filter(e -> e.getCalificacion() <= 3) // 🔍 Filtrar encuestas insatisfechas
                                .map(encuesta -> new Seguimiento(encuesta, sucursal.getNombre())) // 📝 Combinar encuesta + sucursal
                )
                .filter(seg -> seg.encuesta().getComentario().isPresent()) // 🔍 Filtrar encuestas con comentario
                .map(seg -> {
                    String comentario = seg.encuesta().getComentario().get();
                    return "Sucursal " + seg.sucursal() +
                            ": Seguimiento a paciente con comentario: \"" + comentario + "\"";
                })
                .forEach(System.out::println); // 📤 Imprimir mensajes
    }

    // Clase auxiliar para transportar Encuesta + Sucursal juntos, tipo record
    record Seguimiento(Encuesta encuesta, String sucursal) {
    }
}
