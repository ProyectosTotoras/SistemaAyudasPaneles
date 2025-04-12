package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.componentes.PlaceholderTextField;
import com.mutual.SistemaAyudaCuotas.dto.GarantiaInfoDTO;
import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.Parametro;
import com.mutual.SistemaAyudaCuotas.entidades.Socio;
import com.mutual.SistemaAyudaCuotas.filtros.DecimalFilter;
import com.mutual.SistemaAyudaCuotas.filtros.IntegerFilter;
import com.mutual.SistemaAyudaCuotas.servicio.DDJJPepServicio;
import com.mutual.SistemaAyudaCuotas.servicio.IAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ICuotaAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.IParametroServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ISocioServicio;
import com.mutual.SistemaAyudaCuotas.servicio.PagareAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.utilidades.CalculosFechas;
import com.mutual.SistemaAyudaCuotas.utilidades.ConvertorFechas;

@Component
@Scope("prototype")
public class AltaAyudaPesosFormulario extends JPanel {
	
	
	private final IAyudaPesosServicio ayudaServicio;
	private final IParametroServicio parametroServicio;
	private final ICuotaAyudaPesosServicio cuotasServicio;
    private final CalculosFechas calculosFechas;
	//Cuotas
    private GarantiaInfoDTO garantiaInfo;
	private String panelAnterior = "AltaAyudaPesosGarantes";
	private String panelOtraAyuda = "AltaAyudaTipoAyuda";
	private List<CuotaAyudaPesos> cuotas;
	//Creacion de Ayuda
	private AyudaPesos ayuda; 
	private LocalDate fechaHoy;
	private BigDecimal montoSolicitado;
	private BigDecimal tasaMensual;
	private BigDecimal gastosAdmin;
	private String destinoAyuda;
	private int cantidadCuotas;
	private String novacion;
	private BigDecimal montoSellado;
	private BigDecimal interesCuota;
	private BigDecimal interesAcumulado;
	private BigDecimal montoCuota;
	private BigDecimal montoEfectivo;
	private BigDecimal montoTransferencia;
	private BigDecimal netoACobrar;	
	private BigDecimal modulo;
	private boolean ayudaCreada;		
		
	//Valiaciones primer panel
	private boolean isMontoValid = false;
	private boolean isTasaValid = false;
	private boolean isGastosAdminValid = false;
	private boolean isAniosValid = false;
	private boolean isDestinoAyudaValid = false;
	private boolean isCantidadCuotasValid = false;
	private PlaceholderTextField pl;
	//Componentes UI
	private JPanel contentPane;
	private JTextField txtGarantia;
	private JTextField txtNumSocio;
	private JTextField txtNumGarante1;
	private JTextField txtApeNomSocio;
	private JTextField txtApeNomGarante1;
	private JTextField txtApeNomGarante2;
	private JTextField txtNumGarante2;
	private JTextField txtNumGarante3;
	private JTextField txtApeNomGarante3;
		
	private JTextField txtFechaOp;

	private JTextField txtMontoSolicitado;
	private PlaceholderTextField txtTasaMensual;
	private PlaceholderTextField txtGastosAdmin;
	private PlaceholderTextField txtAnios;
	private PlaceholderTextField txtDestinoAyuda;
	private PlaceholderTextField txtCantidadCuotas;
		
	private JTextField txtMesesGracia;
	private JTextField txtMontoSellado;
	private JTextField txtNeto;
		
	private JTextField txtEfectivo;
	private JTextField txtTransferecia;
		
	private PlaceholderTextField txtImporteMov;
	private PlaceholderTextField txtImporteMov2;
	private PlaceholderTextField txtImporteMov3;
		
	private PlaceholderTextField txtNroMov;
	private PlaceholderTextField txtNroMov2;
	private PlaceholderTextField txtNroMov3;
		
	private PlaceholderTextField txtDetalleMov;
	private PlaceholderTextField txtDetalleMov2;
	private PlaceholderTextField txtDetalleMov3;
	
	final Border originalBorder = new JButton().getBorder();
	private JTextField txtSaldoAhorroComun;

	//ERRORES
	private JLabel lblErrorEfectivo;
	private JLabel lblErrorTransferencia;
	private JLabel lblErrorMontoSolicitado;
    private JLabel lblErrorTasaMensual;
    private JLabel lblErrorGastosAdministrativos;
    private JLabel lblErrorAnios;
    private JLabel lblErrorDestinoAyuda;
    private JLabel lblErrorCantindadCuotas;
    private JLabel lblErrorMesesGracia;
    private JLabel lblErrorFechaOp;
	
    private JCheckBox cbxOpExenta;
	private JComboBox<String> cbxNovacion;
	private JTextField txtIntCuo;
	private JTextField txtTotInt;
	private JTextField txtMontoCuota;
	private JLabel lblFechaPrimerCuota;
	private JTextField txtFechaCuota;
	private JTextField txtFechaSellado;
	private JButton btnContinuar;
	private ISocioServicio socioServicio;
	private JButton btnGenerarReporte;
	private JButton btnCrearOtraAyuda;
	private boolean opExenta;
	private AltaAyudaPesosGarantes altaAyudaPesosGarantes;
	private AltaAyudaPesos altaAyudaPesos;
	
	
	public AltaAyudaPesosFormulario(
			IAyudaPesosServicio ayudaServicio,
			ICuotaAyudaPesosServicio cuotasServicio,
			IParametroServicio parametroServicio,
			ISocioServicio socioServicio,
			CalculosFechas calculosFechas) {
		this.socioServicio = socioServicio;
		this.ayudaServicio = ayudaServicio;
		this.cuotasServicio = cuotasServicio;
		this.calculosFechas = calculosFechas;
		this.parametroServicio = parametroServicio;
		cargarInfoInicial();
		generarReporte();
		ayuda = new AyudaPesos();
		cuotas = new ArrayList<CuotaAyudaPesos>();
		novacion = "N";
//		iniciar(null,null,null);
	}

	public void iniciar(CardLayout cardLayout, JPanel cardPanel,JFrame mainFrame) {
		// Configurar key binding para la tecla Escape
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapeAction");
        this.getActionMap().put("escapeAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(ayudaCreada) {
            		limpiarDatos();
            		altaAyudaPesos.borrarDatos();
            		altaAyudaPesosGarantes.limpiarCampos();
            		cardLayout.show(cardPanel, panelOtraAyuda);       
            		
            	}else {
            		cardLayout.show(cardPanel, panelAnterior);            		
            		
            	}
            }
        });
    	
        addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentShown(ComponentEvent e) {
    	        e.getComponent().requestFocusInWindow();
    	        txtFechaOp.requestFocusInWindow();
    	    }
    	});
        
    	JPanel mainPanel = new JPanel((LayoutManager) null);
    	mainPanel.setPreferredSize(new Dimension(1150, 670));
    	mainPanel.setMaximumSize(new Dimension(1150, 670));
    	mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    	mainPanel.setBackground(new Color(240, 240, 240));
    	add(mainPanel);
    	JLabel lblGarantia = new JLabel("Garantía: ");
		lblGarantia.setFont(new Font("72", Font.PLAIN, 17));
		lblGarantia.setBounds(30, 16, 81, 14);
		mainPanel.add(lblGarantia);
		
		txtGarantia = new JTextField();
		txtGarantia.setFocusable(false);
		txtGarantia.setFont(new Font("72", Font.PLAIN, 17));
		txtGarantia.setBounds(121, 16, 263, 23);
		mainPanel.add(txtGarantia);
		txtGarantia.setColumns(10);
		
		JLabel lblSocio = new JLabel("Socio:");
		lblSocio.setFont(new Font("72", Font.PLAIN, 17));
		lblSocio.setBounds(30, 47, 81, 14);
		mainPanel.add(lblSocio);
		
		txtNumSocio = new JTextField();
		txtNumSocio.setFocusable(false);
		txtNumSocio.setFont(new Font("72", Font.PLAIN, 17));
		txtNumSocio.setColumns(10);
		txtNumSocio.setBounds(121, 47, 63, 23);
		mainPanel.add(txtNumSocio);
		
		txtNumGarante1 = new JTextField();
		txtNumGarante1.setFocusable(false);
		txtNumGarante1.setFont(new Font("72", Font.PLAIN, 17));
		txtNumGarante1.setColumns(10);
		txtNumGarante1.setBounds(121, 77, 63, 23);
		mainPanel.add(txtNumGarante1);
		
		txtApeNomSocio = new JTextField();
		txtApeNomSocio.setFocusable(false);
		txtApeNomSocio.setFont(new Font("72", Font.PLAIN, 17));
		txtApeNomSocio.setColumns(10);
		txtApeNomSocio.setBounds(194, 47, 333, 23);
		mainPanel.add(txtApeNomSocio);
		
		txtApeNomGarante1 = new JTextField();
		txtApeNomGarante1.setFocusable(false);
		txtApeNomGarante1.setFont(new Font("72", Font.PLAIN, 17));
		txtApeNomGarante1.setColumns(10);
		txtApeNomGarante1.setBounds(194, 77, 333, 23);
		mainPanel.add(txtApeNomGarante1);
		
		txtApeNomGarante2 = new JTextField();
		txtApeNomGarante2.setFocusable(false);
		txtApeNomGarante2.setFont(new Font("72", Font.PLAIN, 17));
		txtApeNomGarante2.setColumns(10);
		txtApeNomGarante2.setBounds(194, 108, 333, 23);
		mainPanel.add(txtApeNomGarante2);
		
		txtNumGarante2 = new JTextField();
		txtNumGarante2.setFocusable(false);
		txtNumGarante2.setFont(new Font("72", Font.PLAIN, 17));
		txtNumGarante2.setColumns(10);
		txtNumGarante2.setBounds(121, 108, 63, 23);
		mainPanel.add(txtNumGarante2);
		
		JLabel lblGarante1 = new JLabel("Garante 1:");
		lblGarante1.setFont(new Font("72", Font.PLAIN, 17));
		lblGarante1.setBounds(30, 77, 81, 14);
		mainPanel.add(lblGarante1);
		
		JLabel lblGarante2 = new JLabel("Garante 2:");
		lblGarante2.setFont(new Font("72", Font.PLAIN, 17));
		lblGarante2.setBounds(30, 108, 81, 14);
		mainPanel.add(lblGarante2);
		
		txtNumGarante3 = new JTextField();
		txtNumGarante3.setFocusable(false);
		txtNumGarante3.setFont(new Font("72", Font.PLAIN, 17));
		txtNumGarante3.setColumns(10);
		txtNumGarante3.setBounds(121, 139, 63, 23);
		mainPanel.add(txtNumGarante3);
		
		JLabel lblGarante3 = new JLabel("Garante 3:");
		lblGarante3.setFont(new Font("72", Font.PLAIN, 17));
		lblGarante3.setBounds(30, 139, 81, 14);
		mainPanel.add(lblGarante3);
		
		txtApeNomGarante3 = new JTextField();
		txtApeNomGarante3.setFocusable(false);
		txtApeNomGarante3.setFont(new Font("72", Font.PLAIN, 17));
		txtApeNomGarante3.setColumns(10);
		txtApeNomGarante3.setBounds(194, 139, 333, 23);
		mainPanel.add(txtApeNomGarante3);
		
		JLabel lblFechaOp = new JLabel("Fecha de Operación:");
		lblFechaOp.setFont(new Font("72", Font.PLAIN, 17));
		lblFechaOp.setBounds(570, 14, 155, 20);
		mainPanel.add(lblFechaOp);
		
		try {
	        MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
	        dateFormatter.setPlaceholderCharacter('_'); 
	        
	        txtFechaOp =  new JFormattedTextField(dateFormatter);
	    	txtFechaOp.setFocusable(true);
	    	txtFechaOp.setFont(new Font("72", Font.PLAIN, 17));
	    	txtFechaOp.setColumns(10);
	        txtFechaOp.setEditable(true);
	        txtFechaOp.setBounds(735, 11, 104, 26);
	          
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            String fechaEmisionStr = sdf.format(new Date());
            
            txtFechaOp.setText(fechaEmisionStr);
            mainPanel.add(txtFechaOp);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		
		JPanel primerPanel = new JPanel();
		primerPanel.setBackground(new Color(240, 240, 240));
		primerPanel.setBorder(null);
		primerPanel.setBounds(26, 170, 518, 328);
		mainPanel.add(primerPanel);
		primerPanel.setLayout(null);
		
		JLabel lblAnios = new JLabel("Años:");
		lblAnios.setFont(new Font("72", Font.PLAIN, 17));
		lblAnios.setBounds(21, 185, 137, 14);
		primerPanel.add(lblAnios);
		
		JLabel lblDestinoAyuda = new JLabel("Destino Ayuda:");
		lblDestinoAyuda.setFont(new Font("72", Font.PLAIN, 17));
		lblDestinoAyuda.setBounds(21, 241, 137, 14);
		primerPanel.add(lblDestinoAyuda);
		
		JLabel lblCantidadCuotas = new JLabel("Cantidad de Cuotas:");
		lblCantidadCuotas.setFont(new Font("72", Font.PLAIN, 17));
		lblCantidadCuotas.setBounds(21, 294, 181, 14);
		primerPanel.add(lblCantidadCuotas);
		
		JLabel lblGastosAdmin = new JLabel("Gastos Administrativos: ");
		lblGastosAdmin.setFont(new Font("72", Font.PLAIN, 17));
		lblGastosAdmin.setBounds(21, 126, 181, 14);
		primerPanel.add(lblGastosAdmin);
		
		JLabel lblTasaMensual = new JLabel("Tasa Mensual:");
		lblTasaMensual.setFont(new Font("72", Font.PLAIN, 17));
		lblTasaMensual.setBounds(21, 70, 137, 14);
		primerPanel.add(lblTasaMensual);
		
		JLabel lblMontoSolicitado = new JLabel("Monto Solicitado:");
		lblMontoSolicitado.setFont(new Font("72", Font.PLAIN, 17));
		lblMontoSolicitado.setBounds(21, 17, 137, 14);
		primerPanel.add(lblMontoSolicitado);
		
		txtMontoSolicitado = new PlaceholderTextField("00000000.00");
		txtMontoSolicitado.setFont(new Font("72", Font.PLAIN, 17));
		txtMontoSolicitado.setBounds(212, 14, 160, 23);

		((AbstractDocument) txtMontoSolicitado.getDocument()).setDocumentFilter(new DecimalFilter());
		primerPanel.add(txtMontoSolicitado);
 		txtMontoSolicitado.setColumns(10);
		
		txtTasaMensual = new PlaceholderTextField("0.00");
		txtTasaMensual.setFont(new Font("72", Font.PLAIN, 17));
		txtTasaMensual.setBounds(212, 67, 160, 23);
		((AbstractDocument) txtTasaMensual.getDocument()).setDocumentFilter(new DecimalFilter());
 		primerPanel.add(txtTasaMensual);
		txtTasaMensual.setColumns(10);
		
		txtGastosAdmin = new PlaceholderTextField("00000000.00");
		txtGastosAdmin.setFont(new Font("72", Font.PLAIN, 17));
		txtGastosAdmin.setBounds(212, 123, 160, 23);
		((AbstractDocument) txtGastosAdmin.getDocument()).setDocumentFilter(new DecimalFilter());
 		primerPanel.add(txtGastosAdmin);
		txtGastosAdmin.setColumns(10);
		
		txtAnios = new PlaceholderTextField("5");
		txtAnios.setFont(new Font("72", Font.PLAIN, 17));
		txtAnios.setBounds(212, 179, 160, 23);
		((AbstractDocument) txtAnios.getDocument()).setDocumentFilter(new IntegerFilter());
 			primerPanel.add(txtAnios);
		txtAnios.setColumns(10);
		
		txtDestinoAyuda = new PlaceholderTextField("...");
		txtDestinoAyuda.setFont(new Font("72", Font.PLAIN, 17));
		txtDestinoAyuda.setBounds(197, 235, 285, 23);
		primerPanel.add(txtDestinoAyuda);
		txtDestinoAyuda.setColumns(10);
		
		txtCantidadCuotas = new PlaceholderTextField("0");
		txtCantidadCuotas.setFont(new Font("72", Font.PLAIN, 17));
		txtCantidadCuotas.setBounds(222, 291, 160, 23);
		((AbstractDocument) txtCantidadCuotas.getDocument()).setDocumentFilter(new IntegerFilter());
 		primerPanel.add(txtCantidadCuotas);
		txtCantidadCuotas.setColumns(10);
		
		JPanel segundoPanel = new JPanel();
		segundoPanel.setBackground(new Color(240, 240, 240));
		segundoPanel.setLayout(null);
		segundoPanel.setBounds(26, 509, 518, 162);
		mainPanel.add(segundoPanel);
		
		JLabel lblNovacion = new JLabel("Novación:");
		lblNovacion.setFont(new Font("72", Font.PLAIN, 17));
		lblNovacion.setBounds(22, 135, 144, 14);
		segundoPanel.add(lblNovacion);
		
		JLabel lblNeto = new JLabel("NETO A COBRAR:");
		lblNeto.setFont(new Font("72", Font.PLAIN, 17));
		lblNeto.setBounds(22, 104, 144, 14);
		segundoPanel.add(lblNeto);
		
		JLabel lblTasaMensual_1 = new JLabel("Monto Sellado:");
		lblTasaMensual_1.setFont(new Font("72", Font.PLAIN, 17));
		lblTasaMensual_1.setBounds(22, 73, 144, 14);
		segundoPanel.add(lblTasaMensual_1);
		
		JLabel lblMontoSolicitado_1 = new JLabel("Meses de Gracia:");
		lblMontoSolicitado_1.setFont(new Font("72", Font.PLAIN, 17));
		lblMontoSolicitado_1.setBounds(22, 17, 144, 14);
		segundoPanel.add(lblMontoSolicitado_1);
		
		txtMesesGracia = new PlaceholderTextField("0");
		txtMesesGracia.setEditable(false);
		txtMesesGracia.setFont(new Font("72", Font.PLAIN, 17));
		txtMesesGracia.setColumns(10);
		txtMesesGracia.setBounds(215, 11, 160, 23);
		   txtMesesGracia.setFocusable(false);
		((AbstractDocument) txtMesesGracia.getDocument()).setDocumentFilter(new IntegerFilter());
 		segundoPanel.add(txtMesesGracia);
		
		txtMontoSellado = new PlaceholderTextField("00000000.00");
		txtMontoSellado.setFocusable(false);
		txtMontoSellado.setEditable(false);
		txtMontoSellado.setFont(new Font("72", Font.PLAIN, 17));
		txtMontoSellado.setColumns(10);
		txtMontoSellado.setBounds(215, 67, 160, 23);
		segundoPanel.add(txtMontoSellado);
		
		txtNeto = new PlaceholderTextField("00000000.00");
		txtNeto.setFocusable(false);
		txtNeto.setEditable(false);
		txtNeto.setFont(new Font("72", Font.PLAIN, 17));
		txtNeto.setColumns(10);
		txtNeto.setBounds(215, 98, 160, 23);
		segundoPanel.add(txtNeto);
		
		cbxNovacion = new JComboBox<String>();
		cbxNovacion.setBounds(215, 133, 50, 22);
		cbxNovacion.addItem("NO");
		cbxNovacion.addItem("SI");
		cbxNovacion.setSelectedItem("NO");
		segundoPanel.add(cbxNovacion);
		
		lblErrorMesesGracia = new JLabel("error meses de gracia");
		lblErrorMesesGracia.setBackground(new Color(145, 207, 238));
		lblErrorMesesGracia.setForeground(new Color(128, 0, 0));
		lblErrorMesesGracia.setFont(new Font("72", Font.PLAIN, 17));
		lblErrorMesesGracia.setBounds(22, 42, 458, 14);
		lblErrorMesesGracia.setVisible(false);
		segundoPanel.add(lblErrorMesesGracia);
		
		JPanel tercerPanel = new JPanel();
		tercerPanel.setBackground(new Color(240, 240, 240));
		tercerPanel.setBorder(new TitledBorder(null, "Formas De Pago", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tercerPanel.setLayout(null);
		tercerPanel.setBounds(565, 156, 551, 302);
		mainPanel.add(tercerPanel);
		
		JLabel lblGastosAdmts_1_1 = new JLabel("3 - Movimientos");
		lblGastosAdmts_1_1.setFont(new Font("72", Font.PLAIN, 17));
		lblGastosAdmts_1_1.setBounds(10, 133, 144, 14);
		tercerPanel.add(lblGastosAdmts_1_1);
		
		JLabel lblTasaMensual_1_1 = new JLabel("2 - Transferencia:");
		lblTasaMensual_1_1.setFont(new Font("72", Font.PLAIN, 17));
		lblTasaMensual_1_1.setBounds(10, 80, 144, 14);
		tercerPanel.add(lblTasaMensual_1_1);
		
		JLabel lblMontoSolicitado_1_1 = new JLabel("1 - Efectivo:");
		lblMontoSolicitado_1_1.setFont(new Font("72", Font.PLAIN, 17));
		lblMontoSolicitado_1_1.setBounds(10, 29, 144, 14);
		tercerPanel.add(lblMontoSolicitado_1_1);
		
		txtEfectivo = new PlaceholderTextField("00000000.00");
		txtEfectivo.setEditable(false);
		txtEfectivo.setFont(new Font("72", Font.PLAIN, 17));
		txtEfectivo.setColumns(10);
		txtEfectivo.setBounds(189, 26, 160, 23);
		((AbstractDocument) txtEfectivo.getDocument()).setDocumentFilter(new DecimalFilter());
	 	tercerPanel.add(txtEfectivo);
		
		txtTransferecia = new PlaceholderTextField("00000000.00");
		txtTransferecia.setEditable(false);
		txtTransferecia.setFont(new Font("72", Font.PLAIN, 17));
		txtTransferecia.setColumns(10);
		txtTransferecia.setBounds(189, 77, 160, 23);
		((AbstractDocument) txtTransferecia.getDocument()).setDocumentFilter(new DecimalFilter());
	 	tercerPanel.add(txtTransferecia);
		
		txtImporteMov = new PlaceholderTextField("00000000.00");
		txtImporteMov.setFont(new Font("72", Font.PLAIN, 17));
		txtImporteMov.setColumns(10);
		txtImporteMov.setBounds(378, 186, 160, 23);
		((AbstractDocument) txtImporteMov.getDocument()).setDocumentFilter(new DecimalFilter());
		tercerPanel.add(txtImporteMov);
		
		lblErrorEfectivo = new JLabel("error efectivo");
		lblErrorEfectivo.setVisible(false);
		lblErrorEfectivo.setForeground(new Color(128, 0, 0));
		lblErrorEfectivo.setFont(new Font("72", Font.PLAIN, 17));
		lblErrorEfectivo.setBounds(10, 46, 429, 20);
		tercerPanel.add(lblErrorEfectivo);
		
		lblErrorTransferencia = new JLabel("error efectivo");
		lblErrorTransferencia.setVisible(false);
		lblErrorTransferencia.setForeground(new Color(128, 0, 0));
		lblErrorTransferencia.setFont(new Font("72", Font.PLAIN, 17));
		lblErrorTransferencia.setBounds(10, 102, 429, 20);
		tercerPanel.add(lblErrorTransferencia);
		
		txtImporteMov2 = new PlaceholderTextField("00000000.00");
		txtImporteMov2.setFont(new Font("72", Font.PLAIN, 17));
		txtImporteMov2.setColumns(10);
		txtImporteMov2.setBounds(378, 214, 160, 23);
		((AbstractDocument) txtImporteMov2.getDocument()).setDocumentFilter(new DecimalFilter());
		tercerPanel.add(txtImporteMov2);
		
		txtImporteMov3 = new PlaceholderTextField("00000000.00");
		txtImporteMov3.setFont(new Font("72", Font.PLAIN, 17));
		txtImporteMov3.setColumns(10);
		txtImporteMov3.setBounds(378, 245, 160, 23);
		((AbstractDocument) txtImporteMov3.getDocument()).setDocumentFilter(new DecimalFilter());
		tercerPanel.add(txtImporteMov3);
		
		txtDetalleMov = new PlaceholderTextField("00000000.00");
		txtDetalleMov.setPlaceholder("...");
		txtDetalleMov.setFont(new Font("72", Font.PLAIN, 17));
		txtDetalleMov.setColumns(10);
		txtDetalleMov.setBounds(207, 187, 160, 23);
		tercerPanel.add(txtDetalleMov);
		
		txtNroMov = new PlaceholderTextField("00000000.00");
		txtNroMov.setPlaceholder("0000");
		txtNroMov.setFont(new Font("72", Font.PLAIN, 17));
		txtNroMov.setColumns(10);
		txtNroMov.setBounds(20, 185, 160, 23);
		((AbstractDocument) txtNroMov.getDocument()).setDocumentFilter(new IntegerFilter());
		tercerPanel.add(txtNroMov);
		
		txtDetalleMov2 = new PlaceholderTextField("00000000.00");
		txtDetalleMov2.setPlaceholder("...");
		txtDetalleMov2.setFont(new Font("72", Font.PLAIN, 17));
		txtDetalleMov2.setColumns(10);
		txtDetalleMov2.setBounds(207, 216, 160, 23);
		tercerPanel.add(txtDetalleMov2);
		
		txtDetalleMov3 = new PlaceholderTextField("00000000.00");
		txtDetalleMov3.setPlaceholder("...");
		txtDetalleMov3.setFont(new Font("72", Font.PLAIN, 17));
		txtDetalleMov3.setColumns(10);
		txtDetalleMov3.setBounds(208, 245, 160, 23);
		tercerPanel.add(txtDetalleMov3);
		
		txtNroMov2 = new PlaceholderTextField("00000000.00");
		txtNroMov2.setPlaceholder("0000");
		txtNroMov2.setFont(new Font("72", Font.PLAIN, 17));
		txtNroMov2.setColumns(10);
		txtNroMov2.setBounds(20, 213, 160, 23);
		((AbstractDocument) txtNroMov2.getDocument()).setDocumentFilter(new IntegerFilter());
		tercerPanel.add(txtNroMov2);
		
		txtNroMov3 = new PlaceholderTextField("00000000.00");
		txtNroMov3.setPlaceholder("0000");
		txtNroMov3.setFont(new Font("72", Font.PLAIN, 17));
		txtNroMov3.setColumns(10);
		txtNroMov3.setBounds(20, 245, 160, 23);
		((AbstractDocument) txtNroMov3.getDocument()).setDocumentFilter(new IntegerFilter());
		tercerPanel.add(txtNroMov3);
		
		JLabel lblNumeroMov = new JLabel("Número");
		lblNumeroMov.setFont(new Font("72", Font.PLAIN, 17));
		lblNumeroMov.setBounds(20, 159, 144, 14);
		tercerPanel.add(lblNumeroMov);
		
		JLabel lblGastosAdmts_1_1_2 = new JLabel("Detalles");
		lblGastosAdmts_1_1_2.setFont(new Font("72", Font.PLAIN, 17));
		lblGastosAdmts_1_1_2.setBounds(207, 160, 144, 14);
		tercerPanel.add(lblGastosAdmts_1_1_2);
		
		JLabel lblGastosAdmts_1_1_3 = new JLabel("Importe");
		lblGastosAdmts_1_1_3.setFont(new Font("72", Font.PLAIN, 17));
		lblGastosAdmts_1_1_3.setBounds(378, 161, 144, 20);
		tercerPanel.add(lblGastosAdmts_1_1_3);
		
		btnContinuar = new JButton("Crear Ayuda");
		btnContinuar.setFont(new Font("72", Font.PLAIN, 17));
		btnContinuar.setBounds(740, 636, 144, 23);
		btnContinuar.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	        	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	        	try{
	        			crearAyudaPesos();	        			
	        		}catch(Exception ex) {
	        			mostrarMensaje("Error al crear ayuda");
	        		}
	        	}
	       	}
		});
		btnContinuar.addActionListener(e -> {
			try{
    			crearAyudaPesos();	        			
    		}catch(Exception ex) {
    			mostrarMensaje("Error al crear ayuda");
    		}
		});
		mainPanel.add(btnContinuar);
		
		JLabel lblOpExenta = new JLabel("Selecciona si es operación exenta:");
		lblOpExenta.setFont(new Font("72", Font.PLAIN, 17));
		lblOpExenta.setBounds(570, 94, 269, 14);
		mainPanel.add(lblOpExenta);
		
		cbxOpExenta = new JCheckBox("");
		cbxOpExenta.setFont(new Font("Arial", Font.PLAIN, 31));
		cbxOpExenta.setBounds(837, 94, 35, 14);
		mainPanel.add(cbxOpExenta);
		
		JLabel lblSaldoAhorroCompn = new JLabel("Saldo Ahorro Común:");
		lblSaldoAhorroCompn.setFont(new Font("72", Font.PLAIN, 17));
		lblSaldoAhorroCompn.setBounds(570, 128, 170, 14);
		mainPanel.add(lblSaldoAhorroCompn);
		
		txtSaldoAhorroComun = new JTextField();
		txtSaldoAhorroComun.setFont(new Font("72", Font.PLAIN, 17));
		txtSaldoAhorroComun.setFocusable(false);
		txtSaldoAhorroComun.setColumns(10);
		txtSaldoAhorroComun.setBounds(748, 125, 160, 23);
		mainPanel.add(txtSaldoAhorroComun);
		
		lblErrorFechaOp = new JLabel("errorFechaOp");
		lblErrorFechaOp.setForeground(new Color(128, 0, 0));
		lblErrorFechaOp.setFont(new Font("72", Font.PLAIN, 17));
		lblErrorFechaOp.setBounds(570, 44, 428, 14);
		lblErrorFechaOp.setVisible(false);  // Inicialmente oculto
		mainPanel.add(lblErrorFechaOp);
		
		JLabel lblIntcuo = new JLabel("Interés Cuota");
		lblIntcuo.setBounds(583, 483, 144, 14);
		mainPanel.add(lblIntcuo);
		lblIntcuo.setFont(new Font("72", Font.PLAIN, 17));
		
		txtIntCuo = new JTextField();
		txtIntCuo.setBounds(776, 480, 160, 23);
		mainPanel.add(txtIntCuo);
		txtIntCuo.setFocusable(false);
		txtIntCuo.setFont(new Font("72", Font.PLAIN, 17));
		txtIntCuo.setColumns(10);
		
		JLabel lblTotint = new JLabel("Total Interés");
		lblTotint.setBounds(583, 511, 144, 14);
		mainPanel.add(lblTotint);
		lblTotint.setFont(new Font("72", Font.PLAIN, 17));
		
		txtTotInt = new JTextField();
		txtTotInt.setBounds(776, 508, 160, 23);
		mainPanel.add(txtTotInt);
		txtTotInt.setFocusable(false);
		txtTotInt.setFont(new Font("72", Font.PLAIN, 17));
		txtTotInt.setColumns(10);
		
		JLabel lblMontoCuota = new JLabel("Monto Cuota:");
		lblMontoCuota.setBounds(583, 539, 144, 14);
		mainPanel.add(lblMontoCuota);
		lblMontoCuota.setFont(new Font("72", Font.PLAIN, 17));
		
		txtMontoCuota = new JTextField();
		txtMontoCuota.setBounds(776, 536, 160, 23);
		mainPanel.add(txtMontoCuota);
		txtMontoCuota.setFocusable(false);
		txtMontoCuota.setFont(new Font("72", Font.PLAIN, 17));
		txtMontoCuota.setColumns(10);
		
		lblFechaPrimerCuota = new JLabel("Fecha primer cuota:");
		lblFechaPrimerCuota.setBounds(583, 564, 144, 23);
		mainPanel.add(lblFechaPrimerCuota);
		lblFechaPrimerCuota.setFont(new Font("72", Font.PLAIN, 17));
		
		txtFechaCuota = new JTextField();
		txtFechaCuota.setBounds(776, 567, 160, 23);
		mainPanel.add(txtFechaCuota);
		txtFechaCuota.setFocusable(false);
		txtFechaCuota.setFont(new Font("72", Font.PLAIN, 17));
		txtFechaCuota.setColumns(10);
		
		JLabel lblFechaSellado = new JLabel("Fecha Sellado:");
		lblFechaSellado.setBounds(583, 601, 144, 24);
		mainPanel.add(lblFechaSellado);
		lblFechaSellado.setFont(new Font("72", Font.PLAIN, 17));
		
		txtFechaSellado = new JTextField();
		txtFechaSellado.setBounds(736, 605, 160, 23);
		mainPanel.add(txtFechaSellado);
		txtFechaSellado.setFocusable(false);
		txtFechaSellado.setFont(new Font("72", Font.PLAIN, 17));
		txtFechaSellado.setColumns(10);
		
		// Generar Reporte
		btnGenerarReporte = new JButton("Generar Documentación");
		btnGenerarReporte.setFont(new Font("72", Font.PLAIN, 17));
		btnGenerarReporte.setBounds(907, 636, 209, 23);
		btnGenerarReporte.setEnabled(false);
		mainPanel.add(btnGenerarReporte);
	
		txtEfectivo.setEditable(false);
		txtTransferecia.setEditable(false);
		txtImporteMov.setEditable(false);
		txtImporteMov2.setEditable(false);
		txtImporteMov3.setEditable(false);
		txtDetalleMov.setEditable(false);
		txtDetalleMov2.setEditable(false);
		txtDetalleMov3.setEditable(false);
		txtNroMov.setEditable(false);
		txtNroMov2.setEditable(false);
		txtNroMov3.setEditable(false);
		
		txtEfectivo.setFocusable(false);
		txtTransferecia.setFocusable(false);
		txtImporteMov.setFocusable(false);
		txtImporteMov2.setFocusable(false);
		txtImporteMov3.setFocusable(false);
		txtDetalleMov.setFocusable(false);
		txtDetalleMov2.setFocusable(false);
		txtDetalleMov3.setFocusable(false);
		txtNroMov.setFocusable(false);
		txtNroMov2.setFocusable(false);
		txtNroMov3.setFocusable(false);
		
		btnCrearOtraAyuda = new JButton("Crear otra Ayuda");
		btnCrearOtraAyuda.setFont(new Font("72", Font.PLAIN, 17));
		btnCrearOtraAyuda.setEnabled(false);
		btnCrearOtraAyuda.setBounds(564, 636, 159, 23);
		mainPanel.add(btnCrearOtraAyuda);
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaHoyTxt = txtFechaOp.getText().trim(); //necesito pasar el string del txt a una fecha  
		
		if(!fechaHoyTxt.isEmpty()) {	
			fechaHoy = LocalDate.parse(fechaHoyTxt, formatter);
			}else {
			 fechaHoy = LocalDate.now();
		     //  txtFechaOp.setText(fechaHoy.format(formatter));
		}
    	cbxOpExenta.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	            	 // Alternar el estado del checkbox
	                cbxOpExenta.setSelected(!cbxOpExenta.isSelected());
	             
	                // Obtener el estado actual y almacenarlo en una variable
	                opExenta = cbxOpExenta.isSelected();
	                
	                // Opcional: Consumir el evento para evitar comportamientos adicionales
	                e.consume();
	                calculos();
	            }
	        }
	    });
		
    	
    	
		txtMontoSolicitado.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String montoTexto = txtMontoSolicitado.getText().trim();
//			     boolean isMontoValidResult  = validarTxtMontoSolicitado(montoTexto);
			     
//			     if (isMontoValidResult ) {
			    	 montoTexto.replace(",", ".");
			    	    montoSolicitado = new BigDecimal(montoTexto);
			    	    
			            isMontoValid = true; //Monto Valido
			            checkAllPrimerPanelValidado();
			            
			            txtMontoSolicitado.setBorder(originalBorder);
//			            lblErrorMontoSolicitado.setVisible(false); 
//			     }else {
//			    	 txtMontoSolicitado.setBorder(new LineBorder(Color.RED, 2));
//			    	 isMontoValid = false; //Monto Invalido
//			         checkAllPrimerPanelValidado();
//			     }
			}
		});
		
		txtMontoSolicitado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == e.VK_ENTER) {
					txtTasaMensual.requestFocusInWindow();
			            
			}}
		});
		
		txtTasaMensual.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String tasaTexto = txtTasaMensual.getText().trim();
				boolean isTasaValidResult = validarTxtTasaMensual(tasaTexto);  
			     
				if (isTasaValidResult ) {
						tasaTexto.replace(",", ".");
					 	tasaMensual = new BigDecimal(tasaTexto);

			            isTasaValid = true;
				    	checkAllPrimerPanelValidado();
			            txtTasaMensual.setBorder(originalBorder);
//			            lblErrorTasaMensual.setVisible(false); 
			     }else {
			    	 txtTasaMensual.setBorder(new LineBorder(Color.RED, 2));
			    	 isTasaValid  = false; //Tasa Invalida
			         checkAllPrimerPanelValidado();
			     }
			}
		});
		
		txtTasaMensual.addKeyListener(new KeyAdapter() {
			@Override
			public void  keyPressed(KeyEvent e ) {
				if(e.getKeyChar() == e.VK_ENTER) {
					 txtGastosAdmin.requestFocusInWindow(); 
				}
			}
		});
		
		txtGastosAdmin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
            	String texto = txtGastosAdmin.getText().trim();
                boolean isGastoValidResult = validarTxtGastosAdmin(texto);
			     
				if (isGastoValidResult ) {
						texto.replace(",", ".");
					 	gastosAdmin = new BigDecimal(texto);

						System.out.println("cantidad cuotas valida");
					 	isGastosAdminValid = true;
				    	checkAllPrimerPanelValidado();
				    	txtGastosAdmin.setBorder(originalBorder);
//			            lblErrorGastosAdministrativos.setVisible(false); 
			     }else {
			    	 txtGastosAdmin.setBorder(new LineBorder(Color.RED, 2));
			    	 isGastosAdminValid = false;
			    	 checkAllPrimerPanelValidado();
			     }
				
			}});
		
		txtGastosAdmin.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	            	txtAnios.requestFocusInWindow(); 
	            }
	        }
	    });
		
		txtAnios.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				

				String texto = txtAnios.getText().trim();
                boolean isAniosValidResult = validarTxtAnios(texto);
			     
				if (isAniosValidResult ) {

					//System.out.println("anios validos");
					isAniosValid= true;
	                checkAllPrimerPanelValidado();
				    txtAnios.setBorder(originalBorder);
//				    lblErrorAnios.setVisible(false); 
			     }else {
			    	 txtAnios.setBorder(new LineBorder(Color.RED, 2));
			    	isAniosValid= false;
	                checkAllPrimerPanelValidado();
			     }
				
			}});
		
		txtAnios.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	        	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	        		txtDestinoAyuda.requestFocusInWindow(); 
	            }
	        }
	    });
		
		txtDestinoAyuda.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String texto = txtDestinoAyuda.getText().trim();
                boolean isDestinoAValidResult= validarTxtDestinoAyuda(texto);
			     
				if (isDestinoAValidResult ) {

					System.out.println("destino ayuda valida");
					isDestinoAyudaValid= true;
                	checkAllPrimerPanelValidado();
                	txtDestinoAyuda.setBorder(originalBorder);
//                	lblErrorDestinoAyuda.setVisible(false); 
                	
                	destinoAyuda = txtDestinoAyuda.getText();
				}else {
			    	 txtDestinoAyuda.setBorder(new LineBorder(Color.RED, 2));
			    	isDestinoAyudaValid= false;
	                checkAllPrimerPanelValidado();
			     }
				
			}});
		
		txtDestinoAyuda.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	            	txtCantidadCuotas.requestFocusInWindow(); 
	            }
	        }
	    });
		
		txtCantidadCuotas.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String texto = txtCantidadCuotas.getText().trim();
                boolean isCantCuotasValidResult= validarTxtCantidadCuotas(texto);
			     
				if (isCantCuotasValidResult ) {
					System.out.println("cantidad cuotas valida");
					isCantidadCuotasValid = true;
                	checkAllPrimerPanelValidado();
                	txtCantidadCuotas.setBorder(originalBorder);
//                	lblErrorCantindadCuotas.setVisible(false); 
                 	int cantidadCuotasv = Integer.parseInt(txtCantidadCuotas.getText());
                 	cantidadCuotas = cantidadCuotasv;
                 	
                	//CALCULO FECHAS
                 	calcularCuotas();
                	
			     }else {
			    	txtCantidadCuotas.setBorder(new LineBorder(Color.RED, 2));
			    	isCantidadCuotasValid= false;
                	checkAllPrimerPanelValidado();
			     }
				
			}});
		
		txtCantidadCuotas.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	            		txtMesesGracia.requestFocusInWindow(); 	     
	            }
	        }
	    });
		
		txtCantidadCuotas.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_TAB) {
	            		txtMesesGracia.requestFocusInWindow(); 	     
	            }
	        }
	    });
		
		
		txtMesesGracia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String texto = txtMesesGracia.getText().trim();
                boolean isMesesGraciaValidResult= validarTxtMesesGracia(texto);
			     
				if (isMesesGraciaValidResult ) {
			
                	checkAllPrimerPanelValidado();
                	txtMesesGracia.setBorder(originalBorder);
//                	lblErrorCantindadCuotas.setVisible(false); 

                	//CALCULO FECHAS
                	calcularCuotas();
                	
			     }else {
			    	 txtMesesGracia.setBorder(new LineBorder(Color.RED, 2));
                	checkAllPrimerPanelValidado();
			     }
				
			}});
		
		txtMesesGracia.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e ) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					cbxNovacion.requestFocusInWindow();
				}
			}
		});
		
		txtFechaSellado.setText(calculosFechas.calcularFechaSellado(fechaHoy));


		cbxNovacion.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e ) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					

		            if(novacion == "N") {
		            	txtEfectivo.setFocusable(true);
						txtTransferecia.setFocusable(true);
						txtImporteMov.setFocusable(true);
						txtImporteMov2.setFocusable(true);
						txtImporteMov3.setFocusable(true);
						txtDetalleMov.setFocusable(true);
						txtDetalleMov2.setFocusable(true);
						txtDetalleMov3.setFocusable(true);
						txtNroMov.setFocusable(true);
						txtNroMov2.setFocusable(true);
						txtNroMov3.setFocusable(true);
						
		            	txtEfectivo.setEditable(true);
						txtTransferecia.setEditable(true);
						txtImporteMov.setEditable(true);
						txtImporteMov2.setEditable(true);
						txtImporteMov3.setEditable(true);
						txtDetalleMov.setEditable(true);
						txtDetalleMov2.setEditable(true);
						txtDetalleMov3.setEditable(true);
						txtNroMov.setEditable(true);
						txtNroMov2.setEditable(true);
						txtNroMov3.setEditable(true);
						txtEfectivo.requestFocusInWindow();
					
		            }else {
		            	txtEfectivo.setEditable(false);
						txtTransferecia.setEditable(false);
						txtImporteMov.setEditable(false);
						txtImporteMov2.setEditable(false);
						txtImporteMov3.setEditable(false);
						txtDetalleMov.setEditable(false);
						txtDetalleMov2.setEditable(false);
						txtDetalleMov3.setEditable(false);
						txtNroMov.setEditable(false);
						txtNroMov2.setEditable(false);
						txtNroMov3.setEditable(false);
						txtEfectivo.setFocusable(false);
						
						txtTransferecia.setFocusable(false);
						txtImporteMov.setFocusable(false);
						txtImporteMov2.setFocusable(false);
						txtImporteMov3.setFocusable(false);
						txtDetalleMov.setFocusable(false);
						txtDetalleMov2.setFocusable(false);
						txtDetalleMov3.setFocusable(false);
						txtNroMov.setFocusable(false);
						txtNroMov2.setFocusable(false);
						txtNroMov3.setFocusable(false);
		            }
		            
			
					
					
				}
			}
		});

		cbxNovacion.addItemListener(new ItemListener(){
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            String seleccion = (String) e.getItem();
		            
		            if ("NO".equalsIgnoreCase(seleccion)) {
		                novacion = "N";
		            } else if ("SI".equalsIgnoreCase(seleccion)) {
		                novacion = "S";
		            } else {
		                novacion = null; // o algún valor por defecto
		            }
		            
		            if(novacion == "N") {
		            	txtEfectivo.setFocusable(true);
						txtTransferecia.setFocusable(true);
						txtImporteMov.setFocusable(true);
						txtImporteMov2.setFocusable(true);
						txtImporteMov3.setFocusable(true);
						txtDetalleMov.setFocusable(true);
						txtDetalleMov2.setFocusable(true);
						txtDetalleMov3.setFocusable(true);
						txtNroMov.setFocusable(true);
						txtNroMov2.setFocusable(true);
						txtNroMov3.setFocusable(true);
						
		            	txtEfectivo.setEditable(true);
						txtTransferecia.setEditable(true);
						txtImporteMov.setEditable(true);
						txtImporteMov2.setEditable(true);
						txtImporteMov3.setEditable(true);
						txtDetalleMov.setEditable(true);
						txtDetalleMov2.setEditable(true);
						txtDetalleMov3.setEditable(true);
						txtNroMov.setEditable(true);
						txtNroMov2.setEditable(true);
						txtNroMov3.setEditable(true);
						
					
		            }else {
		            	txtEfectivo.setEditable(false);
						txtTransferecia.setEditable(false);
						txtImporteMov.setEditable(false);
						txtImporteMov2.setEditable(false);
						txtImporteMov3.setEditable(false);
						txtDetalleMov.setEditable(false);
						txtDetalleMov2.setEditable(false);
						txtDetalleMov3.setEditable(false);
						txtNroMov.setEditable(false);
						txtNroMov2.setEditable(false);
						txtNroMov3.setEditable(false);
						txtEfectivo.setFocusable(false);
						
						txtTransferecia.setFocusable(false);
						txtImporteMov.setFocusable(false);
						txtImporteMov2.setFocusable(false);
						txtImporteMov3.setFocusable(false);
						txtDetalleMov.setFocusable(false);
						txtDetalleMov2.setFocusable(false);
						txtDetalleMov3.setFocusable(false);
						txtNroMov.setFocusable(false);
						txtNroMov2.setFocusable(false);
						txtNroMov3.setFocusable(false);
		            }
		            
		        }
		    }
		});
		
		
		txtFechaOp.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        String fechaTexto = txtFechaOp.getText().trim();
		        boolean isFechaValida = validarTxtFecha(fechaTexto);

		        if (isFechaValida) {
		            txtFechaOp.setBorder(originalBorder);  // Restaura el borde original
		            lblErrorFechaOp.setVisible(false);  // Oculta el mensaje de error
		            
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    		String fechaHoyTxt = txtFechaOp.getText().trim(); //necesito pasar el string del txt a una fecha  
		    		
		    		if(!fechaHoyTxt.isEmpty()) {	
		    			fechaHoy = LocalDate.parse(fechaHoyTxt, formatter);
		    			}else {
		    			 fechaHoy = LocalDate.now();
		    		     //  txtFechaOp.setText(fechaHoy.format(formatter));
		    		}
		        } else {
		            txtFechaOp.setBorder(new LineBorder(Color.RED, 2));  // Resalta el campo con borde rojo
		            lblErrorFechaOp.setVisible(true);  // Muestra el mensaje de error
		        }
		    }
		});
		

		txtEfectivo.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		    	
		        String montoEfectivoString = txtEfectivo.getText().isEmpty() ? "0.00" : txtEfectivo.getText().trim();
		    
		    
		        	txtEfectivo.setBorder(originalBorder); 
		           montoEfectivo = new BigDecimal(montoEfectivoString);
		    		
		              
		    }
		});
		
		txtTransferecia.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		    	
		        String montoTransferenciaString = txtTransferecia.getText().isEmpty() ? "0.00" : txtTransferecia.getText().trim();
		     

		        	txtTransferecia.setBorder(originalBorder); 
		            
		           montoTransferencia = new BigDecimal(montoTransferenciaString);
		    
		    }
		});
		
		txtImporteMov.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		    	
		    }
		});
		
		txtEfectivo.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(novacion == "N") {
					habilitarMetodosPago(true);
				}else {
					habilitarMetodosPago(false);
				}
			}
		});
		
		txtTransferecia.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(novacion == "N") {
					habilitarMetodosPago(true);
				}else {
					habilitarMetodosPago(false);
				}
			}
		});
		
		txtImporteMov.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(novacion == "N") {
					habilitarMetodosPago(true);
				}else {
					habilitarMetodosPago(false);
				}
			}
		});
		
		txtImporteMov2.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(novacion == "N") {
					habilitarMetodosPago(true);
				}else {
					habilitarMetodosPago(false);
				}
			}
		});
		
		txtImporteMov3.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(novacion == "N") {
					habilitarMetodosPago(true);
				}else {
					habilitarMetodosPago(false);
				}
			}
		});
		
		txtNroMov.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(novacion == "N") {
					habilitarMetodosPago(true);
				}else {
					habilitarMetodosPago(false);
				}
			}
		});
		
		txtNroMov.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(novacion == "N") {
					habilitarMetodosPago(true);
				}else {
					habilitarMetodosPago(false);
				}
			}
		});
		
		txtNroMov.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(novacion == "N") {
					habilitarMetodosPago(true);
				}else {
					habilitarMetodosPago(false);
				}
			}
		});
		
		
		txtDetalleMov.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(novacion == "N") {
					habilitarMetodosPago(true);
				}else {
					habilitarMetodosPago(false);
				}
			}
		});
		
		txtDetalleMov2.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(novacion == "N") {
					habilitarMetodosPago(true);
				}else {
					habilitarMetodosPago(false);
				}
			}
		});
		
		txtDetalleMov3.addFocusListener(new FocusAdapter() {
			@Override 
			public void focusGained(FocusEvent e) {
				if(novacion == "N") {
					habilitarMetodosPago(true);
				}else {
					habilitarMetodosPago(false);
				}
			}
		});
		
		btnGenerarReporte.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				generarReporteInterno();
			}
		});
		
		btnGenerarReporte.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyChar() == e.VK_ENTER) {

					generarReporteInterno();
				}

			}
		});
		
		setFocusTraversalPolicy(new FocusTraversalOnArray((java.awt.Component[]) new java.awt.Component[]{
		 txtFechaOp, cbxOpExenta, txtMontoSolicitado, txtTasaMensual,
		    txtGastosAdmin, txtAnios, txtDestinoAyuda, txtCantidadCuotas,
		    txtMesesGracia, cbxNovacion, txtEfectivo, txtTransferecia,
		    txtNroMov, txtDetalleMov, txtImporteMov, txtNroMov2, txtDetalleMov2,
		    txtImporteMov2, txtNroMov3, txtDetalleMov3, txtImporteMov3, btnContinuar,
		    btnGenerarReporte, btnCrearOtraAyuda,
		    lblGarantia, txtGarantia, lblSocio, txtNumSocio, txtNumGarante1,
		    txtApeNomSocio, txtApeNomGarante1, txtApeNomGarante2, txtNumGarante2,
		    lblGarante1, lblGarante2, txtNumGarante3, lblGarante3, txtApeNomGarante3,
		    lblFechaOp, primerPanel, lblAnios, lblDestinoAyuda, lblCantidadCuotas,
		    lblGastosAdmin, lblTasaMensual, lblMontoSolicitado,
		    // … include all the components you need in the correct order
		}));
//		setFocusTraversalPolicy(new FocusTraversalOnArray(new java.awt.Component[]{txtFechaOp, cbxOpExenta, txtMontoSolicitado, txtTasaMensual, txtGastosAdmin, txtAnios, txtDestinoAyuda, txtCantidadCuotas, txtMesesGracia, cbxNovacion, txtEfectivo, txtTransferecia, txtNroMov, txtDetalleMov, txtImporteMov, txtNroMov2, txtDetalleMov2, txtImporteMov2, txtNroMov3, txtDetalleMov3, txtImporteMov3, btnContinuar, btnGenerarReporte, contentPane, lblGarantia, txtGarantia, lblSocio, txtNumSocio, txtNumGarante1, txtApeNomSocio, txtApeNomGarante1, txtApeNomGarante2, txtNumGarante2, lblGarante1, lblGarante2, txtNumGarante3, lblGarante3, txtApeNomGarante3, lblFechaOp, primerPanel, lblAnios, lblDestinoAyuda, lblCantidadCuotas, lblGastosAdmin, lblTasaMensual, lblMontoSolicitado, lblErrorMontoSolicitado, lblErrorTasaMensual, lblErrorGastosAdministrativos, lblErrorAnios, lblErrorDestinoAyuda, lblErrorCantindadCuotas, segundoPanel, lblNovacion, lblNeto, lblTasaMensual_1, lblMontoSolicitado_1, txtMontoSellado, txtNeto, lblErrorMesesGracia, tercerPanel, lblGastosAdmts_1_1, lblTasaMensual_1_1, lblMontoSolicitado_1_1, lblErrorEfectivo, lblErrorTransferencia, lblNumeroMov, lblGastosAdmts_1_1_2, lblGastosAdmts_1_1_3, lblOpExenta, lblSaldoAhorroCompn, txtSaldoAhorroComun, lblErrorFechaOp, lblIntcuo, txtIntCuo, lblTotint, txtTotInt, lblMontoCuota, txtMontoCuota, lblFechaPrimerCuota, txtFechaCuota, lblFechaSellado, txtFechaSellado, btnCrearOtraAyuda}));
     }

	public void setGarantiaDto(GarantiaInfoDTO garantiaInfo) {
		this.garantiaInfo = garantiaInfo;	
		 cargarInfoInicial();
	}
	
	public boolean getAyudaCreada() {
		return ayudaCreada;
	}
	
	public void cargarInfoInicial() {
		if(this.garantiaInfo != null) {
			//Socio
			txtApeNomSocio.setText(this.garantiaInfo.getNombreSocio());
			txtNumSocio.setText(this.garantiaInfo.getNumeroSocio());
			
			//Tipo Garantía
			txtGarantia.setText(this.garantiaInfo.getTipoGarantia());
			
			//Garantes
			txtNumGarante1.setText(this.garantiaInfo.getNumeroGarante1());
			txtNumGarante2.setText(this.garantiaInfo.getNumeroGarante2());
			txtNumGarante3.setText(this.garantiaInfo.getNumeroGarante3());

			txtApeNomGarante1.setText(this.garantiaInfo.getNombreGarante1());
			txtApeNomGarante2.setText(this.garantiaInfo.getNombreGarante2());
			txtApeNomGarante3.setText(this.garantiaInfo.getNombreGarante3());
			
			txtSaldoAhorroComun.setText(this.garantiaInfo.getSaldoComun());
			
			//Fecha de operación
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        LocalDate today = LocalDate.now();
	        txtFechaOp.setText(today.format(formatter));
	        
	        txtFechaOp.setEditable(true);
		}
	}
	

	private void checkAllPrimerPanelValidado() {
	    if (isMontoValid && isTasaValid && isGastosAdminValid && isAniosValid && isDestinoAyudaValid && isCantidadCuotasValid) {
	        habilitarSegundoPanel();
	    }else {
	    	deshabilitarSegundoPanel();
	    }
	}
		
	private void habilitarSegundoPanel() {
	    txtMesesGracia.setEditable(true);
	    txtMesesGracia.setFocusable(true);
	    calculos();
    }

	private void deshabilitarSegundoPanel() {
	    txtMesesGracia.setEditable(false);
	    txtMesesGracia.setFocusable(false);
		 
	}
	

	private void calculos() {
	    try {
	        // Se "limpia" el texto y se parsea a BigDecimal
	        BigDecimal montoSolicitado = new BigDecimal(txtMontoSolicitado.getText().isEmpty() ? "0.00": txtMontoSolicitado.getText());
	        BigDecimal tasaMensual = new BigDecimal(txtTasaMensual.getText().isEmpty() ? "0.00" : txtTasaMensual.getText());
	        // Para la cantidad de cuotas, asumo que no tiene formato con puntos ni coma.
	        int cantidadCuotas = Integer.parseInt(txtCantidadCuotas.getText().trim().isEmpty() ? "1" : txtCantidadCuotas.getText().trim());
	        System.out.println("cantidad cuotas metodo calculos: " + cantidadCuotas);
	        BigDecimal gastosAdministrativos = parseDecimal(txtGastosAdmin.getText());
	        
	        Parametro parametro = parametroServicio.traerParametro(1L);
	      
	        BigDecimal sellcrepe1 = new BigDecimal("0.00");
	        BigDecimal tablaN5 = new BigDecimal("0.00");
	        BigDecimal tablaN6 = new BigDecimal("0.00");
	        BigDecimal modulo = new BigDecimal("0.00");
	        
	        if (parametro != null) {
	            // Factor de sellado con mayor precisión durante las multiplicaciones
	            sellcrepe1 = (parametro.getSellcrepe1() == null) ? new BigDecimal("0.00250") : parametro.getSellcrepe1();
	            tablaN5 = (parametro.getN5() == null) ? new BigDecimal("0.00") : parametro.getN5();
	            tablaN6 = (parametro.getN6() == null) ? new BigDecimal("0.00") : parametro.getN6();
	            modulo = tablaN5.multiply(tablaN6);
	        }
	            
	        // Cálculo de interés por cuota:
	        BigDecimal int_cuo = montoSolicitado.multiply(tasaMensual)
	                                             .divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP)
	                                             .setScale(2, RoundingMode.HALF_UP);
	        
	        // Cálculo de interés total:
	        BigDecimal tot_int = int_cuo.multiply(new BigDecimal(cantidadCuotas))
	                                     .setScale(2, RoundingMode.HALF_UP);
	        
	        // Cálculo de monto sellado:
	        BigDecimal montoSellado;
	        if (cbxOpExenta.isSelected()) {
	            montoSellado = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
	        } else {
	            montoSellado = (montoSolicitado.add(gastosAdministrativos).add(tot_int))
	                           .multiply(sellcrepe1)
	                           .setScale(2, RoundingMode.HALF_UP);
	        }
	        
	        // Actualizar los campos de texto con los resultados formateados
	        txtMontoSellado.setText(montoSellado.toPlainString());
	        
	        // Cálculo de neto a cobrar:
	        BigDecimal neto = montoSolicitado.subtract(montoSellado)
	                                         .setScale(2, RoundingMode.HALF_UP);
	        
	        // Cálculo de monto por cuota:
	        BigDecimal montoCuota = (montoSolicitado.add(gastosAdministrativos).add(tot_int).add(modulo))
	                                .divide(new BigDecimal(cantidadCuotas), 2, RoundingMode.HALF_UP);
	    
	        // Actualizar otros campos de salida:
	        txtNeto.setText(neto.toPlainString());
	        txtIntCuo.setText(int_cuo.toPlainString());
	        txtTotInt.setText(tot_int.toPlainString());
	        txtMontoCuota.setText(montoCuota.toPlainString());
	        
	        // Asignar valores a las variables de instancia
	        this.montoCuota = montoCuota;
	        this.interesCuota = int_cuo;
	        this.interesAcumulado = tot_int;
	        this.netoACobrar = neto;
	        this.montoSellado = montoSellado;
	        this.modulo = modulo;
	        
	    } catch (NumberFormatException ex) {
	        mostrarMensaje("Error en la conversión de números: " + ex.getMessage());
	        ex.printStackTrace(); 
	    } catch (ArithmeticException ex) {
	        mostrarMensaje("Error aritmético durante los cálculos: " + ex.getMessage());
	        ex.printStackTrace();
	    } catch (Exception ex) {
	        mostrarMensaje("Ocurrió un error durante los cálculos: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	}
	
	// Método auxiliar para convertir una cadena formateada (por ejemplo "1.000.000,00")
		// a BigDecimal, eliminando los puntos de agrupación y reemplazando la coma decimal.
		private BigDecimal parseDecimal(String text) {
		    if (text == null || text.trim().isEmpty()) {
		        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		    }
		    // Elimina los puntos de agrupación y reemplaza la coma por punto.
		    String cleaned = text.trim().replace(".", "").replace(",", ".");
		    try {
		        return new BigDecimal(cleaned).setScale(2, RoundingMode.HALF_UP);
		    } catch (NumberFormatException e) {
		        throw e;  // O bien, manejar la excepción según convenga.
		    }
		}

		private void habilitarMetodosPago(boolean condicion) {
			if(condicion) {
				txtEfectivo.setEditable(true);
				txtTransferecia.setEditable(true);
				txtImporteMov.setEditable(true);
				txtImporteMov2.setEditable(true);
				txtImporteMov3.setEditable(true);
				txtDetalleMov.setEditable(true);
				txtDetalleMov2.setEditable(true);
				txtDetalleMov3.setEditable(true);
				txtNroMov.setEditable(true);
				txtNroMov2.setEditable(true);
				txtNroMov3.setEditable(true);	
				
				txtEfectivo.setFocusable(true);
				txtTransferecia.setFocusable(true);
				txtImporteMov.setFocusable(true);
				txtImporteMov2.setFocusable(true);
				txtImporteMov3.setFocusable(true);
				txtDetalleMov.setFocusable(true);
				txtDetalleMov2.setFocusable(true);
				txtDetalleMov3.setFocusable(true);
				txtNroMov.setFocusable(true);
				txtNroMov2.setFocusable(true);
				txtNroMov3.setFocusable(true);
			}
			else {
				txtEfectivo.setEditable(false);
				txtTransferecia.setEditable(false);
				txtImporteMov.setEditable(false);
				txtImporteMov2.setEditable(false);
				txtImporteMov3.setEditable(false);
				txtDetalleMov.setEditable(false);
				txtDetalleMov2.setEditable(false);
				txtDetalleMov3.setEditable(false);
				txtNroMov.setEditable(false);
				txtNroMov2.setEditable(false);
				txtNroMov3.setEditable(false);
				
				txtEfectivo.setFocusable(false);
				txtTransferecia.setFocusable(false);
				txtImporteMov.setFocusable(false);
				txtImporteMov2.setFocusable(false);
				txtImporteMov3.setFocusable(false);
				txtDetalleMov.setFocusable(false);
				txtDetalleMov2.setFocusable(false);
				txtDetalleMov3.setFocusable(false);
				txtNroMov.setFocusable(false);
				txtNroMov2.setFocusable(false);
				txtNroMov3.setFocusable(false);
			}
		}
		public boolean validarTxtFecha(String fechaTexto) {
		    String pattern = "^\\d{2}/\\d{2}/\\d{4}$";  // Expresión regular para el formato dd/MM/yyyy
		    Pattern r = Pattern.compile(pattern);
		    Matcher m = r.matcher(fechaTexto);

		    // Verifica si la fecha cumple con el formato esperado
		    if (!m.matches()) {
		        lblErrorFechaOp.setText("Formato de fecha inválido. Use dd/MM/yyyy");
		        return false;
		    }

		    // Intenta parsear la fecha
		    try {
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        LocalDate fecha = LocalDate.parse(fechaTexto, formatter);  // Intenta convertir la fecha
		        return true;  // La fecha es válida
		    } catch (Exception e) {
		        lblErrorFechaOp.setText("Fecha no válida.");
		        return false;  // La fecha no es válida
		    }
		}

		public boolean validarTxtMontoSolicitado(String texto) {
		    // Eliminar los puntos de agrupación para obtener el número "crudo".
		    String cleaned = texto.replace(".", "");
		    
		    // Patrón: 1 a 8 dígitos, opcionalmente seguidos de una coma y hasta 2 dígitos.
		    String pattern = "^\\d{1,8}(,\\d{0,2})?$";
		    Pattern r = Pattern.compile(pattern);
		    Matcher m = r.matcher(cleaned);
		    
		    if (!m.matches()) {
//		        lblErrorMontoSolicitado.setText("El monto solicitado debe tener hasta 8 dígitos enteros y hasta 2 decimales. Error de Validación");
//		        lblErrorMontoSolicitado.setVisible(true);
		        return false;
		    }
		    
		    try {
		        // Convertir la coma a punto para que pueda parsearse con Double.parseDouble.
		        String standardized = cleaned.replace(",", ".");
		        double valor = Double.parseDouble(standardized);
		        if (valor <= 0) {
//		            lblErrorMontoSolicitado.setText("El monto solicitado debe ser mayor que 0.");
//		            lblErrorMontoSolicitado.setVisible(true);
		            return false;
		        }
		    } catch (NumberFormatException e) {
//		        lblErrorMontoSolicitado.setText("Formato numérico inválido:");
//		        lblErrorMontoSolicitado.setVisible(true);
		        return false;
		    }
		    
//		    lblErrorMontoSolicitado.setVisible(false);
		    return true;
		}


		
		public boolean validarTxtTasaMensual(String textoInicial) {
			String texto = textoInicial.replace(",", ".");
	        String pattern = "^\\d{1,2}(\\.\\d{1,2})?$";
	        Pattern r = Pattern.compile(pattern);
	        Matcher m = r.matcher(texto);
	        
	        if (!m.matches()) {
	        	
//	        	lblErrorTasaMensual.setText(  "La tasa mensual debe tener hasta 2 dígitos enteros y hasta 2 decimales." + 
//			            "Error de Validación" ); 
//		    	lblErrorTasaMensual.setVisible(true); 

	            return false;
	        }
	        
	        try {
	            double valor = Double.parseDouble(texto);
	            if (valor <= 0) {
//	            	lblErrorTasaMensual.setText(  "La tasa mensual debe ser mayor que 0."); 
//	    	    	lblErrorTasaMensual.setVisible(true); 
	   
	                return false;
	            }
	        } catch (NumberFormatException e) {
//	        	lblErrorTasaMensual.setText(  "Formato numérico inválido."); 
//		    	lblErrorTasaMensual.setVisible(true); 

	            return false;
	        }
	        
	        return true; 
	    }
		
		public boolean validarTxtGastosAdmin(String textoInicial) {
			String texto = textoInicial.replace(",", ".");
		    String pattern = "^\\d{1,8}(\\.\\d{1,2})?$";
		    Pattern r = Pattern.compile(pattern);
		    Matcher m = r.matcher(texto);
		    
		    if (!m.matches()) {
//		    	lblErrorGastosAdministrativos.setText(  "Los gastos administrativos deben tener hasta 8 numeros enteros y hasta 2 decimales." ); 
//		    	lblErrorGastosAdministrativos.setVisible(true); 
		        return false;
		    }
		    
		    try {
		        BigDecimal valor = new BigDecimal(texto);
		        if (valor.compareTo(BigDecimal.ZERO) < 0) {
//		        	lblErrorGastosAdministrativos.setText(  "Los gastos administrativos deben ser mayores o iguales a 0." ); 
//			    	lblErrorGastosAdministrativos.setVisible(true); 
		            return false;
		        }
		    } catch (NumberFormatException e) {
//		    	lblErrorGastosAdministrativos.setText(  "Formato numérico inválido." ); 
//		    	lblErrorGastosAdministrativos.setVisible(true); 
		       
		        return false;
		    }
		    
		    return true; 
		}
		
		public boolean validarTxtAnios(String texto) {
		  
		    if (texto.isEmpty()) {
//		    	lblErrorAnios.setText(  "El campo Años no puede estar vacío." ); 
//		    	lblErrorAnios.setVisible(true); 
		        return false;
		    }

		    try {
		        int anos = Integer.parseInt(texto);
		        if (anos != 5 && anos != 10) {
//		        	lblErrorAnios.setText(  "El campo Años debe ser 5 o 10." ); 
//		        	lblErrorAnios.setVisible(true); 
		            return false;
		        }
		    } catch (NumberFormatException e) {
//		    	lblErrorAnios.setText( "El campo Años debe ser un número entero válido."); 
//		    	lblErrorAnios.setVisible(true); 
		        return false;
		    }

		    return true; 
		}
		
		public boolean validarTxtDestinoAyuda(String texto) {

		    if (texto.isEmpty()) {
//		    	lblErrorDestinoAyuda.setText( "El campo Destino Ayuda no puede estar vacío."); 
//		    	lblErrorDestinoAyuda.setVisible(true); 
		        return false;
		    }

		    if (texto.length() > 20) {
//		    	lblErrorDestinoAyuda.setText( "El campo Destino Ayuda no puede exceder los 20 caracteres."); 
//		    	lblErrorDestinoAyuda.setVisible(true); 
		        return false;
		    }

		    return true; 
		}
		
		public boolean validarTxtCantidadCuotas(String texto) {
		    
		    if (texto.isEmpty()) {
//		    	lblErrorCantindadCuotas.setText( "El campo Cantidad de Cuotas no puede estar vacío."); 
//		    	lblErrorCantindadCuotas.setVisible(true); 
		        return false;
		    }

		    if (texto.length() > 3) {
//		    	lblErrorCantindadCuotas.setText( "El campo Cantidad de Cuotas no puede exceder los 3 dígitos."); 
//		    	lblErrorCantindadCuotas.setVisible(true); 
		        return false;
		    }

		    try {
		        int cantidad = Integer.parseInt(texto);
		        if (cantidad <= 0) {
//		        	lblErrorCantindadCuotas.setText( "El campo Cantidad de Cuotas debe ser mayor que 0."); 
//		        	lblErrorCantindadCuotas.setVisible(true); 
		           
		            return false;
		        }
		    } catch (NumberFormatException e) {
//		    	lblErrorCantindadCuotas.setText( "El campo Cantidad de Cuotas debe ser un número entero válido."); 
//	        	lblErrorCantindadCuotas.setVisible(true); 
		        return false;
		    }
		    return true;
		}

		public boolean validarTxtMesesGracia(String texto) {
			 if (texto.isEmpty()) {
//			           lblErrorMesesGracia.setText("El campo Meses de Gracia no puede estar vacío.");
//			     lblErrorMesesGracia.setVisible(true);
			        return false;
			    }

			    // Verificar que la longitud no exceda los 2 dígitos
			    if (texto.length() > 2) {
//			        lblErrorMesesGracia.setText("El campo Meses de Gracia no puede exceder los 2 dígitos.");
//			        lblErrorMesesGracia.setVisible(true);
			        return false;
			    }
			    
			    try {
			        int mesesGracia = Integer.parseInt(texto);
			        
			        // Verificar que el valor sea mayor o igual a 0
			        if (mesesGracia < 0) {
//			            lblErrorMesesGracia.setText("El campo Meses de Gracia debe ser mayor o igual a 0.");
//			            lblErrorMesesGracia.setVisible(true);
			            return false;
			        }
			        
			        // Opcional: Verificar que el valor no exceda 99
			        if (mesesGracia > 99) {
//			            lblErrorMesesGracia.setText("El campo Meses de Gracia no puede exceder 99.");
//			            lblErrorMesesGracia.setVisible(true);
			            return false;
			        }
			        
			    } catch (NumberFormatException e) {
//			        lblErrorMesesGracia.setText("El campo Meses de Gracia debe ser un número entero válido.");
//			        lblErrorMesesGracia.setVisible(true);
			        return false;
			    }  
			 // Si todas las validaciones pasan, ocultar el mensaje de error
//			    lblErrorMesesGracia.setVisible(false);
			    return true;
		}
		
		public boolean validarCamposIniciales() {
			 String montoTexto = txtMontoSolicitado.getText().trim();
		        

		     boolean isMontoValid = validarTxtMontoSolicitado(montoTexto);
		     
		     if (isMontoValid) {
		    	    BigDecimal monto = new BigDecimal(montoTexto);
		    	    
		            ayuda.setMontoSolicitado(monto);;
		            
		 
		            JOptionPane.showMessageDialog(null, "Monto Solicitado válido y asignado correctamente.", 
		                "Validación Exitosa", JOptionPane.INFORMATION_MESSAGE);
		        }
		     
		     return true;
		}

		private void calcularCuotas(){
			cuotas.clear();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String fechaHoyTxt = txtFechaOp.getText().trim(); //necesito pasar el string del txt a una fecha  
			
			if(!fechaHoyTxt.isEmpty()) {	
				fechaHoy = LocalDate.parse(fechaHoyTxt, formatter);
				}else {
				 fechaHoy = LocalDate.now();
			     //  txtFechaOp.setText(fechaHoy.format(formatter));
			}
			
			
			int cantidadCuotasv = Integer.parseInt(txtCantidadCuotas.getText());
	     	cantidadCuotas = cantidadCuotasv;
	    	//CALCULO FECHAS
	    	if(!txtMesesGracia.getText().isEmpty() && 
	    			!txtCantidadCuotas.getText().isEmpty()) {

	          
	    		var mesesGracia = Integer.parseInt(txtMesesGracia.getText());
	    		
	       	    var fechasPagoCuotas = calculosFechas.calcularFechaPagoCuotas(
	       				cantidadCuotas, 
	       				mesesGracia,
	       				fechaHoy
	       				); 
	       		
	       		if (!fechasPagoCuotas.isEmpty()) {
	       			fechasPagoCuotas.forEach(f -> {
	           			System.out.println(f);
	           		});
	       		    // Obtener la primera fecha de cuota
	       		    LocalDate primeraCuota = fechasPagoCuotas.get(0);
	       		    
	       		    String fechaFormateada = primeraCuota.format(formatter);
	       		    
	       		    // Asignar la fecha formateada al JTextField
	       		    txtFechaCuota.setText(fechaFormateada);
	       		}
	       		
	       	 // Crear y asignar cada cuota
	            for (int i = 0; i < cantidadCuotas; i++) {
	                CuotaAyudaPesos cuota = new CuotaAyudaPesos();
	                Date fechaConvertida = ConvertorFechas.convertLocalDateToDate(fechasPagoCuotas.get(i));
	                
	                cuota.setFechaVtoCuota(fechaConvertida);
	                cuota.setMontoCuota(montoCuota);
	                cuota.setNumeroCuota(i + 1);
	                cuota.setIdCuotaAyudaPesos(null);
	                cuota.setCartaDocumentoEnviada(fechaHoyTxt);
	                cuota.setCartaDocumentoEnviada("");
//	                System.out.println("Numero de Cuota " + cuota.getNumeroCuota());
//	                System.out.println("Vencimiento de Cuota " + cuota.getFechaVtoCuota());
//	                System.out.println("Monto Cuota " + cuota.getMontoCuota());


	                // Añadir la cuota a la lista
	                cuotas.add(cuota);
	            }}
		}
		
		private boolean isMontosIgualNetoACob() {
		    try {
		        // Convert and set scale for each field to 2 decimal places
		        BigDecimal montoEfectivoActual = txtEfectivo.getText().isEmpty() 
		            ? new BigDecimal("0.00") 
		            : new BigDecimal(txtEfectivo.getText().trim()).setScale(2, RoundingMode.HALF_UP);
		        
		        BigDecimal montoTransferenciaActual = txtTransferecia.getText().isEmpty() 
		            ? new BigDecimal("0.00") 
		            : new BigDecimal(txtTransferecia.getText().trim()).setScale(2, RoundingMode.HALF_UP);
		        
		        BigDecimal importeMov1 = txtImporteMov.getText().trim().isEmpty() 
		            ? new BigDecimal("0.00") 
		            : new BigDecimal(txtImporteMov.getText().trim()).setScale(2, RoundingMode.HALF_UP);
		        
		        BigDecimal importeMov2 = txtImporteMov2.getText().trim().isEmpty() 
		            ? new BigDecimal("0.00") 
		            : new BigDecimal(txtImporteMov2.getText().trim()).setScale(2, RoundingMode.HALF_UP);
		        
		        BigDecimal importeMov3 = txtImporteMov3.getText().trim().isEmpty() 
		            ? new BigDecimal("0.00") 
		            : new BigDecimal(txtImporteMov3.getText().trim()).setScale(2, RoundingMode.HALF_UP);
		        
		        // Sum all amounts and set scale to 2 decimals
		        BigDecimal resultado = montoEfectivoActual
		            .add(montoTransferenciaActual)
		            .add(importeMov1)
		            .add(importeMov2)
		            .add(importeMov3)
		            .setScale(2, RoundingMode.HALF_UP);
		        
		        // Ensure netoACobrar is also rounded to 2 decimals
		        BigDecimal netoComparado = netoACobrar.setScale(2, RoundingMode.HALF_UP);
		        
		        // Debug prints (remove in production)
		        System.out.println("Monto Efectivo: " + montoEfectivoActual);
		        System.out.println("Monto Transferencia: " + montoTransferenciaActual);
		        System.out.println("Importe Mov1: " + importeMov1);
		        System.out.println("Importe Mov2: " + importeMov2);
		        System.out.println("Importe Mov3: " + importeMov3);
		        System.out.println("Total sum: " + resultado);
		        System.out.println("Neto a cobrar: " + netoComparado);
		        
		        if (resultado.compareTo(netoComparado) == 0) {
		            return true;
		        } else {
		            mostrarMensaje("El total de montos de forma de pago no coincide con el neto a cobrar.");
		            return false;
		        }
		    } catch (NumberFormatException e) {
		        mostrarMensaje("Error en la conversión de montos. Asegúrese de que los valores sean numéricos válidos.");
		        return false;
		    }
		}
		

		private boolean isTodosCamposValidos() {
			if(!validarTxtFecha(txtFechaOp.getText()) 
					|| !validarTxtMontoSolicitado(txtMontoSolicitado.getText())
					|| !validarTxtTasaMensual(txtTasaMensual.getText())
					|| !validarTxtGastosAdmin(txtGastosAdmin.getText())
					|| !validarTxtAnios(txtAnios.getText())
					|| !validarTxtDestinoAyuda(txtDestinoAyuda.getText())
					|| !validarTxtCantidadCuotas(txtCantidadCuotas.getText())
					|| !validarTxtMesesGracia(txtMesesGracia.getText())
					
					)
				 {
				return false;
			};
			return true;
		}
		
		public void crearAyudaPesos() {
			 
		        		if(!isTodosCamposValidos()) {
		        			mostrarMensaje("Existen campos inválidos!");
		        			//System.out.println("Campos invalidos");
		        			return;
		        		}

		                // Obtener el próximo numeroAyuda
		                Integer proximoNumeroAyuda = obtenerProximoNumeroAyuda();
		        	
		                ayuda.setIdAyudaPesos(null);
		                ayuda.setNroAyuda(proximoNumeroAyuda);
		        		ayuda.setNumeroSocio(Integer.parseInt(garantiaInfo.getNumeroSocio()));
		        		ayuda.setFechaAltaAyuda(ConvertorFechas.convertLocalDateToDate(fechaHoy));
		        		ayuda.setMontoSolicitado(montoSolicitado);
		        		ayuda.setCantidadCuotas(cantidadCuotas);
		        		ayuda.setDestinoAyuda(destinoAyuda);
		        		if(garantiaInfo.getNumeroGarante1() != null) {
		        			if(!garantiaInfo.getNumeroGarante1().isEmpty()){
		        				ayuda.setNroSocioGarante(Integer.parseInt(garantiaInfo.getNumeroGarante1()));	        				
		        			}
		        			else {ayuda.setNroSocioGarante(null);}
		        		}else {ayuda.setNroSocioGarante(null);}
		        		
		        		if(garantiaInfo.getNumeroGarante2() != null) {
		        			if(!garantiaInfo.getNumeroGarante2().isEmpty()){
		        				ayuda.setNroSocioGarante2(Integer.parseInt(garantiaInfo.getNumeroGarante2()));	        				
		        			}
		        			else {ayuda.setNroSocioGarante2(null);}
		        		}else {ayuda.setNroSocioGarante2(null);}
		        		
		        		if(garantiaInfo.getNumeroGarante3() != null) {
		        			if(!garantiaInfo.getNumeroGarante3().isEmpty()){
		        				ayuda.setNroSocioGarante3(Integer.parseInt(garantiaInfo.getNumeroGarante3()));	        				
		        			}
		        			else {ayuda.setNroSocioGarante3(null);}
		        		}else {ayuda.setNroSocioGarante3(null);}
		        		
		        		ayuda.setMontoGastoAdministrativo(gastosAdmin);
		        		
		        		if(montoSellado != null) {
			        			ayuda.setMontoSellado(montoSellado);	        			
		        		}
		        		else {
		        			ayuda.setMontoSellado(new BigDecimal("0.00"));
		        		}
		        		ayuda.setInteres(tasaMensual);	        		
		        		ayuda.setInteresAcu(interesAcumulado);
		        		ayuda.setInteresCuota(interesCuota);

		        		ayuda.setNovacion(novacion);
		        		ayuda.setAyudaSaldada("N");
		        		ayuda.setGarantia(Integer.parseInt(garantiaInfo.getNumeroGarantia()));
			        		
		        		ayuda.setMontoEfectivo(new BigDecimal(0.00));
		        		ayuda.setMontoTransferencia(new BigDecimal(0.00));
	      		        
	      		        ayuda.setBancoCheque1("");
	      		        ayuda.setBancoCheque2("");
	      		        ayuda.setBancoCheque3("");
	      		        
	      		        ayuda.setMontoCheque1(new BigDecimal(0.00));
	      		        ayuda.setMontoCheque2(new BigDecimal(0.00));
	      		        ayuda.setMontoCheque3(new BigDecimal(0.00));
		        		
		        		ayuda.setFechaLegales(null);
		        		ayuda.setFechaOrigLegales(null);
		        		ayuda.setMontoSelladoPcial(modulo);
		        		
		        
		        		if ("N".equals(novacion)) {
		        		    if(isMontosIgualNetoACob()) {
		        		    	
		        		    	BigDecimal montoEfectivo =  txtEfectivo.getText().trim().isEmpty() ? new BigDecimal(0.00) : new BigDecimal( txtEfectivo.getText().trim());
		        		    	ayuda.setMontoEfectivo(montoEfectivo);
		        		        
		        		    	BigDecimal montoTransferencia =  txtTransferecia.getText().trim().isEmpty() ? new BigDecimal(0.00) : new BigDecimal( txtTransferecia.getText().trim());
			        		    ayuda.setMontoTransferencia(montoTransferencia);
		        		        
		        		        Integer nroCheque1 =  txtNroMov.getText().trim().isEmpty() ? 0 :  Integer.parseInt(txtNroMov.getText().trim());	
		        		        ayuda.setNroCheque1(nroCheque1);
		        		        
		        		        Integer nroCheque2 =  txtNroMov2.getText().trim().isEmpty() ? 0 :  Integer.parseInt(txtNroMov2.getText().trim());	
		        		        ayuda.setNroCheque2(nroCheque2);
		        		        
		        		        Integer nroCheque3 =  txtNroMov3.getText().trim().isEmpty() ? 0 :  Integer.parseInt(txtNroMov3.getText().trim());	
		        		        ayuda.setNroCheque3(nroCheque3);
		        		        
		        		     
		        		        String bancoCheque1 =  txtDetalleMov.getText().trim().isEmpty() ? "" :  txtDetalleMov.getText().trim();	
		        		        ayuda.setBancoCheque1(bancoCheque1);
		        		       
		        		        String bancoCheque2 =  txtDetalleMov2.getText().trim().isEmpty() ? "" :  txtDetalleMov2.getText().trim();	    	        		       
		        		        ayuda.setBancoCheque2(bancoCheque2);
		        		       
		        		        String bancoCheque3 =  txtDetalleMov3.getText().trim().isEmpty() ? "" :  txtDetalleMov3.getText().trim();	        		   
		        		        ayuda.setBancoCheque3(bancoCheque3);
		        		        
		        		      
		        		        BigDecimal montoCheque1 =  txtImporteMov.getText().trim().isEmpty() ? new BigDecimal(0.00) : new BigDecimal( txtImporteMov.getText().trim());
		        		        ayuda.setMontoCheque1(montoCheque1);
		        		        
		        		        BigDecimal montoCheque2 =  txtImporteMov2.getText().trim().isEmpty() ? new BigDecimal(0.00) : new BigDecimal( txtImporteMov2.getText().trim());		        		    
		        		        ayuda.setMontoCheque2(montoCheque2);

		        		        BigDecimal montoCheque3 =  txtImporteMov3.getText().trim().isEmpty() ? new BigDecimal(0.00) : new BigDecimal( txtImporteMov3.getText().trim());
		        		        ayuda.setMontoCheque3(montoCheque3);
			        		      
		        		        calcularCuotas();
		        		        
		        		        cuotas.forEach(c -> {
		        		        	c.setNumeroAyuda(proximoNumeroAyuda);
		        		        	var cuotaGuardada = cuotasServicio.crearCuota(c);
		        		        	//System.out.println(cuotaGuardada);
		        		        });
		        		        
		        		        ayudaServicio.crearAyuda(ayuda);
		        		      
		        		        mostrarMensaje("Ayuda Creada");
		        		        ayudaCreada = true;
		        		        declaracionJurada();
		        		      
		        		 	    btnContinuar.setEnabled(false);
		        		 	    btnGenerarReporte.setEnabled(true);
		        		 	   btnGenerarReporte.setFocusable(true);
		        		 	    btnGenerarReporte.requestFocusInWindow();
		        		 	   
		        		    }else
		        		    {
		        		    	mostrarMensaje("no crea ayuda");
		        		    }
		        		} else if ("S".equals(novacion)) {
		        		    ayuda.setMontoEfectivo(new BigDecimal(0.00));
		        		    ayuda.setMontoTransferencia(new BigDecimal(0.00));
		        		    
		        		    ayuda.setNroCheque1(0);
	        		        ayuda.setNroCheque2(0);
	        		        ayuda.setNroCheque3(0);
	         		        
	        		        ayuda.setBancoCheque1("");
	        		        ayuda.setBancoCheque2("");
	        		        ayuda.setBancoCheque3("");
	        		        
	        		        ayuda.setMontoCheque1(new BigDecimal(0.00));
	        		        ayuda.setMontoCheque2(new BigDecimal(0.00));
	        		        ayuda.setMontoCheque3(new BigDecimal(0.00));
	        		        
	        		        cuotas.forEach(c -> {
	        		        	c.setNumeroAyuda(proximoNumeroAyuda);
	        		        	var cuotaGuardada = cuotasServicio.crearCuota(c);
	        		        //	System.out.println(cuotaGuardada);
	        		        });
	        		        
		        		    ayudaServicio.crearAyuda(ayuda);
		        	
	        		        mostrarMensaje("Ayuda Creada");
	        		        ayudaCreada = true;
	        		        declaracionJurada();
	        		      
	        		 	    btnContinuar.setEnabled(false);
	        		 	    btnGenerarReporte.setEnabled(true);
	        		 	    btnGenerarReporte.setFocusable(true);
	        		 	    btnGenerarReporte.requestFocusInWindow();
	        		 	   
		        		}
		        		
		        			/*System.out.println("IdAyudaPesos: " + ayuda.getIdAyudaPesos());
			        		System.out.println("NroAyuda: " + ayuda.getNroAyuda());
			        		System.out.println("NumeroSocio: " + ayuda.getNumeroSocio());
			        		System.out.println("FechaAltaAyuda: " + ayuda.getFechaAltaAyuda());
			        		System.out.println("MontoSolicitado: " + ayuda.getMontoSolicitado());
			        		System.out.println("CantidadCuotas: " + ayuda.getCantidadCuotas());
			        		System.out.println("DestinoAyuda: " + ayuda.getDestinoAyuda());
			        		System.out.println("NroSocioGarante: " + ayuda.getNroSocioGarante());
			        		System.out.println("NroSocioGarante2: " + ayuda.getNroSocioGarante2());
			        		System.out.println("NroSocioGarante3: " + ayuda.getNroSocioGarante3());
			        		System.out.println("MontoGastoAdministrativo: " + ayuda.getMontoGastoAdministrativo());
			        		System.out.println("MontoSellado: " + ayuda.getMontoSellado());
			        		System.out.println("Interes: " + ayuda.getInteres());
			        		System.out.println("InteresAcu: " + ayuda.getInteresAcu());
			        		System.out.println("InteresCuota: " + ayuda.getInteresCuota());
			        		System.out.println("Novacion: " + ayuda.getNovacion());
			        		System.out.println("MontoEfectivo: " + ayuda.getMontoEfectivo());
			        		System.out.println("MontoTransferencia: " + ayuda.getMontoTransferencia());
			        		System.out.println("BancoCheque1: " + ayuda.getBancoCheque1());
			        		System.out.println("BancoCheque2: " + ayuda.getBancoCheque2());
			        		System.out.println("BancoCheque3: " + ayuda.getBancoCheque3());
			        		System.out.println("NroCheque1: " + ayuda.getNroCheque1());
			        		System.out.println("NroCheque2: " + ayuda.getNroCheque2());
			        		System.out.println("NroCheque3: " + ayuda.getNroCheque3());
			        		System.out.println("MontoCheque1: " + ayuda.getMontoCheque1());
			        		System.out.println("MontoCheque2: " + ayuda.getMontoCheque2());
			        		System.out.println("MontoCheque3: " + ayuda.getMontoCheque3());
			        		System.out.println("AyudaSaldada: " + ayuda.getAyudaSaldada());
			        		System.out.println("Garantia: " + ayuda.getGarantia());
			        		System.out.println("FechaLegales: " + ayuda.getFechaLegales());
			        		System.out.println("FechaOrigLegales: " + ayuda.getFechaOrigLegales());
			        		System.out.println("MontoSelladoPcial: " + ayuda.getMontoSelladoPcial());
			        	*/
		        
		   
		}
		
		private void declaracionJurada() {
		    // Convertir Date a LocalDate para el socio y cada garante
			Date fechaVencimientoSocio = garantiaInfo.getFechaVencimientoSocio();
			LocalDate fechaVencimientoSocioLocal = Instant.ofEpochMilli(fechaVencimientoSocio.getTime())
			                             .atZone(ZoneId.systemDefault())
			                             .toLocalDate();
			Date fechaVencimientoSocioGarante1 = garantiaInfo.getFechaVencimientoGarante1();
			LocalDate fechaVencimientoSocioGarante1Local = Instant.ofEpochMilli(fechaVencimientoSocio.getTime())
			                             .atZone(ZoneId.systemDefault())
			                             .toLocalDate();
			Date fechaVencimientoSocioGarante2 = garantiaInfo.getFechaVencimientoGarante2();
			LocalDate fechaVencimientoSocioGarante2Local = Instant.ofEpochMilli(fechaVencimientoSocio.getTime())
			                             .atZone(ZoneId.systemDefault())
			                             .toLocalDate();
			Date fechaVencimientoSocioGarante3 = garantiaInfo.getFechaVencimientoGarante3();
			LocalDate fechaVencimientoSocioGarante3Local = Instant.ofEpochMilli(fechaVencimientoSocio.getTime())
			                             .atZone(ZoneId.systemDefault())
			                             .toLocalDate();
		   
		    Socio socio = 	 null;
	 	    Socio garante1 = null;
	 	    Socio garante2 = null;
	 	    Socio garante3 = null;
		    // Si la fecha de vencimiento es anterior a la fecha de hoy, se imprime la declaración jurada
		    if (fechaVencimientoSocioLocal.compareTo(fechaHoy) < 0) {
		    	if (garantiaInfo.getNumeroSocio() != null && !garantiaInfo.getNumeroSocio().isEmpty()) {
		    	    socio = socioServicio.buscarSocioPorNumeroSocio(
		    	        Integer.parseInt(garantiaInfo.getNumeroSocio())
		    	    );
		    	}

		    }
		    if (fechaVencimientoSocioGarante1Local.compareTo(fechaHoy) < 0) {
		    	if (garantiaInfo.getNumeroGarante1() != null && !garantiaInfo.getNumeroGarante1().isEmpty()) {
		            garante1 = socioServicio.buscarSocioPorNumeroSocio(
		            		Integer.parseInt(garantiaInfo.getNumeroGarante1()));
		    	}

		    }
		    if (fechaVencimientoSocioGarante2Local.compareTo(fechaHoy) < 0) {
		    	if (garantiaInfo.getNumeroGarante2() != null && !garantiaInfo.getNumeroGarante2().isEmpty()) {
		            garante2 = socioServicio.buscarSocioPorNumeroSocio(
		            		Integer.parseInt(garantiaInfo.getNumeroGarante2()));
		    	}
		    }
		    if (fechaVencimientoSocioGarante3Local.compareTo(fechaHoy) < 0) {
		    	if (garantiaInfo.getNumeroGarante3() != null && !garantiaInfo.getNumeroGarante3().isEmpty()) {
		            garante3 = socioServicio.buscarSocioPorNumeroSocio(
		            		Integer.parseInt(garantiaInfo.getNumeroGarante3()));
		    	}
		    }
		    
		    
		    if (socio != null || garante1 != null || garante2 != null || garante3 != null) {
	 	    	imprimirDeclaracion(socio, garante1, garante2, garante3);
	 	    }
		}

		/**
		 * Imprime la declaración jurada de un socio (titular o garante).
		 * Se imprime:
		 * - Apellido y Nombre.
		 * - PEP: Se evalúa el primer carácter del campo (si es 'S' se muestra "SI", de lo contrario "NO") y luego se imprime el valor completo.
		 * - Si tipper es "J": se imprime tipo de documento y CUIT.
		 * - Caso contrario: se imprime tipo de documento y número de documento.
		 */
		private void imprimirDeclaracion(Socio socio, Socio garante1, Socio garante2, Socio garante3) {
		 
			
		DDJJPepServicio ddjjPepServicio = new DDJJPepServicio();
	 		
	 		Integer nroSocio 	= null;
	 		Integer nroSocioG1 	= null;
	 		Integer nroSocioG2 	= null;
	 		Integer nroSocioG3 	= null;
	 		
	 		if (socio != null) {
	 			nroSocio = socio.getNumeroSocio();
	 		}
	 		
	 		if (garante1 != null) {
	 			nroSocioG1 = garante1.getNumeroSocio();
	 		}
	 		
	 		if (garante2 != null) {
	 			nroSocioG2 = garante2.getNumeroSocio();
	 		}
	 		
	 		if (garante3 != null) {
	 			nroSocioG3 = garante3.getNumeroSocio();
	 		}
	 		
	 		ddjjPepServicio.generarReporte(nroSocio, nroSocioG1, nroSocioG2, nroSocioG3);
	 	    
	 	
		}

		
		private synchronized Integer obtenerProximoNumeroAyuda() {
		    Optional<AyudaPesos> ultimaAyudaOpt = ayudaServicio.findTopByOrderByNumeroAyudaDesc();
		    if (ultimaAyudaOpt.isPresent() && ultimaAyudaOpt.get().getNroAyuda() != null) {
		        return ultimaAyudaOpt.get().getNroAyuda() + 1;
		    } else {
		        return 1; // Si no hay registros, iniciar con 1
		    }
		}
		
		private void mostrarMensaje(String mensaje){
	        JOptionPane.showMessageDialog(this,mensaje);
	    }
		
		public void limpiarDatos() {
		    // 1. Restablecer Campos de Texto
		    txtGarantia.setText("");
		    txtNumSocio.setText("");
		    txtNumGarante1.setText("");
		    txtApeNomSocio.setText("");
		    txtApeNomGarante1.setText("");
		    txtApeNomGarante2.setText("");
		    txtNumGarante2.setText("");
		    txtNumGarante3.setText("");
		    txtApeNomGarante3.setText("");
		    txtFechaOp.setText("");
		    txtMontoSolicitado.setText("");
		    txtTasaMensual.setText("");
		    txtGastosAdmin.setText("");
		    txtAnios.setText("");
		    txtDestinoAyuda.setText("");
		    txtCantidadCuotas.setText("");
		    txtMesesGracia.setText("");
		    txtMontoSellado.setText("");
		    txtNeto.setText("");
		    txtEfectivo.setText("");
		    txtTransferecia.setText("");
		    txtImporteMov.setText("");
		    txtImporteMov2.setText("");
		    txtImporteMov3.setText("");
		    txtNroMov.setText("");
		    txtNroMov2.setText("");
		    txtNroMov3.setText("");
		    txtDetalleMov.setText("");
		    txtDetalleMov2.setText("");
		    txtDetalleMov3.setText("");
		    txtSaldoAhorroComun.setText("");
		    txtIntCuo.setText("");
		    txtTotInt.setText("");
		    txtMontoCuota.setText("");
		    txtFechaCuota.setText("");
		    txtFechaSellado.setText("");
		    
		    // 2. Restablecer `JComboBox`
		    cbxNovacion.setSelectedIndex(0); // Asumiendo que el primer elemento es el predeterminado
		    
		    // 3. Desmarcar `JCheckBox`
		    cbxOpExenta.setSelected(false);
		     
			txtEfectivo.setEditable(false);
			txtTransferecia.setEditable(false);
			txtImporteMov.setEditable(false);
			txtImporteMov2.setEditable(false);
			txtImporteMov3.setEditable(false);
			txtDetalleMov.setEditable(false);
			txtDetalleMov2.setEditable(false);
			txtDetalleMov3.setEditable(false);
			txtNroMov.setEditable(false);
			txtNroMov2.setEditable(false);
			txtNroMov3.setEditable(false);
			
			txtEfectivo.setFocusable(false);
			txtTransferecia.setFocusable(false);
			txtImporteMov.setFocusable(false);
			txtImporteMov2.setFocusable(false);
			txtImporteMov3.setFocusable(false);
			txtDetalleMov.setFocusable(false);
			txtDetalleMov2.setFocusable(false);
			txtDetalleMov3.setFocusable(false);
			txtNroMov.setFocusable(false);
			txtNroMov2.setFocusable(false);
			txtNroMov3.setFocusable(false);
			
			
		    // 5. Restablecer Variables Internas
			garantiaInfo = null;
		    opExenta = false;
		    cuotas.clear();
		    ayuda = new AyudaPesos();
		    fechaHoy = LocalDate.now();
		    montoSolicitado = BigDecimal.ZERO;
		    tasaMensual = BigDecimal.ZERO;
		    gastosAdmin = BigDecimal.ZERO;
		    destinoAyuda = "";
		    cantidadCuotas = 1;
		    novacion = "N";
		    montoSellado = BigDecimal.ZERO;
		    interesCuota = BigDecimal.ZERO;
		    interesAcumulado = BigDecimal.ZERO;
		    montoCuota = BigDecimal.ZERO;
		    montoEfectivo = BigDecimal.ZERO;
		    montoTransferencia = BigDecimal.ZERO;
		    netoACobrar = BigDecimal.ZERO;
		    modulo = BigDecimal.ZERO;
		    ayudaCreada = false;
		    // Opcional: Restaurar estados de botones si han sido modificados
		    btnContinuar.setEnabled(true);

		    btnCrearOtraAyuda.setEnabled(false);
		    btnGenerarReporte.setEnabled(false);
		    
		    // Opcional: Restaurar otros estados de componentes según sea necesario
		    // Por ejemplo, deshabilitar métodos de pago si es requerido
		    habilitarMetodosPago(true);
		    
		    // Opcional: Actualizar otros componentes que dependen del estado del formulario
		    // Por ejemplo, recalcular fechas o montos si es necesario
		}
		
		public void generarReporte() {

			

		}
		private void generarReporteInterno() {
		    PagareAyudaPesosServicio pagareAyudaServicio = new PagareAyudaPesosServicio();

		    // Datos extras de Socio y Garantes
		    Socio socio = socioServicio.buscarSocioPorNumeroSocio(ayuda.getNumeroSocio());
		    Socio socioGarante = ayuda.getNroSocioGarante() == null ? null : socioServicio.buscarSocioPorNumeroSocio(ayuda.getNroSocioGarante());
		    Socio socioGarante2 = ayuda.getNroSocioGarante2() == null ? null : socioServicio.buscarSocioPorNumeroSocio(ayuda.getNroSocioGarante2());
		    Socio socioGarante3 = ayuda.getNroSocioGarante3() == null ? null : socioServicio.buscarSocioPorNumeroSocio(ayuda.getNroSocioGarante3());
		    
		    String domicilioGarante1 = (socioGarante != null && socioGarante.getDomicilio() != null) ? socioGarante.getDomicilio() : "";
		    String localidadGarante1 = (socioGarante != null && socioGarante.getLocalidad() != null) ? socioGarante.getLocalidad() : "";

		    String domicilioGarante2 = (socioGarante2 != null && socioGarante2.getDomicilio() != null) ? socioGarante2.getDomicilio() : "";
		    String localidadGarante2 = (socioGarante2 != null && socioGarante2.getLocalidad() != null) ? socioGarante2.getLocalidad() : "";

		    String domicilioGarante3 = (socioGarante3 != null && socioGarante3.getDomicilio() != null) ? socioGarante3.getDomicilio() : "";
		    String localidadGarante3 = (socioGarante3 != null && socioGarante3.getLocalidad() != null) ? socioGarante3.getLocalidad() : "";
		    
		    // Configuración de garantiaDto
		    garantiaInfo.setDireccionSocio(socio.getDomicilio());
		    garantiaInfo.setLocalidadSocio(socio.getLocalidad());
		    garantiaInfo.setDireccionGarante1(domicilioGarante1);
		    garantiaInfo.setLocalidadGarante1(localidadGarante1);
		    garantiaInfo.setDireccionGarante2(domicilioGarante2);
		    garantiaInfo.setLocalidadGarante2(localidadGarante2);
		    garantiaInfo.setDireccionGarante3(domicilioGarante3);
		    garantiaInfo.setLocalidadGarante3(localidadGarante3);

		    // Obtención de años
		    int zano;
		    try {
		        zano = Integer.parseInt(txtAnios.getText().trim());
		    } catch (NumberFormatException ex) {
		        // Manejar el error de formato numérico
		        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido de años.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    // Generación del reporte
		    pagareAyudaServicio.generarReporte(
		        ayuda,
		        montoCuota,
		        cuotas.get(0).getFechaVtoCuota(),
		        garantiaInfo,
		        opExenta,
		        zano,
		        calculosFechas.calcularFechaSellado(fechaHoy),
		        interesAcumulado
		    );
		}

		public void setAltaAyudaPesosGarantes(AltaAyudaPesosGarantes altaAyudaPesosGarantes) {
			this.altaAyudaPesosGarantes = altaAyudaPesosGarantes;
		}

		public void setAltaAyudaPesos(AltaAyudaPesos altaAyudaPesos) {
			this.altaAyudaPesos = altaAyudaPesos;
		}
}
