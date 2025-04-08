package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaAyudas;

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

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.paneles.MenuAyudas;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos.AltaAyudaPesos;

@Component
public class ConsultaBajaAyudaTipoAyuda extends JPanel {
	
    private MenuAyudas menuAyudas;
	private AltaAyudaPesos altaAyudaPesos;
	private String panelAnterior = "MenuAyudas";
	private JButton btnAltaPesos;
	
	public ConsultaBajaAyudaTipoAyuda() {
 
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
    	        btnAltaPesos.requestFocusInWindow();
    	    }
    	});
        
    	JPanel mainPanel = new JPanel((LayoutManager) null);
    	mainPanel.setPreferredSize(new Dimension(450, 270));
    	mainPanel.setMaximumSize(new Dimension(450, 270));
    	mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    	mainPanel.setBackground(new Color(138, 217, 247));
    	mainPanel.getLayout();
    	add(mainPanel);
    	
    	JLabel lblTipoDeAyuda = new JLabel("Tipo De Ayuda");
    	lblTipoDeAyuda.setFont(new Font("72", Font.PLAIN, 22));
    	lblTipoDeAyuda.setAlignmentX(0.5f);
    	lblTipoDeAyuda.setBounds(159, 11, 141, 52);
    	mainPanel.add(lblTipoDeAyuda);
    	
    	btnAltaPesos = new JButton("1 - Pesos");
    	btnAltaPesos.setHorizontalAlignment(SwingConstants.LEFT);
    	btnAltaPesos.setFont(new Font("72", Font.BOLD, 17));
    	btnAltaPesos.setBounds(74, 80, 300, 32);
    	btnAltaPesos.addActionListener(e -> cardLayout.show(cardPanel, "ConsultaBajaAyudaTipoBusqueda"));
    	btnAltaPesos.addKeyListener(new KeyAdapter() {
     		@Override
     		public void keyPressed(KeyEvent e) {
     			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
     				 cardLayout.show(cardPanel, "ConsultaBajaAyudaTipoBusqueda");
     			
     			}
     		}
     	});
    	mainPanel.add(btnAltaPesos);
    	
    	JButton btnAltaDolares = new JButton("2 - Dólares");
    	btnAltaDolares.setHorizontalAlignment(SwingConstants.LEFT);
    	btnAltaDolares.setFont(new Font("72", Font.BOLD, 17));
    	btnAltaDolares.setBounds(74, 130, 300, 32);
    	mainPanel.add(btnAltaDolares);
    	
    	JButton btnFrances = new JButton("3 - Sistema Francés");
    	btnFrances.setHorizontalAlignment(SwingConstants.LEFT);
    	btnFrances.setFont(new Font("72", Font.BOLD, 17));
    	btnFrances.setBounds(74, 180, 300, 32);
    	mainPanel.add(btnFrances);
    	
    
    	 
    }

	public void setMenuAyudas(MenuAyudas menuAyudas) {
		this.menuAyudas = menuAyudas;
	}

	public void setAltaAyudaPesos(AltaAyudaPesos altaAyudaPesos) {
		this.altaAyudaPesos = altaAyudaPesos;
		
	}
  
}
