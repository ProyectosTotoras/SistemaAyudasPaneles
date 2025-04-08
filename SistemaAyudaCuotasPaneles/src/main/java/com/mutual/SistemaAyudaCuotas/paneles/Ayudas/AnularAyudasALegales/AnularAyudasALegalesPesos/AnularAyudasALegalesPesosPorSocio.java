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
import java.util.List;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.AbstractDocument;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.Socio;
import com.mutual.SistemaAyudaCuotas.filtros.IntegerFilter;
import com.mutual.SistemaAyudaCuotas.paneles.MenuAyudas;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos.AltaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaAyudas.ConsultaBajaAyudasPesos.Entidades.AyudaPesosTableModel;
import com.mutual.SistemaAyudaCuotas.servicio.IAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ISocioServicio;

import jakarta.annotation.PostConstruct;

@Component
public class AnularAyudasALegalesPesosPorSocio extends JPanel {
	
	private String panelAnterior = "AnularAyudasALegalesPesosTipoBusqueda";
	private static final long serialVersionUID = 1L;
	private final IAyudaPesosServicio ayudaServicio;
	private final ISocioServicio socioServicio;

	private JPanel contentPane;
	private JTextField txtFiltroNumSocio;
	private JTable table;
    private AyudaPesosTableModel tableModel;
    private JTextField txtNombreSocio;

    private CardLayout cardLayout; 
	private JPanel cardPanel;
	private JFrame mainFrame;
	private AnularAyudasALegalesPesosPantallaSocio anularAyudasALegalesPesosPantallaSocio;
    @Autowired
	public AnularAyudasALegalesPesosPorSocio(
			IAyudaPesosServicio ayudaServicio,
			ISocioServicio socioServicio
			) {
		this.ayudaServicio  = ayudaServicio;
		this.socioServicio = socioServicio;
//		iniciar(null,null,null);
    }
    
    public void iniciar(CardLayout cardLayout, 
    		JPanel cardPanel,JFrame mainFrame) {
    	this.cardLayout = cardLayout;
    	this.mainFrame = mainFrame;
    	this.cardPanel = cardPanel;
    	// Configurar key binding para la tecla Escape
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapeAction");
        this.getActionMap().put("escapeAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 limpiarDatos();
              	cardLayout.show(cardPanel, panelAnterior);
            }
        });
    	
        addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentShown(ComponentEvent e) {
    	        e.getComponent().requestFocusInWindow();
    	        limpiarDatos();
                cargarDatos();
    	        txtFiltroNumSocio.requestFocusInWindow();
    	    }
    	});
        
          Dimension containerSize = new Dimension(950, 700); // Adjust dimensions as needed
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setPreferredSize(containerSize);
        mainPanel.setMaximumSize(containerSize);
       add(mainPanel);
       
		txtFiltroNumSocio = new JTextField();
		txtFiltroNumSocio.setFont(new Font("72", Font.PLAIN, 17));
		txtFiltroNumSocio.setColumns(10);
		txtFiltroNumSocio.setBounds(201, 75, 187, 23);
		mainPanel.add(txtFiltroNumSocio);
		
		JLabel lblFiltroNumSocio = new JLabel("Numero Socio:");
		lblFiltroNumSocio.setFont(new Font("72", Font.PLAIN, 17));
		lblFiltroNumSocio.setBounds(59, 78, 132, 23);
		mainPanel.add(lblFiltroNumSocio);
        
        // Tabla
        tableModel = new AyudaPesosTableModel(List.of()); // Inicialmente vacía
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
       
        table.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
        			
        		int selectedRow = table.getSelectedRow();
                 if (selectedRow != -1) {
                     AyudaPesos ayudaSeleccionada = tableModel.getAyudaAt(selectedRow);
            	    
            	     abrirAyudaPantalla(ayudaSeleccionada);
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
                    txtFiltroNumSocio.requestFocusInWindow();
                }
            }
        };

        InputMap im = table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap am = table.getActionMap();

        im.put(KeyStroke.getKeyStroke("TAB"), "selectNextRow");
        im.put(KeyStroke.getKeyStroke("shift TAB"), "selectPreviousRow");

        am.put("selectNextRow", selectNextRowAction);
        am.put("selectPreviousRow", selectPreviousRowAction);
          
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setFocusable(false);
        scrollPane.setBounds(40, 114, 861, 535);
        mainPanel.add(scrollPane);
        
        JLabel lblAyudasPorNmero = new JLabel("Ayudas Por Número de Socio");
        lblAyudasPorNmero.setFont(new Font("72", Font.PLAIN, 20));
        lblAyudasPorNmero.setBounds(59, 21, 354, 26);
        mainPanel.add(lblAyudasPorNmero);
        
        txtNombreSocio = new JTextField();
        txtNombreSocio.setFocusable(false);
        txtNombreSocio.setEditable(false);
        txtNombreSocio.setFont(new Font("72", Font.PLAIN, 17));
        txtNombreSocio.setColumns(10);
        txtNombreSocio.setBounds(410, 75, 360, 23);
        mainPanel.add(txtNombreSocio);
        
    	txtFiltroNumSocio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					cargarDatos();
					
				}
			}
		});
    	((AbstractDocument) txtFiltroNumSocio.getDocument()).setDocumentFilter(new IntegerFilter());
 		
    	  // Listener para selección de fila en la tabla
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Evitar que se procese dos veces
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        AyudaPesos ayudaSeleccionada = tableModel.getAyudaAt(selectedRow);
                     }
                }
            }
        });
        
        DefaultTableCellRenderer estadoRenderer = new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null && value.toString().contains("Legales")) {
                    c.setForeground(Color.RED);
                } else {
                    c.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
                }
                return c;
            }
        };

        // Asumiendo que la columna "Estado" es la tercera (índice 2)
        table.getColumnModel().getColumn(4).setCellRenderer(estadoRenderer);

      
    }
    public void setAnularAyudasALegalesPesosPantallaSocio(
    		AnularAyudasALegalesPesosPantallaSocio anularAyudasALegalesPesosPantallaSocio) {
		this.anularAyudasALegalesPesosPantallaSocio = anularAyudasALegalesPesosPantallaSocio;
		
	}
	
	  @PostConstruct
	    public void cargarDatosIniciales() {
	        cargarDatos();
	    }
	  
	  private void limpiarDatos() {
		  this.txtFiltroNumSocio.setText("");
		  this.txtNombreSocio.setText("");
	  }
	  
	  private void abrirAyudaPantalla(AyudaPesos ayuda) {
		   if (table.isEditing()) {
		        table.getCellEditor().stopCellEditing();
		    }
		  
		   anularAyudasALegalesPesosPantallaSocio.setAyudaPesos(ayuda);
         	cardLayout.show(cardPanel, "AnularAyudasALegalesPesosPantallaSocio");
	  }
	  
	  private void cargarDatos() {
		    new SwingWorker<List<AyudaPesos>, Void>() {
		        @Override
		        protected List<AyudaPesos> doInBackground() throws Exception {
		            String filtroNumSocio = txtFiltroNumSocio.getText().trim();
		            List<AyudaPesos> ayudas;
		            if (!filtroNumSocio.isEmpty()) {
		                int numeroSocio = Integer.parseInt(filtroNumSocio);
		                Socio socio = socioServicio.buscarSocioPorNumeroSocio(numeroSocio);
		                if (socio == null) {

		                	mostrarMensaje("Socio no encontrado.");
		                	throw new Exception("Socio no encontrado.");      }
		                ayudas = ayudaServicio.listarAyudasPorNumeroSocioFechaLegales(numeroSocio);
		                if (ayudas == null || ayudas.isEmpty()) {
		                	mostrarMensaje("Socio sin Ayudas Legales cargadas");
		                	throw new Exception("Socio sin Ayudas Legales cargadas.");
		                }
		            } else {
		                // Si tienes muchos registros, aquí podrías llamar a un método con paginación.
		                ayudas = ayudaServicio.listarAyudasFechaLegales();
		            }
		            return ayudas;
		        }
		        
		        @Override
		        protected void done() {
		            try {
		                List<AyudaPesos> ayudas = get();
		                tableModel.setAyudas(ayudas);
		                // Actualiza el nombre del socio si corresponde
		                if (!ayudas.isEmpty()) {
		                    Socio socio = txtFiltroNumSocio.getText().isEmpty() ? null : socioServicio.buscarSocioPorNumeroSocio(ayudas.get(0).getNumeroSocio());
		                    txtNombreSocio.setText(socio == null ? "" : socio.getApellidoNombre());
		              }
		            } catch (Exception ex) {
//		                JOptionPane.showMessageDialog(AnularALegalesPorNumSocioPesos.this,
//		                        "Error al cargar los datos: " + ex.getMessage(),
//		                        "Error",
//		                        JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }.execute();
		}
	
	private void mostrarMensaje(String mensaje){
	        JOptionPane.showMessageDialog(this,mensaje);
	}
	
}
