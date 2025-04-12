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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.componentes.PlaceholderTextField;
import com.mutual.SistemaAyudaCuotas.dto.CobroCuotasRangoFechaCuotasDto;
import com.mutual.SistemaAyudaCuotas.dto.CobroCuotasReporteDto;
import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.Cuota;
import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.Garantia;
import com.mutual.SistemaAyudaCuotas.entidades.Socio;
import com.mutual.SistemaAyudaCuotas.filtros.DecimalFilter;
import com.mutual.SistemaAyudaCuotas.filtros.IntegerFilter;
import com.mutual.SistemaAyudaCuotas.paneles.MenuAyudas;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos.AltaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.servicio.IAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ICobroCuotaAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ICobroServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ICuotaAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ICuotaServicio;
import com.mutual.SistemaAyudaCuotas.servicio.IGarantiaServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ISocioServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ReciboCobroCuotaAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ReciboCuotaSocietariaServicio;

@Component
public class AltaCobrosPesosCobrarVariasCuotas extends JPanel {

	private String panelAnterior = "AltaCobrosPesosDesdeHasta";

	//SERVICES
	private ICuotaAyudaPesosServicio cuotasServicio;
	private ICuotaServicio cuotasSocialesServicio;
	private ICobroServicio cobroServicio;
	private IGarantiaServicio garantiaServicio;
	private ISocioServicio socioServicio;
	private ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio;
	private IAyudaPesosServicio ayudaServicio;
	
	//DTOS
	private CobroCuotasReporteDto cobroCuotasReporteDto;
	private CobroCuotasRangoFechaCuotasDto cobroRangoFechasDto;
	
	//ATRIBUTOS
	private AyudaPesos ayudaPesos;
	private List<CuotaAyudaPesos> listaCuotasAdeudadas;
	
	//COMPONENTES
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
	private PlaceholderTextField txtImporteCuota;
	private PlaceholderTextField txtFecha;
	private PlaceholderTextField txtCargoAdic;
	private PlaceholderTextField txtInteresMensual;
	private PlaceholderTextField txtNetoACobrar;
	private PlaceholderTextField txtCantidadCuotas;
	private PlaceholderTextField txtHastaCuota;
	private PlaceholderTextField txtDesdeCuota;
	private PlaceholderTextField txtEfectivoCobro ;
	private PlaceholderTextField txtTransferenciaCobro;
	private PlaceholderTextField txtNroMov;
	private PlaceholderTextField txtDetalleMov;
	private PlaceholderTextField txtImporteMov;
	private PlaceholderTextField txtImporteMov2;
	private PlaceholderTextField txtDetalleMov2;
	private PlaceholderTextField txtNroMov2;
	private PlaceholderTextField txtNroMov3;
	private PlaceholderTextField txtDetalleMov3;
	private PlaceholderTextField txtImporteMov3;
	private PlaceholderTextField txtObservaciones;
	private JButton btnImprimirRecibo;
	private JLabel lblHonorariosAbogado;
	private PlaceholderTextField txtHonorariosAbogado;
	private JLabel lblDescPagoAdel; 
	private PlaceholderTextField txtDescPagoAdelantado;
	private JButton btnCobrar;
	
	public AltaCobrosPesosCobrarVariasCuotas( 
			ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio,
			ICuotaAyudaPesosServicio cuotasServicio, 
			ISocioServicio socioServicio, 
			IGarantiaServicio garantiaServicio,
			IAyudaPesosServicio ayudaServicio,
			ICuotaServicio cuotasSocialesServicio
			) {
		 
		this.ayudaPesos = new AyudaPesos();
		this.cobroRangoFechasDto = new CobroCuotasRangoFechaCuotasDto();
		this.cobroCuotasReporteDto = new CobroCuotasReporteDto();
		
		this.cuotasServicio = cuotasServicio;
		this.garantiaServicio = garantiaServicio;
		this.socioServicio = socioServicio;
		this.ayudaServicio = ayudaServicio;
		this.cuotasSocialesServicio = cuotasSocialesServicio;
		this.cobroCuotaAyudaPesosServicio = cobroCuotaAyudaPesosServicio;
		
//		initialize();
//		cargarDatosAyudaPesos();
//		iniciar(null,null,null);
    }
    
    public void iniciar(CardLayout cardLayout, JPanel cardPanel,JFrame mainFrame) {
    	
    	// Configurar key binding para la tecla Escape
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapeAction");
        this.getActionMap().put("escapeAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	limpiarDatos();
            	btnImprimirRecibo.setEnabled(false);
    	        btnCobrar.setEnabled(true);
              	cardLayout.show(cardPanel, panelAnterior);
            }
        });
    	
        addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentShown(ComponentEvent e) {
    	        e.getComponent().requestFocusInWindow();
    	       
    	        if(listaCuotasAdeudadas == null || listaCuotasAdeudadas.isEmpty()) {
             		
             		JOptionPane.showMessageDialog(contentPane, "No hay cuotas disponibles para cobrar.", "Información", JOptionPane.INFORMATION_MESSAGE);            		
             		cardLayout.show(cardPanel, panelAnterior);
             		return;
             	}
             	  cargarDatosAyudaPesos(); // Actualiza la interfaz con los nuevos datos
          
    	        calcularNetoACobrar();
 	    	   
	    		if(ayudaPesos.getFechaLegales() != null || ayudaPesos.getFechaOrigLegales() != null) {
	         		lblHonorariosAbogado.setVisible(true);
	         		txtHonorariosAbogado.setVisible(true);
	         		lblDescPagoAdel.setVisible(false);
	         		txtDescPagoAdelantado.setVisible(false);
	         	}else {
	         		lblHonorariosAbogado.setVisible(false);
	         		txtHonorariosAbogado.setVisible(false);
	         		lblDescPagoAdel.setVisible(true);
	         		txtDescPagoAdelantado.setVisible(true);
	         	}
    	    }
    	});
    
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
	lblSocio.setBounds(25, 66, 65, 23);
	mainPanel.add(lblSocio);
	
	txtNumeroSocio = new PlaceholderTextField("00000000.00");
	txtNumeroSocio.setFocusable(false);
	txtNumeroSocio.setPlaceholder("0000");
	txtNumeroSocio.setFont(new Font("72", Font.PLAIN, 17));
	txtNumeroSocio.setColumns(10);
	txtNumeroSocio.setBounds(99, 63, 76, 23);
	mainPanel.add(txtNumeroSocio);
	
	JLabel lblSocioGarante = new JLabel("Socio Garante:");
	lblSocioGarante.setFont(new Font("72", Font.PLAIN, 17));
	lblSocioGarante.setBounds(25, 96, 137, 23);
	mainPanel.add(lblSocioGarante);
	
	txtNumSocioGarante = new PlaceholderTextField("00000000.00");
	txtNumSocioGarante.setFocusable(false);
	txtNumSocioGarante.setPlaceholder("0000");
	txtNumSocioGarante.setFont(new Font("72", Font.PLAIN, 17));
	txtNumSocioGarante.setColumns(10);
	txtNumSocioGarante.setBounds(172, 94, 56, 23);
	mainPanel.add(txtNumSocioGarante);
	
	JLabel lblNumeroAyuda = new JLabel("Numero Ayuda:");
	lblNumeroAyuda.setFont(new Font("72", Font.PLAIN, 17));
	lblNumeroAyuda.setBounds(25, 196, 137, 23);
	mainPanel.add(lblNumeroAyuda);
	
	txtNumAyuda = new PlaceholderTextField("00000000.00");
	txtNumAyuda.setFocusable(false);
	txtNumAyuda.setPlaceholder("00000");
	txtNumAyuda.setFont(new Font("72", Font.PLAIN, 17));
	txtNumAyuda.setColumns(10);
	txtNumAyuda.setBounds(217, 196, 65, 23);
	mainPanel.add(txtNumAyuda);
	
	txtNombreSocio = new PlaceholderTextField("00000000.00");
	txtNombreSocio.setFocusable(false);
	txtNombreSocio.setPlaceholder("...");
	txtNombreSocio.setFont(new Font("72", Font.PLAIN, 17));
	txtNombreSocio.setColumns(10);
	txtNombreSocio.setBounds(186, 63, 302, 23);
	mainPanel.add(txtNombreSocio);
	
	JLabel lblSocioGarante1 = new JLabel("Segundo Garante");
	lblSocioGarante1.setFont(new Font("72", Font.PLAIN, 17));
	lblSocioGarante1.setBounds(25, 124, 137, 23);
	mainPanel.add(lblSocioGarante1);
	
	txtNumSocioGarante1 = new PlaceholderTextField("00000000.00");
	txtNumSocioGarante1.setFocusable(false);
	txtNumSocioGarante1.setPlaceholder("0000");
	txtNumSocioGarante1.setFont(new Font("72", Font.PLAIN, 17));
	txtNumSocioGarante1.setColumns(10);
	txtNumSocioGarante1.setBounds(172, 122, 56, 23);
	mainPanel.add(txtNumSocioGarante1);
	
	JLabel lblSocioGarante2 = new JLabel("TercerGarante");
	lblSocioGarante2.setFont(new Font("72", Font.PLAIN, 17));
	lblSocioGarante2.setBounds(25, 153, 137, 23);
	mainPanel.add(lblSocioGarante2);
	
	txtNumSocioGarante2 = new PlaceholderTextField("00000000.00");
	txtNumSocioGarante2.setFocusable(false);
	txtNumSocioGarante2.setPlaceholder("0000");
	txtNumSocioGarante2.setFont(new Font("72", Font.PLAIN, 17));
	txtNumSocioGarante2.setColumns(10);
	txtNumSocioGarante2.setBounds(172, 151, 56, 23);
	mainPanel.add(txtNumSocioGarante2);
	
	txtNombreSocioGarante = new PlaceholderTextField("00000000.00");
	txtNombreSocioGarante.setFocusable(false);
	txtNombreSocioGarante.setPlaceholder("...");
	txtNombreSocioGarante.setFont(new Font("72", Font.PLAIN, 17));
	txtNombreSocioGarante.setColumns(10);
	txtNombreSocioGarante.setBounds(238, 92, 285, 23);
	mainPanel.add(txtNombreSocioGarante);
	
	txtNombreSocioGarante1 = new PlaceholderTextField("00000000.00");
	txtNombreSocioGarante1.setFocusable(false);
	txtNombreSocioGarante1.setPlaceholder("...");
	txtNombreSocioGarante1.setFont(new Font("72", Font.PLAIN, 17));
	txtNombreSocioGarante1.setColumns(10);
	txtNombreSocioGarante1.setBounds(238, 120, 285, 23);
	mainPanel.add(txtNombreSocioGarante1);
	
	txtNombreSocioGarante2 = new PlaceholderTextField("00000000.00");
	txtNombreSocioGarante2.setFocusable(false);
	txtNombreSocioGarante2.setPlaceholder("...");
	txtNombreSocioGarante2.setFont(new Font("72", Font.PLAIN, 17));
	txtNombreSocioGarante2.setColumns(10);
	txtNombreSocioGarante2.setBounds(238, 150, 285, 23);
	mainPanel.add(txtNombreSocioGarante2);
	
	JLabel lblFechaEmision = new JLabel("Fecha Emisión:");
	lblFechaEmision.setFont(new Font("72", Font.PLAIN, 17));
	lblFechaEmision.setBounds(25, 224, 137, 23);
	mainPanel.add(lblFechaEmision);
	
	txtFechaEmision = new PlaceholderTextField("00000000.00");
	txtFechaEmision.setFocusable(false);
	txtFechaEmision.setPlaceholder("00/00/0000");
	txtFechaEmision.setFont(new Font("72", Font.PLAIN, 17));
	txtFechaEmision.setColumns(10);
	txtFechaEmision.setBounds(217, 224, 94, 23);
	mainPanel.add(txtFechaEmision);
	
	JLabel lblMontoSolicitado = new JLabel("Monto Solicitado:");
	lblMontoSolicitado.setFont(new Font("72", Font.PLAIN, 17));
	lblMontoSolicitado.setBounds(25, 252, 137, 23);
	mainPanel.add(lblMontoSolicitado);
	
	txtMontoSolicitado = new PlaceholderTextField("00000000.00");
	txtMontoSolicitado.setFocusable(false);
	txtMontoSolicitado.setFont(new Font("72", Font.PLAIN, 17));
	txtMontoSolicitado.setColumns(10);
	txtMontoSolicitado.setBounds(217, 252, 108, 23);
	mainPanel.add(txtMontoSolicitado);
	
	JLabel lblTasaInteres = new JLabel("Tasa de Interés:");
	lblTasaInteres.setFont(new Font("72", Font.PLAIN, 17));
	lblTasaInteres.setBounds(25, 280, 137, 23);
	mainPanel.add(lblTasaInteres);
	
	txtTasaInteres = new PlaceholderTextField("00000000.00");
	txtTasaInteres.setFocusable(false);
	txtTasaInteres.setPlaceholder("00.00");
	txtTasaInteres.setFont(new Font("72", Font.PLAIN, 17));
	txtTasaInteres.setColumns(10);
	txtTasaInteres.setBounds(217, 280, 94, 23);
	mainPanel.add(txtTasaInteres);
	
	JLabel lblGastosAdmin = new JLabel("Gtos. Administrativos:");
	lblGastosAdmin.setFont(new Font("72", Font.PLAIN, 17));
	lblGastosAdmin.setBounds(25, 308, 164, 23);
	mainPanel.add(lblGastosAdmin);
	
	txtGastosAdmin = new PlaceholderTextField("00000000.00");
	txtGastosAdmin.setFocusable(false);
	txtGastosAdmin.setFont(new Font("72", Font.PLAIN, 17));
	txtGastosAdmin.setColumns(10);
	txtGastosAdmin.setBounds(217, 308, 108, 23);
	mainPanel.add(txtGastosAdmin);
	
	JLabel lblGastosCobranza = new JLabel("Gastos Cobranza:");
	lblGastosCobranza.setFont(new Font("72", Font.PLAIN, 17));
	lblGastosCobranza.setBounds(25, 341, 137, 23);
	mainPanel.add(lblGastosCobranza);
	
	txtGastosCobranza = new PlaceholderTextField("00000000.00");
	txtGastosCobranza.setFocusable(false);
	txtGastosCobranza.setFont(new Font("72", Font.PLAIN, 17));
	txtGastosCobranza.setColumns(10);
	txtGastosCobranza.setBounds(217, 341, 108, 23);
	mainPanel.add(txtGastosCobranza);
	
	JLabel lblGarantia = new JLabel("Garantía:");
	lblGarantia.setFont(new Font("72", Font.PLAIN, 17));
	lblGarantia.setBounds(25, 379, 137, 23);
	mainPanel.add(lblGarantia);
	
	txtGarantia = new PlaceholderTextField("00000000.00");
	txtGarantia.setFocusable(false);
	txtGarantia.setPlaceholder("...");
	txtGarantia.setFont(new Font("72", Font.PLAIN, 17));
	txtGarantia.setColumns(10);
	txtGarantia.setBounds(119, 379, 287, 23);
	mainPanel.add(txtGarantia);
	
	txtDestino = new PlaceholderTextField("00000000.00");
	txtDestino.setFocusable(false);
	txtDestino.setPlaceholder("...");
	txtDestino.setFont(new Font("72", Font.PLAIN, 17));
	txtDestino.setColumns(10);
	txtDestino.setBounds(119, 406, 287, 23);
	mainPanel.add(txtDestino);
	
	JLabel lblDestino = new JLabel("Destino:");
	lblDestino.setFont(new Font("72", Font.PLAIN, 17));
	lblDestino.setBounds(25, 406, 137, 23);
	mainPanel.add(lblDestino);
	
	txtCantCuotas = new PlaceholderTextField("00000000.00");
	txtCantCuotas.setFocusable(false);
	txtCantCuotas.setPlaceholder("0");
	txtCantCuotas.setFont(new Font("72", Font.PLAIN, 17));
	txtCantCuotas.setColumns(10);
	txtCantCuotas.setBounds(217, 437, 39, 23);
	mainPanel.add(txtCantCuotas);
	
	JLabel lblCantCuotas = new JLabel("Cantidad de Cuotas:");
	lblCantCuotas.setFont(new Font("72", Font.PLAIN, 17));
	lblCantCuotas.setBounds(25, 437, 164, 23);
	mainPanel.add(lblCantCuotas);
	
	txtInteresCuota = new PlaceholderTextField("00000000.00");
	txtInteresCuota.setFocusable(false);
	txtInteresCuota.setFont(new Font("72", Font.PLAIN, 17));
	txtInteresCuota.setColumns(10);
	txtInteresCuota.setBounds(217, 468, 108, 23);
	mainPanel.add(txtInteresCuota);
	
	JLabel lblInteresCuota = new JLabel("Interés Cuota:");
	lblInteresCuota.setFont(new Font("72", Font.PLAIN, 17));
	lblInteresCuota.setBounds(25, 468, 137, 23);
	mainPanel.add(lblInteresCuota);
	
	txtMontoCuota = new PlaceholderTextField("00000000.00");
	txtMontoCuota.setFocusable(false);
	txtMontoCuota.setFont(new Font("72", Font.PLAIN, 17));
	txtMontoCuota.setColumns(10);
	txtMontoCuota.setBounds(217, 496, 108, 23);
	mainPanel.add(txtMontoCuota);
	
	JLabel lblMontoCuota = new JLabel("Monto Cuota:");
	lblMontoCuota.setFont(new Font("72", Font.PLAIN, 17));
	lblMontoCuota.setBounds(25, 496, 137, 23);
	mainPanel.add(lblMontoCuota);
	
	JLabel lblFecha = new JLabel("Fecha:");
	lblFecha.setFont(new Font("72", Font.PLAIN, 17));
	lblFecha.setBounds(641, 124, 137, 23);
	mainPanel.add(lblFecha);
	
	txtFecha = new PlaceholderTextField("00000000.00");
	txtFecha.setPlaceholder("00/00/0000");
	txtFecha.setFont(new Font("72", Font.PLAIN, 17));
	txtFecha.setFocusable(false);
	txtFecha.setColumns(10);
	txtFecha.setBounds(832, 121, 100, 23);
	mainPanel.add(txtFecha);
	
	txtImporteCuota = new PlaceholderTextField("00000000.00");
	txtImporteCuota.setPlaceholder("00000");
	txtImporteCuota.setFont(new Font("72", Font.PLAIN, 17));
	txtImporteCuota.setFocusable(false);
	txtImporteCuota.setColumns(10);
	txtImporteCuota.setBounds(832, 145, 100, 23);
	mainPanel.add(txtImporteCuota);
	
	JLabel lblImporteCuotas = new JLabel("Importe Cuota:");
	lblImporteCuotas.setFont(new Font("72", Font.PLAIN, 17));
	lblImporteCuotas.setBounds(641, 148, 137, 23);
	mainPanel.add(lblImporteCuotas);
	
	JLabel lblInteresMensual = new JLabel("Interés Mensual:");
	lblInteresMensual.setFont(new Font("72", Font.PLAIN, 17));
	lblInteresMensual.setBounds(641, 176, 137, 23);
	mainPanel.add(lblInteresMensual);
	
	txtInteresMensual = new PlaceholderTextField("00000000.00");
	txtInteresMensual.setPlaceholder("00000");
	txtInteresMensual.setFont(new Font("72", Font.PLAIN, 17));
	txtInteresMensual.setFocusable(false);
	txtInteresMensual.setColumns(10);
	txtInteresMensual.setBounds(832, 173, 100, 23);
	mainPanel.add(txtInteresMensual);
	
	txtCargoAdic = new PlaceholderTextField("00000000.00");
	txtCargoAdic.setPlaceholder("00.00");
	txtCargoAdic.setFont(new Font("72", Font.PLAIN, 17));
	txtCargoAdic.setColumns(10);
	txtCargoAdic.setBounds(832, 202, 100, 23);
	((AbstractDocument) txtCargoAdic.getDocument()).setDocumentFilter(new DecimalFilter());
	
	mainPanel.add(txtCargoAdic);
	
	JLabel lblCargoAdicionalPmora = new JLabel("Cargo Adicional p/Mora:");
	lblCargoAdicionalPmora.setFont(new Font("72", Font.PLAIN, 17));
	lblCargoAdicionalPmora.setBounds(641, 205, 181, 23);
	mainPanel.add(lblCargoAdicionalPmora);
	
	JLabel lblFormasDePago = new JLabel("Formas de Pago");
	lblFormasDePago.setFont(new Font("72", Font.PLAIN, 17));
	lblFormasDePago.setBounds(619, 264, 137, 23);
	mainPanel.add(lblFormasDePago);
	
	JLabel lblCargoAdicionalPmora_1 = new JLabel("1 - Efectivo");
	lblCargoAdicionalPmora_1.setFont(new Font("72", Font.PLAIN, 17));
	lblCargoAdicionalPmora_1.setBounds(641, 289, 181, 23);
	mainPanel.add(lblCargoAdicionalPmora_1);
	
	txtEfectivoCobro = new PlaceholderTextField("00000000.00");
	txtEfectivoCobro.setFont(new Font("72", Font.PLAIN, 17));
	txtEfectivoCobro.setColumns(10);
	txtEfectivoCobro.setBounds(832, 286, 162, 23);
	((AbstractDocument) txtEfectivoCobro.getDocument()).setDocumentFilter(new DecimalFilter());
	
	mainPanel.add(txtEfectivoCobro);
	
	txtTransferenciaCobro = new PlaceholderTextField("00000000.00");
	txtTransferenciaCobro.setFont(new Font("72", Font.PLAIN, 17));
	txtTransferenciaCobro.setColumns(10);
	txtTransferenciaCobro.setBounds(832, 314, 164, 23);
	((AbstractDocument) txtTransferenciaCobro.getDocument()).setDocumentFilter(new DecimalFilter());
	
	mainPanel.add(txtTransferenciaCobro);
	
	JLabel lblNumeroAyuda_5_1 = new JLabel("2 - Transferencia");
	lblNumeroAyuda_5_1.setFont(new Font("72", Font.PLAIN, 17));
	lblNumeroAyuda_5_1.setBounds(641, 317, 137, 23);
	mainPanel.add(lblNumeroAyuda_5_1);
	
	JLabel lblGastosAdmts_1_1 = new JLabel("3 - Movimientos");
	lblGastosAdmts_1_1.setFont(new Font("72", Font.PLAIN, 17));
	lblGastosAdmts_1_1.setBounds(634, 353, 144, 23);
	mainPanel.add(lblGastosAdmts_1_1);
	
	JLabel lblNumeroMov = new JLabel("Número");
	lblNumeroMov.setFont(new Font("72", Font.PLAIN, 17));
	lblNumeroMov.setBounds(476, 379, 144, 23);
	mainPanel.add(lblNumeroMov);
	
	JLabel lblGastosAdmts_1_1_2 = new JLabel("Detalles");
	lblGastosAdmts_1_1_2.setFont(new Font("72", Font.PLAIN, 17));
	lblGastosAdmts_1_1_2.setBounds(663, 378, 144, 23);
	mainPanel.add(lblGastosAdmts_1_1_2);
	
	JLabel lblGastosAdmts_1_1_3 = new JLabel("Importe");
	lblGastosAdmts_1_1_3.setFont(new Font("72", Font.PLAIN, 17));
	lblGastosAdmts_1_1_3.setBounds(834, 379, 144, 23);
	mainPanel.add(lblGastosAdmts_1_1_3);
	
	txtNroMov = new PlaceholderTextField("00000000.00");
	txtNroMov.setPlaceholder("0000");
	txtNroMov.setFont(new Font("72", Font.PLAIN, 17));
	txtNroMov.setColumns(10);
	txtNroMov.setBounds(476, 405, 160, 23);
	((AbstractDocument) txtNroMov.getDocument()).setDocumentFilter(new IntegerFilter());
	
	mainPanel.add(txtNroMov);
	
	txtDetalleMov = new PlaceholderTextField("00000000.00");
	txtDetalleMov.setPlaceholder("...");
	txtDetalleMov.setFont(new Font("72", Font.PLAIN, 17));
	txtDetalleMov.setColumns(10);
	txtDetalleMov.setBounds(663, 405, 160, 23);
	mainPanel.add(txtDetalleMov);
	
	txtImporteMov = new PlaceholderTextField("00000000.00");
	txtImporteMov.setFont(new Font("72", Font.PLAIN, 17));
	txtImporteMov.setColumns(10);
	txtImporteMov.setBounds(834, 404, 160, 23);
	((AbstractDocument) txtImporteMov.getDocument()).setDocumentFilter(new DecimalFilter());
	
	mainPanel.add(txtImporteMov);
	
	txtImporteMov2 = new PlaceholderTextField("00000000.00");
	txtImporteMov2.setFont(new Font("72", Font.PLAIN, 17));
	txtImporteMov2.setColumns(10);
	txtImporteMov2.setBounds(834, 432, 160, 23);
	((AbstractDocument) txtImporteMov2.getDocument()).setDocumentFilter(new DecimalFilter());
	
	mainPanel.add(txtImporteMov2);
	
	txtDetalleMov2 = new PlaceholderTextField("00000000.00");
	txtDetalleMov2.setPlaceholder("...");
	txtDetalleMov2.setFont(new Font("72", Font.PLAIN, 17));
	txtDetalleMov2.setColumns(10);
	txtDetalleMov2.setBounds(663, 434, 160, 23);
	mainPanel.add(txtDetalleMov2);
	
	txtNroMov2 = new PlaceholderTextField("00000000.00");
	txtNroMov2.setPlaceholder("0000");
	txtNroMov2.setFont(new Font("72", Font.PLAIN, 17));
	txtNroMov2.setColumns(10);
	txtNroMov2.setBounds(476, 433, 160, 23);
	((AbstractDocument) txtNroMov2.getDocument()).setDocumentFilter(new IntegerFilter());
	
	mainPanel.add(txtNroMov2);
	
	txtNroMov3 = new PlaceholderTextField("00000000.00");
	txtNroMov3.setPlaceholder("0000");
	txtNroMov3.setFont(new Font("72", Font.PLAIN, 17));
	txtNroMov3.setColumns(10);
	txtNroMov3.setBounds(476, 465, 160, 23);
	((AbstractDocument) txtNroMov3.getDocument()).setDocumentFilter(new IntegerFilter());
	
	mainPanel.add(txtNroMov3);
	
	txtDetalleMov3 = new PlaceholderTextField("00000000.00");
	txtDetalleMov3.setPlaceholder("...");
	txtDetalleMov3.setFont(new Font("72", Font.PLAIN, 17));
	txtDetalleMov3.setColumns(10);
	txtDetalleMov3.setBounds(664, 465, 160, 23);
	mainPanel.add(txtDetalleMov3);
	
	txtImporteMov3 = new PlaceholderTextField("00000000.00");
	txtImporteMov3.setFont(new Font("72", Font.PLAIN, 17));
	txtImporteMov3.setColumns(10);
	txtImporteMov3.setBounds(834, 463, 160, 23);
	((AbstractDocument) txtImporteMov3.getDocument()).setDocumentFilter(new DecimalFilter());
	
	mainPanel.add(txtImporteMov3);
	
	btnCobrar = new JButton("Terminar y Cobrar Cuota");
	btnCobrar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			cobrarCuotas();
		}
	});
	btnCobrar.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				cobrarCuotas();
			}
		}
	});
	btnCobrar.setFont(new Font("72", Font.BOLD, 17));
	btnCobrar.setBounds(531, 586, 269, 27);
	mainPanel.add(btnCobrar);
	
	JLabel lblObservaciones = new JLabel("Observaciones:");
	lblObservaciones.setFont(new Font("72", Font.PLAIN, 17));
	lblObservaciones.setBounds(25, 550, 137, 23);
	mainPanel.add(lblObservaciones);
	
	txtObservaciones = new PlaceholderTextField("00000000.00");
	txtObservaciones.setPlaceholder("...");
	txtObservaciones.setFont(new Font("72", Font.PLAIN, 17));
	txtObservaciones.setColumns(10);
	txtObservaciones.setBounds(185, 547, 175, 23);
	mainPanel.add(txtObservaciones);
	
	JLabel lblNetoACobrar = new JLabel("Neto a Cobrar:");
	lblNetoACobrar.setFont(new Font("72", Font.PLAIN, 17));
	lblNetoACobrar.setBounds(454, 537, 137, 23);
	mainPanel.add(lblNetoACobrar);
	
	txtNetoACobrar = new PlaceholderTextField("00000000.00");
	txtNetoACobrar.setFocusable(false);
	txtNetoACobrar.setPlaceholder("...");
	txtNetoACobrar.setFont(new Font("72", Font.PLAIN, 17));
	txtNetoACobrar.setColumns(10);
	txtNetoACobrar.setBounds(601, 534, 175, 23);
	mainPanel.add(txtNetoACobrar);
	
	JLabel lblNumeroAyuda_5_2 = new JLabel("Cantidad de cuotas a pagar:");
	lblNumeroAyuda_5_2.setFont(new Font("72", Font.PLAIN, 17));
	lblNumeroAyuda_5_2.setBounds(601, 35, 221, 23);
	mainPanel.add(lblNumeroAyuda_5_2);
	
	txtCantidadCuotas = new PlaceholderTextField("00000000.00");
	txtCantidadCuotas.setFocusable(false);
	txtCantidadCuotas.setPlaceholder("0");
	txtCantidadCuotas.setFont(new Font("72", Font.PLAIN, 17));
	txtCantidadCuotas.setColumns(10);
	txtCantidadCuotas.setBounds(830, 35, 39, 23);
	mainPanel.add(txtCantidadCuotas);
	
	JLabel lblNumeroAyuda_5_2_1 = new JLabel("Desde Cuota:");
	lblNumeroAyuda_5_2_1.setFont(new Font("72", Font.PLAIN, 17));
	lblNumeroAyuda_5_2_1.setBounds(601, 62, 221, 23);
	mainPanel.add(lblNumeroAyuda_5_2_1);
	
	txtDesdeCuota = new PlaceholderTextField("00000000.00");
	txtDesdeCuota.setFocusable(false);
	txtDesdeCuota.setPlaceholder("0");
	txtDesdeCuota.setFont(new Font("72", Font.PLAIN, 17));
	txtDesdeCuota.setColumns(10);
	txtDesdeCuota.setBounds(830, 62, 39, 23);
	mainPanel.add(txtDesdeCuota);
	
	JLabel lblNumeroAyuda_5_2_2 = new JLabel("Hasta Cuota:");
	lblNumeroAyuda_5_2_2.setFont(new Font("72", Font.PLAIN, 17));
	lblNumeroAyuda_5_2_2.setBounds(601, 87, 221, 23);
	mainPanel.add(lblNumeroAyuda_5_2_2);
	
	txtHastaCuota = new PlaceholderTextField("00000000.00");
	txtHastaCuota.setFocusable(false);
	txtHastaCuota.setPlaceholder("0");
	txtHastaCuota.setFont(new Font("72", Font.PLAIN, 17));
	txtHastaCuota.setColumns(10);
	txtHastaCuota.setBounds(830, 87, 39, 23);
	mainPanel.add(txtHastaCuota);

	btnImprimirRecibo = new JButton("Imprimir Recibo");
	btnImprimirRecibo.setFont(new Font("72", Font.BOLD, 17));
	btnImprimirRecibo.setBounds(810, 586, 164, 27);
	btnImprimirRecibo.setEnabled(false);
	
	mainPanel.add(btnImprimirRecibo);
	
	lblDescPagoAdel = new JLabel("Desc. Pago Adel:");
	lblDescPagoAdel.setFont(new Font("72", Font.PLAIN, 17));
	lblDescPagoAdel.setBounds(641, 239, 181, 23);
	mainPanel.add(lblDescPagoAdel);

	lblHonorariosAbogado = new JLabel("Honorarios abogado:");
	lblHonorariosAbogado.setFont(new Font("72", Font.PLAIN, 17));
	lblHonorariosAbogado.setBounds(641, 239, 181, 23);
	mainPanel.add(lblHonorariosAbogado);
	 
	txtHonorariosAbogado = new PlaceholderTextField("00000000.00");
	txtHonorariosAbogado.setPlaceholder("0.00");
	txtHonorariosAbogado.setFont(new Font("72", Font.PLAIN, 17));
	txtHonorariosAbogado.setColumns(10);
	txtHonorariosAbogado.setBounds(832, 233, 100, 23);
	mainPanel.add(txtHonorariosAbogado);
	    
	btnImprimirRecibo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			imprimirRecibo();
		}
	});
	
	txtDescPagoAdelantado = new PlaceholderTextField("00000000.00");
	txtDescPagoAdelantado.setPlaceholder("0.00");
	txtDescPagoAdelantado.setFont(new Font("72", Font.PLAIN, 17));
	txtDescPagoAdelantado.setColumns(10);
	txtDescPagoAdelantado.setBounds(832, 233, 100, 23);
	mainPanel.add(txtDescPagoAdelantado);
	
	txtDescPagoAdelantado.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				String texto  = txtDescPagoAdelantado.getText().trim().isEmpty() ? "0" : txtDescPagoAdelantado.getText().trim();
				
			}
		}
	});
	btnImprimirRecibo.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				imprimirRecibo();
			}
		}
	});
 
	DocumentListener netoACobrarListener = new DocumentListener() {
	    @Override
	    public void insertUpdate(DocumentEvent e) {
	        calcularNetoACobrar();
	    }

	    @Override
	    public void removeUpdate(DocumentEvent e) {
	        calcularNetoACobrar();
	    }

	    @Override
	    public void changedUpdate(DocumentEvent e) {
	        calcularNetoACobrar();
	    }
	};

	txtCargoAdic.getDocument().addDocumentListener(netoACobrarListener);
	txtDescPagoAdelantado.getDocument().addDocumentListener(netoACobrarListener);
	txtHonorariosAbogado.getDocument().addDocumentListener(netoACobrarListener);

    }

	//------------ATRIBUTOS-------------------------
	//ayuda
	public AyudaPesos getAyudaPesos() {
		return ayudaPesos;
	}
	
	public void setAyudaPesos(AyudaPesos ayudaPesos) {
	    if (ayudaPesos != null) {
	        this.ayudaPesos = ayudaPesos;
	    } else {
	        this.ayudaPesos = new AyudaPesos();
	    }
	}
	
	public List<CuotaAyudaPesos> getListaCuotasAdeudadas() {
		return listaCuotasAdeudadas;
	}
	
	//cuotas adeudadas
	public void setListaCuotasAdeudadas(List<CuotaAyudaPesos> listaCuotasAdeudadas) {
	    if (listaCuotasAdeudadas != null) {
	        this.listaCuotasAdeudadas = listaCuotasAdeudadas;
	    } else {
	        this.listaCuotasAdeudadas = new ArrayList<CuotaAyudaPesos>();
	    } 
	    
	}

	//rango de fechas
	public CobroCuotasRangoFechaCuotasDto getCobroRangoFechasDto() {
		return cobroRangoFechasDto;
	}
	
	public void setCobroRangoFechasDto(CobroCuotasRangoFechaCuotasDto cobroRangoFechasDto) {
	    if (cobroRangoFechasDto != null) {
	        this.cobroRangoFechasDto = cobroRangoFechasDto;
	    } else {
	        this.cobroRangoFechasDto = new CobroCuotasRangoFechaCuotasDto();
	    } 
	}
	
	// ventanaAnterior
//	public void setVentanaPadre(JFrame ventanaPadre) {
//	    this.ventanaPadre = ventanaPadre;
//	}

	//-------------METODOS----------------------
	private void cobrarCuotas() {
	    try {
	        // Obtener el rango de cuotas desde el DTO
	        int desde = cobroRangoFechasDto.getDesde();
	        int hasta = cobroRangoFechasDto.getHasta();
	        Integer nroAyuda = ayudaPesos.getNroAyuda();
	        BigDecimal interesCuota = ayudaPesos.getInteresCuota() == null ? new BigDecimal("0.00") : ayudaPesos.getInteresCuota();
	        if(nroAyuda == null) {
	            JOptionPane.showMessageDialog(this, "No hay una ayuda seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        // Obtener todas las cuotas asociadas a la ayuda
	        List<CuotaAyudaPesos> cuotas = cuotasServicio.buscarCuotasPorNumeroAyuda(nroAyuda);
	        if(cuotas == null || cuotas.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "No hay cuotas disponibles para esta ayuda.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	   
	        
	        // Filtrar las cuotas dentro del rango y que no han sido cobradas
	        List<CuotaAyudaPesos> cuotasACobrar = new ArrayList<>();

	        BigDecimal totalImporteCuotas = BigDecimal.ZERO;
	        BigDecimal totalIntereses = BigDecimal.ZERO;
	        BigDecimal totalDescuentos = BigDecimal.ZERO;
	        BigDecimal totalCargosAdicionales = BigDecimal.ZERO;
	        BigDecimal totalHonorariosAbog = BigDecimal.ZERO;
	        BigDecimal montoCuotaCobro = BigDecimal.ZERO;
	        
	        
	        // Obtener cargos adicionales y descuentos de los campos de texto
            BigDecimal cargoAdic = new BigDecimal(txtCargoAdic.getText().isEmpty() ? "0.00" : txtCargoAdic.getText().replace(",", "."));
            BigDecimal descuento = new BigDecimal(txtDescPagoAdelantado.getText().isEmpty() ? "0.00" : txtDescPagoAdelantado.getText().replace(",", "."));
            BigDecimal honorariosAbog = new BigDecimal(txtHonorariosAbogado.getText().isEmpty() ? "0.00" : txtHonorariosAbogado.getText().replace(",", "."));

            
            for(CuotaAyudaPesos cuota : cuotas) {
	            int numeroCuota = cuota.getNumeroCuota();
	            if(numeroCuota >= desde && numeroCuota <= hasta) {
	                boolean yaCobrada = cobroCuotaAyudaPesosServicio.existeCobro(nroAyuda, numeroCuota);
	                if(!yaCobrada) {
	                    cuotasACobrar.add(cuota);
	                    totalImporteCuotas = totalImporteCuotas.add(cuota.getMontoCuota());
	                    
	                    // Asumimos que cada cuota tiene un campo de interés; ajustar según tu modelo
	                     totalIntereses = totalIntereses.add(interesCuota);
	                    
	                  
	                }
	            }
	        }
            totalHonorariosAbog = totalHonorariosAbog.add(honorariosAbog);
            totalCargosAdicionales = totalCargosAdicionales.add(cargoAdic);
            totalDescuentos = totalDescuentos.add(descuento);
	        if(cuotasACobrar.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "No hay cuotas disponibles en el rango seleccionado para cobrar.", "Información", JOptionPane.INFORMATION_MESSAGE);
	            return;
	        }
	        
	        // Calcular el Neto a Cobrar
	        BigDecimal netoACobrar = totalImporteCuotas.add(totalCargosAdicionales).add(totalHonorariosAbog).subtract(totalDescuentos);
	        
	        if(!isMontosIgualNetoACob(netoACobrar)) {
	        	return;
	        }
	        
	        // Mostrar el Neto a Cobrar al usuario para confirmación
	        int confirm = JOptionPane.showConfirmDialog(this, 
	            "Total Importe Cuotas: " + totalImporteCuotas + "\n" +
	            "Total Intereses: " + totalIntereses + "\n" +
	            "Total Cargos Adicionales: " + totalCargosAdicionales + "\n" +
	            "Total Descuentos: " + totalDescuentos + "\n" +
	            "Neto a Cobrar: " + netoACobrar + "\n\n" +
	            "¿Desea proceder con el cobro?", 
	            "Confirmar Cobro", JOptionPane.YES_NO_OPTION);
	        
	        if(confirm != JOptionPane.YES_OPTION) {
	            return; // El usuario canceló la operación
	        }
	    
	        // Realizar el cobro de cada cuota en el rango
	        for(CuotaAyudaPesos cuota : cuotasACobrar) {
	            CobroCuotaAyudaPesos cobro = new CobroCuotaAyudaPesos();
	            cobro.setNumeroAyuda(nroAyuda);
	            cobro.setNumeroCuota(cuota.getNumeroCuota());
	            cobro.setFechaPagoCuota(new Date());
	            cobro.setMontoCuota(cuota.getMontoCuota());
	            cobro.setCargos(cargoAdic); // Asigna el cargo adicional
	            cobro.setDescuento(descuento); // Asigna el descuento
	            cobro.setMontoEfectivo(new BigDecimal(txtEfectivoCobro.getText().isEmpty() ? "0.00" : txtEfectivoCobro.getText().trim().replace(",", ".")));
	            cobro.setMontoTransferencia(new BigDecimal(txtTransferenciaCobro.getText().isEmpty() ? "0.00" : txtTransferenciaCobro.getText().trim().replace(",", ".")));
	            cobro.setMontoCheque1(new BigDecimal(txtImporteMov.getText().isEmpty() ? "0.00" : txtImporteMov.getText().replace(",", ".")));
	            cobro.setMontoCheque2(new BigDecimal(txtImporteMov2.getText().isEmpty() ? "0.00" : txtImporteMov2.getText().replace(",", ".")));
	            cobro.setMontoCheque3(new BigDecimal(txtImporteMov3.getText().isEmpty() ? "0.00" : txtImporteMov3.getText().replace(",", ".")));
	            cobro.setBancoCheque1(txtDetalleMov.getText());
	            cobro.setBancoCheque2(txtDetalleMov2.getText());
	            cobro.setBancoCheque3(txtDetalleMov3.getText());
	            cobro.setNroCheque1(txtNroMov.getText().isEmpty() ? 0 : safeParseInt(txtNroMov.getText()));
	            cobro.setNroCheque2(txtNroMov2.getText().isEmpty() ? 0 : safeParseInt(txtNroMov2.getText()));
	            cobro.setNroCheque3(txtNroMov3.getText().isEmpty() ? 0 : safeParseInt(txtNroMov3.getText()));
	            montoCuotaCobro = cuota.getMontoCuota(); // Son todos iguales
	            // Guardar el cobro en la base de datos
	            cobroCuotaAyudaPesosServicio.crearCobroCuotaAyudaPesos(cobro);
	        }
	      
	        List<CuotaAyudaPesos> cuotasAunNoCobradas = new ArrayList<>();
	        
	        for(CuotaAyudaPesos cuota : cuotas) {
	            int numeroCuota = cuota.getNumeroCuota();
	                boolean yaCobrada = cobroCuotaAyudaPesosServicio.existeCobro(nroAyuda, numeroCuota);
	                if(!yaCobrada) {
	                	cuotasAunNoCobradas.add(cuota);
	                }
	                
	            }
	        
	   
	        Integer nroSocio = ayudaPesos.getNumeroSocio();
	        String nombreSocio = this.txtNombreSocio.getText();
	        String nombreGarante1 = this.txtNombreSocioGarante.getText();
	        String nombreGarante2 = this.txtNombreSocioGarante1.getText();
	        String nombreGarante3 = this.txtNombreSocioGarante2.getText();
	        
	        Integer cantidadCuotas = Integer.parseInt(this.txtCantCuotas.getText());
	        
	        String novacion = ayudaPesos.getNovacion();
	        
	        cobroCuotasReporteDto = new CobroCuotasReporteDto();
	        
	        cobroCuotasReporteDto.setLegales(ayudaPesos.getFechaLegales());
	        cobroCuotasReporteDto.setCantidadCuotas(cantidadCuotas);
	        cobroCuotasReporteDto.setNroAyuda(nroAyuda);
	        cobroCuotasReporteDto.setNroSocio(nroSocio);
	        cobroCuotasReporteDto.setNombreSocio(nombreSocio);
	        cobroCuotasReporteDto.setNombreGarante1(nombreGarante1);
	        cobroCuotasReporteDto.setNombreGarante2(nombreGarante2);
	        cobroCuotasReporteDto.setNombreGarante3(nombreGarante3);
	        cobroCuotasReporteDto.setImporteCuotaAmortizacion(totalImporteCuotas);
	        cobroCuotasReporteDto.setImporteMontoCuota(montoCuotaCobro);
	        
	        cobroCuotasReporteDto.setFechaTasasServiciosAl(cuotasACobrar.get(cuotasACobrar.size() - 1).getFechaVtoCuota());
	        cobroCuotasReporteDto.setImporteTasasServiciosAl(totalIntereses);
	        cobroCuotasReporteDto.setImporteCargosAdicionales(totalCargosAdicionales);
	      
	     // m.descto * cancuo
	        if (ayudaPesos.getFechaLegales() != null) {
	        	if (totalDescuentos.compareTo(BigDecimal.ZERO) < 0) {
	        		cobroCuotasReporteDto.setImporteHonorariosDescPagoAdel(totalDescuentos.abs().multiply(new BigDecimal(cantidadCuotas)));
	        	} else {
	        		cobroCuotasReporteDto.setImporteHonorariosDescPagoAdel(totalDescuentos.multiply(new BigDecimal(cantidadCuotas)));
	        	}
	        } else {
	        	cobroCuotasReporteDto.setImporteHonorariosDescPagoAdel(totalDescuentos.multiply(new BigDecimal(cantidadCuotas)));
	        }
	        
	        cobroCuotasReporteDto.setCuotas(cuotasAunNoCobradas.size());
	        cobroCuotasReporteDto.setNovacion(novacion);
	      
	        cobroCuotasReporteDto.setEfectivo(new BigDecimal(txtEfectivoCobro.getText().isEmpty() ? "0.00" : txtEfectivoCobro.getText().trim().replace(",", ".")));
	        cobroCuotasReporteDto.setTransferencia(new BigDecimal(txtTransferenciaCobro.getText().isEmpty() ? "0.00" : txtTransferenciaCobro.getText().trim().replace(",", ".")));
	       
	        BigDecimal montoCheque1 = new BigDecimal(txtImporteMov.getText().isEmpty() ? "0.00" : txtImporteMov.getText().trim().replace(",", "."));
	        BigDecimal montoCheque2 = new BigDecimal(txtImporteMov2.getText().isEmpty() ? "0.00" : txtImporteMov2.getText().trim().replace(",", "."));
	        BigDecimal montoCheque3 = new BigDecimal(txtImporteMov3.getText().isEmpty() ? "0.00" : txtImporteMov3.getText().trim().replace(",", "."));
	        
	        BigDecimal totalCheques = montoCheque1.add(montoCheque2).add(montoCheque3);
	        
	        int nroCheque1 = txtNroMov.getText().isEmpty() ? 0 : Integer.parseInt(txtNroMov.getText());
	        int nroCheque2 = txtNroMov2.getText().isEmpty() ? 0 : Integer.parseInt(txtNroMov2.getText());
	        int nroCheque3 = txtNroMov3.getText().isEmpty() ? 0 : Integer.parseInt(txtNroMov3.getText());

	        cobroCuotasReporteDto.setTotalCheques(totalCheques);
	        
	        cobroCuotasReporteDto.setNroCheque1(nroCheque1);
	        cobroCuotasReporteDto.setBanco1(txtDetalleMov.getText());
	        cobroCuotasReporteDto.setCheque1(montoCheque1);
	        
	        cobroCuotasReporteDto.setNroCheque2(nroCheque2);
	        cobroCuotasReporteDto.setBanco2(txtDetalleMov2.getText());
	        cobroCuotasReporteDto.setCheque2(montoCheque2);
	        
	        cobroCuotasReporteDto.setNroCheque3(nroCheque3);
	        cobroCuotasReporteDto.setBanco3(txtDetalleMov3.getText());
	        cobroCuotasReporteDto.setCheque3(montoCheque3);
	        
	        cobroCuotasReporteDto.setCuotaDesde(cobroRangoFechasDto.getDesde()); //Integer.parseInt(this.txtDesdeCuota.getText())
	        cobroCuotasReporteDto.setCuotaHasta(cobroRangoFechasDto.getHasta()); //Integer.parseInt(this.txtHastaCuota.getText())
	       
 // Después de configurar todos los atributos en cobroCuotasReporteDto...

	      /*  System.out.println("=== Detalles de CobroCuotasReporteDto ===");
	        System.out.println("Legales: " + cobroCuotasReporteDto.getLegales());
	        System.out.println("Cantidad Cuotas: " + cobroCuotasReporteDto.getCantidadCuotas());
	        System.out.println("Nro Ayuda: " + cobroCuotasReporteDto.getNroAyuda());
	        System.out.println("Nro Socio: " + cobroCuotasReporteDto.getNroSocio());
	        System.out.println("Nombre Socio: " + cobroCuotasReporteDto.getNombreSocio());
	        System.out.println("Nombre Garante 1: " + cobroCuotasReporteDto.getNombreGarante1());
	        System.out.println("Nombre Garante 2: " + cobroCuotasReporteDto.getNombreGarante2());
	        System.out.println("Nombre Garante 3: " + cobroCuotasReporteDto.getNombreGarante3());
	        System.out.println("Importe Cuota Amortizacion: " + cobroCuotasReporteDto.getImporteCuotaAmortizacion());
	        System.out.println("Fecha Tasas Servicios Al: " + cobroCuotasReporteDto.getFechaTasasServiciosAl());
	        System.out.println("Importe Tasas Servicios Al: " + cobroCuotasReporteDto.getImporteTasasServiciosAl());
	        System.out.println("Importe Cargos Adicionales: " + cobroCuotasReporteDto.getImporteCargosAdicionales());
	        System.out.println("Importe Honorarios Desc Pago Adel: " + cobroCuotasReporteDto.getImporteHonorariosDescPagoAdel());
	        System.out.println("Cuotas: " + cobroCuotasReporteDto.getCuotas());
	        System.out.println("Novacion: " + cobroCuotasReporteDto.getNovacion());
	        System.out.println("Efectivo: " + cobroCuotasReporteDto.getEfectivo());
	        System.out.println("Transferencia: " + cobroCuotasReporteDto.getTransferencia());
	        System.out.println("Total Cheques: " + cobroCuotasReporteDto.getTotalCheques());
	        System.out.println("Nro Cheque 1: " + cobroCuotasReporteDto.getNroCheque1());
	        System.out.println("Banco 1: " + cobroCuotasReporteDto.getBanco1());
	        System.out.println("Cheque 1: " + cobroCuotasReporteDto.getCheque1());
	        System.out.println("Nro Cheque 2: " + cobroCuotasReporteDto.getNroCheque2());
	        System.out.println("Banco 2: " + cobroCuotasReporteDto.getBanco2());
	        System.out.println("Cheque 2: " + cobroCuotasReporteDto.getCheque2());
	        System.out.println("Nro Cheque 3: " + cobroCuotasReporteDto.getNroCheque3());
	        System.out.println("Banco 3: " + cobroCuotasReporteDto.getBanco3());
	        System.out.println("Cheque 3: " + cobroCuotasReporteDto.getCheque3());
	        System.out.println("Cuota Desde: " + cobroCuotasReporteDto.getCuotaDesde());
	        System.out.println("Cuota Hasta: " + cobroCuotasReporteDto.getCuotaHasta());
*/
	        
	        // Actualizar el campo Neto a Cobrar en la interfaz
	        this.txtNetoACobrar.setText(netoACobrar.toString());
	        
	        JOptionPane.showMessageDialog(this, "Cuotas cobradas exitosamente.\nTotal Cuotas: " + cuotasACobrar.size(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
	       List<CuotaAyudaPesos> cuotasPendientes = 
	    		   cuotasServicio.buscarCuotasPendientesPorNumeroAyuda(ayudaPesos.getNroAyuda());
            
	        if(cuotasPendientes.size() == 0 || cuotasPendientes == null) {
		        JOptionPane.showMessageDialog(this, "Esta ayuda a sido saldada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		        ayudaPesos.setAyudaSaldada("S");
		        ayudaServicio.actualizarAyuda(ayudaPesos);
	        }
	        
	        btnImprimirRecibo.setEnabled(true);
	        btnImprimirRecibo.setFocusable(true);
	        btnImprimirRecibo.requestFocusInWindow();
	        btnCobrar.setEnabled(false);
	        BigDecimal montoCuotasSociales = cobrarCuotasSociales();
	  
	        
	        // Opcional: Cerrar la ventana o limpiar campos
	      //  dispose();
	        
	    } catch (NumberFormatException nfe) {
	    	System.out.println("cobrarCuotas error");
	    	nfe.printStackTrace();
	    	JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos para los campos de cobro.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Ocurrió un error al realizar el cobro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private int safeParseInt(String text) {
	    if (text == null || text.trim().isEmpty()) {
	        return 0;
	    }
	    try {
	        String normalized = text.trim().replace(",", ""); // Remove commas
	        return Integer.parseInt(normalized);
	    } catch (NumberFormatException ex) {
	        throw new NumberFormatException("Invalid integer format: " + text);
	    }
	}

	
     private void imprimirRecibo() {
		
		ReciboCobroCuotaAyudaPesosServicio reporteReciboAyuda = new ReciboCobroCuotaAyudaPesosServicio();
		reporteReciboAyuda.generarReporte(cobroCuotasReporteDto);
		
	}
  


	private BigDecimal cobrarCuotasSociales() {
	  try {
		  // Buscar las cuotas adeudadas del socio y de cada garante
		    List<Cuota> cuotasAdeudadas = cuotasSocialesServicio.buscarCuotasPorNumeroSocioHastaMesActual(ayudaPesos.getNumeroSocio());
		    List<Cuota> cuotasAdeudadasGarante1 = cuotasSocialesServicio.buscarCuotasPorNumeroSocioHastaMesActual(ayudaPesos.getNroSocioGarante());
		    List<Cuota> cuotasAdeudadasGarante2 = cuotasSocialesServicio.buscarCuotasPorNumeroSocioHastaMesActual(ayudaPesos.getNroSocioGarante2());
		    List<Cuota> cuotasAdeudadasGarante3 = cuotasSocialesServicio.buscarCuotasPorNumeroSocioHastaMesActual(ayudaPesos.getNroSocioGarante3());

		    // Si no hay cuotas para nadie, no se procede
		    if ((cuotasAdeudadas == null || cuotasAdeudadas.isEmpty()) &&
		        (cuotasAdeudadasGarante1 == null || cuotasAdeudadasGarante1.isEmpty()) &&
		        (cuotasAdeudadasGarante2 == null || cuotasAdeudadasGarante2.isEmpty()) &&
		        (cuotasAdeudadasGarante3 == null || cuotasAdeudadasGarante3.isEmpty())) {
		        return null;
		    }else {
		    	if(!(cuotasAdeudadas == null || cuotasAdeudadas.isEmpty())) {
		    		 JOptionPane.showMessageDialog(this, "Se encontraron " + cuotasAdeudadas.size() +" cuotas sociales adeudadas del socio.");
		    	}if(!(cuotasAdeudadasGarante1 == null || cuotasAdeudadasGarante1.isEmpty())) {
		    		 JOptionPane.showMessageDialog(this, "Se encontraron " + cuotasAdeudadasGarante1.size() +" cuotas sociales adeudadas del primer garante.");
		    	}if(!(cuotasAdeudadasGarante2 == null || cuotasAdeudadasGarante2.isEmpty())) {
		    		 JOptionPane.showMessageDialog(this, "Se encontraron " + cuotasAdeudadasGarante2.size() +" cuotas sociales adeudadas del segundo garante.");
		    	}if(!(cuotasAdeudadasGarante3 == null || cuotasAdeudadasGarante3.isEmpty())) {
		    		 JOptionPane.showMessageDialog(this, "Se encontraron " + cuotasAdeudadasGarante3.size() +" cuotas sociales adeudadas del tercer garante.");
		    	}
		    }

		   

		    BigDecimal totalMonto = BigDecimal.ZERO;
		    StringBuilder contenidoArchivo = new StringBuilder();

		    // Obtener la fecha actual para el cobro
		    Date fechaCobro = new Date();
		 
		    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		    String fechaStr = sdf.format(fechaCobro);
		    // Procesar cuotas del socio principal
		    totalMonto = procesarCuotas(cuotasAdeudadas, totalMonto, contenidoArchivo, fechaCobro, "Socio");

		    // Procesar cuotas de cada garante (puedes indicar en el formato que es garante)
		    totalMonto = procesarCuotas(cuotasAdeudadasGarante1, totalMonto, contenidoArchivo, fechaCobro, "Garante1");
		    totalMonto = procesarCuotas(cuotasAdeudadasGarante2, totalMonto, contenidoArchivo, fechaCobro, "Garante2");
		    totalMonto = procesarCuotas(cuotasAdeudadasGarante3, totalMonto, contenidoArchivo, fechaCobro, "Garante3");

		    
		    
		  //Generamos el recibo de Cobrar cuotas Societarias
		    ReciboCuotaSocietariaServicio reporteReciboCuotaSocietaria = new ReciboCuotaSocietariaServicio();
		    
		    //Preparamos los datos
		    Integer nroSocio 		= null;
			Integer nroSocioG1 		= null;
			Integer nroSocioG2 		= null;
			Integer nroSocioG3 		= null;
			String nombreSocio 		= null;
			String nombreSocioG1 	= null;
			String nombreSocioG2 	= null;
			String nombreSocioG3 	= null;
			String periodoDesde 	= null;
			String periodoHasta 	= null;
			String periodoDesdeG1 	= null;
			String periodoHastaG1 	= null;
			String periodoDesdeG2 	= null;
			String periodoHastaG2 	= null;
			String periodoDesdeG3 	= null;
			String periodoHastaG3 	= null;
			BigDecimal deudaSocio	= null;
			BigDecimal deudaSocioG1 = null;
			BigDecimal deudaSocioG2 = null;
			BigDecimal deudaSocioG3 = null;
			BigDecimal totalACobrar = totalMonto;
			
			// Obtenemos el anio y mes actual a partir de la fecha de cobro
		    Calendar calActual = Calendar.getInstance();
		    calActual.setTime(fechaCobro);
		    int anioActual = calActual.get(Calendar.YEAR);
		    int mesActual = calActual.get(Calendar.MONTH) + 1;
		    	    
		    if(!(cuotasAdeudadas == null || cuotasAdeudadas.isEmpty())) {
		    	
		    	nroSocio = cuotasAdeudadas.getFirst().getNumeroSocio();
		    	
		    	Socio socio = socioServicio.buscarSocioPorNumeroSocio(nroSocio);
		    	nombreSocio = socio.getApellidoNombre();
		    	
		    	periodoDesde = cuotasAdeudadas.getFirst().getMes() + "/" + cuotasAdeudadas.getFirst().getAno();
				periodoHasta = cuotasAdeudadas.getLast().getMes() + "/" +cuotasAdeudadas.getLast().getAno();
				
				BigDecimal montoTotalAdeudadoSocio = BigDecimal.ZERO;
				for (Cuota cuota : cuotasAdeudadas) {
		            // Solo sumamos la cuota si es del anio actual y su mes es menor o igual al mes actual
		            if ((cuota.getAno() < anioActual) || (cuota.getAno() == anioActual && cuota.getMes() <= mesActual)) {
		            	montoTotalAdeudadoSocio = montoTotalAdeudadoSocio.add(cuota.getMonto());
		                cuota.getMonto();
		                
		            }
		        }
				
				deudaSocio = montoTotalAdeudadoSocio;
		    	
		    }
		    
		    if(!(cuotasAdeudadasGarante1 == null || cuotasAdeudadasGarante1.isEmpty())) {
		    	
		    	nroSocioG1 	= cuotasAdeudadasGarante1.getFirst().getNumeroSocio();
		    	
		    	Socio socioG1 = socioServicio.buscarSocioPorNumeroSocio(nroSocioG1);
		    	nombreSocioG1 = socioG1.getApellidoNombre();
		    	
		    	periodoDesdeG1 = cuotasAdeudadasGarante1.getFirst().getMes() + "/" + cuotasAdeudadasGarante1.getFirst().getAno();
				periodoHastaG1 = cuotasAdeudadasGarante1.getLast().getMes() + "/" +cuotasAdeudadasGarante1.getLast().getAno();
				
				BigDecimal montoTotalAdeudadoSocio = BigDecimal.ZERO;
				for (Cuota cuota : cuotasAdeudadasGarante1) {
		            // Solo sumamos la cuota si es del anio actual y su mes es menor o igual al mes actual
		            if ((cuota.getAno() < anioActual) || (cuota.getAno() == anioActual && cuota.getMes() <= mesActual)) {
		            	montoTotalAdeudadoSocio = montoTotalAdeudadoSocio.add(cuota.getMonto());
		                cuota.getMonto();
		                
		            }
		        }
				
				deudaSocioG1 = montoTotalAdeudadoSocio;
		    	
		    }
		    
		    if(!(cuotasAdeudadasGarante2 == null || cuotasAdeudadasGarante2.isEmpty())) {
		    	
		    	nroSocioG2 	= cuotasAdeudadasGarante2.getFirst().getNumeroSocio();
		    	
		    	Socio socioG2 = socioServicio.buscarSocioPorNumeroSocio(nroSocioG2);
		    	nombreSocioG2 = socioG2.getApellidoNombre();
		    	
		    	periodoDesdeG2 = cuotasAdeudadasGarante2.getFirst().getMes() + "/" + cuotasAdeudadasGarante2.getFirst().getAno();
				periodoHastaG2 = cuotasAdeudadasGarante2.getLast().getMes() + "/" +cuotasAdeudadasGarante2.getLast().getAno();
				
				BigDecimal montoTotalAdeudadoSocio = BigDecimal.ZERO;
				for (Cuota cuota : cuotasAdeudadasGarante2) {
		            // Solo sumamos la cuota si es del anio actual y su mes es menor o igual al mes actual
		            if ((cuota.getAno() < anioActual) || (cuota.getAno() == anioActual && cuota.getMes() <= mesActual)) {
		            	montoTotalAdeudadoSocio = montoTotalAdeudadoSocio.add(cuota.getMonto());
		                cuota.getMonto();
		                
		            }
		        }
				
				deudaSocioG2 = montoTotalAdeudadoSocio;
		    	
		    }
		    
		    if(!(cuotasAdeudadasGarante3 == null || cuotasAdeudadasGarante3.isEmpty())) {
		    	
		    	nroSocioG3 	= cuotasAdeudadasGarante3.getFirst().getNumeroSocio();
		    	
		    	Socio socioG3 = socioServicio.buscarSocioPorNumeroSocio(nroSocioG3);
		    	nombreSocioG3 = socioG3.getApellidoNombre();
		    	
		    	periodoDesdeG3 = cuotasAdeudadasGarante3.getFirst().getMes() + "/" + cuotasAdeudadasGarante3.getFirst().getAno();
				periodoHastaG3 = cuotasAdeudadasGarante3.getLast().getMes() + "/" +cuotasAdeudadasGarante3.getLast().getAno();
				
				BigDecimal montoTotalAdeudadoSocio = BigDecimal.ZERO;
				for (Cuota cuota : cuotasAdeudadasGarante3) {
		            // Solo sumamos la cuota si es del anio actual y su mes es menor o igual al mes actual
		            if ((cuota.getAno() < anioActual) || (cuota.getAno() == anioActual && cuota.getMes() <= mesActual)) {
		            	montoTotalAdeudadoSocio = montoTotalAdeudadoSocio.add(cuota.getMonto());
		                cuota.getMonto();
		                
		            }
		        }
				
				deudaSocioG3 = montoTotalAdeudadoSocio;
		    	
		    }
		    
		    reporteReciboCuotaSocietaria.generarReporte(nroSocio, nroSocioG1, nroSocioG2, nroSocioG3, nombreSocio, nombreSocioG1, 
					nombreSocioG2, nombreSocioG3, periodoDesde, periodoHasta, periodoDesdeG1, 
					periodoHastaG1, periodoDesdeG2, periodoHastaG2, periodoDesdeG3, periodoHastaG3, 
					deudaSocio, deudaSocioG1, deudaSocioG2, deudaSocioG3, totalACobrar);

		    File folder = new File("src/main/resources/Cobros");
		    if (!folder.exists()) {
		        folder.mkdirs();
		    }
		    File archivoCobros = new File(folder, fechaStr + ".txt");

		    System.out.println("Archivo de cobros: " + archivoCobros.getAbsolutePath());

		    try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCobros, true))) {
		        bw.write(contenidoArchivo.toString());
		    } catch (IOException e) {
		        JOptionPane.showMessageDialog(this, "Error al generar el archivo de cobros: " + e.getMessage());
		        e.printStackTrace();
		        return null;
		    }

		    JOptionPane.showMessageDialog(this, "Cobros generados exitosamente.\nTotal adeudado: $" + totalMonto);
			  
		    return totalMonto;
		    
	  }catch(Exception ex){
		  JOptionPane.showMessageDialog(this, "Ocurrio un sError al cobrar cuotas sociales.");
		  
		  return null;
	  }
	}

	private BigDecimal procesarCuotas(List<Cuota> cuotas, BigDecimal totalMonto, StringBuilder contenidoArchivo, Date fechaCobro, String rol) {
	    // Obtenemos el año y mes actual a partir de la fecha de cobro.
	    Calendar calActual = Calendar.getInstance();
	    calActual.setTime(fechaCobro);
	    int anioActual = calActual.get(Calendar.YEAR);
	    int mesActual = calActual.get(Calendar.MONTH) + 1; // Calendar.MONTH es 0-based
	   
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String fechaStr = sdf.format(fechaCobro);
	    System.out.println(cuotas.size());
	    if (cuotas != null && !cuotas.isEmpty()) {
	        for (Cuota cuota : cuotas) {
	            // Solo procesamos la cuota si es del año actual y su mes es menor o igual al mes actual.
	        	
	            if ((cuota.getAno() < anioActual) || (cuota.getAno() == anioActual && cuota.getMes() <= mesActual)) {
	            	System.out.println(cuota.getIdCuota() + cuota.getMonto().toString() + cuota.getNumeroSocio());
	                totalMonto = totalMonto.add(cuota.getMonto());
	                // Se agrega el rol para identificar si la cuota corresponde al socio o a un garante.
	                String lineaCobro = cuota.getNumeroSocio() + ","
	                                    + cuota.getMes() + ","
	                                    + cuota.getAno() + ","
	                                    + fechaStr + ","
	                                    + cuota.getMonto();
	                contenidoArchivo.append(lineaCobro).append(System.lineSeparator());
	                cuotasSocialesServicio.borrarCuota(cuota);
	            }
	        }
	    }
	    return totalMonto;
	}
	
	private BigDecimal parseNumeroFormateado(String numeroFormateado) {
	    if (numeroFormateado == null || numeroFormateado.isEmpty()) {
	        return BigDecimal.ZERO;
	    }
	    String normalized = numeroFormateado.trim();
	    // If the input contains a comma, assume it's in local format (e.g., "1.234,56")
	    if (normalized.contains(",")) {
	        // Remove any grouping separators (dots) and convert the decimal comma to a dot
	        normalized = normalized.replace(".", "").replace(",", ".");
	    }
	    // Otherwise, assume the input is already in standard format (e.g., "1234.56")
	    return new BigDecimal(normalized);
	}
	
	private void calcularNetoACobrar() {
	    try {
	        BigDecimal importeCuota = parseNumeroFormateado(txtImporteCuota.getText());
	        BigDecimal descuento = parseNumeroFormateado(txtDescPagoAdelantado.getText());
	        BigDecimal cargoAdic = parseNumeroFormateado(txtCargoAdic.getText());
	        BigDecimal honorariosAbog = parseNumeroFormateado(txtHonorariosAbogado.getText());
	        
	        BigDecimal netoACobrar = importeCuota.add(cargoAdic).add(honorariosAbog).subtract(descuento);
	        
	        
	        // Use a DecimalFormat to display the result in your desired format:
	        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	        symbols.setGroupingSeparator('.');
	        symbols.setDecimalSeparator(',');
	        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
	        
	        txtNetoACobrar.setText(df.format(netoACobrar));
	    } catch (NumberFormatException e) {
	    	System.out.println("calcularNetoACobrar error");
	        txtNetoACobrar.setText("Error");
	    }
	}
	
	public void cargarDatosAyudaPesos() {
	    if (this.ayudaPesos != null) {
	        try {

            	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            	symbols.setGroupingSeparator('.');
            	symbols.setDecimalSeparator(',');
            	DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
            
	            Integer numeroSocio = ayudaPesos.getNumeroSocio();
	            Socio socio = (numeroSocio != null) ? socioServicio.buscarSocioPorNumeroSocio(numeroSocio) : null;

	            Integer nroSocioGarante = ayudaPesos.getNroSocioGarante();
	            Socio socioGarante = (nroSocioGarante != null) ? socioServicio.buscarSocioPorNumeroSocio(nroSocioGarante) : null;

	            Integer nroSocioGarante2 = ayudaPesos.getNroSocioGarante2();
	            Socio socioGarante1 = (nroSocioGarante2 != null) ? socioServicio.buscarSocioPorNumeroSocio(nroSocioGarante2) : null;

	            Integer nroSocioGarante3 = ayudaPesos.getNroSocioGarante3();
	            Socio socioGarante2 = (nroSocioGarante3 != null) ? socioServicio.buscarSocioPorNumeroSocio(nroSocioGarante3) : null;

	            // 2. Obtener datos de Garantía
	            Integer garantiaId = ayudaPesos.getGarantia();
	            Garantia garantia = (garantiaId != null) ? garantiaServicio.buscarGarantiaPorId(garantiaId) : null;

	            // 3. Obtener datos de Cuotas
	            Integer nroAyuda = ayudaPesos.getNroAyuda();
	            List<CuotaAyudaPesos> cuotas = (nroAyuda != null) ? cuotasServicio.buscarCuotasPorNumeroAyuda(nroAyuda) : null;

	            // 4. Asignar valores a los campos de texto de Socio y Garantía
	            String numSocioStr = (numeroSocio != null) ? numeroSocio.toString() : "N/A";
	            String nomSocioStr = (socio != null) ? socio.getApellidoNombre() : "N/A";

	            String numSocioGaranteStr = (nroSocioGarante != null) ? nroSocioGarante.toString() : "N/A";
	            String numSocioGarante1Str = (nroSocioGarante2 != null) ? nroSocioGarante2.toString() : "N/A";
	            String numSocioGarante2Str = (nroSocioGarante3 != null) ? nroSocioGarante3.toString() : "N/A";

	            String nomSocioGaranteStr = (socioGarante != null) ? socioGarante.getApellidoNombre() : "N/A";
	            String nomSocioGarante1Str = (socioGarante1 != null) ? socioGarante1.getApellidoNombre() : "N/A";
	            String nomSocioGarante2Str = (socioGarante2 != null) ? socioGarante2.getApellidoNombre() : "N/A";

	            this.txtNumeroSocio.setText(numSocioStr);
	            this.txtNombreSocio.setText(nomSocioStr);

	            this.txtNumSocioGarante.setText(numSocioGaranteStr);
	            this.txtNumSocioGarante1.setText(numSocioGarante1Str);
	            this.txtNumSocioGarante2.setText(numSocioGarante2Str);

	            this.txtNombreSocioGarante.setText(nomSocioGaranteStr);
	            this.txtNombreSocioGarante1.setText(nomSocioGarante1Str);
	            this.txtNombreSocioGarante2.setText(nomSocioGarante2Str);

	            // 5. Asignar otros campos con verificaciones de nulidad
	            String nroAyudaStr = (nroAyuda != null) ? nroAyuda.toString() : "N/A";
	            this.txtNumAyuda.setText(nroAyudaStr);
	            // Mostrar la fecha actual en txtFecha
	            
	            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
	         	String fechaEmisionStr = (ayudaPesos.getFechaAltaAyuda() != null) ? sdf.format(ayudaPesos.getFechaAltaAyuda()) : "N/A";
	            this.txtFechaEmision.setText(fechaEmisionStr);

	            String montoSolicitadoStr = (ayudaPesos.getMontoSolicitado() != null) ? df.format(ayudaPesos.getMontoSolicitado()) : "N/A";
	            this.txtMontoSolicitado.setText(montoSolicitadoStr);

	            String tasaInteresStr = (ayudaPesos.getInteres() != null) ? df.format(ayudaPesos.getInteres()) : "N/A";
	            this.txtTasaInteres.setText(tasaInteresStr);

	            String gastosAdminStr = (ayudaPesos.getMontoGastoAdministrativo() != null) ? df.format(ayudaPesos.getMontoGastoAdministrativo()) : "N/A";
	            this.txtGastosAdmin.setText(gastosAdminStr);

	            String gastosCobranza = (ayudaPesos.getMontoSellado() != null) ? df.format(ayudaPesos.getMontoSellado()) : "N/A";
	            this.txtGastosCobranza.setText(gastosCobranza);

	            String garantiaStr = (garantia != null) ? garantia.getGarantiaNom() : "N/A";
	            this.txtGarantia.setText(garantiaStr);

	            String destinoStr = (ayudaPesos.getDestinoAyuda() != null) ? ayudaPesos.getDestinoAyuda() : "N/A";
	            this.txtDestino.setText(destinoStr);

	            String cantCuotasStr = (ayudaPesos.getCantidadCuotas() != null) ? Integer.toString(ayudaPesos.getCantidadCuotas()) : "N/A";
	            this.txtCantCuotas.setText(cantCuotasStr);

	            String interesCuotaStr = (ayudaPesos.getInteresCuota() != null) ? df.format(ayudaPesos.getInteresCuota()) : "N/A";
	            this.txtInteresCuota.setText(interesCuotaStr);
	            this.txtInteresMensual.setText(interesCuotaStr);

	            // 6. Calcular y asignar el total de las cuotas a cobrar
	            int desde = cobroRangoFechasDto.getDesde();
	            int hasta = cobroRangoFechasDto.getHasta();

	            // Filtrar las cuotas dentro del rango y que no han sido cobradas
	            List<CuotaAyudaPesos> cuotasACobrar = new ArrayList<>();
	            BigDecimal totalImporteCuotas = BigDecimal.ZERO;
	            BigDecimal totalIntereses = BigDecimal.ZERO;
	            BigDecimal interesCuota = ayudaPesos.getInteresCuota() == null ? new BigDecimal("0.00") : ayudaPesos.getInteresCuota();
	  	      
	            if (cuotas != null && !cuotas.isEmpty()) {
	                for (CuotaAyudaPesos cuota : cuotas) {
	                    int numeroCuota = cuota.getNumeroCuota();
	                    if (numeroCuota >= desde && numeroCuota <= hasta) {
	                        boolean yaCobrada = cobroCuotaAyudaPesosServicio.existeCobro(nroAyuda, numeroCuota);
	                        if (!yaCobrada) {
	                            cuotasACobrar.add(cuota);
	                            if (cuota.getMontoCuota() != null) {
	                                totalImporteCuotas = totalImporteCuotas.add(cuota.getMontoCuota());
	                            }
	                            if (interesCuota != null) {
	                                totalIntereses = totalIntereses.add(interesCuota);
	                            } 
	                        }
	                    }
	                }
	            }

	            String montoCuotaStr = "N/A";
	            if (cuotas != null && !cuotas.isEmpty()) {
	                CuotaAyudaPesos cuota = cuotas.get(0);
	                montoCuotaStr = (cuota.getMontoCuota() != null) ? df.format(cuota.getMontoCuota()) : "N/A";
	            }
	            
	            this.txtMontoCuota.setText(montoCuotaStr);
	            
	            // Asignar la cantidad de cuotas a cobrar
	            this.txtCantidadCuotas.setText(String.valueOf(cuotasACobrar.size()));
	            
	            this.txtDesdeCuota.setText(Integer.toString(desde));
	            
	            this.txtHastaCuota.setText(Integer.toString(hasta));
	            
	            // Asignar el total de los montos de las cuotas a cobrar
	            this.txtImporteCuota.setText(df.format(totalImporteCuotas));

	            // Asignar el total de intereses
	            this.txtInteresMensual.setText(df.format(totalIntereses));

	            // Mostrar la fecha actual en txtFecha
	            Date hoy = new Date();
	            String fechaHoyStr = sdf.format(hoy);
	            this.txtFecha.setText(fechaHoyStr);

	            // Opcional: Actualizar el Neto a Cobrar automáticamente
	            calcularNetoACobrar();
	        }catch(Exception ex) {
	            JOptionPane.showMessageDialog(this, "Ocurrió un error al cargar los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	     	   }
	        }
	    }

	private boolean isMontosIgualNetoACob(BigDecimal netoACobrar) {
	    try {
	        String efectivoText = txtEfectivoCobro.getText().trim().replace(",", ".");
	        BigDecimal montoEfectivoActual = efectivoText.isEmpty() ? new BigDecimal("0.00") : new BigDecimal(efectivoText);

	        String transferenciaText = txtTransferenciaCobro.getText().trim().replace(",", ".");
	        BigDecimal montoTransferenciaActual = transferenciaText.isEmpty() ? new BigDecimal("0.00") : new BigDecimal(transferenciaText);

	        String mov1Text = txtImporteMov.getText().trim().replace(",", ".");
	        BigDecimal importeMov1 = mov1Text.isEmpty() ? new BigDecimal("0.00") : new BigDecimal(mov1Text);

	        String mov2Text = txtImporteMov2.getText().trim().replace(",", ".");
	        BigDecimal importeMov2 = mov2Text.isEmpty() ? new BigDecimal("0.00") : new BigDecimal(mov2Text);

	        String mov3Text = txtImporteMov3.getText().trim().replace(",", ".");
	        BigDecimal importeMov3 = mov3Text.isEmpty() ? new BigDecimal("0.00") : new BigDecimal(mov3Text);

	        BigDecimal resultado = montoEfectivoActual.add(montoTransferenciaActual)
	                .add(importeMov1)
	                .add(importeMov2)
	                .add(importeMov3);

	        System.out.println("Suma de montos: " + resultado);
	        System.out.println("Neto a cobrar: " + netoACobrar);

	        if (resultado.compareTo(netoACobrar) == 0) {
	            System.out.println("Los montos son iguales al neto");
	            return true;
	        } else {
	            mostrarMensaje("El total de montos de forma de pago no coincide con el neto a cobrar.");
	            return false;
	        }
	    } catch (NumberFormatException e) {
	    	System.out.println("isMontosIgualNetoACob error");
	    	mostrarMensaje("Error en la conversión de montos. Asegúrese de que los valores sean numéricos válidos.");
	        return false;
	    }
	}

	
	private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
    }
	
	
	public void limpiarDatos() {
	    // Reiniciar todos los campos de texto (PlaceholderTextField)
	    txtNumeroSocio.setText("");
	    txtNumSocioGarante.setText("");
	    txtNumAyuda.setText("");
	    txtNombreSocio.setText("");
	    txtNumSocioGarante1.setText("");
	    txtNumSocioGarante2.setText("");
	    txtNombreSocioGarante.setText("");
	    txtNombreSocioGarante1.setText("");
	    txtNombreSocioGarante2.setText("");
	    txtFechaEmision.setText("");
	    txtMontoSolicitado.setText("");
	    txtTasaInteres.setText("");
	    txtGastosAdmin.setText("");
	    txtGastosCobranza.setText("");
	    txtGarantia.setText("");
	    txtDestino.setText("");
	    txtCantCuotas.setText("");
	    txtInteresCuota.setText("");
	    txtMontoCuota.setText("");
	    txtDescPagoAdelantado.setText("");
	    txtImporteCuota.setText("");
	    txtFecha.setText("");
	    txtCargoAdic.setText("");
	    txtInteresMensual.setText("");
	    txtNetoACobrar.setText("");
	    txtCantidadCuotas.setText("");
	    txtHastaCuota.setText("");
	    txtDesdeCuota.setText("");
	    txtEfectivoCobro.setText("");
	    txtTransferenciaCobro.setText("");
	    txtNroMov.setText("");
	    txtDetalleMov.setText("");
	    txtImporteMov.setText("");
	    txtImporteMov2.setText("");
	    txtDetalleMov2.setText("");
	    txtNroMov2.setText("");
	    txtNroMov3.setText("");
	    txtDetalleMov3.setText("");
	    txtImporteMov3.setText("");
	    txtObservaciones.setText("");
	    
	    btnImprimirRecibo.setEnabled(false);
	    btnCobrar.setEnabled(true);
	    
	    // Reiniciar atributos internos (opcional, según tu lógica)
	    ayudaPesos = new AyudaPesos();
	    cobroRangoFechasDto = new CobroCuotasRangoFechaCuotasDto();
	    cobroCuotasReporteDto = new CobroCuotasReporteDto();
	}
}
