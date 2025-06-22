import java.util.List;

public class Ordenes {
    //1. METODO GENERICO
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        System.out.println("\n Mostrando Órdenes registradas:");
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
            System.out.println("-------------------");
        }
    }

    //2. METODO GENERICO
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("Órdenes personalizadas...");
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada) {
                OrdenPersonalizada orden = (OrdenPersonalizada) obj;
                System.out.println("Procesando orden personalizada de " + orden.getCliente() + "con costo adicional de $" + costoAdicional);
            }
        }
    }
}