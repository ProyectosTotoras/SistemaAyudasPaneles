package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaAyudas.ConsultaBajaAyudasPesos.Entidades;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;

public class AyudaPesosTableModel extends AbstractTableModel {

    private final String[] columnNames = {
        "Nro Ayuda", "Nro Socio",
       "Fecha Emisión", "Monto Solicitado", "Estado"
        // Agrega más columnas según necesites
    };
    
    private List<AyudaPesos> ayudas;

    public AyudaPesosTableModel(List<AyudaPesos> ayudas) {
        this.ayudas = ayudas;
    }

    @Override
    public int getRowCount() {
        return ayudas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AyudaPesos ayuda = ayudas.get(rowIndex);
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
	    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    	symbols.setGroupingSeparator('.');
    	symbols.setDecimalSeparator(',');
    	DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
    	String tipoAyuda;
    	if(ayuda.getFechaLegales() != null || ayuda.getFechaOrigLegales() != null) {
    		tipoAyuda = "Legales  " + " " + sdf.format(ayuda.getFechaLegales());
    	}else {
    		tipoAyuda = "Normal";
    	}
    	
        switch (columnIndex) {
       
            case 0: return ayuda.getNroAyuda();
            case 1: return ayuda.getNumeroSocio();
            case 2: return sdf.format(ayuda.getFechaAltaAyuda()) ;
            case 3: return df.format(ayuda.getMontoSolicitado());
            case 4: return tipoAyuda;
           
            // Agrega más casos según las columnas
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public void setAyudas(List<AyudaPesos> ayudas) {
        this.ayudas = ayudas;
        fireTableDataChanged();
    }
    
 // Método para obtener la ayuda en una fila específica
    public AyudaPesos getAyudaAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < ayudas.size()) {
            return ayudas.get(rowIndex);
        }
        return null;
    }
}