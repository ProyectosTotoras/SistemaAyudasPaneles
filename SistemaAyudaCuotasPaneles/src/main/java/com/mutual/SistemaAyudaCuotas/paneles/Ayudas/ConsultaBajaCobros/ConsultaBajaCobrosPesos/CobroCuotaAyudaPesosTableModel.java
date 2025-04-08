package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaCobros.ConsultaBajaCobrosPesos;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaPesos;

public class CobroCuotaAyudaPesosTableModel extends AbstractTableModel {

    private final String[] columnNames = {
        "Cuota", "Monto", "Cargos", 
        "Fecha",
    };
    
    private List<CobroCuotaAyudaPesos> cobros;

    public CobroCuotaAyudaPesosTableModel(List<CobroCuotaAyudaPesos> cobros) {
        this.cobros = cobros;
    }

    @Override
    public int getRowCount() {
        return (cobros != null ? cobros.size() : 0);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CobroCuotaAyudaPesos cobro = cobros.get(rowIndex);
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
	    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    	symbols.setGroupingSeparator('.');
    	symbols.setDecimalSeparator(',');
    	DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
    	
        switch (columnIndex) {
       
            case 0: return cobro.getNumeroCuota();
            case 1: return df.format(cobro.getMontoCuota());
            case 2: return cobro.getCargos();
            case 3: return sdf.format(cobro.getFechaPagoCuota());
           
            // Agrega más casos según las columnas
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public void setCobros(List<CobroCuotaAyudaPesos> cobros) {
        this.cobros = (cobros != null ? cobros : new ArrayList<>());
        fireTableDataChanged();
    }
    
 // Método para obtener la ayuda en una fila específica
    public CobroCuotaAyudaPesos getCobroCuotaAyudaPesosAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < cobros.size()) {
            return cobros.get(rowIndex);
        }
        return null;
    }
}