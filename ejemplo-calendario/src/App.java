import java.time.LocalDate;
import java.time.YearMonth;

import es.usal.prog3.calendar.ConsoleCalendar;
import es.usal.prog3.calendar.EventStyle;

/**
 * Proyecto de ejemplo de la biblioteca ConsoleCalendar.
 * Ejecútelo y pruebe a cambiar los eventos, el estilo y el modo ASCII.
 */
public class App {

    public static void main(String[] args) {
        ConsoleCalendar cal = new ConsoleCalendar();

        // Si su consola no muestra bien los bordes o los colores, active el modo ASCII
        boolean ascii = args.length > 0 && args[0].equals("--ascii");
        cal.setModoAscii(ascii);

        LocalDate hoy = LocalDate.now();
        cal.setSubtitulo("Ejemplo de uso de ConsoleCalendar");

        // Cada evento es una fecha, un texto y (opcionalmente) un estilo
        cal.agregarEvento(hoy, "Evento de hoy");
        cal.agregarEvento(hoy.plusDays(1), "Evento de mañana", EventStyle.DESTACADO);
        cal.agregarEvento(hoy.plusDays(1), "Otro evento de mañana");
        cal.agregarEvento(hoy.minusDays(2), "Evento que no se hizo", EventStyle.ATRASADO);
        cal.agregarEvento(hoy.minusDays(3), "Evento ya hecho", EventStyle.COMPLETADO);

        // Vista mensual del mes actual
        cal.imprimirMes(YearMonth.from(hoy));

        // Vista semanal de la semana actual
        cal.imprimirSemana(hoy);

        // Para dibujar otra información, primero se eliminan los eventos anteriores
        cal.limpiarEventos();
        cal.setSubtitulo(null);
        cal.imprimirMes(YearMonth.from(hoy).plusMonths(1));
    }
}
