import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear órdenes de cada tipo
        OrdenMasa masa1 = new OrdenMasa(1506, "EXP01");
        OrdenMasa masa2 = new OrdenMasa(489, "EXP03");

        OrdenPersonalizada persona1 = new OrdenPersonalizada(650, "EXP24", "Cliente A");
        OrdenPersonalizada persona2 = new OrdenPersonalizada(320, "EXP02", "Cliente B");

        OrdenPrototipo proto1 = new OrdenPrototipo(234, "EXP13", "Diseño inicial");
        OrdenPrototipo proto2 = new OrdenPrototipo(567, "EXP05", "Pruebas finales");

        // Lista específica para cada tipo
        List<OrdenMasa> listaMasa = new ArrayList<>();
        listaMasa.add(masa1);
        listaMasa.add(masa2);

        List<OrdenPersonalizada> listaPers = new ArrayList<>();
        listaPers.add(persona1);
        listaPers.add(persona2);

        List<OrdenPrototipo> listaProto = new ArrayList<>();
        listaProto.add(proto1);
        listaProto.add(proto2);

        // Lista genérica con todas las órdenes
        List<OrdenProduccion> todasLasOrdenes = new ArrayList<>();
        todasLasOrdenes.addAll(listaMasa);
        todasLasOrdenes.addAll(listaPers);
        todasLasOrdenes.addAll(listaProto);

        // Probar los métodos genéricos
        Ordenes.mostrarOrdenes(todasLasOrdenes);
        Ordenes.procesarPersonalizadas(todasLasOrdenes, 250);

        // Prueba con listas específicas
        System.out.println("\n=== Mostrando solo órdenes de producción en masa ===");
        Ordenes.mostrarOrdenes(listaMasa);
    }
}