import java.util.*;
import java.util.stream.*;

public class Confirmacion {

    public static void main(String[] args) {
        List<Pedido> pedidos = List.of(
                new Pedido("\uD83E\uDE77 Yoyis", "domicilio", "443-750-019"),
                new Pedido("\uD83D\uDC99 Didier", "domicilio", "477-645-887"),
                new Pedido("\uD83E\uDDE1 Lorena", "local", "492-769-827"),
                new Pedido("\uD83D\uDC9B Katia", "domicilio", null)
        );

        // 🛒 Almacenamos los mensajes en una lista
        List<String> mensajes = pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))
                .map(Pedido::getTelefono)
                .filter(Optional::isPresent)
                .map(Optional::get)
                //Creación mensaje
                .map(tel -> "\uD83D\uDCF2 Confirmación enviada al número: " + tel)
                .toList();

        System.out.println("==== Confirmaciones de pedidos a domicilio ====");
        mensajes.forEach(System.out::println);
    }
}