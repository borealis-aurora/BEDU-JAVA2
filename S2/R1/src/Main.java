import misionespacial.SistemaNavegacion;
import misionespacial.SistemaComunicaciones;
import misionespacial.SistemaControlT;
import misionespacial.SistemaSoporteVital;

import java.util.concurrent.*;

public class Main{
    // Aplicación de ExecutorService
    public static void main(String[] args) throws Exception {
        System.out.println("🚀 Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Utilizacion de Future para recuperar los resultados
        Future<String> navegacion = executor.submit(new SistemaNavegacion());
        Future<String> soporteVital = executor.submit(new SistemaSoporteVital());
        Future<String> comunicaciones = executor.submit(new SistemaComunicaciones());
        Future<String> controlTermico = executor.submit(new SistemaControlT());

        System.out.println(navegacion.get());
        System.out.println(soporteVital.get());
        System.out.println(comunicaciones.get());
        System.out.println(controlTermico.get());

        executor.shutdown();
        System.out.println("✅ Todos los sistemas reportan estado operativo.");
    }

}
