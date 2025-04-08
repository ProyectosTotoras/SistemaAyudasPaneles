package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaAyudas.ConsultaBajaAyudasPesos.Entidades;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;

public class CuotasAyudasPesosTableModel extends AbstractTableModel {
	

	    private final String[] columnNames = {
	        "Nro Cuota", "Monto", "Vencimiento" 
	        // Agrega más columnas según necesites
	    };  
	    
	    private List<CuotaAyudaPesos> ayudas;

	    public CuotasAyudasPesosTableModel(List<CuotaAyudaPesos> ayudas) {
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
	        CuotaAyudaPesos ayuda = ayudas.get(rowIndex);
	        switch (columnIndex) {
	            case 0: return ayuda.getNumeroCuota();
	            case 1: return ayuda.getMontoCuota();
	            case 2: return ayuda.getFechaVtoCuota();
	            default: return null;
	        }
	    }

	    @Override
	    public String getColumnName(int column) {
	        return columnNames[column];
	    }
	    
	    public void setAyudas(List<CuotaAyudaPesos> ayudas) {
	        this.ayudas = ayudas;
	        fireTableDataChanged();
	    }
	    
	 // Método para obtener la ayuda en una fila específica
	    public CuotaAyudaPesos getAyudaAt(int rowIndex) {
	        if (rowIndex >= 0 && rowIndex < ayudas.size()) {
	            return ayudas.get(rowIndex);
	        }
	        return null;
	    }
	
}
