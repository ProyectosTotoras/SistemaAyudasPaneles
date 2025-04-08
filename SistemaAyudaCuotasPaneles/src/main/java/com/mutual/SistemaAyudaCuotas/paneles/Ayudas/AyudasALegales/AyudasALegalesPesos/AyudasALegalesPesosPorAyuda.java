package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AyudasALegales.AyudasALegalesPesos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.border.BevelBorder;
import javax.swing.text.AbstractDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.Socio;
import com.mutual.SistemaAyudaCuotas.filtros.IntegerFilter;
import com.mutual.SistemaAyudaCuotas.servicio.IAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ISocioServicio;

import jakarta.annotation.PostConstruct;

@Component
public class AyudasALegalesPesosPorAyuda extends JPanel {
	
	private String panelAnterior = "AyudasALegalesPesosTipoBusqueda";
	private final IAyudaPesosServicio ayudaServicio;
	private final ISocioServicio socioServicio;

	private JTextField txtFiltroNroAyuda;
	private AyudasALegalesPesosPantallaAyuda ayudasALegalesPesosPantallaAyuda;
	
	@Autowired
	public AyudasALegalesPesosPorAyuda(
			IAyudaPesosServicio ayudaServicio, 
			ISocioServicio socioServicio 
			) {
		this.ayudaServicio =ayudaServicio;
		this.socioServicio = socioServicio;
		
//		iniciar(null,null,null);
    }
    
    public void iniciar(CardLayout cardLayout, JPanel cardPanel,JFrame mainFrame) {
    	
    	// Configurar key binding para la tecla Escape
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapeAction");
        this.getActionMap().put("escapeAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
              	limpiarDatos();
              	cardLayout.show(cardPanel, panelAnterior);
            }
        });
    	
        addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentShown(ComponentEvent e) {
    	        e.getComponent().requestFocusInWindow();
    	        limpiarDatos();
    	        txtFiltroNroAyuda.requestFocusInWindow();
    	    }
    	});
        Dimension containerSize = new Dimension(500, 300); // Adjust dimensions as needed
     
        // Create a main panel with absolute positioning; use the same fixed size
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setPreferredSize(containerSize);
        mainPanel.setMaximumSize(containerSize);
        add(mainPanel);
       
		txtFiltroNroAyuda = new JTextField();
		txtFiltroNroAyuda.setFont(new Font("72", Font.PLAIN, 17));
		txtFiltroNroAyuda.setColumns(10);
		txtFiltroNroAyuda.setBounds(204, 102, 247, 23);
		mainPanel.add(txtFiltroNroAyuda);
		
		JLabel lblFiltroNumAyuda = new JLabel("Numero de Ayuda:");
		lblFiltroNumAyuda.setFont(new Font("72", Font.PLAIN, 17));
		lblFiltroNumAyuda.setBounds(55, 105, 139, 23);
		mainPanel.add(lblFiltroNumAyuda);
        
        JLabel lblAyudasPorNmero = new JLabel("Buscar Ayuda");
        lblAyudasPorNmero.setFont(new Font("72", Font.PLAIN, 20));
        lblAyudasPorNmero.setBounds(225, 46, 150, 26);
        mainPanel.add(lblAyudasPorNmero);
        
    	txtFiltroNroAyuda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					AyudaPesos ayuda =  cargarDatos();
					if(ayuda != null) {
						
						ayudasALegalesPesosPantallaAyuda.setAyudaPesos(ayuda);
		              	cardLayout.show(cardPanel, "AyudasALegalesPesosPantallaAyuda");
					}
				}
			}
		});
    	((AbstractDocument) txtFiltroNroAyuda.getDocument()).setDocumentFilter(new IntegerFilter());
    	
    }

	public void setAyudasALegalesPesosPantallaAyuda(AyudasALegalesPesosPantallaAyuda ayudasALegalesPesosPantallaAyuda) {
		this.ayudasALegalesPesosPantallaAyuda= ayudasALegalesPesosPantallaAyuda;
	}

	  private void mostrarMensaje(String mensaje){
	        JOptionPane.showMessageDialog(this,mensaje);
	    }

	 private void limpiarDatos() {
		  this.txtFiltroNroAyuda.setText("");
	  }
	 
	  private AyudaPesos cargarDatos() {
		     try {
	           // Obtener filtros
	            String filtroNumAyuda = txtFiltroNroAyuda.getText().trim();
	            Socio socio = new Socio();
	            
	            AyudaPesos ayuda;
	            
	            if (!filtroNumAyuda.isEmpty()) {
	                ayuda = ayudaServicio.buscarAyudaPorNumeroAyudaSinFechaLegales(
	                    Integer.parseInt(filtroNumAyuda) 
	                ).get();
	            
	                if(ayuda == null) {
	                	mostrarMensaje("Ayuda no encontrada");
//	                	JOptionPane.showMessageDialog(this, "Ayuda no encontrada.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
	                	return null;
	                }
	                socio = socioServicio.buscarSocioPorNumeroSocio(ayuda.getNumeroSocio());
	            } else {
	            	ayuda = null;
	            }
	            
	            if(ayuda == null)
	            {
	            	return null;
	            }
	            return ayuda;
		     } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(this, "Por favor, ingresa números válidos en los filtros.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
		     } catch (Exception ex) {
		            JOptionPane.showMessageDialog(this, "Ayuda no encontrada en legales", "Error", JOptionPane.ERROR_MESSAGE);
		     }
		     return null;  
	  }
}
