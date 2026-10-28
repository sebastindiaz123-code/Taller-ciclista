package ciclismo;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import org.junit.jupiter.api.Test;

class CiclistaTest {

    

    @Test
    void velocistaGuardaAtributosYTipo() {
        Velocista v = new Velocista(1, "Ana", 450.5, 65.2);
        assertEquals(1, v.getIdentificador());
        assertEquals("Ana", v.getNombre());
        assertEquals(0, v.getTiempoAcumulado());
        assertEquals(450.5, v.getPotenciaPromedio());
        assertEquals(65.2, v.getVelocidadPromedioSprint());
        assertEquals("Velocista", v.imprimirTipo());
    }

    @Test
    void escaladorGuardaAtributosYTipo() {
        Escalador e = new Escalador(2, "Luis", 1.5f, 12.0f);
        assertEquals(2, e.getIdentificador());
        assertEquals("Luis", e.getNombre());
        assertEquals(0, e.getTiempoAcumulado());
        assertEquals(1.5f, e.getAceleracionPromedioSubida());
        assertEquals(12.0f, e.getGradoRampaSoportada());
        assertEquals("Escalador", e.imprimirTipo());
    }

    @Test
    void contrarrelojistaGuardaAtributosYTipo() {
        Contrarrelojista c = new Contrarrelojista(3, "Marta", 58.7);
        assertEquals(3, c.getIdentificador());
        assertEquals("Marta", c.getNombre());
        assertEquals(0, c.getTiempoAcumulado());
        assertEquals(58.7, c.getVelocidadMaxima());
        assertEquals("Contrarrelojista", c.imprimirTipo());
    }

    // ---------- Setters ----------

    @Test
    void settersDeCiclistaActualizanValores() {
        Ciclista c = new Velocista(1, "Ana", 400, 60);
        c.setNombre("Ana María");
        c.setIdentificador(99);
        assertEquals("Ana María", c.getNombre());
        assertEquals(99, c.getIdentificador());
    }

    @Test
    void settersDeVelocistaActualizanValores() {
        Velocista v = new Velocista(1, "Ana", 400, 60);
        v.setPotenciaPromedio(500.0);
        v.setVelocidadPromedioSprint(70.5);
        assertEquals(500.0, v.getPotenciaPromedio());
        assertEquals(70.5, v.getVelocidadPromedioSprint());
    }

    @Test
    void settersDeEscaladorActualizanValores() {
        Escalador e = new Escalador(2, "Luis", 1f, 10f);
        e.setAceleracionPromedioSubida(2.5f);
        e.setGradoRampaSoportada(18.0f);
        assertEquals(2.5f, e.getAceleracionPromedioSubida());
        assertEquals(18.0f, e.getGradoRampaSoportada());
    }

    @Test
    void setterDeContrarrelojistaActualizaValor() {
        Contrarrelojista c = new Contrarrelojista(3, "Marta", 55);
        c.setVelocidadMaxima(62.3);
        assertEquals(62.3, c.getVelocidadMaxima());
    }

    // ---------- Tiempo acumulado ----------

    @Test
    void acumularTiempoSumaMinutos() {
        Ciclista c = new Velocista(1, "Ana", 400, 60);
        c.acumularTiempo(120);
        c.acumularTiempo(30.5);
        assertEquals(150.5, c.getTiempoAcumulado());
    }

    @Test
    void acumularTiempoCeroNoCambiaElTotal() {
        Ciclista c = new Escalador(2, "Luis", 1f, 10f);
        c.acumularTiempo(0);
        assertEquals(0, c.getTiempoAcumulado());
    }

    @Test
    void acumularTiempoNegativoLanzaExcepcion() {
        Ciclista c = new Escalador(2, "Luis", 1f, 10f);
        assertThrows(IllegalArgumentException.class, () -> c.acumularTiempo(-5));
    }

    @Test
    void acumularTiempoNegativoNoAlteraElTotal() {
        Ciclista c = new Escalador(2, "Luis", 1f, 10f);
        c.acumularTiempo(40);
        assertThrows(IllegalArgumentException.class, () -> c.acumularTiempo(-5));
        assertEquals(40, c.getTiempoAcumulado());
    }

    // ---------- Polimorfismo y toString ----------

    @Test
    void polimorfismoImprimirTipo() {
        List<Ciclista> lista = List.of(
                new Velocista(1, "Ana", 400, 60),
                new Escalador(2, "Luis", 1f, 10f),
                new Contrarrelojista(3, "Marta", 55));
        assertEquals("Velocista", lista.get(0).imprimirTipo());
        assertEquals("Escalador", lista.get(1).imprimirTipo());
        assertEquals("Contrarrelojista", lista.get(2).imprimirTipo());
    }

    @Test
    void toStringIncluyeIdNombreYTiempo() {
        Ciclista c = new Velocista(7, "Ana", 400, 60);
        c.acumularTiempo(15);
        String texto = c.toString();
        assertTrue(texto.contains("7"));
        assertTrue(texto.contains("Ana"));
        assertTrue(texto.contains("15"));
    }

    // ---------- Reglas del enunciado (estructura de las clases) ----------

    @Test
    void ciclistaEsAbstractaYLasEspecialidadesNo() {
        assertTrue(Modifier.isAbstract(Ciclista.class.getModifiers()));
        assertFalse(Modifier.isAbstract(Velocista.class.getModifiers()));
        assertFalse(Modifier.isAbstract(Escalador.class.getModifiers()));
        assertFalse(Modifier.isAbstract(Contrarrelojista.class.getModifiers()));
    }

    @Test
    void lasEspecialidadesHeredanDeCiclista() {
        assertTrue(Ciclista.class.isAssignableFrom(Velocista.class));
        assertTrue(Ciclista.class.isAssignableFrom(Escalador.class));
        assertTrue(Ciclista.class.isAssignableFrom(Contrarrelojista.class));
    }

    @Test
    void imprimirTipoEsMetodoAbstractoQueDevuelveString() throws NoSuchMethodException {
        Method m = Ciclista.class.getDeclaredMethod("imprimirTipo");
        assertTrue(Modifier.isAbstract(m.getModifiers()));
        assertEquals(String.class, m.getReturnType());
    }

    @Test
    void todosLosAtributosSonPrivados() {
        List<Class<?>> clases = List.of(Ciclista.class, Velocista.class,
                Escalador.class, Contrarrelojista.class, Equipo.class);
        for (Class<?> clase : clases) {
            for (Field campo : clase.getDeclaredFields()) {
                assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        clase.getSimpleName() + "." + campo.getName() + " debe ser privado");
            }
        }
    }

    @Test
    void losMetodosPropiosDeLasEspecialidadesSonProtegidos() {
        List<Class<?>> especialidades = List.of(Velocista.class, Escalador.class, Contrarrelojista.class);
        for (Class<?> clase : especialidades) {
            for (Method metodo : clase.getDeclaredMethods()) {
                if (metodo.getName().equals("imprimirTipo")) {
                    continue; // sobrescribe el método público de Ciclista
                }
                assertTrue(Modifier.isProtected(metodo.getModifiers()),
                        clase.getSimpleName() + "." + metodo.getName() + " debe ser protegido");
            }
        }
    }
}
