package com.mutual.SistemaAyudaCuotas.utilidades;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConvertorFechas {
	
	    /**
	     * Convierte un LocalDate a Date.
	     *
	     * @param localDate la fecha en formato LocalDate
	     * @return la fecha convertida en formato Date
	     */
	    public static Date convertLocalDateToDate(LocalDate localDate) {
	        if (localDate == null) {
	            return null;
	        }
	        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    }
	    
	    
	    public static LocalDate convertDateToLocalDate(Date date) {
	        if (date == null) {
	            return null; // Manejar el caso donde date es null
	        }
	        
	        // Use the java.sql.Date method if applicable.
	        if (date instanceof java.sql.Date) {
	            return ((java.sql.Date) date).toLocalDate();
	        }
	        
	        Instant instant = date.toInstant();
	        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
	    }


}

