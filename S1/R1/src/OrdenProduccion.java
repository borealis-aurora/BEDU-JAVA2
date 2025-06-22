public abstract class OrdenProduccion{
    protected int cantidad;
    protected String codigo;

    // Constructor
    public OrdenProduccion(int cantidad, String codigo) {
        this.cantidad = cantidad;
        this.codigo = codigo;
    }

    public int getCantidad(){
        return cantidad;
    }

    public String getCodigo(){
        return codigo;
    }

    // Así usarán el metodo las demas clases
    public abstract void mostrarResumen();

}