package com.mutual.SistemaAyudaCuotas.paneles.ListadosVarios;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.servicio.ListSaldosAyudasPorSocioServicio;

import net.sf.jasperreports.engine.JRException;

@Component
public class ListadosVarios extends JPanel {

	private String panelAnterior = "MenuPrincipal";
	private JButton btnAltaAyudas;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JFrame mainFrame;
	
	@Autowired
	private final  ListSaldosAyudasPorSocioServicio listSaldosAyudasPorSocioServicio;
	 
	
	public ListadosVarios(ListSaldosAyudasPorSocioServicio listSaldosAyudasPorSocioServicio) {
		this.listSaldosAyudasPorSocioServicio = listSaldosAyudasPorSocioServicio;
	}
	
	  public void iniciar(
			  CardLayout cardLayout,
			  JPanel cardPanel,
			  JFrame mainFrame) {
	    	this.mainFrame = mainFrame;
	    	this.cardPanel = cardPanel;
	    	this.cardLayout = cardLayout;
	    	
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
		   // Create the main panel with fixed size (the one with all buttons)
        JPanel mainPanel = new JPanel(null); // use absolute positioning inside mainPanel
        Dimension fixedSize = new Dimension(544, 600);
        mainPanel.setPreferredSize(fixedSize);
        mainPanel.setMaximumSize(fixedSize);
        mainPanel.setBackground(new Color(145, 207, 238));
        mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        add(mainPanel);
        
    
        // Now add the left components to mainPanel (using same absolute positioning as original)
        btnAltaAyudas = new JButton("1 - Control de Movimientos.");
        btnAltaAyudas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
               
                }
            }
        });
        btnAltaAyudas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnAltaAyudas.setHorizontalAlignment(SwingConstants.LEFT);
        btnAltaAyudas.setFont(new Font("72", Font.BOLD, 17));
        btnAltaAyudas.setBounds(81, 25, 378, 36);
        mainPanel.add(btnAltaAyudas);
        
        JButton btnConsultaAyudas = new JButton("2 - Saldos Ayudas por Socios.");
        btnConsultaAyudas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                	try {
						generarListadoSaldosSaldosAyudasPorSocio();
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
               }
            }
        });
        btnConsultaAyudas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					generarListadoSaldosSaldosAyudasPorSocio();
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        btnConsultaAyudas.setHorizontalAlignment(SwingConstants.LEFT);
        btnConsultaAyudas.setFont(new Font("72", Font.BOLD, 17));
        btnConsultaAyudas.setBounds(81, 72, 378, 36);
        mainPanel.add(btnConsultaAyudas);
        
        JButton btnAltaCobros = new JButton("3 - Ayudas por Socios.");
        btnAltaCobros.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                }
            }
        });
        btnAltaCobros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnAltaCobros.setHorizontalAlignment(SwingConstants.LEFT);
        btnAltaCobros.setFont(new Font("72", Font.BOLD, 17));
        btnAltaCobros.setBounds(81, 119, 378, 36);
        mainPanel.add(btnAltaCobros);
        
        JButton btnConsultabBajaCobros = new JButton("4 - Ayudas Vencidas.");
        btnConsultabBajaCobros.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                }
            }
        });
        btnConsultabBajaCobros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           }
        });
        btnConsultabBajaCobros.setHorizontalAlignment(SwingConstants.LEFT);
        btnConsultabBajaCobros.setFont(new Font("72", Font.BOLD, 17));
        btnConsultabBajaCobros.setBounds(81, 166, 378, 36);
        mainPanel.add(btnConsultabBajaCobros);
        
        JButton btnPasarAyudaLegales = new JButton("5 - Ayudas y Cupones del Dia.");
        btnPasarAyudaLegales.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                 }
            }
        });
        btnPasarAyudaLegales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnPasarAyudaLegales.setHorizontalAlignment(SwingConstants.LEFT);
        btnPasarAyudaLegales.setFont(new Font("72", Font.BOLD, 17));
        btnPasarAyudaLegales.setBounds(81, 213, 378, 36);
        mainPanel.add(btnPasarAyudaLegales);
        
        JButton btnAnularPasarAyudaLegales = new JButton("6 - Cartas de Reclamo (30 Días).");
        btnAnularPasarAyudaLegales.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                }
            }
        });
        btnAnularPasarAyudaLegales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
            }
        });
        btnAnularPasarAyudaLegales.setHorizontalAlignment(SwingConstants.LEFT);
        btnAnularPasarAyudaLegales.setFont(new Font("72", Font.BOLD, 17));
        btnAnularPasarAyudaLegales.setBounds(81, 260, 378, 36);
        mainPanel.add(btnAnularPasarAyudaLegales);
        
        JButton btnAltaGarantias = new JButton("7 - Cartas de Reclamo (60 Días).");
        btnAltaGarantias.setHorizontalAlignment(SwingConstants.LEFT);
        btnAltaGarantias.setFont(new Font("72", Font.BOLD, 17));
        btnAltaGarantias.setBounds(81, 307, 378, 36);
        mainPanel.add(btnAltaGarantias);
        
        JButton btnAltaTasaSeguroVida = new JButton("8 - Ayudas en Gestión Judicial.");
        btnAltaTasaSeguroVida.setHorizontalAlignment(SwingConstants.LEFT);
        btnAltaTasaSeguroVida.setFont(new Font("72", Font.BOLD, 17));
        btnAltaTasaSeguroVida.setBounds(81, 354, 378, 36);
        mainPanel.add(btnAltaTasaSeguroVida);
        
        JButton btnRecalcularVtosPesos = new JButton("9 - Sellados entre Fecha.");
        btnRecalcularVtosPesos.setHorizontalAlignment(SwingConstants.LEFT);
        btnRecalcularVtosPesos.setFont(new Font("72", Font.BOLD, 17));
        btnRecalcularVtosPesos.setBounds(81, 401, 378, 36);
        mainPanel.add(btnRecalcularVtosPesos);
        
        JButton btnTipos = new JButton("10 - Tipos de Garantía.");
        btnTipos.setHorizontalAlignment(SwingConstants.LEFT);
        btnTipos.setFont(new Font("72", Font.BOLD, 17));
        btnTipos.setBounds(81, 446, 378, 36);
        mainPanel.add(btnTipos);
        
        JButton btnAyudas = new JButton("11 - Ayudas x Destino.");
        btnAyudas.setHorizontalAlignment(SwingConstants.LEFT);
        btnAyudas.setFont(new Font("72", Font.BOLD, 17));
        btnAyudas.setBounds(81, 493, 378, 36);
        mainPanel.add(btnAyudas);
        
        JButton btnAyudas_1 = new JButton("12 - Saldos por Socio.");
        btnAyudas_1.setHorizontalAlignment(SwingConstants.LEFT);
        btnAyudas_1.setFont(new Font("72", Font.BOLD, 17));
        btnAyudas_1.setBounds(81, 537, 378, 36);
        mainPanel.add(btnAyudas_1);
        
	}
	
	private void generarListadoSaldosSaldosAyudasPorSocio() throws JRException {
		
		
		   listSaldosAyudasPorSocioServicio.generarListado();
	    }
}
