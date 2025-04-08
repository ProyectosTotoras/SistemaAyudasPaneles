package com.mutual.SistemaAyudaCuotas.utilidades;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.entidades.Feriado;
import com.mutual.SistemaAyudaCuotas.servicio.IFeriadoServicio;

@Component
public class CalculosFechas {
	
	  private final IFeriadoServicio feriadoService;

	    @Autowired
	    public CalculosFechas(IFeriadoServicio feriadoService) {
	        this.feriadoService = feriadoService;
	    }
	
	    public List<LocalDate> calcularFechaPagoCuotas(int cantidadCuotas, int mesesGracia, LocalDate fechaActual) {
	        // Depuración: Imprimir los valores de entrada
//	        System.out.println("Inicio del cálculo de fechas de pago.");
//	        System.out.println("Cantidad de Cuotas: " + cantidadCuotas);
//	        System.out.println("Meses de Gracia: " + mesesGracia);
//	        System.out.println("Fecha Actual: " + fechaActual);

	        // Día del mes actual que deseamos mantener
	        int diaDeseado = fechaActual.getDayOfMonth();
//	        System.out.println("Día Deseado para las Cuotas: " + diaDeseado);

	        // Calcular la fecha de la primera cuota de manera segura
	        LocalDate fechaPrimerCuota = fechaActual.plusMonths(mesesGracia + 1);
	        fechaPrimerCuota = setDesiredDayOrNextMonth(fechaPrimerCuota, diaDeseado);
//	        System.out.println("Fecha Primer Cuota (después de ajustar día): " + fechaPrimerCuota);

	        // Ajustar la fecha si cae en fin de semana o feriado
	        fechaPrimerCuota = ajustarFechaPago(fechaPrimerCuota);
//	        System.out.println("Fecha Primer Cuota (después de ajustar fin de semana/feriado): " + fechaPrimerCuota);

	        // Lista para almacenar las fechas de pago
	        List<LocalDate> fechasPago = new ArrayList<>();
	        fechasPago.add(fechaPrimerCuota);

	        // Calcular las fechas de pago restantes
	        LocalDate fechaPagoAnterior = fechaPrimerCuota;
	        for (int i = 1; i < cantidadCuotas; i++) {
	            // Añadir un mes
	            LocalDate fechaPago = fechaPagoAnterior.plusMonths(1);
	            fechaPago = setDesiredDayOrNextMonth(fechaPago, diaDeseado);
//	            System.out.println("Fecha de Cuota " + (i + 1) + " (después de ajustar día): " + fechaPago);

	            // Ajustar la fecha si cae en fin de semana o feriado
	            fechaPago = ajustarFechaPago(fechaPago);
//	            System.out.println("Fecha de Cuota " + (i + 1) + " (después de ajustar fin de semana/feriado): " + fechaPago);

	            fechasPago.add(fechaPago);
	            fechaPagoAnterior = fechaPago;
	        }

	        System.out.println("Cálculo de fechas de pago completado.");
	        return fechasPago;
	    }


		

    /**
     * Calcula la fecha de sellado basada en la lógica especificada.
     *
     * @return Fecha de sellado formateada como String en formato dd/MM/yyyy.
     */
	    public String calcularFechaSellado(LocalDate fechaActual) {
//	        System.out.println("Fecha Actual: " + fechaActual);

	        int diaActual = fechaActual.getDayOfMonth();
	        int mesActual = fechaActual.getMonthValue();
	        int anoActual = fechaActual.getYear();
//	        System.out.println("Día Actual: " + diaActual);
//	        System.out.println("Mes Actual: " + mesActual);
//	        System.out.println("Año Actual: " + anoActual);

	        LocalDate fechaSellado;
	        int diaDeseado = 25; // Suponiendo que el día 25 es fijo para sellado

	        if (diaActual > 15) {
	            if (mesActual == 12) {
	                // Si es diciembre, el sellado se paga el 10 de enero del próximo año
	                fechaSellado = LocalDate.of(anoActual + 1, 1, 10);
//	                System.out.println("Sellado en diciembre, ajustando a: " + fechaSellado);
	            } else {
	                // Si no es diciembre, el sellado se paga el 10 del próximo mes
	                fechaSellado = fechaActual.plusMonths(1);
	                fechaSellado = setDesiredDayOrNextMonth(fechaSellado, 10);
//	                System.out.println("Sellado después del día 15, ajustando a: " + fechaSellado);
	            }
	        } else {
	            // Si el día actual es menor o igual a 15, el sellado se paga el 25 del mes actual
	            fechaSellado = setDesiredDayOrNextMonth(fechaActual, 25);
//	            System.out.println("Sellado antes o en el día 15, ajustando a: " + fechaSellado);
	        }

	        // Ajustar la fecha si cae en fin de semana o feriado
	        fechaSellado = ajustarFechaPago(fechaSellado);
//	        System.out.println("Fecha de Sellado ajustada: " + fechaSellado);

	        // Formatear la fecha a String
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String fechsell = fechaSellado.format(formatter);

	        return fechsell;
	    }

    
	private  LocalDate ajustarFechaPago(LocalDate fecha) {
	    while (esFinDeSemana(fecha) || esFeriado(fecha)) {
	        fecha = fecha.plusDays(1); // Avanza un día
	    }
	    return fecha;
	}
	private  boolean esFinDeSemana(LocalDate fecha) {
	    DayOfWeek dia = fecha.getDayOfWeek();
	    return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
	}
	
	private  boolean esFeriado(LocalDate fecha) {
	    // Aquí debes implementar la lógica para verificar si la fecha es un feriado
	    // Por ejemplo, podrías tener una lista de LocalDate con los feriados
	    List<LocalDate> feriados = obtenerFeriados();
	    return feriados.contains(fecha);
	}
	
	 /**
     * Obtiene la lista de feriados.
     *
     * @return Lista de fechas feriadas.
     */
    private  List<LocalDate> obtenerFeriados() {

    	List<LocalDate> feriados = new ArrayList<>();
        List<Feriado> feriadosDb = feriadoService.listarFeriados();
        feriadosDb.forEach(f -> {
        	feriados.add(ConvertorFechas.convertDateToLocalDate(f.getFecha()));
        });
        
        return feriados;
    }
    /**
     * Ajusta la fecha al día deseado o al primer día del siguiente mes si el día no existe.
     *
     * @param fecha       La fecha base.
     * @param desiredDay  El día que se desea establecer.
     * @return La fecha ajustada.
     */
    private LocalDate setDesiredDayOrNextMonth(LocalDate fecha, int desiredDay) {
        int maxDia = fecha.getMonth().length(fecha.isLeapYear());
        if (desiredDay <= maxDia) {
            return fecha.withDayOfMonth(desiredDay);
        } else {
            // Ajusta al primer día del siguiente mes
            return fecha.plusMonths(1).withDayOfMonth(1);
        }
    }

}
