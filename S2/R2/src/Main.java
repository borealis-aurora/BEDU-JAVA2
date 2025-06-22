import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("🏥 Iniciando simulación de acceso al recurso...");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // Pool de 4 hilos para simular concurrencia
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(new Profesional("Dra. Medina", salaCirugia));
        executor.submit(new Profesional("Enfermera Núñez", salaCirugia));
        executor.submit(new Profesional("Dra. Villanueva", salaCirugia));
        executor.submit(new Profesional("Dr. Vázquez", salaCirugia));

        executor.shutdown();
    }
}