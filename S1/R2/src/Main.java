import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creación de los Materiales
        Video video1 = new Video("Introducción a Teoría de la Inf.", "Prof. Amir", 120);
        Video video2 = new Video("Información y Entropía de Shannon", "Prof. Karim", 60);

        Articulo articulo1 = new Articulo("Información Clásica vs Cuántica", "Emmanuel Desurvire", 3600);
        Articulo articulo2 = new Articulo("Quantum Information", "Nielsen Chuang", 3500);

        Ejercicio ejercicio1 = new Ejercicio("Ejercicios sobre Esfera de Bloch", "Prof. Lucio");
        Ejercicio ejercicio2 = new Ejercicio("Ejercicios de Mecánica Cuántica", "Prof. Lucio");

        // Lista genérica de todos los materiales
        List<MaterialCurso> Materiales = new ArrayList<>();
        Materiales.add(video1);
        Materiales.add(video2);
        Materiales.add(articulo1);
        Materiales.add(articulo2);
        Materiales.add(ejercicio1);
        Materiales.add(ejercicio2);

        // Videos específicos
        List<Video> videos = new ArrayList<>();
        videos.add(video1);
        videos.add(video2);

        // Métodos genéricos
        MaterialesEducativos.mostrarMateriales(Materiales);
        MaterialesEducativos.contarDuracionVideos(videos);
        MaterialesEducativos.marcarEjerciciosRevisados(Materiales);

        // Se muestran los ejercicios ya actualizados
        System.out.println("\n=== MATERIALES FINALES ===");
        MaterialesEducativos.mostrarMateriales(Materiales);
    }
}