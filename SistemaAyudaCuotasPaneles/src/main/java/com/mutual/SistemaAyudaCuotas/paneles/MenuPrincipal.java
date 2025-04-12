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

import com.mutual.SistemaAyudaCuotas.SistemaAyudaCuotasCardLayout;

public class MenuPrincipal extends JPanel {
	
	private JButton btnMantenimientoAyudas;
	
    public MenuPrincipal(CardLayout cardLayout, JPanel cardPanel, JFrame mainFrame) {
    	addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentShown(ComponentEvent e) {
    	        e.getComponent().requestFocusInWindow();
    	        btnMantenimientoAyudas.requestFocusInWindow();
    	    }
    	});
    	
    	JPanel mainPanel = new JPanel((LayoutManager) null);
    	mainPanel.setPreferredSize(new Dimension(856, 481));
    	mainPanel.setMaximumSize(new Dimension(856, 481));
    	mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    	mainPanel.setBackground(new Color(145, 207, 238));
    	add(mainPanel);
    	
    	btnMantenimientoAyudas = new JButton("1 - Mantenimiento de Ayudas Económicas");
    	btnMantenimientoAyudas.setHorizontalAlignment(SwingConstants.LEFT);
    	btnMantenimientoAyudas.setFont(new Font("72", Font.BOLD, 17));
    	btnMantenimientoAyudas.setBounds(83, 57, 378, 36);
    	mainPanel.add(btnMantenimientoAyudas);
    	
    	btnMantenimientoAyudas.addActionListener(e -> cardLayout.show(cardPanel, "MenuAyudas"));
    	btnMantenimientoAyudas.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
    				 cardLayout.show(cardPanel, "MenuAyudas");
//    		         ((CardLayoutExample) mainFrame).showPanel("MenuAyudas");
    			}
    		}
    	});
    	
    	
    	JButton btnListados = new JButton("2 - Listados Varios");
    	btnListados.setHorizontalAlignment(SwingConstants.LEFT);
    	btnListados.setFont(new Font("72", Font.BOLD, 17));
    	btnListados.setBounds(83, 104, 378, 36);
    	btnListados.addActionListener(e -> cardLayout.show(cardPanel, "ListadosVarios"));
    	btnListados.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
    				 cardLayout.show(cardPanel, "ListadosVarios");
//    		         ((CardLayoutExample) mainFrame).showPanel("MenuAyudas");
    			}
    		}
    	});
    	mainPanel.add(btnListados);
    	
    	JButton btnMantenimientoGarantias = new JButton("3 - Mantenimiento de Garantías");
    	btnMantenimientoGarantias.setHorizontalAlignment(SwingConstants.LEFT);
    	btnMantenimientoGarantias.setFont(new Font("72", Font.BOLD, 17));
    	btnMantenimientoGarantias.setBounds(83, 151, 378, 36);
    	mainPanel.add(btnMantenimientoGarantias);
    	
    	JButton btnVencimientoAyudas = new JButton("4 - Vencimiento de Ayudas");
    	btnVencimientoAyudas.setHorizontalAlignment(SwingConstants.LEFT);
    	btnVencimientoAyudas.setFont(new Font("72", Font.BOLD, 17));
    	btnVencimientoAyudas.setBounds(83, 198, 378, 36);
    	mainPanel.add(btnVencimientoAyudas);
    	
    	JButton btnEstadisticasCuotas = new JButton("5 - Estadísticas de Cuotas");
    	btnEstadisticasCuotas.setHorizontalAlignment(SwingConstants.LEFT);
    	btnEstadisticasCuotas.setFont(new Font("72", Font.BOLD, 17));
    	btnEstadisticasCuotas.setBounds(83, 245, 378, 36);
    	mainPanel.add(btnEstadisticasCuotas);
    	
    	JButton btnSalir = new JButton("6 - Salida del Sistema");
    	btnSalir.setHorizontalAlignment(SwingConstants.LEFT);
    	btnSalir.setFont(new Font("72", Font.BOLD, 17));
    	btnSalir.setBounds(83, 378, 378, 36);
    	mainPanel.add(btnSalir);
    	
    	JLabel lblHoraActual = new JLabel("HoraActual");
    	lblHoraActual.setFont(new Font("72", Font.PLAIN, 15));
    	lblHoraActual.setBounds(762, 11, 120, 14);
    	mainPanel.add(lblHoraActual);
    	
    	JLabel lblFechaActual = new JLabel("27/03/2025");
    	lblFechaActual.setFont(new Font("72", Font.PLAIN, 15));
    	lblFechaActual.setBounds(93, 63, 120, 14);
    	mainPanel.add(lblFechaActual);
    	JLabel lblTitulo = new JLabel("SISTEMA DE AYUDA EN CUOTAS");
    	lblTitulo.setBounds(255, 3, 347, 26);
    	mainPanel.add(lblTitulo);
    	lblTitulo.setFont(new Font("72", Font.BOLD, 22));
    }
}


