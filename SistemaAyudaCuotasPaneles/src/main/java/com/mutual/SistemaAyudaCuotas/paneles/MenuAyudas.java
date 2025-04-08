package com.mutual.SistemaAyudaCuotas.paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.border.BevelBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.mutual.SistemaAyudaCuotas.SistemaAyudaCuotasCardLayout;

public class MenuAyudas extends JPanel {
	
	private String panelAnterior = "MenuPrincipal";
	private JButton btnAltaAyudas;
    public MenuAyudas(CardLayout cardLayout, JPanel cardPanel,JFrame mainFrame) {
        
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
    	        btnAltaAyudas.requestFocusInWindow();
    	    }
    	});
        
        JPanel mainPanel = new JPanel((LayoutManager) null);
        mainPanel.setPreferredSize(new Dimension(544, 559));
        mainPanel.setMaximumSize(new Dimension(544, 559));
        mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        mainPanel.setBackground(new Color(145, 207, 238));
        add(mainPanel);
        
        btnAltaAyudas = new JButton("1 - Alta de Ayudas.");
        btnAltaAyudas.setHorizontalAlignment(SwingConstants.LEFT);
        btnAltaAyudas.setFont(new Font("72", Font.BOLD, 17));
        btnAltaAyudas.setBounds(83, 73, 378, 36);
        btnAltaAyudas.addActionListener(e -> cardLayout.show(cardPanel, "AltaAyudaTipoAyuda"));
        btnAltaAyudas.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
    				 cardLayout.show(cardPanel, "AltaAyudaTipoAyuda");
    			}
    		}
    	});
        mainPanel.add(btnAltaAyudas);
        
        JButton btnConsultaAyudas = new JButton("2 - Consulta/Anulación de Ayudas.");
        btnConsultaAyudas.setHorizontalAlignment(SwingConstants.LEFT);
        btnConsultaAyudas.setFont(new Font("72", Font.BOLD, 17));
        btnConsultaAyudas.setBounds(83, 120, 378, 36);
        btnConsultaAyudas.addActionListener(e -> cardLayout.show(cardPanel, "ConsultaBajaAyudaTipoAyuda"));
        btnConsultaAyudas.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
    				 cardLayout.show(cardPanel, "ConsultaBajaAyudaTipoAyuda");
    			}
    		}
    	});
        mainPanel.add(btnConsultaAyudas);
        
        JButton btnAltaCobros = new JButton("3 - Alta de Cobros.");
        btnAltaCobros.setHorizontalAlignment(SwingConstants.LEFT);
        btnAltaCobros.setFont(new Font("72", Font.BOLD, 17));
        btnAltaCobros.setBounds(83, 167, 378, 36);
        btnAltaCobros.addActionListener(e -> cardLayout.show(cardPanel, "AltaCobrosTipoAyuda"));
        btnAltaCobros.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
    				 cardLayout.show(cardPanel, "AltaCobrosTipoAyuda");
    			}
    		}
    	});
        mainPanel.add(btnAltaCobros);
        
        JButton btnConsultabBajaCobros = new JButton("4 - Consulta/Baja de Cobros.");
        btnConsultabBajaCobros.setHorizontalAlignment(SwingConstants.LEFT);
        btnConsultabBajaCobros.setFont(new Font("72", Font.BOLD, 17));
        btnConsultabBajaCobros.setBounds(83, 214, 378, 36);
        btnConsultabBajaCobros.addActionListener(e -> cardLayout.show(cardPanel, "ConsultaBajaCobrosTipoAyuda"));
        btnConsultabBajaCobros.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
    				 cardLayout.show(cardPanel, "ConsultaBajaCobrosTipoAyuda");
    			}
    		}
    	});
        mainPanel.add(btnConsultabBajaCobros);
        
        JButton btnPasarAyudaLegales = new JButton("5 - Pasar Ayudas a Legales.");
        btnPasarAyudaLegales.setHorizontalAlignment(SwingConstants.LEFT);
        btnPasarAyudaLegales.setFont(new Font("72", Font.BOLD, 17));
        btnPasarAyudaLegales.setBounds(83, 261, 378, 36);
        btnPasarAyudaLegales.addActionListener(e -> cardLayout.show(cardPanel, "AyudasALegalesTipoAyuda"));
        btnPasarAyudaLegales.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
    				 cardLayout.show(cardPanel, "AyudasALegalesTipoAyuda");
    			}
    		}
    	});
        mainPanel.add(btnPasarAyudaLegales);
        
        JButton btnAnularPasarAyudaLegales = new JButton("6 - Anular Pasaje a Legales.");
        btnAnularPasarAyudaLegales.setHorizontalAlignment(SwingConstants.LEFT);
        btnAnularPasarAyudaLegales.setFont(new Font("72", Font.BOLD, 17));
        btnAnularPasarAyudaLegales.setBounds(83, 308, 378, 36);
        btnAnularPasarAyudaLegales.addActionListener(e -> cardLayout.show(cardPanel, "AnularAyudasALegalesTipoAyuda"));
        btnAnularPasarAyudaLegales.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
    				 cardLayout.show(cardPanel, "AnularAyudasALegalesTipoAyuda");
    			}
    		}
    	});
        mainPanel.add(btnAnularPasarAyudaLegales);
        
        JButton btnAltaGarantias = new JButton("7 - Alta de Garantías.");
        btnAltaGarantias.setHorizontalAlignment(SwingConstants.LEFT);
        btnAltaGarantias.setFont(new Font("72", Font.BOLD, 17));
        btnAltaGarantias.setBounds(83, 355, 378, 36);
        mainPanel.add(btnAltaGarantias);
        
        JButton btnAltaTasaSeguroVida = new JButton("8 - Alta tasa Seguro Vida.");
        btnAltaTasaSeguroVida.setHorizontalAlignment(SwingConstants.LEFT);
        btnAltaTasaSeguroVida.setFont(new Font("72", Font.BOLD, 17));
        btnAltaTasaSeguroVida.setBounds(83, 402, 378, 36);
        mainPanel.add(btnAltaTasaSeguroVida);
        
        JButton btnRecalcularVtosPesos = new JButton("9 - Recalcular Vtos.PESOS.");
        btnRecalcularVtosPesos.setHorizontalAlignment(SwingConstants.LEFT);
        btnRecalcularVtosPesos.setFont(new Font("72", Font.BOLD, 17));
        btnRecalcularVtosPesos.setBounds(83, 449, 378, 36);
        mainPanel.add(btnRecalcularVtosPesos);
        
        JLabel lblTitulo = new JLabel("1 - Mantenimiento de Ayudas.");
        lblTitulo.setBounds(125, 11, 280, 24);
        mainPanel.add(lblTitulo);
        lblTitulo.setFont(new Font("72", Font.BOLD, 20));
        lblTitulo.setAlignmentX(0.5f);
       
        setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnAltaAyudas, btnConsultaAyudas, btnAltaCobros, btnConsultabBajaCobros, btnPasarAyudaLegales, btnAnularPasarAyudaLegales, btnAltaGarantias, btnAltaTasaSeguroVida, btnRecalcularVtosPesos, mainPanel, lblTitulo}));
        
        }
}
