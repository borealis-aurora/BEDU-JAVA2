import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class UCISistemaMonitoreo {

    // Clase para representar los signos vitales de un paciente
    static class VitalSigns {
        int patientId;
        int heartRate; // FC (bpm)
        String bloodPressure; // PA (mmHg) formato "120/80"
        int oxygenLevel; // SpO2 (%)

        public VitalSigns(int patientId, int heartRate, String bloodPressure, int oxygenLevel) {
            this.patientId = patientId;
            this.heartRate = heartRate;
            this.bloodPressure = bloodPressure;
            this.oxygenLevel = oxygenLevel;
        }

        @Override
        public String toString() {
            return String.format("Paciente %d - FC: %d, PA: %s, SpO2: %d%%",
                    patientId, heartRate, bloodPressure, oxygenLevel);
        }
    }

    // Método para generar valores aleatorios de signos vitales
    private static VitalSigns generateRandomVitalSigns(int patientId) {
        Random random = ThreadLocalRandom.current();

        // Generar valores aleatorios
        int heartRate = 40 + random.nextInt(100); // 40-140 bpm
        int systolic = 70 + random.nextInt(90); // 70-160 mmHg
        int diastolic = 40 + random.nextInt(60); // 40-100 mmHg
        String bloodPressure = systolic + "/" + diastolic;
        int oxygenLevel = 80 + random.nextInt(25); // 80-105%

        return new VitalSigns(patientId, heartRate, bloodPressure, oxygenLevel);
    }

    // Método para verificar si los signos vitales son críticos
    private static boolean isCritical(VitalSigns signs) {
        String[] bpParts = signs.bloodPressure.split("/");
        int systolic = Integer.parseInt(bpParts[0]);
        int diastolic = Integer.parseInt(bpParts[1]);

        return signs.heartRate < 50 || signs.heartRate > 120 || // FC crítica
                systolic < 90 || diastolic < 60 || // PA baja
                systolic > 140 || diastolic > 90 || // PA alta
                signs.oxygenLevel < 90; // SpO2 bajo
    }

    // Método para generar alertas
    private static String generateAlert(VitalSigns signs) {
        String[] bpParts = signs.bloodPressure.split("/");
        int systolic = Integer.parseInt(bpParts[0]);
        int diastolic = Integer.parseInt(bpParts[1]);

        if (signs.heartRate < 50) {
            return String.format("⚠️ Paciente %d - FC crítica baja: %d bpm", signs.patientId, signs.heartRate);
        } else if (signs.heartRate > 120) {
            return String.format("⚠️ Paciente %d - FC crítica alta: %d bpm", signs.patientId, signs.heartRate);
        } else if (systolic < 90 || diastolic < 60) {
            return String.format("⚠️ Paciente %d - PA crítica baja: %s mmHg", signs.patientId, signs.bloodPressure);
        } else if (systolic > 140 || diastolic > 90) {
            return String.format("⚠️ Paciente %d - PA crítica alta: %s mmHg", signs.patientId, signs.bloodPressure);
        } else if (signs.oxygenLevel < 90) {
            return String.format("⚠️ Paciente %d - SpO2 baja: %d%%", signs.patientId, signs.oxygenLevel);
        }
        return null;
    }

    public static void main(String[] args) {
        // Crear flujos para 3 pacientes
        Flux<VitalSigns> patient1 = Flux.interval(Duration.ofMillis(300))
                .map(tick -> generateRandomVitalSigns(1));

        Flux<VitalSigns> patient2 = Flux.interval(Duration.ofMillis(300))
                .map(tick -> generateRandomVitalSigns(2));

        Flux<VitalSigns> patient3 = Flux.interval(Duration.ofMillis(300))
                .map(tick -> generateRandomVitalSigns(3));

        // Fusionar los flujos y aplicar backpressure
        Flux.merge(patient1, patient2, patient3)
                .filter(UCIMonitoringSystem::isCritical) // Filtrar solo eventos críticos
                .delayElements(Duration.ofSeconds(1)) // Backpressure: procesar 1 evento por segundo
                .map(UCIMonitoringSystem::generateAlert) // Generar mensajes de alerta
                .subscribeOn(Schedulers.parallel()) // Procesar en paralelo
                .subscribe(System.out::println); // Mostrar alertas

        // Mantener el programa en ejecución
        try {
            Thread.sleep(60000); // Ejecutar por 60 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}