public class OrdenPersonalizada extends OrdenProduccion {
    private final String cliente;
    public OrdenPersonalizada(int cantidad, String codigo, String cliente){
        super(cantidad, codigo);
        this.cliente = cliente;
    }
    public String getCliente() {
        return cliente;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("Orden - Producción Personalizada");
        System.out.println("Código: " + codigo);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Cliente: " + cliente);
    }
}
