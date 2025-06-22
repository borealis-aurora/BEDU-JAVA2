package misionespacial;
import java.util.concurrent.*;

public class SistemaComunicaciones implements Callable<String>{
    @Override
    public String call() throws Exception {
        Thread.sleep(800);
        return "Comunicaciones: enlace con estación terrestre establecido.";
    }
}
