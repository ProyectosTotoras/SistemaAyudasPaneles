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
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.BevelBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.paneles.MenuAyudas;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos.AltaAyudaPesos;

@Component
public class AltaCobrosPesosCobrarVariasOUna extends JPanel {
	
	private String panelAnterior = "AltaCobrosPesosPantallaAyuda";
	private JButton btnBusquedaSocios;
	private AyudaPesos ayudaPesos;
	private List<CuotaAyudaPesos> listaCuotasAdeudadas;
	private AltaCobrosPesosCobrarUnaCuota altaCobrosPesosCobrarUnaCuota;
	private AltaCobrosPesosDesdeHasta altaCobrosPesosDesdeHasta;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JFrame mainFrame;
	private JButton btnCobrarUnaCuota;
	public AltaCobrosPesosCobrarVariasOUna() {
		//iniciar(null,null,null);
    }
    
    public void iniciar(CardLayout cardLayout, JPanel cardPanel,JFrame mainFrame) {
    	this.cardLayout = cardLayout;
    	this.cardPanel = cardPanel;
    	this.mainFrame = mainFrame;
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
    	        btnCobrarUnaCuota.requestFocusInWindow();
    	    }
    	});
        
    	JPanel mainPanel = new JPanel((LayoutManager) null);
    	mainPanel.setPreferredSize(new Dimension(560, 300));
    	mainPanel.setMaximumSize(new Dimension(450, 270));
    	mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    	mainPanel.setBackground(new Color(138, 217, 247));
    	mainPanel.getLayout();
    	add(mainPanel);
  
	   btnCobrarUnaCuota = new JButton("1 - Cobrar una Cuota");
		btnCobrarUnaCuota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCobrarUnaCuota();
			}
		});
		
		btnCobrarUnaCuota.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e ) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					abrirCobrarUnaCuota();
				}
			}
		});
		btnCobrarUnaCuota.setFont(new Font("72", Font.BOLD, 17));
		btnCobrarUnaCuota.setBounds(104, 104, 325, 32);
		mainPanel.add(btnCobrarUnaCuota);
		
		JButton btnCobrarVariasCuotas = new JButton("2 - Cobrar Varias Cuotas");
		btnCobrarVariasCuotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCobroVariasDesdeHasta();
			}
		});
		
		btnCobrarVariasCuotas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e ) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					abrirCobroVariasDesdeHasta();
				}
			}
		});
		btnCobrarVariasCuotas.setFont(new Font("72", Font.BOLD, 17));
		btnCobrarVariasCuotas.setBounds(104, 162, 325, 32);
		mainPanel.add(btnCobrarVariasCuotas);
    	
    }

	public void setAyudaPesos(AyudaPesos ayudaPesos) {
	    if (ayudaPesos != null) {
	        this.ayudaPesos = ayudaPesos;
	    } else {
	        this.ayudaPesos = new AyudaPesos();
	    } 
	    
	}
	
	public void setlistaCuotasAdeudadas(List<CuotaAyudaPesos> listaCuotasAdeudadas) {
	    if (listaCuotasAdeudadas != null) {
	        this.listaCuotasAdeudadas = listaCuotasAdeudadas;
	    } else {
	        this.listaCuotasAdeudadas = new ArrayList<CuotaAyudaPesos>();
	    } 
	    
	}
	
	private void abrirCobrarUnaCuota() {
		if(listaCuotasAdeudadas.size() == 0) {
			mostrarMensaje("Ayuda sin cuotas adeudadas");
		}else {
			altaCobrosPesosCobrarUnaCuota.setListaCuotasAdeudadas(listaCuotasAdeudadas);
			altaCobrosPesosCobrarUnaCuota.setAyudaPesos(ayudaPesos);
			cardLayout.show(cardPanel, "AltaCobrosPesosCobrarUnaCuota");
		}
	
	}
	
	private void abrirCobroVariasDesdeHasta() {
		if( listaCuotasAdeudadas.size() == 0) {
			mostrarMensaje("Ayuda sin cuotas adeudadas");
		}else {
			altaCobrosPesosDesdeHasta.setListaCuotasAdeudadas(listaCuotasAdeudadas);
			altaCobrosPesosDesdeHasta.setAyudaPesos(ayudaPesos);
			cardLayout.show(cardPanel, "AltaCobrosPesosDesdeHasta");
		}
	}
	
//	public void limpiarDatos() {
//		this.cobrarUnaCuota.limpiarDatos();
//		this.cobrarVariasCuota.limpiarDatos();
//	}

	public void setAltaCobrosPesosCobrarUnaCuota(AltaCobrosPesosCobrarUnaCuota altaCobrosPesosCobrarUnaCuota) {
		this.altaCobrosPesosCobrarUnaCuota = altaCobrosPesosCobrarUnaCuota;
		
	}

	public void setAltaCobrosPesosDesdeHasta(
			AltaCobrosPesosDesdeHasta altaCobrosPesosDesdeHasta) {
		this.altaCobrosPesosDesdeHasta = altaCobrosPesosDesdeHasta;
	}
	 
	private void mostrarMensaje(String mensaje){
		JOptionPane.showMessageDialog(this,mensaje);
    }
}
