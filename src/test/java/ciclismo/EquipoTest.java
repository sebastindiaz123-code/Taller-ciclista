package ciclismo;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Modifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EquipoTest {

    private Equipo equipo;
    private Velocista velocista;
    private Escalador escalador;

    @BeforeEach
    void preparar() {
        Equipo.reiniciarSumaTiempos();
        equipo = new Equipo("Team Colombia", "Colombia");
        velocista = new Velocista(1, "Ana", 400, 60);
        escalador = new Escalador(2, "Luis", 1f, 10f);
    }

    

    @Test
    void equipoGuardaNombreYPais() {
        assertEquals("Team Colombia", equipo.getNombre());
        assertEquals("Colombia", equipo.getPais());
        assertTrue(equipo.getCiclistas().isEmpty());
    }

    @Test
    void settersDeEquipoActualizanValores() {
        equipo.setNombre("Team España");
        equipo.setPais("España");
        assertEquals("Team España", equipo.getNombre());
        assertEquals("España", equipo.getPais());
    }

    @Test
    void toStringIncluyeNombrePaisYCantidadDeCiclistas() {
        equipo.agregarCiclista(velocista);
        String texto = equipo.toString();
        assertTrue(texto.contains("Team Colombia"));
        assertTrue(texto.contains("Colombia"));
        assertTrue(texto.contains("1"));
    }

    

    @Test
    void agregarCiclistasLosIncluyeEnElEquipo() {
        equipo.agregarCiclista(velocista);
        equipo.agregarCiclista(escalador);
        assertEquals(2, equipo.getCiclistas().size());
        assertTrue(equipo.getCiclistas().contains(velocista));
        assertTrue(equipo.getCiclistas().contains(escalador));
    }

    @Test
    void agregarCiclistaNuloLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> equipo.agregarCiclista(null));
    }

    @Test
    void agregarElMismoCiclistaDosVecesLanzaExcepcionYNoDuplicaLaSuma() {
        velocista.acumularTiempo(50);
        equipo.agregarCiclista(velocista);
        assertThrows(IllegalArgumentException.class, () -> equipo.agregarCiclista(velocista));
        assertEquals(1, equipo.getCiclistas().size());
        assertEquals(50, Equipo.getSumaTiempos());
    }

    @Test
    void laListaDeCiclistasNoSePuedeModificarDesdeFuera() {
        assertThrows(UnsupportedOperationException.class,
                () -> equipo.getCiclistas().add(velocista));
    }

    

    @Test
    void sumaTiemposEmpiezaEnCero() {
        assertEquals(0, Equipo.getSumaTiempos());
    }

    @Test
    void agregarCiclistaConTiempoPrevioLoSumaAlTotal() {
        velocista.acumularTiempo(50);
        equipo.agregarCiclista(velocista);
        assertEquals(50, Equipo.getSumaTiempos());
    }

    @Test
    void sumaTiempoEsLaSumaDeLosTiemposDeLosCiclistas() {
        equipo.agregarCiclista(velocista);
        equipo.agregarCiclista(escalador);
        equipo.registrarTiempo(velocista, 100);
        equipo.registrarTiempo(escalador, 75.5);
        assertEquals(175.5, Equipo.getSumaTiempos());
        assertEquals(100, velocista.getTiempoAcumulado());
        assertEquals(75.5, escalador.getTiempoAcumulado());
    }

    @Test
    void registrarVariasVecesAcumulaElTiempoDelCiclistaYDelEquipo() {
        equipo.agregarCiclista(velocista);
        equipo.registrarTiempo(velocista, 30);
        equipo.registrarTiempo(velocista, 20);
        assertEquals(50, velocista.getTiempoAcumulado());
        assertEquals(50, Equipo.getSumaTiempos());
    }

    @Test
    void registrarTiempoNegativoLanzaExcepcionYNoAlteraLaSuma() {
        equipo.agregarCiclista(velocista);
        equipo.registrarTiempo(velocista, 30);
        assertThrows(IllegalArgumentException.class,
                () -> equipo.registrarTiempo(velocista, -10));
        assertEquals(30, velocista.getTiempoAcumulado());
        assertEquals(30, Equipo.getSumaTiempos());
    }

    @Test
    void registrarTiempoDeCiclistaAjenoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> equipo.registrarTiempo(velocista, 10));
    }

    @Test
    void registrarTiempoDeCiclistaNuloLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> equipo.registrarTiempo(null, 10));
    }

    @Test
    void sumaTiemposEsEstaticaYSeComparteEntreEquipos() throws NoSuchFieldException {
        assertTrue(Modifier.isStatic(Equipo.class.getDeclaredField("sumaTiempos").getModifiers()));

        Equipo otro = new Equipo("Team Brasil", "Brasil");
        Contrarrelojista c = new Contrarrelojista(3, "Marta", 55);
        equipo.agregarCiclista(velocista);
        otro.agregarCiclista(c);
        equipo.registrarTiempo(velocista, 100);
        otro.registrarTiempo(c, 40);
        assertEquals(140, Equipo.getSumaTiempos());
    }

    @Test
    void reiniciarSumaTiemposLaDejaEnCero() {
        equipo.agregarCiclista(velocista);
        equipo.registrarTiempo(velocista, 100);
        Equipo.reiniciarSumaTiempos();
        assertEquals(0, Equipo.getSumaTiempos());
    }
}
