public class OrdenMasa extends OrdenProduccion {
    public OrdenMasa(int cantidad, String codigo) {
        super(cantidad, codigo);
    }

    @Override //Sobreescribiendo
    public void mostrarResumen() {
        System.out.println("Orden - Producción en Masa");
        System.out.println("Código: " + codigo);
        System.out.println("Cantidad: " + cantidad);
    }
}
