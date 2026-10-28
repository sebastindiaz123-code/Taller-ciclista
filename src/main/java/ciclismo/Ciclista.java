package ciclismo;


public abstract class Ciclista {

    private int identificador;
    private String nombre;
    private double tiempoAcumulado; 

    public Ciclista(int identificador, String nombre) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.tiempoAcumulado = 0;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTiempoAcumulado() {
        return tiempoAcumulado;
    }

    
    public void acumularTiempo(double minutos) {
        if (minutos < 0) {
            throw new IllegalArgumentException("El tiempo no puede ser negativo");
        }
        this.tiempoAcumulado += minutos;
    }

    
    public abstract String imprimirTipo();

    @Override
    public String toString() {
        return "Ciclista [id=" + identificador + ", nombre=" + nombre
                + ", tiempoAcumulado=" + tiempoAcumulado + " min]";
    }
}
