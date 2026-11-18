# ConsoleCalendar

Biblioteca Java para dibujar **calendarios en la consola** (vista mensual y semanal), proporcionada por el
profesorado de **Programación III** (Universidad de Salamanca) para la práctica final.

La biblioteca **solo sabe dibujar**: recibe eventos (una fecha, un texto y un estilo) y los pinta en una
cuadrícula. No sabe nada de vuestra aplicación: qué eventos mostrar, cómo filtrarlos, cómo navegar entre
meses o qué estado tiene cada uno es responsabilidad del programa que la usa.

**Vista mensual** (`imprimirMes`): un símbolo por evento en cada día.

```
                                NOVIEMBRE 2026
                         Bancal: todos | Tipo: todos
+----------+----------+----------+----------+----------+----------+----------+
|   LUN    |   MAR    |   MIÉ    |   JUE    |   VIE    |   SÁB    |   DOM    |
+----------+----------+----------+----------+----------+----------+----------+
|          |          |          |          |          |          |  1 v     |
+----------+----------+----------+----------+----------+----------+----------+
|  2       |  3       |  4       |  5       |  6       |  7       |  8 !     |
+----------+----------+----------+----------+----------+----------+----------+
|  9       | 10 v     | 11       | 12       | 13       | 14       | 15 !!    |
+----------+----------+----------+----------+----------+----------+----------+
| 16       |[17]      | 18       | 19       | 20 *     | 21       | 22 *     |
+----------+----------+----------+----------+----------+----------+----------+
| 23       | 24       | 25 *     | 26       | 27       | 28       | 29 *     |
+----------+----------+----------+----------+----------+----------+----------+
| 30 *     |          |          |          |          |          |          |
+----------+----------+----------+----------+----------+----------+----------+
Leyenda: * pendiente   ! atrasado   v completado   # destacado   [n] hoy
Total: 10 evento(s) - 5 pendiente(s), 3 atrasado(s), 2 completado(s), 0 destacado(s)
```

**Vista semanal** (`imprimirSemana`): de lunes a domingo, con el texto de cada evento.

```
SEMANA DEL 16/11/2026 AL 22/11/2026
Bancal: todos | Tipo: todos
------------------------------------------------------------
LUN 16/11
    ! RIEGO - Tomate (B1) - Riego de Tomate
    v SIEMBRA - Rábano (B3) - Siembra de Rábano
MAR 17/11  <- hoy
    -
MIÉ 18/11
    * RIEGO - Lechuga (B2) - Riego de Lechuga
JUE 19/11
    -
VIE 20/11
    * RIEGO - Pimiento (B1) - Riego de Pimiento
SÁB 21/11
    -
DOM 22/11
    * RIEGO - Tomate (B1) - Riego de Tomate
    # ABONADO - Lechuga (B2) - Abonado de Lechuga
------------------------------------------------------------
Leyenda: * pendiente   ! atrasado   v completado   # destacado   [n] hoy
Total: 6 evento(s) - 3 pendiente(s), 1 atrasado(s), 1 completado(s), 1 destacado(s)
```

(Ambas salidas en modo ASCII; sin él, se dibujan con bordes Unicode y colores.)

## Contenido del repositorio

| Ruta | Contenido |
|---|---|
| `lib/consolecalendar-1.0.jar` | La biblioteca, lista para añadir a vuestro proyecto. |
| `docs/` | Documentación Javadoc (abrid `docs/index.html` en el navegador). |
| `ejemplo-calendario/` | Proyecto de ejemplo para VSCode, listo para abrir y ejecutar. |

Requisitos: **JDK 21** o superior. La biblioteca no tiene dependencias externas.

## Cómo añadirla a vuestro proyecto (VSCode)

1. Copiad `consolecalendar-1.0.jar` en la carpeta `lib/` de vuestro proyecto.
2. Comprobad que `.vscode/settings.json` incluye las bibliotecas de esa carpeta:

   ```json
   {
       "java.project.sourcePaths": ["src"],
       "java.project.outputPath": "bin",
       "java.project.referencedLibraries": ["lib/**/*.jar"]
   }
   ```

3. Importad las clases donde las uséis (recordad: **solo en el paquete `view`**):

   ```java
   import es.usal.progiii.calendar.ConsoleCalendar;
   import es.usal.progiii.calendar.EventStyle;
   ```

## Uso básico

```java
ConsoleCalendar cal = new ConsoleCalendar();
cal.setSubtitulo("Bancal: B1 | Tipo: todos");
cal.agregarEvento(LocalDate.of(2026, 11, 20), "RIEGO · Pimiento (B1)");
cal.agregarEvento(LocalDate.of(2026, 11, 15), "RIEGO · Tomate (B1)",
                  EventStyle.ATRASADO);
cal.imprimirMes(YearMonth.of(2026, 11));          // vista mensual
cal.imprimirSemana(LocalDate.of(2026, 11, 20));   // vista semanal (lunes a domingo)
```

Para dibujar otra información (por ejemplo, tras cambiar un filtro), eliminad primero los eventos
anteriores con `limpiarEventos()`.

## API

| Método | Descripción |
|---|---|
| `agregarEvento(LocalDate, String)` | Añade un evento con estilo `NORMAL`. |
| `agregarEvento(LocalDate, String, EventStyle)` | Añade un evento con el estilo indicado. |
| `limpiarEventos()` | Elimina todos los eventos. |
| `imprimirMes(YearMonth)` | Imprime la vista mensual: un símbolo por evento en cada día, la leyenda y un resumen. |
| `imprimirSemana(LocalDate)` | Imprime la semana (de lunes a domingo) que contiene esa fecha, con el texto de cada evento. |
| `setSubtitulo(String)` | Texto que se muestra bajo el título (por ejemplo, los filtros aplicados). |
| `setHoy(LocalDate)` | Fecha que se marca como "hoy" (por defecto, la del sistema). |
| `setModoAscii(boolean)` | Desactiva los colores y los caracteres Unicode, para consolas que no los muestran bien. |
| `setSalida(PrintStream)` | Cambia el destino de la salida (por defecto, `System.out`). |

### Estilos (`EventStyle`)

| Estilo | Símbolo | Símbolo ASCII | Color |
|---|---|---|---|
| `NORMAL` | `•` | `*` | sin color |
| `ATRASADO` | `!` | `!` | rojo |
| `COMPLETADO` | `✓` | `v` | verde |
| `DESTACADO` | `◆` | `#` | cian |

El calendario **no decide** qué estilo corresponde a cada evento: lo indica vuestro programa al añadirlo.

## Proyecto de ejemplo

Abrid la carpeta `ejemplo-calendario` en VSCode y ejecutad `App.java`, o desde la terminal:

```bash
cd ejemplo-calendario
javac -cp "lib/*" -d bin src/App.java
java -cp "bin:lib/*" App            # en Windows: java -cp "bin;lib/*" App
java -cp "bin:lib/*" App --ascii    # sin colores ni caracteres Unicode
```

Si vuestra consola no muestra bien los bordes o los colores (por ejemplo, algunas consolas de Windows),
usad el modo ASCII con `setModoAscii(true)`.

## Dudas y errores

Si encontráis un error en la biblioteca, comunicadlo al profesorado a través del foro de Studium,
indicando la versión, el sistema operativo y un ejemplo mínimo que lo reproduzca.

## Autor y licencia

Desarrollado por **André Filipe Sales Mendes** ([andremendes@usal.es](mailto:andremendes@usal.es)),
Universidad de Salamanca.

Distribuido bajo la licencia [MIT](LICENSE): podéis usar, copiar y modificar la biblioteca libremente,
manteniendo el aviso de copyright.
