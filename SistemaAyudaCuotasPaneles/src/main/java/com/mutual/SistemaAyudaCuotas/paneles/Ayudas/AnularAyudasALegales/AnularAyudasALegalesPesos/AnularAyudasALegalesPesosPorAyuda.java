package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AnularAyudasALegales.AnularAyudasALegalesPesos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Optional;

import javax.swing.border.BevelBorder;
import javax.swing.text.AbstractDocument;

import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.Socio;
import com.mutual.SistemaAyudaCuotas.filtros.IntegerFilter;
import com.mutual.SistemaAyudaCuotas.servicio.IAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ISocioServicio;

import jakarta.annotation.PostConstruct;

@Component
public class AnularAyudasALegalesPesosPorAyuda extends JPanel {
	
	private String panelAnterior = "AnularAyudasALegalesPesosTipoBusqueda";
	private final IAyudaPesosServicio ayudaServicio;
	private final ISocioServicio socioServicio;

	private JTextField txtFiltroNroAyuda;
	private AnularAyudasALegalesPesosPantAyuda anularAyudasALegalesPesosPantallaAyuda;
	public AnularAyudasALegalesPesosPorAyuda(
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
						
						anularAyudasALegalesPesosPantallaAyuda.setAyudaPesos(ayuda);
		              	cardLayout.show(cardPanel, "AnularAyudasALegalesPesosPantallaAyuda");
					}
				}
			}
		});
    	((AbstractDocument) txtFiltroNroAyuda.getDocument()).setDocumentFilter(new IntegerFilter());
    	
    }

    public void setAnularAyudasALegalesPesosPantallaAyuda(
    		AnularAyudasALegalesPesosPantAyuda anularAyudasALegalesPesosPantallaAyuda) {
		this.anularAyudasALegalesPesosPantallaAyuda = anularAyudasALegalesPesosPantallaAyuda;
		
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
		        if (!filtroNumAyuda.isEmpty()) {
		            Optional<AyudaPesos> ayudaOptional = ayudaServicio
		                .buscarAyudaPorNumeroAyudaFechaLegales(Integer.parseInt(filtroNumAyuda));
		            
		            if (!ayudaOptional.isPresent()) {
		            	 mostrarMensaje("La Ayuda no esta en legales.");
		            	 
//		                JOptionPane.showMessageDialog(this, "La Ayuda no esta en legales.", 
//		                    "Error de Formato", JOptionPane.ERROR_MESSAGE);
		                return null;
		            }
		            AyudaPesos ayuda = ayudaOptional.get();
		            
		            return ayuda;
		        } else {
		            return null;
		        }
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(this, "Por favor, ingresa números válidos en los filtros.", 
		            "Error de Formato", JOptionPane.ERROR_MESSAGE);
		    } catch (Exception ex) {
//		        JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + ex.getMessage(), 
//		            "Error", JOptionPane.ERROR_MESSAGE);
		    }
		    return null;  
		}
}
