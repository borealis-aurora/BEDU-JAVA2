public class OrdenPrototipo extends OrdenProduccion {
    private final String faseDesarrollo;

    public OrdenPrototipo(int cantidad, String codigo, String faseDesarrollo){
        super(cantidad, codigo);
        this.faseDesarrollo = faseDesarrollo;
    }

    public String getfaseDesarrollo(){
        return faseDesarrollo;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("Orden - Prototipo");
        System.out.println("Código: " + codigo);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Fase: " + faseDesarrollo);
    }
}
