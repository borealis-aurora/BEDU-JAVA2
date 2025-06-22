public class Profesional implements Runnable {
    private final String nombreRecurso;
    private final RecursoMedico recurso;

    public Profesional(String nombreRecurso, RecursoMedico recurso) {
        this.nombreRecurso = nombreRecurso;
        this.recurso = recurso;
    }

    @Override
    public void run() {
        recurso.usar(nombreRecurso);
    }
}
