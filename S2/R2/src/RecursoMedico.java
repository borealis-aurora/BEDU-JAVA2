import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class RecursoMedico {
    protected String nombreRecurso;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombreRecurso){
        this.nombreRecurso = nombreRecurso;
    }

    public void usar(String profesional){
        lock.lock(); // Bloquea el recurso
        try{
            System.out.println("👨‍⚕️ " + profesional + " ha ingresado a " + nombreRecurso);
            Thread.sleep(3000); // Simulación del tiempo de uso 3 segundos
            System.out.println("✅ " + profesional + " ha salido de " + nombreRecurso);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock(); // Libera el recurso
        }
    }
}