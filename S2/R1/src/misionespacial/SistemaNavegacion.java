package misionespacial;

import java.util.concurrent.*;

public class SistemaNavegacion implements Callable<String>{
    @Override
    public String call() throws Exception {
        Thread.sleep(1100);
        return "Navegación: trayectoria corregida con éxito.";
    }
}