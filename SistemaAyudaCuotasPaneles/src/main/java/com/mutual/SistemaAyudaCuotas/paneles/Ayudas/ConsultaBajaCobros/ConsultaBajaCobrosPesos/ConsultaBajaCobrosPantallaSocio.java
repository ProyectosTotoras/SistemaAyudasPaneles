package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaCobros.ConsultaBajaCobrosPesos;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.componentes.PlaceholderTextField;
import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaPesos;
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
public class ConsultaBajaCobrosPantallaSocio extends JPanel {
	
	private AyudaPesos ayudaPesos;
	private String panelAnterior = "ConsultaBajaCobrosPorSocio";

	private static final long serialVersionUID = 1L;
	private ICuotaAyudaPesosServicio cuotasServicio;
	private IGarantiaServicio garantiaServicio;
	private ISocioServicio socioServicio;
	private IAyudaPesosServicio ayudaServicio;
	private ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio;
	
	private JPanel contentPane;
    private CobroCuotaAyudaPesosTableModel tableModel;
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
	private JTable table;
	private ConsultaBajaCobrosConsultaCobro consultaBajaCobrosConsultaCobro;
	private JLabel lblAyudasPorNmero;
	private CardLayout cardLayout; 
	private JPanel cardPanel;
	private JFrame mainFrame;
	public ConsultaBajaCobrosPantallaSocio( 
			 ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio,
				ICuotaAyudaPesosServicio cuotasServicio, 
				ISocioServicio socioServicio, 
				IGarantiaServicio garantiaServicio,
				IAyudaPesosServicio ayudaServicio
		) {
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

        Dimension containerSize = new Dimension(1080, 600); // Adjust dimensions as needed
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
		lblSocioGarante.setFont(new Font("72", Font.PLAIN, 17));
		lblSocioGarante.setBounds(25, 96, 137, 23);
		mainPanel.add(lblSocioGarante);
		
		txtNumSocioGarante = new PlaceholderTextField("00000000.00");
		txtNumSocioGarante.setFocusable(false);
		txtNumSocioGarante.setForeground(new Color(0, 0, 0));
		txtNumSocioGarante.setPlaceholder("0000");
		txtNumSocioGarante.setFont(new Font("72", Font.PLAIN, 17));
		txtNumSocioGarante.setColumns(10);
		txtNumSocioGarante.setBounds(185, 94, 56, 23);
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
		txtNombreSocio.setBounds(186, 63, 380, 23);
		mainPanel.add(txtNombreSocio);
		
		JLabel lblSocioGarante1 = new JLabel("Segundo Garante:");
		lblSocioGarante1.setFont(new Font("72", Font.PLAIN, 17));
		lblSocioGarante1.setBounds(25, 124, 137, 23);
		mainPanel.add(lblSocioGarante1);
		
		txtNumSocioGarante1 = new PlaceholderTextField("00000000.00");
		txtNumSocioGarante1.setFocusable(false);
		txtNumSocioGarante1.setForeground(new Color(0, 0, 0));
		txtNumSocioGarante1.setPlaceholder("0000");
		txtNumSocioGarante1.setFont(new Font("72", Font.PLAIN, 17));
		txtNumSocioGarante1.setColumns(10);
		txtNumSocioGarante1.setBounds(185, 122, 56, 23);
		mainPanel.add(txtNumSocioGarante1);
		
		JLabel lblSocioGarante2 = new JLabel("Tercer Garante");
		lblSocioGarante2.setFont(new Font("72", Font.PLAIN, 17));
		lblSocioGarante2.setBounds(25, 153, 137, 23);
		mainPanel.add(lblSocioGarante2);
		
		txtNumSocioGarante2 = new PlaceholderTextField("00000000.00");
		txtNumSocioGarante2.setFocusable(false);
		txtNumSocioGarante2.setForeground(new Color(0, 0, 0));
		txtNumSocioGarante2.setPlaceholder("0000");
		txtNumSocioGarante2.setFont(new Font("72", Font.PLAIN, 17));
		txtNumSocioGarante2.setColumns(10);
		txtNumSocioGarante2.setBounds(185, 151, 56, 23);
		mainPanel.add(txtNumSocioGarante2);
		
		txtNombreSocioGarante = new PlaceholderTextField("00000000.00");
		txtNombreSocioGarante.setFocusable(false);
		txtNombreSocioGarante.setForeground(new Color(0, 0, 0));
		txtNombreSocioGarante.setPlaceholder("...");
		txtNombreSocioGarante.setFont(new Font("72", Font.PLAIN, 17));
		txtNombreSocioGarante.setColumns(10);
		txtNombreSocioGarante.setBounds(251, 94, 315, 23);
		mainPanel.add(txtNombreSocioGarante);
		
		txtNombreSocioGarante1 = new PlaceholderTextField("00000000.00");
		txtNombreSocioGarante1.setFocusable(false);
		txtNombreSocioGarante1.setForeground(new Color(0, 0, 0));
		txtNombreSocioGarante1.setPlaceholder("...");
		txtNombreSocioGarante1.setFont(new Font("72", Font.PLAIN, 17));
		txtNombreSocioGarante1.setColumns(10);
		txtNombreSocioGarante1.setBounds(251, 122, 315, 23);
		mainPanel.add(txtNombreSocioGarante1);
		
		txtNombreSocioGarante2 = new PlaceholderTextField("00000000.00");
		txtNombreSocioGarante2.setFocusable(false);
		txtNombreSocioGarante2.setForeground(new Color(0, 0, 0));
		txtNombreSocioGarante2.setPlaceholder("...");
		txtNombreSocioGarante2.setFont(new Font("72", Font.PLAIN, 17));
		txtNombreSocioGarante2.setColumns(10);
		txtNombreSocioGarante2.setBounds(251, 151, 315, 23);
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
		txtGarantia.setBounds(172, 369, 274, 23);
		mainPanel.add(txtGarantia);
		
		txtDestino = new PlaceholderTextField("00000000.00");
		txtDestino.setFocusable(false);
		txtDestino.setForeground(new Color(0, 0, 0));
		txtDestino.setPlaceholder("...");
		txtDestino.setFont(new Font("72", Font.PLAIN, 17));
		txtDestino.setColumns(10);
		txtDestino.setBounds(172, 396, 274, 23);
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
		lblCantCuotas.setBounds(25, 425, 181, 23);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFocusable(false);
		scrollPane.setBounds(576, 63, 494, 496);
		mainPanel.add(scrollPane);
		
		JLabel lblTotalDeCuotas = new JLabel("Total de cobros");
		lblTotalDeCuotas.setFont(new Font("72", Font.BOLD, 17));
		lblTotalDeCuotas.setBounds(745, 40, 182, 23);
		mainPanel.add(lblTotalDeCuotas);
	        
        // Tabla
        tableModel = new CobroCuotaAyudaPesosTableModel(List.of()); // Inicialmente vacía
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
       
        table.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
        			
        		int selectedRow = table.getSelectedRow();
                 if (selectedRow != -1) {
                     CobroCuotaAyudaPesos cobroSeleccionado = tableModel.getCobroCuotaAyudaPesosAt(selectedRow);
                     if (table.isEditing()) {
         		        table.getCellEditor().stopCellEditing();
         		    }
         		  
                     consultaBajaCobrosConsultaCobro.setAyudaPesos(ayudaPesos);
                     consultaBajaCobrosConsultaCobro.setCobroCuotaAyudaPesos(cobroSeleccionado);		  
                     consultaBajaCobrosConsultaCobro.setPanelAnterior("ConsultaBajaCobrosPantallaSocio");
                     cardLayout.show(cardPanel, "ConsultaBajaCobrosConsultaCobro");
                    }
                  }
        	}
        });
        // Configuración para selección por filas
        table.setRowSelectionAllowed(true); // Permitir selección de filas
        table.setColumnSelectionAllowed(false); // Desactivar selección de columnas
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Modo de selección: una fila a la vez

        // Opcional: Ajustar el ancho de las columnas
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false); // Desactivar reordenamiento de columnas
        // Redefinir las acciones de Tab y Shift+Tab
        Action selectNextRowAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row < table.getRowCount() - 1) {
                    table.setRowSelectionInterval(row + 1, row + 1);
                    table.scrollRectToVisible(table.getCellRect(row + 1, 0, true));
                } else {
                    // Si es la última fila, transferir el enfoque al siguiente campo de texto
                    txtNombreSocio.requestFocusInWindow();
                }
            }
        };

        Action selectPreviousRowAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row > 0) {
                    table.setRowSelectionInterval(row - 1, row - 1);
                    table.scrollRectToVisible(table.getCellRect(row - 1, 0, true));
                } else {
                    // Si es la primera fila, transferir el enfoque al campo de texto anterior
                //    txtFiltroNumSocio.requestFocusInWindow();
                }
            }
        };

        InputMap im = table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap am = table.getActionMap();

        im.put(KeyStroke.getKeyStroke("TAB"), "selectNextRow");
        im.put(KeyStroke.getKeyStroke("shift TAB"), "selectPreviousRow");

        am.put("selectNextRow", selectNextRowAction);
        am.put("selectPreviousRow", selectPreviousRowAction);
        
        // Listener para selección de fila en la tabla
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Evitar que se procese dos veces
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        CobroCuotaAyudaPesos ayudaSeleccionada = tableModel.getCobroCuotaAyudaPesosAt(selectedRow);
                     }
                }
            }
        });
        scrollPane.setViewportView(table);
        
        lblAyudasPorNmero = new JLabel("Ayudas Por Número de Socio");
        lblAyudasPorNmero.setFont(new Font("72", Font.PLAIN, 20));
        lblAyudasPorNmero.setBounds(364, 11, 354, 26);
        mainPanel.add(lblAyudasPorNmero);
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
	            List<CobroCuotaAyudaPesos> cobros = new ArrayList<>();
	            
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
	            
					cobros = cobroCuotaAyudaPesosServicio.buscarCobroCuotaAyudaPesosPorNumAyuda(ayudaPesos.getNroAyuda());
		                
					  if(cobros == null ) {
		            	   List<CuotaAyudaPesos> cuotas = cuotasServicio.buscarCuotasPorNumeroAyuda(ayudaPesos.getNroAyuda());
		            	   if (cuotas != null && !cuotas.isEmpty()) {
		            	       montoCuota = cuotas.get(0).getMontoCuota();
		            	   } else {
		            	       // Handle the case when no cuotas are found.
		            	       montoCuota = BigDecimal.ZERO;
		            	   }	                	
		                }else {
		                	if(cobros.size() == 0) {
			                	montoCuota =  cuotasServicio.buscarCuotasPorNumeroAyuda(ayudaPesos.getNroAyuda()).get(0).getMontoCuota();	                		                		
		                	}
		                	montoCuota = cobros.get(0).getMontoCuota();
		                	
		                }
	                
	                return null;
	            }
	            
	            @Override
	            protected void done() {
	                try {
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
			            
//			            if(cobros == null || cobros.size() == 0)
//			            {
//			            	
//			             	cardLayout.show(cardPanel, panelAnterior);
//			                
//			            }
			            
		                tableModel.setCobros(cobros);
	                } catch (Exception ex) {
		                ex.printStackTrace();
		                throw ex;  // Opcional: volver a lanzar la excepción para manejarla en done()
		            
	                }
	            }
	        }.execute();
	    }
	}

	public void setConsultaBajaCobrosConsultaCobro(ConsultaBajaCobrosConsultaCobro consultaBajaCobrosConsultaCobro) {
		this.consultaBajaCobrosConsultaCobro = consultaBajaCobrosConsultaCobro;
	}
}
