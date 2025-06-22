import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MeridianPrime {

    private static final Random random = new Random();

    public static void main(String[] args) {
        // Crear los flujos para cada sistema
        Flux<String> trafficSensors = simulateTrafficSensors();
        Flux<String> airPollution = simulateAirPollution();
        Flux<String> accidents = simulateTrafficAccidents();
        Flux<String> maglevTrains = simulateMaglevTrains();
        Flux<String> smartTrafficLights = simulateSmartTrafficLights();

        // Combinar todos los flujos (Reto)
        Flux<String> allSystems = Flux.merge(
                trafficSensors,
                airPollution,
                accidents,
                maglevTrains,
                smartTrafficLights
        );

        // Flujo individual
        System.out.println("=== INICIANDO MONITOREO DE MERIDIAN PRIME ===");

        trafficSensors
                .subscribeOn(Schedulers.parallel())
                .subscribe(event -> System.out.println("[TRÁFICO] " + event));

        airPollution
                .subscribeOn(Schedulers.parallel())
                .subscribe(event -> System.out.println("[CONTAMINACIÓN] " + event));

        accidents
                .subscribeOn(Schedulers.parallel())
                .subscribe(event -> System.out.println("[ACCIDENTES] " + event));

        maglevTrains
                .subscribeOn(Schedulers.parallel())
                .subscribe(event -> System.out.println("[TRENES] " + event));

        smartTrafficLights
                .subscribeOn(Schedulers.parallel())
                .subscribe(event -> System.out.println("[SEMÁFOROS] " + event));

        // Mantener el programa en ejecución
        try {
            Thread.sleep(60000); // Ejecutar por 60 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 1. Sensores de tráfico - Congestión (0-100%)
    private static Flux<String> simulateTrafficSensors() {
        return Flux.interval(Duration.ofMillis(500))
                .onBackpressureBuffer(10) // Manejo de backpressure
                .map(tick -> {
                    int congestion = random.nextInt(101);
                    if (congestion > 70) {
                        return String.format("¡Congestión crítica! Nivel: %d%%", congestion);
                    }
                    return null; // Filtramos los no críticos después
                })
                .filter(message -> message != null);
    }

    // 2. Contaminación del aire - Nivel de partículas PM2.5 (ug/m3)
    private static Flux<String> simulateAirPollution() {
        return Flux.interval(Duration.ofMillis(600))
                .map(tick -> {
                    int pollutionLevel = 10 + random.nextInt(60); // 10-70 ug/m3
                    if (pollutionLevel > 50) {
                        return String.format("¡Alerta de contaminación! PM2.5: %d ug/m3", pollutionLevel);
                    }
                    return null;
                })
                .filter(message -> message != null);
    }

    // 3. Accidentes viales - Prioridad (Baja, Media, Alta)
    private static Flux<String> simulateTrafficAccidents() {
        List<String> priorities = Arrays.asList("Baja", "Media", "Alta");
        return Flux.interval(Duration.ofMillis(800))
                .map(tick -> {
                    String priority = priorities.get(random.nextInt(3));
                    if ("Alta".equals(priority)) {
                        return String.format("¡Accidente con prioridad %s! Enviando unidades de emergencia.", priority);
                    }
                    return null;
                })
                .filter(message -> message != null);
    }

    // 4. Trenes maglev - Retraso en minutos (0-10 min)
    private static Flux<String> simulateMaglevTrains() {
        return Flux.interval(Duration.ofMillis(700))
                .map(tick -> {
                    int delay = random.nextInt(11); // 0-10 minutos
                    if (delay > 5) {
                        return String.format("¡Retraso crítico en tren maglev! %d minutos de retraso.", delay);
                    }
                    return null;
                })
                .filter(message -> message != null);
    }

    // 5. Semáforos inteligentes - Estado (Verde, Amarillo, Rojo)
    private static Flux<String> simulateSmartTrafficLights() {
        List<String> states = Arrays.asList("Verde", "Amarillo", "Rojo");
        AtomicInteger redCount = new AtomicInteger(0);

        return Flux.interval(Duration.ofMillis(400))
                .map(tick -> {
                    String state = states.get(random.nextInt(3));

                    if ("Rojo".equals(state)) {
                        int count = redCount.incrementAndGet();
                        if (count > 3) {
                            redCount.set(0);
                            return String.format("¡Semáforo en rojo %d veces seguidas en cruce %d!",
                                    count, random.nextInt(10) + 1);
                        }
                    } else {
                        redCount.set(0);
                    }
                    return null;
                })
                .filter(message -> message != null);
    }
}