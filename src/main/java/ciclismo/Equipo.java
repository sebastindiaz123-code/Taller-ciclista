package ciclismo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Equipo {

    private String nombre;
    private String pais;
    private final List<Ciclista> ciclistas;
    private static double sumaTiempos = 0; // minutos

    public Equipo(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
        this.ciclistas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Ciclista> getCiclistas() {
        return Collections.unmodifiableList(ciclistas);
    }

    public static double getSumaTiempos() {
        return sumaTiempos;
    }

    
    public static void reiniciarSumaTiempos() {
        sumaTiempos = 0;
    }

    
    public void agregarCiclista(Ciclista ciclista) {
        if (ciclista == null) {
            throw new IllegalArgumentException("El ciclista no puede ser nulo");
        }
        if (ciclistas.contains(ciclista)) {
            throw new IllegalArgumentException("El ciclista ya pertenece al equipo");
        }
        ciclistas.add(ciclista);
        sumaTiempos += ciclista.getTiempoAcumulado();
    }

    
    public void registrarTiempo(Ciclista ciclista, double minutos) {
        if (!ciclistas.contains(ciclista)) {
            throw new IllegalArgumentException("El ciclista no pertenece al equipo");
        }
        ciclista.acumularTiempo(minutos);
        sumaTiempos += minutos;
    }

    @Override
    public String toString() {
        return "Equipo [nombre=" + nombre + ", pais=" + pais
                + ", ciclistas=" + ciclistas.size() + ", sumaTiempos=" + sumaTiempos + " min]";
    }
}
