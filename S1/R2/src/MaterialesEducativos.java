import java.util.List;

public class MaterialesEducativos {
    // 1. METODO GENERICO
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        System.out.println("\n---- Materiales del Curso ----");
        for (MaterialCurso material : lista) {
            material.mostrarDetalle();
        }
    }

    // 2. METODO GENERICO
    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        System.out.println("\n---- Duracion de los Videos ---");
        for (Video video : lista) {
            total += video.getDuracion();
        }
        System.out.println("Duración total: " + total + " minutos");
    }

    // 3. METODO GENERICO
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        System.out.println("\n---- Marcando ejercicios como Revisados");
        for (Object obj : lista) {
            if (obj instanceof Ejercicio) {
                Ejercicio ejercicio = (Ejercicio) obj;
                ejercicio.setRevisado(true);
                System.out.println("Ejercicio '" + ejercicio.titulo + "'marcado como revisado");
            }
        }
    }
}