package ciclismo;


public class Escalador extends Ciclista {

    private float aceleracionPromedioSubida; // m/s2
    private float gradoRampaSoportada;       // grados

    public Escalador(int identificador, String nombre,
                     float aceleracionPromedioSubida, float gradoRampaSoportada) {
        super(identificador, nombre);
        this.aceleracionPromedioSubida = aceleracionPromedioSubida;
        this.gradoRampaSoportada = gradoRampaSoportada;
    }

    protected float getAceleracionPromedioSubida() {
        return aceleracionPromedioSubida;
    }

    protected void setAceleracionPromedioSubida(float aceleracionPromedioSubida) {
        this.aceleracionPromedioSubida = aceleracionPromedioSubida;
    }

    protected float getGradoRampaSoportada() {
        return gradoRampaSoportada;
    }

    protected void setGradoRampaSoportada(float gradoRampaSoportada) {
        this.gradoRampaSoportada = gradoRampaSoportada;
    }

    @Override
    public String imprimirTipo() {
        return "Escalador";
    }
}
