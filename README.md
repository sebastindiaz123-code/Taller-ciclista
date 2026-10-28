# Taller de Ciclistas – Clases abstractas

Ejercicio de programación orientada a objetos en Java sobre una carrera ciclística:
un **equipo** está formado por **ciclistas**, y cada ciclista tiene una **especialidad**.

## Integrantes

Juan Sebastian Charrupi
Adrian Andrade 

## Diseño

- `Ciclista` (**abstracta**): identificador (`int`), nombre (`String`), tiempo acumulado (min, inicia en 0) y método abstracto `imprimirTipo()` que devuelve un `String`.
- `Velocista`: potencia promedio (vatios) y velocidad promedio en sprint (km/h), ambos `double`.
- `Escalador`: aceleración promedio en subida (m/s²) y grado de rampa soportada (grados), ambos `float`.
- `Contrarrelojista`: velocidad máxima (km/h).
- `Equipo`: nombre (`String`), país, lista de ciclistas y suma de tiempos de carrera en minutos (atributo **estático**).

Todos los atributos son privados; los métodos de las especialidades son protegidos y los constructores llaman al de la clase padre con `super`. Las especialidades no son clases abstractas.
