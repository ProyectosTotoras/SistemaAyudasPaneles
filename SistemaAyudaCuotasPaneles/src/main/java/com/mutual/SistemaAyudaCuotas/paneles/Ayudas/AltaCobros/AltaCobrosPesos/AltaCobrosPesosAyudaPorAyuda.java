package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaCobros.AltaCobrosPesos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.BevelBorder;
import javax.swing.text.AbstractDocument;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.Socio;
import com.mutual.SistemaAyudaCuotas.filtros.IntegerFilter;
import com.mutual.SistemaAyudaCuotas.paneles.MenuAyudas;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos.AltaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaAyudas.ConsultaBajaAyudasPesos.Entidades.AyudaPesosTableModel;
import com.mutual.SistemaAyudaCuotas.servicio.IAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ISocioServicio;

import jakarta.annotation.PostConstruct;

@Component
public class AltaCobrosPesosAyudaPorAyuda extends JPanel {
	
	private String panelAnterior = "MenuAyudas";
	private final IAyudaPesosServicio ayudaServicio;
	private final ISocioServicio socioServicio;

	private JTextField txtFiltroNroAyuda;
	private AltaCobrosPesosPantallaAyuda altaCobrosPesosPantallaAyuda;
	
	public AltaCobrosPesosAyudaPorAyuda(
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
              	cardLayout.show(cardPanel, panelAnterior);
            }
        });
    	
        addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentShown(ComponentEvent e) {
    	        e.getComponent().requestFocusInWindow();
    	        txtFiltroNroAyuda.requestFocusInWindow();
    	    }
    	});
    	

		JPanel mainPanel = new JPanel((LayoutManager) null);
		mainPanel.setPreferredSize(new Dimension(550, 300));
		mainPanel.setMaximumSize(new Dimension(550, 300));
		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainPanel.setBackground(UIManager.getColor("Button.background"));
		add(mainPanel);
		
		txtFiltroNroAyuda = new JTextField();
		txtFiltroNroAyuda.setFont(new Font("72", Font.PLAIN, 17));
		txtFiltroNroAyuda.setColumns(10);
		txtFiltroNroAyuda.setBounds(203, 138, 247, 23);
		txtFiltroNroAyuda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					AyudaPesos ayuda =  cargarDatos();
					if(ayuda != null) {
//						abrirAyudaPantalla(ayuda);
						altaCobrosPesosPantallaAyuda.setAyudaPesos(ayuda);
						altaCobrosPesosPantallaAyuda.setPanelAnterior("AltaCobrosPesosAyudaPorAyuda");
						cardLayout.show(cardPanel, "AltaCobrosPesosPantallaAyuda");
					}
				}
			}
		});
    	((AbstractDocument) txtFiltroNroAyuda.getDocument()).setDocumentFilter(new IntegerFilter());
 		
		mainPanel.add(txtFiltroNroAyuda);
		
		JLabel lblFiltroNumAyuda = new JLabel("Numero de Ayuda:");
		lblFiltroNumAyuda.setFont(new Font("72", Font.PLAIN, 17));
		lblFiltroNumAyuda.setBounds(53, 138, 139, 23);
		mainPanel.add(lblFiltroNumAyuda);
		
		JLabel lblAyudasPorNmero = new JLabel("Buscar Ayuda");
		lblAyudasPorNmero.setFont(new Font("72", Font.BOLD, 20));
		lblAyudasPorNmero.setBounds(203, 77, 150, 26);
		mainPanel.add(lblAyudasPorNmero);
    }
	
	public void setAltaCobrosPesosPantallaAyuda(AltaCobrosPesosPantallaAyuda altaCobrosPesosPantallaAyuda) {
		this.altaCobrosPesosPantallaAyuda = altaCobrosPesosPantallaAyuda;
	}
	 @PostConstruct
	    public void cargarDatosIniciales() {
	        cargarDatos();
	    }
	 
	 private void limpiarDatos() {
		  this.txtFiltroNroAyuda.setText("");
	  }
	 
	  private AyudaPesos cargarDatos() {
		     try {
	           // Obtener filtros
	            String filtroNumAyuda = txtFiltroNroAyuda != null ? txtFiltroNroAyuda.getText().trim() : "";
	            Socio socio = new Socio();
	            
	            AyudaPesos ayuda;
	            
	            if (!filtroNumAyuda.isEmpty()) {
	                ayuda = ayudaServicio.buscarAyudaPorNumeroAyuda(
	                    Integer.parseInt(filtroNumAyuda) 
	                );
	            
	                if(ayuda == null) {
	                	mostrarMensaje("Ayuda no encontrada");
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
		         ex.printStackTrace(); 
		    	 JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		     }
		     return null;  
	  }
	  
	  private void mostrarMensaje(String mensaje){
	        JOptionPane.showMessageDialog(this,mensaje);
	    } 
	
}
