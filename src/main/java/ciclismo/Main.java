package ciclismo;


public class Main {

    public static void main(String[] args) {
        Equipo equipo = new Equipo("Team Colombia", "Colombia");

        Velocista velocista = new Velocista(1, "Ana Torres", 450.5, 65.2);
        Escalador escalador = new Escalador(2, "Luis Pérez", 1.5f, 12.0f);
        Contrarrelojista contrarrelojista = new Contrarrelojista(3, "Marta Gómez", 58.7);

        equipo.agregarCiclista(velocista);
        equipo.agregarCiclista(escalador);
        equipo.agregarCiclista(contrarrelojista);

        equipo.registrarTiempo(velocista, 210.5);
        equipo.registrarTiempo(escalador, 225.0);
        equipo.registrarTiempo(contrarrelojista, 198.25);

        System.out.println(equipo);
        System.out.println("--- Ciclistas (polimorfismo con imprimirTipo) ---");
        for (Ciclista c : equipo.getCiclistas()) {
            System.out.println(c.imprimirTipo() + " -> " + c);
        }
        System.out.println("Suma de tiempos del equipo: " + Equipo.getSumaTiempos() + " min");
    }
}
