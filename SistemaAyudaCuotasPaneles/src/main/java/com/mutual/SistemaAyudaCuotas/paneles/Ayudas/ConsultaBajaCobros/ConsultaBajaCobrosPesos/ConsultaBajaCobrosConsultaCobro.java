package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaCobros.ConsultaBajaCobrosPesos;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
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

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.componentes.PlaceholderTextField;
import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.Garantia;
import com.mutual.SistemaAyudaCuotas.entidades.Socio;
import com.mutual.SistemaAyudaCuotas.servicio.IAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ICobroCuotaAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ICuotaAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.IGarantiaServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ISocioServicio;

@Component
public class ConsultaBajaCobrosConsultaCobro extends JPanel {

	private static final long serialVersionUID = 1L;
	private ICuotaAyudaPesosServicio cuotasServicio;
	private IGarantiaServicio garantiaServicio;
	private ISocioServicio socioServicio;
	private IAyudaPesosServicio ayudaServicio;
	private ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio;
	
	private JPanel contentPane;
	private AyudaPesos ayudaPesos;
	private CobroCuotaAyudaPesos cobro;
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
	private PlaceholderTextField txtCuota;
	private PlaceholderTextField txtFecha;
	private PlaceholderTextField txtMontoCobrado;
	private PlaceholderTextField txtDescuentos;
	private PlaceholderTextField txtEfectivo;
	private PlaceholderTextField txtTransferencia;
	private PlaceholderTextField txtCheques;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JFrame mainFrame;
	protected String panelAnterior;
	public ConsultaBajaCobrosConsultaCobro(
			ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio,
			ICuotaAyudaPesosServicio cuotasServicio, 
			ISocioServicio socioServicio, 
			IGarantiaServicio garantiaServicio,
			IAyudaPesosServicio ayudaServicio
			) {
		this.ayudaPesos = new AyudaPesos();
		this.cobro = new CobroCuotaAyudaPesos();
		this.cuotasServicio = cuotasServicio;
		this.garantiaServicio = garantiaServicio;
		this.socioServicio = socioServicio;
		this.ayudaServicio = ayudaServicio;
		this.cobroCuotaAyudaPesosServicio = cobroCuotaAyudaPesosServicio;
	}
	
	public void iniciar(CardLayout cardLayout, JPanel cardPanel,JFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.cardLayout = cardLayout;
		this.cardPanel = cardPanel;
		
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
		
		Dimension containerSize = new Dimension(1000, 600); // Adjust dimensions as needed
        
        // Create a main panel with absolute positioning; use the same fixed size
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
		txtNumeroSocio.setFont(new Font("72", Font.PLAIN, 17));
		txtNumeroSocio.setColumns(10);
		txtNumeroSocio.setBounds(99, 63, 76, 23);
		mainPanel.add(txtNumeroSocio);
		
		JLabel lblSocioGarante = new JLabel("Socio Garante:");
		lblSocioGarante.setFont(new Font("72", Font.BOLD, 17));
		lblSocioGarante.setBounds(25, 96, 137, 23);
		mainPanel.add(lblSocioGarante);
		
		txtNumSocioGarante = new PlaceholderTextField("00000000.00");
		txtNumSocioGarante.setFocusable(false);
		txtNumSocioGarante.setForeground(new Color(0, 0, 0));
		txtNumSocioGarante.setPlaceholder("0000");
		txtNumSocioGarante.setFont(new Font("72", Font.PLAIN, 17));
		txtNumSocioGarante.setColumns(10);
		txtNumSocioGarante.setBounds(185, 92, 56, 23);
		mainPanel.add(txtNumSocioGarante);
		
		JLabel lblNumeroAyuda = new JLabel("Numero Ayuda:");
		lblNumeroAyuda.setFont(new Font("72", Font.PLAIN, 17));
		lblNumeroAyuda.setBounds(25, 196, 137, 23);
		mainPanel.add(lblNumeroAyuda);
		
		txtNumAyuda = new PlaceholderTextField("00000000.00");
		txtNumAyuda.setFocusable(false);
		txtNumAyuda.setForeground(new Color(0, 0, 0));
		txtNumAyuda.setPlaceholder("00000");
		txtNumAyuda.setFont(new Font("72", Font.PLAIN, 17));
		txtNumAyuda.setColumns(10);
		txtNumAyuda.setBounds(216, 196, 65, 23);
		mainPanel.add(txtNumAyuda);
		
		txtNombreSocio = new PlaceholderTextField("00000000.00");
		txtNombreSocio.setFocusable(false);
		txtNombreSocio.setForeground(new Color(0, 0, 0));
		txtNombreSocio.setPlaceholder("...");
		txtNombreSocio.setFont(new Font("72", Font.PLAIN, 17));
		txtNombreSocio.setColumns(10);
		txtNombreSocio.setBounds(185, 65, 192, 23);
		mainPanel.add(txtNombreSocio);
		
		JLabel lblSocioGarante1 = new JLabel("Segundo Garante:");
		lblSocioGarante1.setFont(new Font("72", Font.PLAIN, 17));
		lblSocioGarante1.setBounds(25, 122, 137, 23);
		mainPanel.add(lblSocioGarante1);
		
		txtNumSocioGarante1 = new PlaceholderTextField("00000000.00");
		txtNumSocioGarante1.setFocusable(false);
		txtNumSocioGarante1.setForeground(new Color(0, 0, 0));
		txtNumSocioGarante1.setPlaceholder("0000");
		txtNumSocioGarante1.setFont(new Font("72", Font.PLAIN, 17));
		txtNumSocioGarante1.setColumns(10);
		txtNumSocioGarante1.setBounds(185, 120, 56, 23);
		mainPanel.add(txtNumSocioGarante1);
		
		JLabel lblSocioGarante2 = new JLabel("Tercer Garante:");
		lblSocioGarante2.setFont(new Font("72", Font.PLAIN, 17));
		lblSocioGarante2.setBounds(25, 153, 137, 23);
		mainPanel.add(lblSocioGarante2);
		
		txtNumSocioGarante2 = new PlaceholderTextField("00000000.00");
		txtNumSocioGarante2.setFocusable(false);
		txtNumSocioGarante2.setForeground(new Color(0, 0, 0));
		txtNumSocioGarante2.setPlaceholder("0000");
		txtNumSocioGarante2.setFont(new Font("72", Font.PLAIN, 17));
		txtNumSocioGarante2.setColumns(10);
		txtNumSocioGarante2.setBounds(185, 149, 56, 23);
		mainPanel.add(txtNumSocioGarante2);
		
		txtNombreSocioGarante = new PlaceholderTextField("00000000.00");
		txtNombreSocioGarante.setFocusable(false);
		txtNombreSocioGarante.setForeground(new Color(0, 0, 0));
		txtNombreSocioGarante.setPlaceholder("...");
		txtNombreSocioGarante.setFont(new Font("72", Font.PLAIN, 17));
		txtNombreSocioGarante.setColumns(10);
		txtNombreSocioGarante.setBounds(251, 92, 285, 23);
		mainPanel.add(txtNombreSocioGarante);
		
		txtNombreSocioGarante1 = new PlaceholderTextField("00000000.00");
		txtNombreSocioGarante1.setFocusable(false);
		txtNombreSocioGarante1.setForeground(new Color(0, 0, 0));
		txtNombreSocioGarante1.setPlaceholder("...");
		txtNombreSocioGarante1.setFont(new Font("72", Font.PLAIN, 17));
		txtNombreSocioGarante1.setColumns(10);
		txtNombreSocioGarante1.setBounds(251, 120, 285, 23);
		mainPanel.add(txtNombreSocioGarante1);
		
		txtNombreSocioGarante2 = new PlaceholderTextField("00000000.00");
		txtNombreSocioGarante2.setFocusable(false);
		txtNombreSocioGarante2.setForeground(new Color(0, 0, 0));
		txtNombreSocioGarante2.setPlaceholder("...");
		txtNombreSocioGarante2.setFont(new Font("72", Font.PLAIN, 17));
		txtNombreSocioGarante2.setColumns(10);
		txtNombreSocioGarante2.setBounds(251, 147, 285, 23);
		mainPanel.add(txtNombreSocioGarante2);
		
		JLabel lblFechaEmision = new JLabel("Fecha Emisión:");
		lblFechaEmision.setFont(new Font("72", Font.PLAIN, 17));
		lblFechaEmision.setBounds(25, 224, 137, 23);
		mainPanel.add(lblFechaEmision);
		
		txtFechaEmision = new PlaceholderTextField("00000000.00");
		txtFechaEmision.setFocusable(false);
		txtFechaEmision.setForeground(new Color(0, 0, 0));
		txtFechaEmision.setPlaceholder("00/00/0000");
		txtFechaEmision.setFont(new Font("72", Font.PLAIN, 17));
		txtFechaEmision.setColumns(10);
		txtFechaEmision.setBounds(216, 224, 94, 23);
		mainPanel.add(txtFechaEmision);
		
		JLabel lblMontoSolicitado = new JLabel("Monto Solicitado:");
		lblMontoSolicitado.setFont(new Font("72", Font.PLAIN, 17));
		lblMontoSolicitado.setBounds(25, 252, 137, 23);
		mainPanel.add(lblMontoSolicitado);
		
		txtMontoSolicitado = new PlaceholderTextField("00000000.00");
		txtMontoSolicitado.setFocusable(false);
		txtMontoSolicitado.setForeground(new Color(0, 0, 0));
		txtMontoSolicitado.setFont(new Font("72", Font.PLAIN, 17));
		txtMontoSolicitado.setColumns(10);
		txtMontoSolicitado.setBounds(216, 252, 108, 23);
		mainPanel.add(txtMontoSolicitado);
		
		JLabel lblTasaInteres = new JLabel("Tasa de Interés:");
		lblTasaInteres.setFont(new Font("72", Font.PLAIN, 17));
		lblTasaInteres.setBounds(25, 280, 137, 23);
		mainPanel.add(lblTasaInteres);
		
		txtTasaInteres = new PlaceholderTextField("00000000.00");
		txtTasaInteres.setFocusable(false);
		txtTasaInteres.setForeground(new Color(0, 0, 0));
		txtTasaInteres.setPlaceholder("00.00");
		txtTasaInteres.setFont(new Font("72", Font.PLAIN, 17));
		txtTasaInteres.setColumns(10);
		txtTasaInteres.setBounds(216, 280, 94, 23);
		mainPanel.add(txtTasaInteres);
		
		JLabel lblGastosAdmin = new JLabel("Gtos. Administrativos:");
		lblGastosAdmin.setFont(new Font("72", Font.PLAIN, 17));
		lblGastosAdmin.setBounds(25, 308, 164, 23);
		mainPanel.add(lblGastosAdmin);
		
		txtGastosAdmin = new PlaceholderTextField("00000000.00");
		txtGastosAdmin.setFocusable(false);
		txtGastosAdmin.setForeground(new Color(0, 0, 0));
		txtGastosAdmin.setFont(new Font("72", Font.PLAIN, 17));
		txtGastosAdmin.setColumns(10);
		txtGastosAdmin.setBounds(216, 308, 108, 23);
		mainPanel.add(txtGastosAdmin);
		
		JLabel lblGastosCobranza = new JLabel("Gastos Cobranza:");
		lblGastosCobranza.setFont(new Font("72", Font.PLAIN, 17));
		lblGastosCobranza.setBounds(25, 341, 137, 23);
		mainPanel.add(lblGastosCobranza);
		
		txtGastosCobranza = new PlaceholderTextField("00000000.00");
		txtGastosCobranza.setFocusable(false);
		txtGastosCobranza.setForeground(new Color(0, 0, 0));
		txtGastosCobranza.setFont(new Font("72", Font.PLAIN, 17));
		txtGastosCobranza.setColumns(10);
		txtGastosCobranza.setBounds(216, 341, 108, 23);
		mainPanel.add(txtGastosCobranza);
		
		JLabel lblGarantia = new JLabel("Garantía:");
		lblGarantia.setFont(new Font("72", Font.PLAIN, 17));
		lblGarantia.setBounds(25, 369, 137, 23);
		mainPanel.add(lblGarantia);
		
		txtGarantia = new PlaceholderTextField("00000000.00");
		txtGarantia.setFocusable(false);
		txtGarantia.setForeground(new Color(0, 0, 0));
		txtGarantia.setPlaceholder("...");
		txtGarantia.setFont(new Font("72", Font.PLAIN, 17));
		txtGarantia.setColumns(10);
		txtGarantia.setBounds(185, 369, 306, 23);
		mainPanel.add(txtGarantia);
		
		txtDestino = new PlaceholderTextField("00000000.00");
		txtDestino.setFocusable(false);
		txtDestino.setForeground(new Color(0, 0, 0));
		txtDestino.setPlaceholder("...");
		txtDestino.setFont(new Font("72", Font.PLAIN, 17));
		txtDestino.setColumns(10);
		txtDestino.setBounds(185, 396, 306, 23);
		mainPanel.add(txtDestino);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setFont(new Font("72", Font.PLAIN, 17));
		lblDestino.setBounds(25, 396, 137, 23);
		mainPanel.add(lblDestino);
		
		txtCantCuotas = new PlaceholderTextField("00000000.00");
		txtCantCuotas.setFocusable(false);
		txtCantCuotas.setForeground(new Color(0, 0, 0));
		txtCantCuotas.setPlaceholder("0");
		txtCantCuotas.setFont(new Font("72", Font.PLAIN, 17));
		txtCantCuotas.setColumns(10);
		txtCantCuotas.setBounds(216, 425, 39, 23);
		mainPanel.add(txtCantCuotas);
		
		JLabel lblCantCuotas = new JLabel("Cantidad de Cuotas:");
		lblCantCuotas.setFont(new Font("72", Font.PLAIN, 17));
		lblCantCuotas.setBounds(25, 425, 150, 23);
		mainPanel.add(lblCantCuotas);
		
		txtInteresCuota = new PlaceholderTextField("00000000.00");
		txtInteresCuota.setFocusable(false);
		txtInteresCuota.setForeground(new Color(0, 0, 0));
		txtInteresCuota.setFont(new Font("72", Font.PLAIN, 17));
		txtInteresCuota.setColumns(10);
		txtInteresCuota.setBounds(216, 456, 108, 23);
		mainPanel.add(txtInteresCuota);
		
		JLabel lblInteresCuota = new JLabel("Interés Cuota:");
		lblInteresCuota.setFont(new Font("72", Font.PLAIN, 17));
		lblInteresCuota.setBounds(25, 456, 137, 23);
		mainPanel.add(lblInteresCuota);
		
		txtMontoCuota = new PlaceholderTextField("00000000.00");
		txtMontoCuota.setFocusable(false);
		txtMontoCuota.setForeground(new Color(0, 0, 0));
		txtMontoCuota.setFont(new Font("72", Font.PLAIN, 17));
		txtMontoCuota.setColumns(10);
		txtMontoCuota.setBounds(216, 484, 108, 23);
		mainPanel.add(txtMontoCuota);
		
		JLabel lblMontoCuota = new JLabel("Monto Cuota:");
		lblMontoCuota.setFont(new Font("72", Font.PLAIN, 17));
		lblMontoCuota.setBounds(25, 484, 137, 23);
		mainPanel.add(lblMontoCuota);
		
		txtCuota = new PlaceholderTextField("00000000.00");
		txtCuota.setPlaceholder("00");
		txtCuota.setForeground(Color.BLACK);
		txtCuota.setFont(new Font("72", Font.PLAIN, 17));
		txtCuota.setFocusable(false);
		txtCuota.setColumns(10);
		txtCuota.setBounds(703, 66, 192, 23);
		mainPanel.add(txtCuota);
		
		JLabel lblCuota = new JLabel("Cuota:");
		lblCuota.setFont(new Font("72", Font.PLAIN, 17));
		lblCuota.setBounds(564, 66, 112, 23);
		mainPanel.add(lblCuota);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("72", Font.PLAIN, 17));
		lblFecha.setBounds(564, 122, 112, 23);
		mainPanel.add(lblFecha);
		
		txtFecha = new PlaceholderTextField("00000000.00");
		txtFecha.setPlaceholder("00/00/0000");
		txtFecha.setForeground(Color.BLACK);
		txtFecha.setFont(new Font("72", Font.PLAIN, 17));
		txtFecha.setFocusable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(703, 118, 192, 23);
		mainPanel.add(txtFecha);
		
		JLabel lblMontoCobrado = new JLabel("Monto Cobrado:");
		lblMontoCobrado.setFont(new Font("72", Font.PLAIN, 17));
		lblMontoCobrado.setBounds(564, 168, 129, 23);
		mainPanel.add(lblMontoCobrado);
		
		txtMontoCobrado = new PlaceholderTextField("00000000.00");
		txtMontoCobrado.setForeground(Color.BLACK);
		txtMontoCobrado.setFont(new Font("72", Font.PLAIN, 17));
		txtMontoCobrado.setFocusable(false);
		txtMontoCobrado.setColumns(10);
		txtMontoCobrado.setBounds(703, 166, 192, 23);
		mainPanel.add(txtMontoCobrado);
		
		txtDescuentos = new PlaceholderTextField("00000000.00");
		txtDescuentos.setForeground(Color.BLACK);
		txtDescuentos.setFont(new Font("72", Font.PLAIN, 17));
		txtDescuentos.setFocusable(false);
		txtDescuentos.setColumns(10);
		txtDescuentos.setBounds(703, 194, 192, 23);
		mainPanel.add(txtDescuentos);
		
		JLabel lblDescuentos = new JLabel("Descuentos:");
		lblDescuentos.setFont(new Font("72", Font.PLAIN, 17));
		lblDescuentos.setBounds(564, 196, 112, 23);
		mainPanel.add(lblDescuentos);
		
		JLabel lblEfectivo = new JLabel("Efectivo:");
		lblEfectivo.setFont(new Font("72", Font.PLAIN, 17));
		lblEfectivo.setBounds(564, 252, 112, 23);
		mainPanel.add(lblEfectivo);
		
		txtEfectivo = new PlaceholderTextField("00000000.00");
		txtEfectivo.setForeground(Color.BLACK);
		txtEfectivo.setFont(new Font("72", Font.PLAIN, 17));
		txtEfectivo.setFocusable(false);
		txtEfectivo.setColumns(10);
		txtEfectivo.setBounds(703, 250, 192, 23);
		mainPanel.add(txtEfectivo);
		
		txtTransferencia = new PlaceholderTextField("00000000.00");
		txtTransferencia.setForeground(Color.BLACK);
		txtTransferencia.setFont(new Font("72", Font.PLAIN, 17));
		txtTransferencia.setFocusable(false);
		txtTransferencia.setColumns(10);
		txtTransferencia.setBounds(703, 278, 192, 23);
		mainPanel.add(txtTransferencia);
		
		JLabel lblTransferencia = new JLabel("Transferencia:");
		lblTransferencia.setFont(new Font("72", Font.PLAIN, 17));
		lblTransferencia.setBounds(564, 280, 112, 23);
		mainPanel.add(lblTransferencia);
		
		JLabel lblCheques = new JLabel("Cheques:");
		lblCheques.setFont(new Font("72", Font.PLAIN, 17));
		lblCheques.setBounds(564, 311, 108, 23);
		mainPanel.add(lblCheques);
		
		txtCheques = new PlaceholderTextField("00000000.00");
		txtCheques.setForeground(Color.BLACK);
		txtCheques.setFont(new Font("72", Font.PLAIN, 17));
		txtCheques.setFocusable(false);
		txtCheques.setColumns(10);
		txtCheques.setBounds(703, 311, 192, 23);
		mainPanel.add(txtCheques);
		
		JButton btnBorrarCobro = new JButton("Borrar Cobro");
		btnBorrarCobro.setFont(new Font("72", Font.PLAIN, 17));
		btnBorrarCobro.setFocusable(true);
		btnBorrarCobro.setBounds(656, 421, 137, 23);
		mainPanel.add(btnBorrarCobro);
	    btnBorrarCobro.addActionListener(e -> {
	    	manejarBorrarCobro();
	    });
	    btnBorrarCobro.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
	    			manejarBorrarCobro();
	    		}
	    	}
	    });
	    
	   
	}
	
	
	//-------------ATRIBUTOS---------------

	public String getPanelAnterior() {
		return panelAnterior;
	}

	public void setPanelAnterior(String panelAnterior) {
		this.panelAnterior = panelAnterior;
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
		
		public CobroCuotaAyudaPesos getCobroCuotaAyudaPesos() {
			return cobro;
		}
		
		public void setCobroCuotaAyudaPesos(CobroCuotaAyudaPesos cobro) {
		    if (cobro != null) {
		        this.cobro = cobro;
		        cargarDatosCobroCuota(); // Cargar datos solo cuando se establece AyudaPesos

		    
		    } else {
		        this.cobro = new CobroCuotaAyudaPesos();
		    } 
		    
		    cargarDatosCobroCuota(); // Actualiza la interfaz con los nuevos datos
		}
		//-------------METODOS-----------
		
		
		private void manejarBorrarCobro() {
		    // Verificar que haya una ayuda seleccionada
		    if (ayudaPesos == null || ayudaPesos.getIdAyudaPesos() == null) {
		        JOptionPane.showMessageDialog(this, "No hay una ayuda seleccionada para anular.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    // Mostrar el diálogo de confirmación personalizado
		    mostrarDialogoConfirmacion();
		}
		
		private void mostrarDialogoConfirmacion() {
		    // Crear el JDialog
		    JDialog dialog = new JDialog(mainFrame, "Confirmar Eliminacion", true);
		    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    dialog.setSize(400, 150);
		    dialog.getContentPane().setLayout(new BorderLayout());
		    dialog.setLocationRelativeTo(this); // Centrar el diálogo respecto a la ventana principal

		    // Crear el mensaje
		    JLabel mensaje = new JLabel("¿Estás seguro de que deseas borrar este Cobro?");
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
		    			anularCobro();
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
		    	anularCobro();
		    	dialog.dispose();
				});

		    btnNo.addActionListener(e -> {
		    		// Método para anular la ayuda
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

		private void anularCobro() {
		    try {
		    
		        cobroCuotaAyudaPesosServicio.borrarCobroPorId(cobro.getIdCobroCuotaAyudaPesos());
		        
		        JOptionPane.showMessageDialog(this, "Cobro borrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

		        cardLayout.show(cardPanel, panelAnterior);

		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(this, "Ocurrió un error al borrar el Cobro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}

		
		public void cargarDatosCobroCuota() {
		    if (this.cobro != null) {
		        try {
		        	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		        	String fechaPago = (cobro.getFechaPagoCuota() != null) ? sdf.format(cobro.getFechaPagoCuota()) : "N/A";
		        	
		        	BigDecimal efectivo = (cobro.getMontoEfectivo() == null) ? BigDecimal.ZERO : cobro.getMontoEfectivo();
		        	BigDecimal transferencia= (cobro.getMontoTransferencia() == null) ? BigDecimal.ZERO : cobro.getMontoTransferencia();
		        
		        	BigDecimal cheque1 = (cobro.getMontoCheque1() == null) ? BigDecimal.ZERO : cobro.getMontoCheque1();
		        	BigDecimal cheque2 = (cobro.getMontoCheque2() == null) ? BigDecimal.ZERO : cobro.getMontoCheque2();
		        	BigDecimal cheque3 = (cobro.getMontoCheque3() == null) ? BigDecimal.ZERO : cobro.getMontoCheque3();
		        	BigDecimal totalCheques = cheque1.add(cheque2).add(cheque3);

		        	BigDecimal cheques = totalCheques;
		        	BigDecimal montoCobro= (cobro.getMontoCuota() == null) ? BigDecimal.ZERO : cobro.getMontoCuota();
		        	BigDecimal descuento= (cobro.getDescuento() == null) ? BigDecimal.ZERO : cobro.getDescuento();
		        
		        	txtCuota.setText(Integer.toString(cobro.getNumeroCuota()));
		        	txtFecha.setText(fechaPago);
		        	
		        	txtMontoCobrado.setText(montoCobro.toString());
		        	txtDescuentos.setText(descuento.toString());
		        	
		        	txtEfectivo.setText(efectivo.toString());
		        	txtTransferencia.setText(transferencia.toString());
		        	txtCheques.setText(cheques.toString());
		          
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(this, "Ocurrió un error al cargar los datos de cobro en Consulta Cobro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }}
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
			            CobroCuotaAyudaPesos cobro = new CobroCuotaAyudaPesos();
			            
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
			                
			                // 2. Obtener datos de Garantía
				            Integer garantiaId = ayudaPesos.getGarantia();
				            garantia = (garantiaId != null) ? garantiaServicio.buscarGarantiaPorId(garantiaId) : null;
				            
				            BigDecimal montoMov1 = ayudaPesos.getMontoCheque1() == null ?  BigDecimal.ZERO : ayudaPesos.getMontoCheque1();
				            BigDecimal montoMov2 = ayudaPesos.getMontoCheque2() == null ?  BigDecimal.ZERO : ayudaPesos.getMontoCheque2() ;
				            BigDecimal montoMov3 = ayudaPesos.getMontoCheque3() == null ?  BigDecimal.ZERO : ayudaPesos.getMontoCheque3();
				            
							cheques =  montoMov1.add(montoMov3).add(montoMov2);
			            
							cobro = cobroCuotaAyudaPesosServicio.buscarCobroCuotaAyudaPesosPorNroAyuda(ayudaPesos.getNroAyuda());
				                
							  if(cobro == null ) {
				                	montoCuota =  cuotasServicio.buscarCuotasPorNumeroAyuda(ayudaPesos.getNroAyuda()).get(0).getMontoCuota();	                	
				                }else {
				                	
				                	montoCuota = cobro.getMontoCuota();
				                	
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

				            txtMontoCuota.setText(montoCuota == null ? "0,00" : df.format(montoCuota));

			            }
			        }.execute();
			    }
			}
			
			
}
