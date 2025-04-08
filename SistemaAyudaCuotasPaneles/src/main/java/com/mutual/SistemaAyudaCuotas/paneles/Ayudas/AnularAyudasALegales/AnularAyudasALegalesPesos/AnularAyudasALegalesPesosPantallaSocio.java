package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AnularAyudasALegales.AnularAyudasALegalesPesos;

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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.componentes.PlaceholderTextField;
import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.Garantia;
import com.mutual.SistemaAyudaCuotas.entidades.Socio;
import com.mutual.SistemaAyudaCuotas.paneles.MenuAyudas;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos.AltaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.servicio.IAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ICobroCuotaAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ICuotaAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.IGarantiaServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ISocioServicio;

@Component
public class AnularAyudasALegalesPesosPantallaSocio extends JPanel {
	
	private AyudaPesos ayudaPesos;
	private String panelAnterior = "AnularAyudasALegalesPesosPorSocio";
	private ICuotaAyudaPesosServicio cuotasServicio;
	private IGarantiaServicio garantiaServicio;
	private ISocioServicio socioServicio;
	private IAyudaPesosServicio ayudaServicio;
	private ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio;
	
	private JTable table;	
	private PlaceholderTextField txtNumeroSocio;	
	private PlaceholderTextField txtNumSocioGarante;	
	private PlaceholderTextField txtNumAyuda;	
	private PlaceholderTextField txtNombreSocio;	
	private PlaceholderTextField txtNumSocioGarante1;	
	private PlaceholderTextField txtNumSocioGarante2;	
	private PlaceholderTextField txtNombreSocioGarante;	
	private PlaceholderTextField txtNombreSocioGarante1;
	private PlaceholderTextField txtNombreSocioGarante2;
	private PlaceholderTextField txtFechaEmision;	
	private PlaceholderTextField txtMontoSolicitado;	
	private PlaceholderTextField txtTasaInteres;	
	private PlaceholderTextField txtGastosAdmin;	
	private PlaceholderTextField txtGastosCobranza;	
	private PlaceholderTextField txtGarantia;
	private PlaceholderTextField txtDestino;	
	private PlaceholderTextField txtCantCuotas;	
	private PlaceholderTextField txtInteresCuota;	
	private PlaceholderTextField txtMontoCuota;	
	private PlaceholderTextField txtMontoEfectivo;
	private PlaceholderTextField txtTransferencia;

	private PlaceholderTextField txtCheques;
	private JButton btnPasarALegales;
	private JLabel lblFechaAyudaLegales;
	private JLabel lblAyudaLegales;
	private CardLayout cardLayout; 
	private JPanel cardPanel;
	private JFrame mainFrame;
	
	public AnularAyudasALegalesPesosPantallaSocio( 
			 ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio,
				ICuotaAyudaPesosServicio cuotasServicio, 
				ISocioServicio socioServicio, 
				IGarantiaServicio garantiaServicio,
				IAyudaPesosServicio ayudaServicio) {
		this.ayudaPesos = new AyudaPesos();
		this.cuotasServicio = cuotasServicio;
		this.garantiaServicio = garantiaServicio;
		this.socioServicio = socioServicio;
		this.ayudaServicio = ayudaServicio;
		this.cobroCuotaAyudaPesosServicio = cobroCuotaAyudaPesosServicio;
	
//		iniciar(null,null,null);
    }
    
    public void iniciar(CardLayout cardLayout, JPanel cardPanel,JFrame mainFrame) {
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
//    	        btnAnularAyuda.requestFocusInWindow();
    	        cargarDatosAyudaPesos();
    	  	     
    	    }
    	});
    
        Dimension containerSize = new Dimension(1080, 670); 
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setPreferredSize(containerSize);
        mainPanel.setMaximumSize(containerSize);
        add(mainPanel);
		
        JLabel lblSocio = new JLabel("Socio:");
		lblSocio.setFont(new Font("72", Font.PLAIN, 17));
		lblSocio.setBounds(25, 66, 65, 23);
		mainPanel.add(lblSocio);
		
		txtNumeroSocio = new PlaceholderTextField("00000000.00");
		txtNumeroSocio.setFocusable(false);
		txtNumeroSocio.setForeground(new Color(0, 0, 0));
		txtNumeroSocio.setPlaceholder("0000");
		txtNumeroSocio.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNumeroSocio.setColumns(10);
		txtNumeroSocio.setBounds(99, 63, 76, 23);
		mainPanel.add(txtNumeroSocio);
		
		JLabel lblSocioGarante = new JLabel("Socio Garante:");
		lblSocioGarante.setFont(new Font("72", Font.PLAIN, 17));
		lblSocioGarante.setBounds(25, 96, 137, 23);
		mainPanel.add(lblSocioGarante);
		
		txtNumSocioGarante = new PlaceholderTextField("00000000.00");
		txtNumSocioGarante.setFocusable(false);
		txtNumSocioGarante.setForeground(new Color(0, 0, 0));
		txtNumSocioGarante.setPlaceholder("0000");
		txtNumSocioGarante.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNumSocioGarante.setColumns(10);
		txtNumSocioGarante.setBounds(180, 92, 56, 23);
		mainPanel.add(txtNumSocioGarante);
		
		JLabel lblNumeroAyuda = new JLabel("Numero Ayuda:");
		lblNumeroAyuda.setFont(new Font("72", Font.PLAIN, 17));
		lblNumeroAyuda.setBounds(25, 196, 137, 23);
		mainPanel.add(lblNumeroAyuda);
		
		txtNumAyuda = new PlaceholderTextField("00000000.00");
		txtNumAyuda.setFocusable(false);
		txtNumAyuda.setForeground(new Color(0, 0, 0));
		txtNumAyuda.setPlaceholder("00000");
		txtNumAyuda.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNumAyuda.setColumns(10);
		txtNumAyuda.setBounds(211, 196, 65, 23);
		mainPanel.add(txtNumAyuda);
		
		txtNombreSocio = new PlaceholderTextField("00000000.00");
		txtNombreSocio.setFocusable(false);
		txtNombreSocio.setForeground(new Color(0, 0, 0));
		txtNombreSocio.setPlaceholder("...");
		txtNombreSocio.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombreSocio.setColumns(10);
		txtNombreSocio.setBounds(185, 63, 346, 23);
		mainPanel.add(txtNombreSocio);
		
		JLabel lblSocioGarante1 = new JLabel("Segundo Garante:");
		lblSocioGarante1.setFont(new Font("72", Font.PLAIN, 17));
		lblSocioGarante1.setBounds(25, 124, 137, 23);
		mainPanel.add(lblSocioGarante1);
		
		txtNumSocioGarante1 = new PlaceholderTextField("00000000.00");
		txtNumSocioGarante1.setFocusable(false);
		txtNumSocioGarante1.setForeground(new Color(0, 0, 0));
		txtNumSocioGarante1.setPlaceholder("0000");
		txtNumSocioGarante1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNumSocioGarante1.setColumns(10);
		txtNumSocioGarante1.setBounds(180, 120, 56, 23);
		mainPanel.add(txtNumSocioGarante1);
		
		JLabel lblSocioGarante2 = new JLabel("Tercer Garante");
		lblSocioGarante2.setFont(new Font("72", Font.PLAIN, 17));
		lblSocioGarante2.setBounds(25, 153, 137, 23);
		mainPanel.add(lblSocioGarante2);
		
		txtNumSocioGarante2 = new PlaceholderTextField("00000000.00");
		txtNumSocioGarante2.setFocusable(false);
		txtNumSocioGarante2.setForeground(new Color(0, 0, 0));
		txtNumSocioGarante2.setPlaceholder("0000");
		txtNumSocioGarante2.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNumSocioGarante2.setColumns(10);
		txtNumSocioGarante2.setBounds(180, 149, 56, 23);
		mainPanel.add(txtNumSocioGarante2);
		
		txtNombreSocioGarante = new PlaceholderTextField("00000000.00");
		txtNombreSocioGarante.setFocusable(false);
		txtNombreSocioGarante.setForeground(new Color(0, 0, 0));
		txtNombreSocioGarante.setPlaceholder("...");
		txtNombreSocioGarante.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombreSocioGarante.setColumns(10);
		txtNombreSocioGarante.setBounds(246, 92, 285, 23);
		mainPanel.add(txtNombreSocioGarante);
		
		txtNombreSocioGarante1 = new PlaceholderTextField("00000000.00");
		txtNombreSocioGarante1.setFocusable(false);
		txtNombreSocioGarante1.setForeground(new Color(0, 0, 0));
		txtNombreSocioGarante1.setPlaceholder("...");
		txtNombreSocioGarante1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombreSocioGarante1.setColumns(10);
		txtNombreSocioGarante1.setBounds(246, 120, 285, 23);
		mainPanel.add(txtNombreSocioGarante1);
		
		txtNombreSocioGarante2 = new PlaceholderTextField("00000000.00");
		txtNombreSocioGarante2.setFocusable(false);
		txtNombreSocioGarante2.setForeground(new Color(0, 0, 0));
		txtNombreSocioGarante2.setPlaceholder("...");
		txtNombreSocioGarante2.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombreSocioGarante2.setColumns(10);
		txtNombreSocioGarante2.setBounds(246, 150, 285, 23);
		mainPanel.add(txtNombreSocioGarante2);
		
		JLabel lblFechaEmision = new JLabel("Fecha Emisión:");
		lblFechaEmision.setFont(new Font("72", Font.PLAIN, 17));
		lblFechaEmision.setBounds(25, 224, 137, 23);
		mainPanel.add(lblFechaEmision);
		
		txtFechaEmision = new PlaceholderTextField("00000000.00");
		txtFechaEmision.setFocusable(false);
		txtFechaEmision.setForeground(new Color(0, 0, 0));
		txtFechaEmision.setPlaceholder("00/00/0000");
		txtFechaEmision.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFechaEmision.setColumns(10);
		txtFechaEmision.setBounds(211, 224, 94, 23);
		mainPanel.add(txtFechaEmision);
		
		JLabel lblMontoSolicitado = new JLabel("Monto Solicitado:");
		lblMontoSolicitado.setFont(new Font("72", Font.PLAIN, 17));
		lblMontoSolicitado.setBounds(25, 252, 137, 23);
		mainPanel.add(lblMontoSolicitado);
		
		txtMontoSolicitado = new PlaceholderTextField("00000000.00");
		txtMontoSolicitado.setFocusable(false);
		txtMontoSolicitado.setForeground(new Color(0, 0, 0));
		txtMontoSolicitado.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMontoSolicitado.setColumns(10);
		txtMontoSolicitado.setBounds(211, 252, 108, 23);
		mainPanel.add(txtMontoSolicitado);
		
		JLabel lblTasaInteres = new JLabel("Tasa de Interés:");
		lblTasaInteres.setFont(new Font("72", Font.PLAIN, 17));
		lblTasaInteres.setBounds(25, 280, 137, 23);
		mainPanel.add(lblTasaInteres);
		
		txtTasaInteres = new PlaceholderTextField("00000000.00");
		txtTasaInteres.setFocusable(false);
		txtTasaInteres.setForeground(new Color(0, 0, 0));
		txtTasaInteres.setPlaceholder("00.00");
		txtTasaInteres.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTasaInteres.setColumns(10);
		txtTasaInteres.setBounds(211, 280, 94, 23);
		mainPanel.add(txtTasaInteres);
		
		JLabel lblGastosAdmin = new JLabel("Gtos. Administrativos:");
		lblGastosAdmin.setFont(new Font("72", Font.PLAIN, 17));
		lblGastosAdmin.setBounds(25, 308, 164, 23);
		mainPanel.add(lblGastosAdmin);
		
		txtGastosAdmin = new PlaceholderTextField("00000000.00");
		txtGastosAdmin.setFocusable(false);
		txtGastosAdmin.setForeground(new Color(0, 0, 0));
		txtGastosAdmin.setFont(new Font("Arial", Font.PLAIN, 16));
		txtGastosAdmin.setColumns(10);
		txtGastosAdmin.setBounds(211, 308, 108, 23);
		mainPanel.add(txtGastosAdmin);
		
		JLabel lblGastosCobranza = new JLabel("Gastos Cobranza:");
		lblGastosCobranza.setFont(new Font("72", Font.PLAIN, 17));
		lblGastosCobranza.setBounds(25, 341, 137, 23);
		mainPanel.add(lblGastosCobranza);
		
		txtGastosCobranza = new PlaceholderTextField("00000000.00");
		txtGastosCobranza.setFocusable(false);
		txtGastosCobranza.setForeground(new Color(0, 0, 0));
		txtGastosCobranza.setFont(new Font("Arial", Font.PLAIN, 16));
		txtGastosCobranza.setColumns(10);
		txtGastosCobranza.setBounds(211, 341, 108, 23);
		mainPanel.add(txtGastosCobranza);
		
		JLabel lblGarantia = new JLabel("Garantía:");
		lblGarantia.setFont(new Font("72", Font.PLAIN, 17));
		lblGarantia.setBounds(25, 369, 137, 23);
		mainPanel.add(lblGarantia);
		
		txtGarantia = new PlaceholderTextField("00000000.00");
		txtGarantia.setFocusable(false);
		txtGarantia.setForeground(new Color(0, 0, 0));
		txtGarantia.setPlaceholder("...");
		txtGarantia.setFont(new Font("Arial", Font.PLAIN, 16));
		txtGarantia.setColumns(10);
		txtGarantia.setBounds(180, 369, 337, 23);
		mainPanel.add(txtGarantia);
		
		txtDestino = new PlaceholderTextField("00000000.00");
		txtDestino.setFocusable(false);
		txtDestino.setForeground(new Color(0, 0, 0));
		txtDestino.setPlaceholder("...");
		txtDestino.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDestino.setColumns(10);
		txtDestino.setBounds(180, 396, 337, 23);
		mainPanel.add(txtDestino);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setFont(new Font("72", Font.PLAIN, 17));
		lblDestino.setBounds(25, 396, 137, 23);
		mainPanel.add(lblDestino);
		
		txtCantCuotas = new PlaceholderTextField("00000000.00");
		txtCantCuotas.setFocusable(false);
		txtCantCuotas.setForeground(new Color(0, 0, 0));
		txtCantCuotas.setPlaceholder("0");
		txtCantCuotas.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCantCuotas.setColumns(10);
		txtCantCuotas.setBounds(211, 425, 39, 23);
		mainPanel.add(txtCantCuotas);
		
		JLabel lblCantCuotas = new JLabel("Cantidad de Cuotas:");
		lblCantCuotas.setFont(new Font("72", Font.PLAIN, 17));
		lblCantCuotas.setBounds(25, 425, 164, 23);
		mainPanel.add(lblCantCuotas);
		
		txtInteresCuota = new PlaceholderTextField("00000000.00");
		txtInteresCuota.setFocusable(false);
		txtInteresCuota.setForeground(new Color(0, 0, 0));
		txtInteresCuota.setFont(new Font("Arial", Font.PLAIN, 16));
		txtInteresCuota.setColumns(10);
		txtInteresCuota.setBounds(211, 456, 108, 23);
		mainPanel.add(txtInteresCuota);
		
		JLabel lblInteresCuota = new JLabel("Interés Cuota:");
		lblInteresCuota.setFont(new Font("72", Font.PLAIN, 17));
		lblInteresCuota.setBounds(25, 456, 137, 23);
		mainPanel.add(lblInteresCuota);
		
		txtMontoCuota = new PlaceholderTextField("00000000.00");
		txtMontoCuota.setFocusable(false);
		txtMontoCuota.setForeground(new Color(0, 0, 0));
		txtMontoCuota.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMontoCuota.setColumns(10);
		txtMontoCuota.setBounds(211, 484, 108, 23);
		mainPanel.add(txtMontoCuota);
		
		JLabel lblMontoCuota = new JLabel("Monto Cuota:");
		lblMontoCuota.setFont(new Font("72", Font.PLAIN, 17));
		lblMontoCuota.setBounds(25, 484, 137, 23);
		mainPanel.add(lblMontoCuota);
		
		txtMontoEfectivo = new PlaceholderTextField("00000000.00");
		txtMontoEfectivo.setFocusable(false);
		txtMontoEfectivo.setForeground(new Color(0, 0, 0));
		txtMontoEfectivo.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMontoEfectivo.setColumns(10);
		txtMontoEfectivo.setBounds(211, 515, 108, 23);
		mainPanel.add(txtMontoEfectivo);
		
		JLabel lblEfectivo = new JLabel("Efectivo:");
		lblEfectivo.setFont(new Font("72", Font.PLAIN, 17));
		lblEfectivo.setBounds(25, 515, 137, 23);
		mainPanel.add(lblEfectivo);
		
		txtTransferencia = new PlaceholderTextField("00000000.00");
		txtTransferencia.setFocusable(false);
		txtTransferencia.setForeground(new Color(0, 0, 0));
		txtTransferencia.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTransferencia.setColumns(10);
		txtTransferencia.setBounds(211, 546, 108, 23);
		mainPanel.add(txtTransferencia);
		
		JLabel lblTransferencia = new JLabel("Transferencia:");
		lblTransferencia.setFont(new Font("72", Font.PLAIN, 17));
		lblTransferencia.setBounds(25, 546, 137, 23);
		mainPanel.add(lblTransferencia);
		
		txtCheques = new PlaceholderTextField("00000000.00");
		txtCheques.setFocusable(false);
		txtCheques.setForeground(new Color(0, 0, 0));
		txtCheques.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCheques.setColumns(10);
		txtCheques.setBounds(211, 574, 108, 23);
		mainPanel.add(txtCheques);
		
		JLabel lblCheques = new JLabel("Cheques:");
		lblCheques.setFont(new Font("72", Font.PLAIN, 17));
		lblCheques.setBounds(25, 574, 137, 23);
		mainPanel.add(lblCheques);
		
		   JScrollPane scrollPane = new JScrollPane();
		   scrollPane.setFocusable(false);
	        scrollPane.setBounds(561, 61, 494, 496);
	        mainPanel.add(scrollPane);

	        // Table Initialization
	        table = new JTable(new DefaultTableModel(
	                new CuotaAyudaPesos[][]{
	                },
	                new String[]{ "Cuota", "Monto", "Vencimiento"}
	        ) {
	        	@Override
	        	public boolean isCellEditable(int row, int column) {
	        		return false;
	        	}
	        });
	        table.setFont(new Font("Arial", Font.PLAIN, 16));
	        table.setRowHeight(25);

	        table.setRowSelectionAllowed(false);
	        table.setColumnSelectionAllowed(false);
	        table.setCellSelectionEnabled(false);
	      

	        table.setFocusable(false);
	        table.setCellEditor(null);
	        scrollPane.setViewportView(table);
	        
	        JLabel lblTotalDeCuotas = new JLabel("Total de cuotas");
	        lblTotalDeCuotas.setFont(new Font("72", Font.BOLD, 17));
	        lblTotalDeCuotas.setBounds(717, 29, 182, 23);
	        mainPanel.add(lblTotalDeCuotas);
	
	        
	        try {
	            MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
	            dateFormatter.setPlaceholderCharacter('_');
	          
	            // Actualizar otros campos (fecha, montos, etc.)
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                String fechaEmisionStr = sdf.format(new Date());
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        
	    
	       
	        btnPasarALegales = new JButton("Anular Ayuda en Legales");
	        btnPasarALegales.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		mostrarDialogoConfirmacion();
	        	}
	        });
	        
	        btnPasarALegales.addKeyListener(new KeyAdapter() {
	        	@Override 
	        	public void keyPressed(KeyEvent e) {
	        		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
	        			mostrarDialogoConfirmacion();		    			
	        		}
	        	}
	        });
	        btnPasarALegales.setFont(new Font("72", Font.PLAIN, 17));
	        btnPasarALegales.setFocusable(true);
	        btnPasarALegales.setBounds(670, 583, 229, 23);
	        mainPanel.add(btnPasarALegales);
	        
	        lblFechaAyudaLegales = new JLabel("Ayuda Legales");
	        lblFechaAyudaLegales.setFont(new Font("72", Font.PLAIN, 17));
	        lblFechaAyudaLegales.setBounds(173, 29, 137, 23);
	        mainPanel.add(lblFechaAyudaLegales);
	        
	        lblAyudaLegales = new JLabel("Ayuda Legales");
	        lblAyudaLegales.setFont(new Font("72", Font.PLAIN, 17));
	        lblAyudaLegales.setBounds(25, 29, 137, 23);
	        mainPanel.add(lblAyudaLegales);
	        
    }
	private void manejarAnularAyuda() {
	    try {
	        this.ayudaPesos.setFechaLegales(null);
	        this.ayudaPesos.setFechaOrigLegales(null);
	        ayudaServicio.actualizarAyuda(ayudaPesos);
	        JOptionPane.showMessageDialog(this, "Ayuda a Legales anulada.");
	    	cardLayout.show(cardPanel, panelAnterior);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Error al anular ayuda", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	}
	
	 private void mostrarDialogoConfirmacion() {
	    // Crear el JDialog
	    JDialog dialog = new JDialog(mainFrame, "Confirmar Acción", true);
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.setSize(400, 150);
	    dialog.getContentPane().setLayout(new BorderLayout());
	    dialog.setLocationRelativeTo(this); // Centrar el diálogo respecto a la ventana principal

	    // Crear el mensaje
	    JLabel mensaje = new JLabel("¿Estás seguro de que deseas anular esta Ayuda en Legales?");
	    mensaje.setHorizontalAlignment(SwingConstants.CENTER);
	    mensaje.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
	    dialog.getContentPane().add(mensaje, BorderLayout.CENTER);

	    // Crear el panel de botones
	    JPanel panelBotones = new JPanel();
	    panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

	    JButton btnSi = new JButton("Sí");
	    JButton btnNo = new JButton("No");

	    panelBotones.add(btnSi);
	    panelBotones.add(btnNo);

	    dialog.getContentPane().add(panelBotones, BorderLayout.SOUTH);

	    btnSi.addKeyListener(new KeyAdapter() {
	    	@Override 
	    	public void keyPressed(KeyEvent e) {
	    		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
	    			manejarAnularAyuda(); // Método para anular la ayuda
	    			dialog.dispose();	    			
	    		}
	    	}
	    });
	    
	    btnNo.addKeyListener(new KeyAdapter() {
	    	@Override 
	    	public void keyPressed(KeyEvent e) {
	    		    dialog.dispose();
	    	}
	    });
	    
	    btnSi.addActionListener(e -> {
			manejarAnularAyuda(); // Método para anular la ayuda
			dialog.dispose();
			});

	    btnNo.addActionListener(e -> {
	    	// Método para anular la ayuda
	    	dialog.dispose();
	    });


	    // Añadir FocusListeners para manejar el foco y permitir navegación con Tab
	    btnSi.addFocusListener(new java.awt.event.FocusAdapter() {
	        public void focusGained(java.awt.event.FocusEvent evt) {
	            // Opcional: cambiar apariencia al ganar foco
	        }
	    });

	    btnNo.addFocusListener(new java.awt.event.FocusAdapter() {
	        public void focusGained(java.awt.event.FocusEvent evt) {
	            // Opcional: cambiar apariencia al ganar foco
	        }
	    });

	    // Mostrar el diálogo
	    dialog.setVisible(true);
	}

	
	public void cargarDatosAyudaPesos() {
	    if (this.ayudaPesos != null) {
	        new SwingWorker<Void, Void>() {
	            // Variables que se usarán para actualizar la UI
	            Socio socio;
	            Socio socioGarante;
	            Socio socioGarante1;
	            Socio socioGarante2;
	            Garantia garantia;
	            BigDecimal cheques;
	            BigDecimal montoCuota;
	            List<CuotaAyudaPesos> cuotasOptimized;
	            
	            @Override
	            protected Void doInBackground() throws Exception {
	                // 1. Obtener datos de Socios (podrías optimizar consultando en lote o cachear si es posible)
	                Integer numeroSocio = ayudaPesos.getNumeroSocio();
	                socio = (numeroSocio != null) ? socioServicio.buscarSocioPorNumeroSocio(numeroSocio) : null;
	                
	                Integer nroSocioGarante = ayudaPesos.getNroSocioGarante();
	                socioGarante = (nroSocioGarante != null) ? socioServicio.buscarSocioPorNumeroSocio(nroSocioGarante) : null;
	                
	                Integer nroSocioGarante2 = ayudaPesos.getNroSocioGarante2();
	                socioGarante1 = (nroSocioGarante2 != null) ? socioServicio.buscarSocioPorNumeroSocio(nroSocioGarante2) : null;
	                
	                Integer nroSocioGarante3 = ayudaPesos.getNroSocioGarante3();
	                socioGarante2 = (nroSocioGarante3 != null) ? socioServicio.buscarSocioPorNumeroSocio(nroSocioGarante3) : null;
	                
		            Integer garantiaId = ayudaPesos.getGarantia();
		            garantia = (garantiaId != null) ? garantiaServicio.buscarGarantiaPorId(garantiaId) : null;

		            BigDecimal montoMov1 = ayudaPesos.getMontoCheque1();
		            BigDecimal montoMov2 = ayudaPesos.getMontoCheque1();
		            BigDecimal montoMov3 = ayudaPesos.getMontoCheque1();
		            
		            cheques =  montoMov1.add(montoMov3).add(montoMov2);
	            
	                cuotasOptimized = cuotasServicio.buscarCuotasPendientesPorNumeroAyuda(ayudaPesos.getNroAyuda());
	                if(cuotasOptimized == null || cuotasOptimized.size() == 0) {
	                	montoCuota =  cobroCuotaAyudaPesosServicio.buscarCobroCuotaAyudaPesosPorNumAyuda(ayudaPesos.getNroAyuda()).get(0).getMontoCuota();	                	
	                }else {
	                	montoCuota = cuotasOptimized.get(0).getMontoCuota();
	                }
	                return null;
	            }
	            
	            @Override
	            protected void done() {
	              	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	            	symbols.setGroupingSeparator('.');
	            	symbols.setDecimalSeparator(',');
	            	DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
	            
	            	txtNumeroSocio.setText((ayudaPesos.getNumeroSocio() != null) ? ayudaPesos.getNumeroSocio().toString() : "N/A");
	                txtNombreSocio.setText((socio != null) ? socio.getApellidoNombre() : "N/A");
	               
	                txtNumSocioGarante.setText((ayudaPesos.getNroSocioGarante() != null) ? ayudaPesos.getNroSocioGarante().toString() : "N/A");
	                txtNombreSocioGarante.setText((socioGarante != null) ? socioGarante.getApellidoNombre() : "N/A");
	                
	                txtNumSocioGarante1.setText((ayudaPesos.getNroSocioGarante2() != null) ? ayudaPesos.getNroSocioGarante2().toString() : "N/A");
	                txtNombreSocioGarante1.setText((socioGarante1 != null) ? socioGarante1.getApellidoNombre() : "N/A");
	                
	                txtNumSocioGarante2.setText((ayudaPesos.getNroSocioGarante3() != null) ? ayudaPesos.getNroSocioGarante3().toString() : "N/A");
	                txtNombreSocioGarante2.setText((socioGarante2 != null) ? socioGarante2.getApellidoNombre() : "N/A");
	                
	                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
	                String fechaEmisionStr = (ayudaPesos.getFechaAltaAyuda() != null) ? sdf.format(ayudaPesos.getFechaAltaAyuda()) : "N/A";
	                txtFechaEmision.setText(fechaEmisionStr);
	                
	                txtMontoSolicitado.setText((ayudaPesos.getMontoSolicitado() != null) ? df.format(ayudaPesos.getMontoSolicitado()) : "N/A");
	                txtTasaInteres.setText((ayudaPesos.getInteres() != null) ? df.format(ayudaPesos.getInteres()) : "N/A");
	                txtGastosAdmin.setText((ayudaPesos.getMontoGastoAdministrativo() != null) ? df.format(ayudaPesos.getMontoGastoAdministrativo()) : "N/A");
	                txtGastosCobranza.setText((ayudaPesos.getMontoSellado() != null) ? df.format(ayudaPesos.getMontoSellado()) : "N/A");
	            
	                Integer nroAyuda = ayudaPesos.getNroAyuda();
		     
	                String nroAyudaStr = (nroAyuda != null) ? nroAyuda.toString() : "N/A";
		            txtNumAyuda.setText(nroAyudaStr);
		  
		            String montoSolicitadoStr = (ayudaPesos.getMontoSolicitado() != null) ? df.format(ayudaPesos.getMontoSolicitado()) : "N/A";
		            txtMontoSolicitado.setText(montoSolicitadoStr);

		            String tasaInteresStr = (ayudaPesos.getInteres() != null) ? df.format(ayudaPesos.getInteres()) : "N/A";
		            txtTasaInteres.setText(tasaInteresStr);

		            String gastosAdminStr = (ayudaPesos.getMontoGastoAdministrativo() != null) ? df.format(ayudaPesos.getMontoGastoAdministrativo()) : "N/A";
		            txtGastosAdmin.setText(gastosAdminStr);

		            String gastosCobranza = (ayudaPesos.getMontoSellado() != null) ? df.format(ayudaPesos.getMontoSellado()) : "N/A";
		            txtGastosCobranza.setText(gastosCobranza);

		            String garantiaStr = (garantia != null) ? garantia.getGarantiaNom() : "N/A";
		            txtGarantia.setText(garantiaStr);

		            String destinoStr = (ayudaPesos.getDestinoAyuda() != null) ? ayudaPesos.getDestinoAyuda() : "N/A";
		            txtDestino.setText(destinoStr);

		            String cantCuotasStr = (ayudaPesos.getCantidadCuotas() != null) ? ayudaPesos.getCantidadCuotas().toString() : "N/A";
		            txtCantCuotas.setText(cantCuotasStr);

		            String interesCuotaStr = (ayudaPesos.getInteresCuota() != null) ? df.format(ayudaPesos.getInteresCuota()) : "N/A";
		            txtInteresCuota.setText(interesCuotaStr);

		            txtMontoCuota.setText(montoCuota == null ? "0.00" : df.format(montoCuota));
		            
		            String montoEfectivoStr = (ayudaPesos.getMontoEfectivo() != null) ? df.format(ayudaPesos.getMontoEfectivo()) : "N/A";
		            txtMontoEfectivo.setText(montoEfectivoStr);

		            lblFechaAyudaLegales.setText(ayudaPesos.getFechaLegales() != null ? sdf.format(ayudaPesos.getFechaLegales()) : "  /  /    ");
				    lblFechaAyudaLegales.setForeground(Color.RED);
		            
		            String transferenciaStr = (ayudaPesos.getMontoTransferencia() != null) ? df.format(ayudaPesos.getMontoTransferencia()) : "N/A";
		            txtTransferencia.setText(transferenciaStr);

		            txtCheques.setText(cheques == null ? "0.00" : df.format(cheques));
	               
		            llenarTablaCuotas(cuotasOptimized);
	            }
	        }.execute();
	    }
	}

	public AyudaPesos getAyudaPesos() {
		return ayudaPesos;
	}
	
	public void setAyudaPesos(AyudaPesos ayudaPesos) {
	    if (ayudaPesos != null) {
	        this.ayudaPesos = ayudaPesos;
	        cargarDatosAyudaPesos(); // Cargar datos solo cuando se establece AyudaPesos

	    } else {
	        this.ayudaPesos = new AyudaPesos();
	    } 
	    
	    cargarDatosAyudaPesos(); // Actualiza la interfaz con los nuevos datos
	}
	
	private void llenarTablaCuotas(List<CuotaAyudaPesos> cuotas) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    // Limpiar filas existentes
	    model.setRowCount(0);
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
	    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    	symbols.setGroupingSeparator('.');
    	symbols.setDecimalSeparator(',');
    	DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
    	
	    if (cuotas != null && !cuotas.isEmpty()) {
	        for (CuotaAyudaPesos cuota : cuotas) {
	        	String fechaEmisionStr =  sdf.format(  cuota.getFechaVtoCuota());
	        	  
	        	Object[] rowData = new Object[]{
	                cuota.getNumeroCuota(),
	                df.format(cuota.getMontoCuota()),
	                fechaEmisionStr
	            };
	            model.addRow(rowData);
	        }
	    } else {
	   
	    }
	}	
}
