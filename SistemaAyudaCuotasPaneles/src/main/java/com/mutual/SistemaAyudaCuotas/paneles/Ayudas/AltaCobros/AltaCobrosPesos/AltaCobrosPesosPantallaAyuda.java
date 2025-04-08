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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

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
public class AltaCobrosPesosPantallaAyuda extends JPanel {
	
	//SERVICIOS 
	private ICuotaAyudaPesosServicio cuotasServicio;
	private IGarantiaServicio garantiaServicio;
	private ISocioServicio socioServicio;
	private IAyudaPesosServicio ayudaServicio;
	private ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio;

	//PROPIEDADES
	private AyudaPesos ayudaPesos;
	private String panelAnterior;
	
	//COMPONENTES GUI
	private JTable table;
	private JPanel contentPane;
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
	private PlaceholderTextField txtNroMov;
	private PlaceholderTextField txtNroMov2;
	private PlaceholderTextField txtNroMov3;
	private PlaceholderTextField txtDetalleMov;
	private PlaceholderTextField txtDetalleMov2;
	private PlaceholderTextField txtDetalleMov3;
	private PlaceholderTextField txtImporteMov;
	private PlaceholderTextField txtImporteMov2;
	private PlaceholderTextField txtImporteMov3;
	private JLabel lblAyudalegales;
	private JLabel lblAyudaPendiente;
	private JLabel lblFechaAyudaLegales;
	private JLabel lblNovacion;
	
	private JFrame mainFrame;
	private CardLayout cardLayout; 
	private JPanel cardPanel;
	private List<CuotaAyudaPesos> listaCuotasAdeudadas;
	protected boolean cartelMostrado;
	private AltaCobrosPesosCobrarVariasOUna altaCobrosPesosCobrarVariasOUna;
	
	public AltaCobrosPesosPantallaAyuda(ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio,
			ICuotaAyudaPesosServicio cuotasServicio, 
			ISocioServicio socioServicio, 
			IGarantiaServicio garantiaServicio,
			IAyudaPesosServicio ayudaServicio) {
		this.ayudaPesos = new AyudaPesos();
		this.listaCuotasAdeudadas = new ArrayList<CuotaAyudaPesos>();
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
            	System.out.println(panelAnterior);
              	cardLayout.show(cardPanel, panelAnterior);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "accionEnter");
	    this.getActionMap().put("accionEnter", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            abrirOpcionesUnaVarias();
	        }
	    });
        addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentShown(ComponentEvent e) {
    	        e.getComponent().requestFocusInWindow();
    	        cargarDatosAyudaPesos();
    	        
    	        if(!cartelMostrado) {
          		  List<CuotaAyudaPesos> cuotasOptimized = cuotasServicio.buscarCuotasPendientesPorNumeroAyuda(ayudaPesos.getNroAyuda());
	                  if (cuotasOptimized != null && !cuotasOptimized.isEmpty()) {
	                      Date fechaPrimeraCuota = cuotasOptimized.get(0).getFechaVtoCuota();
	                      
	                     Calendar calPrimera = Calendar.getInstance();
	                      calPrimera.setTime(fechaPrimeraCuota);
	                      int anioPrimera = calPrimera.get(Calendar.YEAR);
	                      
	                      // Obtener el año actual
	                      Calendar calActual = Calendar.getInstance();
	                      int anioActual = calActual.get(Calendar.YEAR);
	                      
	                      if (anioPrimera < anioActual) {
	                    	  mostrarMensaje("AÑO VENCIDO.");
	                      } else {
//	                          System.out.println("El año de la primera cuota es igual o mayor al año actual.");
	                      }
	                  }
	                  cartelMostrado = true;
    	    }
    	    }});
        Dimension containerSize = new Dimension(1200, 700);
        
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setPreferredSize(containerSize);
        mainPanel.setMaximumSize(containerSize);
       	mainPanel.getLayout();
    	add(mainPanel);
		
		JLabel lblSocio = new JLabel("Socio:");
		lblSocio.setFont(new Font("72", Font.PLAIN, 17));
		lblSocio.setBounds(23, 14, 65, 23);
		mainPanel.add(lblSocio);
		
		txtNumeroSocio = new PlaceholderTextField("00000000.00");
		txtNumeroSocio.setFocusable(false);
		txtNumeroSocio.setForeground(new Color(0, 0, 0));
		txtNumeroSocio.setPlaceholder("0000");
		txtNumeroSocio.setFont(new Font("72", Font.PLAIN, 16));
		txtNumeroSocio.setColumns(10);
		txtNumeroSocio.setBounds(97, 11, 76, 23);
		mainPanel.add(txtNumeroSocio);
		
		JLabel lblSocioGarante = new JLabel("Socio Garante:");
		lblSocioGarante.setFont(new Font("72", Font.PLAIN, 17));
		lblSocioGarante.setBounds(23, 44, 137, 23);
		mainPanel.add(lblSocioGarante);
		
		txtNumSocioGarante = new PlaceholderTextField("00000000.00");
		txtNumSocioGarante.setFocusable(false);
		txtNumSocioGarante.setForeground(new Color(0, 0, 0));
		txtNumSocioGarante.setPlaceholder("0000");
		txtNumSocioGarante.setFont(new Font("72", Font.PLAIN, 16));
		txtNumSocioGarante.setColumns(10);
		txtNumSocioGarante.setBounds(183, 40, 56, 23);
		mainPanel.add(txtNumSocioGarante);
		
		JLabel lblNumeroAyuda = new JLabel("Numero Ayuda:");
		lblNumeroAyuda.setFont(new Font("72", Font.PLAIN, 17));
		lblNumeroAyuda.setBounds(23, 135, 137, 23);
		mainPanel.add(lblNumeroAyuda);
		
		txtNumAyuda = new PlaceholderTextField("00000000.00");
		txtNumAyuda.setFocusable(false);
		txtNumAyuda.setForeground(new Color(0, 0, 0));
		txtNumAyuda.setPlaceholder("00000");
		txtNumAyuda.setFont(new Font("72", Font.PLAIN, 16));
		txtNumAyuda.setColumns(10);
		txtNumAyuda.setBounds(214, 135, 65, 23);
		mainPanel.add(txtNumAyuda);
		
		txtNombreSocio = new PlaceholderTextField("00000000.00");
		txtNombreSocio.setFocusable(false);
		txtNombreSocio.setForeground(new Color(0, 0, 0));
		txtNombreSocio.setPlaceholder("...");
		txtNombreSocio.setFont(new Font("72", Font.PLAIN, 16));
		txtNombreSocio.setColumns(10);
		txtNombreSocio.setBounds(183, 11, 382, 23);
		mainPanel.add(txtNombreSocio);
		
		lblAyudaPendiente = new JLabel("Ayuda Pendiente");
		lblAyudaPendiente.setFont(new Font("72", Font.BOLD, 18));
		lblAyudaPendiente.setBounds(657, 63, 160, 23);
		mainPanel.add(lblAyudaPendiente);
		
		JLabel lblSocioGarante1 = new JLabel("Segundo Garante:");
		lblSocioGarante1.setFont(new Font("72", Font.PLAIN, 17));
		lblSocioGarante1.setBounds(23, 72, 137, 14);
		mainPanel.add(lblSocioGarante1);
		
		txtNumSocioGarante1 = new PlaceholderTextField("00000000.00");
		txtNumSocioGarante1.setFocusable(false);
		txtNumSocioGarante1.setForeground(new Color(0, 0, 0));
		txtNumSocioGarante1.setPlaceholder("0000");
		txtNumSocioGarante1.setFont(new Font("72", Font.PLAIN, 16));
		txtNumSocioGarante1.setColumns(10);
		txtNumSocioGarante1.setBounds(183, 68, 56, 23);
		mainPanel.add(txtNumSocioGarante1);
		
		JLabel lblSocioGarante2 = new JLabel("Tercer Garante:");
		lblSocioGarante2.setFont(new Font("72", Font.PLAIN, 17));
		lblSocioGarante2.setBounds(23, 101, 137, 23);
		mainPanel.add(lblSocioGarante2);
		
		txtNumSocioGarante2 = new PlaceholderTextField("00000000.00");
		txtNumSocioGarante2.setFocusable(false);
		txtNumSocioGarante2.setForeground(new Color(0, 0, 0));
		txtNumSocioGarante2.setPlaceholder("0000");
		txtNumSocioGarante2.setFont(new Font("72", Font.PLAIN, 16));
		txtNumSocioGarante2.setColumns(10);
		txtNumSocioGarante2.setBounds(183, 97, 56, 23);
		mainPanel.add(txtNumSocioGarante2);
		
		txtNombreSocioGarante = new PlaceholderTextField("00000000.00");
		txtNombreSocioGarante.setFocusable(false);
		txtNombreSocioGarante.setForeground(new Color(0, 0, 0));
		txtNombreSocioGarante.setPlaceholder("...");
		txtNombreSocioGarante.setFont(new Font("72", Font.PLAIN, 16));
		txtNombreSocioGarante.setColumns(10);
		txtNombreSocioGarante.setBounds(249, 40, 316, 23);
		mainPanel.add(txtNombreSocioGarante);
		
		txtNombreSocioGarante1 = new PlaceholderTextField("00000000.00");
		txtNombreSocioGarante1.setFocusable(false);
		txtNombreSocioGarante1.setForeground(new Color(0, 0, 0));
		txtNombreSocioGarante1.setPlaceholder("...");
		txtNombreSocioGarante1.setFont(new Font("72", Font.PLAIN, 16));
		txtNombreSocioGarante1.setColumns(10);
		txtNombreSocioGarante1.setBounds(249, 68, 316, 23);
		mainPanel.add(txtNombreSocioGarante1);
		
		txtNombreSocioGarante2 = new PlaceholderTextField("00000000.00");
		txtNombreSocioGarante2.setFocusable(false);
		txtNombreSocioGarante2.setForeground(new Color(0, 0, 0));
		txtNombreSocioGarante2.setPlaceholder("...");
		txtNombreSocioGarante2.setFont(new Font("72", Font.PLAIN, 16));
		txtNombreSocioGarante2.setColumns(10);
		txtNombreSocioGarante2.setBounds(249, 98, 316, 23);
		mainPanel.add(txtNombreSocioGarante2);
		
		JLabel lblFechaEmision = new JLabel("Fecha Emisión:");
		lblFechaEmision.setFont(new Font("72", Font.PLAIN, 17));
		lblFechaEmision.setBounds(23, 163, 137, 23);
		mainPanel.add(lblFechaEmision);
		
		txtFechaEmision = new PlaceholderTextField("00000000.00");
		txtFechaEmision.setFocusable(false);
		txtFechaEmision.setForeground(new Color(0, 0, 0));
		txtFechaEmision.setPlaceholder("00/00/0000");
		txtFechaEmision.setFont(new Font("72", Font.PLAIN, 16));
		txtFechaEmision.setColumns(10);
		txtFechaEmision.setBounds(214, 163, 94, 23);
		mainPanel.add(txtFechaEmision);
		
		JLabel lblMontoSolicitado = new JLabel("Monto Solicitado:");
		lblMontoSolicitado.setFont(new Font("72", Font.PLAIN, 17));
		lblMontoSolicitado.setBounds(23, 191, 137, 23);
		mainPanel.add(lblMontoSolicitado);
		
		txtMontoSolicitado = new PlaceholderTextField("00000000.00");
		txtMontoSolicitado.setFocusable(false);
		txtMontoSolicitado.setForeground(new Color(0, 0, 0));
		txtMontoSolicitado.setFont(new Font("72", Font.PLAIN, 16));
		txtMontoSolicitado.setColumns(10);
		txtMontoSolicitado.setBounds(214, 191, 108, 23);
		mainPanel.add(txtMontoSolicitado);
		
		JLabel lblTasaInteres = new JLabel("Tasa de Interés:");
		lblTasaInteres.setFont(new Font("72", Font.PLAIN, 17));
		lblTasaInteres.setBounds(23, 219, 137, 23);
		mainPanel.add(lblTasaInteres);
		
		txtTasaInteres = new PlaceholderTextField("00000000.00");
		txtTasaInteres.setFocusable(false);
		txtTasaInteres.setForeground(new Color(0, 0, 0));
		txtTasaInteres.setPlaceholder("00.00");
		txtTasaInteres.setFont(new Font("72", Font.PLAIN, 16));
		txtTasaInteres.setColumns(10);
		txtTasaInteres.setBounds(214, 219, 94, 23);
		mainPanel.add(txtTasaInteres);
		
		JLabel lblGastosAdmin = new JLabel("Gtos. Administrativos:");
		lblGastosAdmin.setFont(new Font("72", Font.PLAIN, 17));
		lblGastosAdmin.setBounds(23, 247, 164, 23);
		mainPanel.add(lblGastosAdmin);
		
		txtGastosAdmin = new PlaceholderTextField("00000000.00");
		txtGastosAdmin.setFocusable(false);
		txtGastosAdmin.setForeground(new Color(0, 0, 0));
		txtGastosAdmin.setFont(new Font("72", Font.PLAIN, 16));
		txtGastosAdmin.setColumns(10);
		txtGastosAdmin.setBounds(214, 247, 108, 23);
		mainPanel.add(txtGastosAdmin);
		
		JLabel lblGastosCobranza = new JLabel("Gastos Cobranza:");
		lblGastosCobranza.setFont(new Font("72", Font.PLAIN, 17));
		lblGastosCobranza.setBounds(23, 280, 137, 23);
		mainPanel.add(lblGastosCobranza);
		
		txtGastosCobranza = new PlaceholderTextField("00000000.00");
		txtGastosCobranza.setFocusable(false);
		txtGastosCobranza.setForeground(new Color(0, 0, 0));
		txtGastosCobranza.setFont(new Font("72", Font.PLAIN, 16));
		txtGastosCobranza.setColumns(10);
		txtGastosCobranza.setBounds(214, 280, 108, 23);
		mainPanel.add(txtGastosCobranza);
		
		JLabel lblGarantia = new JLabel("Garantía:");
		lblGarantia.setFont(new Font("72", Font.PLAIN, 17));
		lblGarantia.setBounds(23, 308, 137, 23);
		mainPanel.add(lblGarantia);
		
		txtGarantia = new PlaceholderTextField("00000000.00");
		txtGarantia.setFocusable(false);
		txtGarantia.setForeground(new Color(0, 0, 0));
		txtGarantia.setPlaceholder("...");
		txtGarantia.setFont(new Font("72", Font.PLAIN, 16));
		txtGarantia.setColumns(10);
		txtGarantia.setBounds(170, 308, 288, 23);
		mainPanel.add(txtGarantia);
		
		txtDestino = new PlaceholderTextField("00000000.00");
		txtDestino.setFocusable(false);
		txtDestino.setForeground(new Color(0, 0, 0));
		txtDestino.setPlaceholder("...");
		txtDestino.setFont(new Font("72", Font.PLAIN, 16));
		txtDestino.setColumns(10);
		txtDestino.setBounds(170, 335, 288, 23);
		mainPanel.add(txtDestino);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setFont(new Font("72", Font.PLAIN, 17));
		lblDestino.setBounds(23, 335, 137, 23);
		mainPanel.add(lblDestino);
		
		txtCantCuotas = new PlaceholderTextField("00000000.00");
		txtCantCuotas.setFocusable(false);
		txtCantCuotas.setForeground(new Color(0, 0, 0));
		txtCantCuotas.setPlaceholder("0");
		txtCantCuotas.setFont(new Font("72", Font.PLAIN, 16));
		txtCantCuotas.setColumns(10);
		txtCantCuotas.setBounds(214, 364, 39, 23);
		mainPanel.add(txtCantCuotas);
		
		JLabel lblCantCuotas = new JLabel("Cantidad de Cuotas:");
		lblCantCuotas.setFont(new Font("72", Font.PLAIN, 17));
		lblCantCuotas.setBounds(23, 364, 181, 23);
		mainPanel.add(lblCantCuotas);
		
		txtInteresCuota = new PlaceholderTextField("00000000.00");
		txtInteresCuota.setFocusable(false);
		txtInteresCuota.setForeground(new Color(0, 0, 0));
		txtInteresCuota.setFont(new Font("72", Font.PLAIN, 16));
		txtInteresCuota.setColumns(10);
		txtInteresCuota.setBounds(214, 395, 108, 23);
		mainPanel.add(txtInteresCuota);
		
		JLabel lblInteresCuota = new JLabel("Interés Cuota:");
		lblInteresCuota.setFont(new Font("72", Font.PLAIN, 17));
		lblInteresCuota.setBounds(23, 395, 137, 23);
		mainPanel.add(lblInteresCuota);
		
		txtMontoCuota = new PlaceholderTextField("00000000.00");
		txtMontoCuota.setFocusable(false);
		txtMontoCuota.setForeground(new Color(0, 0, 0));
		txtMontoCuota.setFont(new Font("72", Font.PLAIN, 16));
		txtMontoCuota.setColumns(10);
		txtMontoCuota.setBounds(214, 423, 108, 23);
		mainPanel.add(txtMontoCuota);
		
		JLabel lblMontoCuota = new JLabel("Monto Cuota:");
		lblMontoCuota.setFont(new Font("72", Font.PLAIN, 17));
		lblMontoCuota.setBounds(23, 423, 137, 23);
		mainPanel.add(lblMontoCuota);
		
		txtMontoEfectivo = new PlaceholderTextField("00000000.00");
		txtMontoEfectivo.setFocusable(false);
		txtMontoEfectivo.setForeground(new Color(0, 0, 0));
		txtMontoEfectivo.setFont(new Font("72", Font.PLAIN, 16));
		txtMontoEfectivo.setColumns(10);
		txtMontoEfectivo.setBounds(214, 475, 108, 23);
		mainPanel.add(txtMontoEfectivo);
		
		JLabel lblEfectivo = new JLabel("Efectivo:");
		lblEfectivo.setFont(new Font("72", Font.PLAIN, 17));
		lblEfectivo.setBounds(23, 475, 137, 23);
		mainPanel.add(lblEfectivo);
		
		txtTransferencia = new PlaceholderTextField("00000000.00");
		txtTransferencia.setFocusable(false);
		txtTransferencia.setForeground(new Color(0, 0, 0));
		txtTransferencia.setFont(new Font("72", Font.PLAIN, 16));
		txtTransferencia.setColumns(10);
		txtTransferencia.setBounds(214, 506, 108, 23);
		mainPanel.add(txtTransferencia);
		
		JLabel lblTransferencia = new JLabel("Transferencia:");
		lblTransferencia.setFont(new Font("72", Font.PLAIN, 17));
		lblTransferencia.setBounds(23, 506, 137, 23);
		mainPanel.add(lblTransferencia);
		
		txtCheques = new PlaceholderTextField("00000000.00");
		txtCheques.setFocusable(false);
		txtCheques.setForeground(new Color(0, 0, 0));
		txtCheques.setFont(new Font("72", Font.PLAIN, 16));
		txtCheques.setColumns(10);
		txtCheques.setBounds(214, 534, 108, 23);
		mainPanel.add(txtCheques);
		
		JLabel lblCheques = new JLabel("Cheques:");
		lblCheques.setFont(new Font("72", Font.PLAIN, 17));
		lblCheques.setBounds(23, 534, 137, 23);
		mainPanel.add(lblCheques);
		
		   JScrollPane scrollPane = new JScrollPane();
		   scrollPane.setFocusable(false);
	        scrollPane.setBounds(657, 97, 494, 496);
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
	        scrollPane.setViewportView(table);
	        
	        JLabel lblTotalDeCuotas = new JLabel("Total de cuotas");
	        lblTotalDeCuotas.setFont(new Font("72", Font.BOLD, 17));
	        lblTotalDeCuotas.setBounds(976, 72, 182, 23);
	        mainPanel.add(lblTotalDeCuotas);
	        
	        JLabel lblNumeroMov = new JLabel("Número");
	        lblNumeroMov.setFont(new Font("72", Font.PLAIN, 17));
	        lblNumeroMov.setBounds(23, 565, 144, 23);
	        mainPanel.add(lblNumeroMov);
	        
	        JLabel lblGastosAdmts_1_1_2 = new JLabel("Detalles");
	        lblGastosAdmts_1_1_2.setFont(new Font("72", Font.PLAIN, 17));
	        lblGastosAdmts_1_1_2.setBounds(203, 565, 144, 23);
	        mainPanel.add(lblGastosAdmts_1_1_2);
	        
	        JLabel lblGastosAdmts_1_1_3 = new JLabel("Importe");
	        lblGastosAdmts_1_1_3.setFont(new Font("72", Font.PLAIN, 17));
	        lblGastosAdmts_1_1_3.setBounds(423, 565, 144, 23);
	        mainPanel.add(lblGastosAdmts_1_1_3);
	        
	        txtImporteMov = new PlaceholderTextField("00000000.00");
	        txtImporteMov.setFocusable(false);
	        txtImporteMov.setFont(new Font("72", Font.PLAIN, 17));
	        txtImporteMov.setColumns(10);
	        txtImporteMov.setBounds(423, 590, 160, 23);
	        mainPanel.add(txtImporteMov);
	        
	        txtDetalleMov = new PlaceholderTextField("00000000.00");
	        txtDetalleMov.setFocusable(false);
	        txtDetalleMov.setPlaceholder("...");
	        txtDetalleMov.setFont(new Font("72", Font.PLAIN, 17));
	        txtDetalleMov.setColumns(10);
	        txtDetalleMov.setBounds(203, 589, 205, 23);
	        mainPanel.add(txtDetalleMov);
	        
	        txtNroMov = new PlaceholderTextField("00000000.00");
	        txtNroMov.setFocusable(false);
	        txtNroMov.setPlaceholder("0000");
	        txtNroMov.setFont(new Font("72", Font.PLAIN, 17));
	        txtNroMov.setColumns(10);
	        txtNroMov.setBounds(23, 591, 160, 23);
	        mainPanel.add(txtNroMov);
	        
	        txtNroMov2 = new PlaceholderTextField("00000000.00");
	        txtNroMov2.setFocusable(false);
	        txtNroMov2.setPlaceholder("0000");
	        txtNroMov2.setFont(new Font("72", Font.PLAIN, 17));
	        txtNroMov2.setColumns(10);
	        txtNroMov2.setBounds(23, 619, 160, 23);
	        mainPanel.add(txtNroMov2);
	        
	        txtDetalleMov2 = new PlaceholderTextField("00000000.00");
	        txtDetalleMov2.setFocusable(false);
	        txtDetalleMov2.setPlaceholder("...");
	        txtDetalleMov2.setFont(new Font("72", Font.PLAIN, 17));
	        txtDetalleMov2.setColumns(10);
	        txtDetalleMov2.setBounds(203, 618, 205, 23);
	        mainPanel.add(txtDetalleMov2);
	        
	        txtImporteMov2 = new PlaceholderTextField("00000000.00");
	        txtImporteMov2.setFocusable(false);
	        txtImporteMov2.setFont(new Font("72", Font.PLAIN, 17));
	        txtImporteMov2.setColumns(10);
	        txtImporteMov2.setBounds(423, 618, 160, 23);
	        mainPanel.add(txtImporteMov2);
	        
	        txtImporteMov3 = new PlaceholderTextField("00000000.00");
	        txtImporteMov3.setFocusable(false);
	        txtImporteMov3.setFont(new Font("72", Font.PLAIN, 17));
	        txtImporteMov3.setColumns(10);
	        txtImporteMov3.setBounds(423, 649, 160, 23);
	        mainPanel.add(txtImporteMov3);
	        
	        txtDetalleMov3 = new PlaceholderTextField("...");
	        txtDetalleMov3.setFocusable(false);
	        txtDetalleMov3.setFont(new Font("72", Font.PLAIN, 17));
	        txtDetalleMov3.setColumns(10);
	        txtDetalleMov3.setBounds(204, 649, 204, 23);
	        mainPanel.add(txtDetalleMov3);
	        
	        txtNroMov3 = new PlaceholderTextField("00000000.00");
	        txtNroMov3.setFocusable(false);
	        txtNroMov3.setPlaceholder("0000");
	        txtNroMov3.setFont(new Font("72", Font.PLAIN, 17));
	        txtNroMov3.setColumns(10);
	        txtNroMov3.setBounds(23, 649, 160, 23);
	        mainPanel.add(txtNroMov3);
	        
	        lblAyudalegales = new JLabel("AyudaLegales?");
	        lblAyudalegales.setFont(new Font("72", Font.BOLD, 19));
	        lblAyudalegales.setBounds(766, 23, 205, 26);
	        mainPanel.add(lblAyudalegales);
	        
	        lblFechaAyudaLegales = new JLabel("Ayuda Legales");
	        lblFechaAyudaLegales.setFont(new Font("72", Font.BOLD, 20));
	        lblFechaAyudaLegales.setBounds(985, 25, 150, 26);
	        mainPanel.add(lblFechaAyudaLegales);
	        
	        lblNovacion = new JLabel("novacion?");
	        lblNovacion.setFont(new Font("72", Font.PLAIN, 17));
	        lblNovacion.setBounds(23, 451, 137, 23);
	        mainPanel.add(lblNovacion);
    	 
    }
    public AyudaPesos getAyudaPesos() {
		return ayudaPesos;
	}
	
	public void setAyudaPesos(AyudaPesos ayudaPesos) {
	    if (ayudaPesos != null) {
	        this.ayudaPesos = ayudaPesos;
	        cargarDatosAyudaPesos(); 
	    
	    } else {
	        this.ayudaPesos = new AyudaPesos();
	    } 
	    
	    cargarDatosAyudaPesos(); 
	}
	
	public void setPanelAnterior(String panelNombre) {
		this.panelAnterior = panelNombre;
	}
	
    private void manejarAnularAyuda() {
	    if (ayudaPesos == null || ayudaPesos.getIdAyudaPesos() == null) {
	        JOptionPane.showMessageDialog(this, "No hay una ayuda seleccionada para anular.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    mostrarDialogoConfirmacion();
	}
	
	private void mostrarDialogoConfirmacion() {

	    JDialog dialog = new JDialog(mainFrame, "Confirmar Anulación", true);
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.setSize(400, 150);
	    dialog.getContentPane().setLayout(new BorderLayout());
	    dialog.setLocationRelativeTo(this); 
	    
	    JLabel mensaje = new JLabel("¿Estás seguro de que deseas anular esta ayuda?");
	    mensaje.setHorizontalAlignment(SwingConstants.CENTER);
	    mensaje.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
	    dialog.getContentPane().add(mensaje, BorderLayout.CENTER);

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
	    			anularAyuda();
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
			anularAyuda(); 
			dialog.dispose();
			});

	    btnNo.addActionListener(e -> {
	    		dialog.dispose();
	    	});
	    btnSi.addFocusListener(new java.awt.event.FocusAdapter() {
	        public void focusGained(java.awt.event.FocusEvent evt) {
	          }
	    });

	    btnNo.addFocusListener(new java.awt.event.FocusAdapter() {
	        public void focusGained(java.awt.event.FocusEvent evt) {
	           }
	    });

	    dialog.setVisible(true);
	}

	private void anularAyuda() {
	    try {
	        ayudaServicio.eliminarAyuda(ayudaPesos.getIdAyudaPesos()); 
	        JOptionPane.showMessageDialog(this, "La ayuda ha sido anulada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	    	cardLayout.show(cardPanel, panelAnterior);
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Ocurrió un error al anular la ayuda: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
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
		            List<CuotaAyudaPesos> cuotasOptimized = new ArrayList<>();
		            
		            @Override
		            protected Void doInBackground() throws Exception {
		            	List<Integer> ids = new ArrayList<>();
			            if (ayudaPesos.getNumeroSocio() != null) {
			                ids.add(ayudaPesos.getNumeroSocio());
			            }
			            if (ayudaPesos.getNroSocioGarante() != null) {
			                ids.add(ayudaPesos.getNroSocioGarante());
			            }
			            if (ayudaPesos.getNroSocioGarante2() != null) {
			                ids.add(ayudaPesos.getNroSocioGarante2());
			            }
			            if (ayudaPesos.getNroSocioGarante3() != null) {
			                ids.add(ayudaPesos.getNroSocioGarante3());
			            }

			            List<Socio> socios = socioServicio.buscarSociosPorNumeros(ids);

			            Map<Integer, Socio> socioMap = new HashMap<>();
			            for (Socio s : socios) {
			                socioMap.put(s.getNumeroSocio(), s);
			            }
			            socio = (ayudaPesos.getNumeroSocio() != null) ? socioMap.get(ayudaPesos.getNumeroSocio()) : null;
			            socioGarante = (ayudaPesos.getNroSocioGarante() != null) ? socioMap.get(ayudaPesos.getNroSocioGarante()) : null;
			            socioGarante1 = (ayudaPesos.getNroSocioGarante2() != null) ? socioMap.get(ayudaPesos.getNroSocioGarante2()) : null;
			            socioGarante2 = (ayudaPesos.getNroSocioGarante3() != null) ? socioMap.get(ayudaPesos.getNroSocioGarante3()) : null;
			            
			           Integer garantiaId = ayudaPesos.getGarantia();
			            garantia = (garantiaId != null) ? garantiaServicio.buscarGarantiaPorId(garantiaId) : null;

			            BigDecimal montoMov1 = ayudaPesos.getMontoCheque1() == null ?  BigDecimal.ZERO : ayudaPesos.getMontoCheque1();
			            BigDecimal montoMov2 = ayudaPesos.getMontoCheque2() == null ?  BigDecimal.ZERO : ayudaPesos.getMontoCheque2() ;
			            BigDecimal montoMov3 = ayudaPesos.getMontoCheque3() == null ?  BigDecimal.ZERO : ayudaPesos.getMontoCheque3();
			            
						cheques =  montoMov1.add(montoMov3).add(montoMov2);
		            
		                cuotasOptimized = cuotasServicio.buscarCuotasPendientesPorNumeroAyuda(ayudaPesos.getNroAyuda());
		                if(cuotasOptimized == null ) {
		                	if(cuotasOptimized.size() == 0) {
		                		montoCuota =  cobroCuotaAyudaPesosServicio.buscarCobroCuotaAyudaPesosPorNumAyuda(ayudaPesos.getNroAyuda()).get(0).getMontoCuota();	                	
		                	}
		                }
		                else {
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
		            
		            	  
		            	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
			           
		            	
		            	txtNumeroSocio.setText((ayudaPesos.getNumeroSocio() != null) ? ayudaPesos.getNumeroSocio().toString() : "N/A");
		                txtNombreSocio.setText((socio != null) ? socio.getApellidoNombre() : "N/A");
		              
		                txtNumSocioGarante.setText((ayudaPesos.getNroSocioGarante() != null) ? ayudaPesos.getNroSocioGarante().toString() : "N/A");
		                txtNombreSocioGarante.setText((socioGarante != null) ? socioGarante.getApellidoNombre() : "N/A");
		                
		                txtNumSocioGarante1.setText((ayudaPesos.getNroSocioGarante2() != null) ? ayudaPesos.getNroSocioGarante2().toString() : "N/A");
		                txtNombreSocioGarante1.setText((socioGarante1 != null) ? socioGarante1.getApellidoNombre() : "N/A");
		                
		                txtNumSocioGarante2.setText((ayudaPesos.getNroSocioGarante3() != null) ? ayudaPesos.getNroSocioGarante3().toString() : "N/A");
		                txtNombreSocioGarante2.setText((socioGarante2 != null) ? socioGarante2.getApellidoNombre() : "N/A");
		                
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

			            String transferenciaStr = (ayudaPesos.getMontoTransferencia() != null) ? df.format(ayudaPesos.getMontoTransferencia()) : "N/A";
			            txtTransferencia.setText(transferenciaStr);

			            txtCheques.setText(cheques == null ? "0.00" : df.format(cheques));
			            listaCuotasAdeudadas = cuotasOptimized;
			            lblAyudalegales.setVisible(false);
			            lblFechaAyudaLegales.setVisible(false);
			            if(ayudaPesos != null) {
				            if(ayudaPesos.getFechaLegales() != null || ayudaPesos.getFechaOrigLegales() != null) {
				            	lblAyudalegales.setText("Ayuda en Legales");
				            	lblAyudalegales.setForeground(Color.RED);
				            	lblAyudalegales.setVisible(true);
				            	
				            	   lblFechaAyudaLegales.setText(sdf.format(ayudaPesos.getFechaLegales()));
						            lblFechaAyudaLegales.setForeground(Color.RED);
						            lblFechaAyudaLegales.setVisible(true);
				            }
			            if(ayudaPesos.getAyudaSaldada() != null) {
			            	if(ayudaPesos.getAyudaSaldada().equals("S")) {
			            		lblAyudaPendiente.setText("Ayuda Saldada");
			            	}else {
			            		lblAyudaPendiente.setText("Ayuda Pendiente");
			            	}
			            	
			            }
			           
			           }
		                llenarTablaCuotas(cuotasOptimized);
		                
		            }
		        }.execute();
		    }
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
	
	private void abrirOpcionesUnaVarias() {
		this.setVisible(false);
		
		altaCobrosPesosCobrarVariasOUna.setlistaCuotasAdeudadas(this.listaCuotasAdeudadas);
		altaCobrosPesosCobrarVariasOUna.setAyudaPesos(ayudaPesos);

      	cardLayout.show(cardPanel, "AltaCobrosPesosCobrarVariasOUna");
	}

	public void setAltaCobrosPesosCobrarVariasOUna(AltaCobrosPesosCobrarVariasOUna altaCobrosPesosCobrarVariasOUna) {
		this.altaCobrosPesosCobrarVariasOUna = altaCobrosPesosCobrarVariasOUna;
	}
	
}
