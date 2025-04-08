package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.componentes.PlaceholderTextField;
import com.mutual.SistemaAyudaCuotas.dto.GarantiaInfoDTO;
import com.mutual.SistemaAyudaCuotas.entidades.Garantia;
import com.mutual.SistemaAyudaCuotas.entidades.MovimientoCajaAhorroComun;
import com.mutual.SistemaAyudaCuotas.entidades.Socio;
import com.mutual.SistemaAyudaCuotas.filtros.IntegerFilter;
import com.mutual.SistemaAyudaCuotas.servicio.IGarantiaServicio;
import com.mutual.SistemaAyudaCuotas.servicio.IMovimientoCajaAhorroComunServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ISocioServicio;
import com.mutual.SistemaAyudaCuotas.utilidades.ConvertorFechas;
import com.mutual.SistemaAyudaCuotas.utilidades.SocioValidator;

@Component
@Scope("prototype")
public class AltaAyudaPesosGarantes extends JPanel {
	
	
	private String panelAnterior = "AltaAyudaPesos";
	private Socio socio;
	//SERVICIOS	
	
	private final IGarantiaServicio garantiaServicio;
	private final ISocioServicio socioPruebaServicio;
	private final IMovimientoCajaAhorroComunServicio movimientoCajaAhorroComunServicio;
	
	
	//COMPONENTES
	private JTextField txtSocio;
	private JTextField txtGarante1;
	private JTextField txtGarante2;
	private JTextField txtGarante3;
	private JTextField txtNombreGarante1;
	private JTextField txtNombreGarante2;
	private JTextField txtNombreGarante3;
	private JTextField txtSaldo;
	private JTextField txtIdSocio;
	private PlaceholderTextField txtSaldoG1;
	private PlaceholderTextField txtSaldoG2;	
	private PlaceholderTextField txtSaldoG3;
	private JLabel lblSaldoAhorroComn;
	private JComboBox<Garantia> ccbGarantias;
	private JButton btnContinuar;
	private Date fechaVencimientoGarante1;
	private Date fechaVencimientoGarante2;
	private Date fechaVencimientoGarante3;
      
	private AltaAyudaPesosFormulario altaAyudaPesosFormulario;
	

    public AltaAyudaPesosGarantes(
    		IGarantiaServicio garantiaServicio,
			IMovimientoCajaAhorroComunServicio movimientoCajaAhorroComunServicio,
			ISocioServicio socioPruebaServicio) {
		this.socioPruebaServicio = socioPruebaServicio;
		this.garantiaServicio = garantiaServicio;
		this.movimientoCajaAhorroComunServicio = movimientoCajaAhorroComunServicio;
		
//    	iniciar(null, null, null);
    }
	
	
	public void  iniciar(CardLayout cardLayout, JPanel cardPanel,JFrame mainFrame) {
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
    	        txtGarante1.requestFocusInWindow();
    	    }
    	});
        
    	JPanel mainPanel = new JPanel((LayoutManager) null);
    	mainPanel.setPreferredSize(new Dimension(660, 500));
    	mainPanel.setMaximumSize(new Dimension(660, 500));
    	mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    	mainPanel.setBackground(new Color(138, 217, 247));
    	add(mainPanel);
    	 
    	JLabel lblSocio_1 = new JLabel("Socio:");
 		lblSocio_1.setFont(new Font("72", Font.PLAIN, 17));
 		lblSocio_1.setBounds(45, 111, 54, 23);
 		mainPanel.add(lblSocio_1);
        
 		
		txtSocio = new JTextField();
		txtSocio.setFocusable(false);
		txtSocio.setFont(new Font("72", Font.PLAIN, 17));
		txtSocio.setColumns(10);
		txtSocio.setBounds(109, 109, 336, 23);
		mainPanel.add(txtSocio);
		
		JLabel lblAltaDeAyudas_1 = new JLabel("Alta de Ayudas en Pesos");
		lblAltaDeAyudas_1.setFont(new Font("72", Font.BOLD, 20));
		lblAltaDeAyudas_1.setBounds(199, 39, 263, 34);
		mainPanel.add(lblAltaDeAyudas_1);
		
		JLabel lblGarante = new JLabel("Seleccionar Garante 1:");
		lblGarante.setFont(new Font("72", Font.PLAIN, 17));
		lblGarante.setBounds(45, 163, 170, 23);
		mainPanel.add(lblGarante);

		JLabel lblGarante2 = new JLabel("Seleccionar Garante 2:");
		lblGarante2.setFont(new Font("72", Font.PLAIN, 17));
		lblGarante2.setBounds(45, 192, 170, 23);
		mainPanel.add(lblGarante2);
		
		JLabel lblGarante3 = new JLabel("Seleccionar Garante 3:");
		lblGarante3.setFont(new Font("72", Font.PLAIN, 17));
		lblGarante3.setBounds(45, 221, 170, 23);
		mainPanel.add(lblGarante3);
 		
		txtGarante1 = new PlaceholderTextField("0000");
		txtGarante1.setFont(new Font("72", Font.PLAIN, 17));
		txtGarante1.setColumns(10);
		txtGarante1.setBounds(237, 161, 54, 23);
		((AbstractDocument) txtGarante1.getDocument()).setDocumentFilter(new IntegerFilter());
		mainPanel.add(txtGarante1);
		
		txtGarante2 = new PlaceholderTextField("0000");
		txtGarante2.setFont(new Font("72", Font.PLAIN, 17));
		txtGarante2.setColumns(10);
		txtGarante2.setBounds(237, 191, 54, 23);
		((AbstractDocument) txtGarante2.getDocument()).setDocumentFilter(new IntegerFilter());
		mainPanel.add(txtGarante2);
		
		txtGarante3 = new PlaceholderTextField("0000");
		txtGarante3.setFont(new Font("72", Font.PLAIN, 17));
		txtGarante3.setColumns(10);
		txtGarante3.setBounds(237, 219, 54, 23);
		((AbstractDocument) txtGarante3.getDocument()).setDocumentFilter(new IntegerFilter());
		mainPanel.add(txtGarante3);
	
		ccbGarantias = new JComboBox();
		ccbGarantias.setBounds(144, 323, 186, 22);
		// Agregar opciones al JComboBox
		var listaGarantias = garantiaServicio.listarGarantias();
		
		listaGarantias.forEach(g -> {
			ccbGarantias.addItem(g);
		} );
		mainPanel.add(ccbGarantias);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setFont(new Font("72", Font.PLAIN, 17));
		btnContinuar.setBounds(263, 400, 109, 29);
		btnContinuar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(asignarValores()) {
						cardLayout.show(cardPanel, "AltaAyudaPesosFormulario");
					}
				}
				
			}
		});
		btnContinuar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(asignarValores()) {
					cardLayout.show(cardPanel, "AltaAyudaPesosFormulario");
				};
			}
		});
	
		mainPanel.add(btnContinuar);
		
		lblSaldoAhorroComn = new JLabel("Saldo Ahorro Común:");
		lblSaldoAhorroComn.setFont(new Font("72", Font.PLAIN, 17));
		lblSaldoAhorroComn.setBounds(45, 267, 166, 23);
		mainPanel.add(lblSaldoAhorroComn);
		
		txtSaldo = new JTextField();
		txtSaldo.setFocusable(false);
		txtSaldo.setFont(new Font("72", Font.PLAIN, 17));
		txtSaldo.setColumns(10);
		txtSaldo.setBounds(217, 267, 139, 23);
		mainPanel.add(txtSaldo);
		
		txtIdSocio = new JTextField();
		txtIdSocio.setVisible(false);
		txtIdSocio.setFont(new Font("72", Font.PLAIN, 17));
		txtIdSocio.setFocusable(false);
		txtIdSocio.setColumns(10);
		txtIdSocio.setBounds(491, 109, 146, 23);
		mainPanel.add(txtIdSocio);
		
		JLabel lblGarantpas = new JLabel("Garantías:");
		lblGarantpas.setFont(new Font("72", Font.PLAIN, 17));
		lblGarantpas.setBounds(45, 325, 89, 23);
		mainPanel.add(lblGarantpas);
		
		txtNombreGarante1 = new JTextField();
		txtNombreGarante1.setFocusable(false);
		txtNombreGarante1.setFont(new Font("72", Font.PLAIN, 17));
		txtNombreGarante1.setColumns(10);
		txtNombreGarante1.setBounds(301, 163, 336, 23);
		mainPanel.add(txtNombreGarante1);
		
		txtNombreGarante2 = new JTextField();
		txtNombreGarante2.setFocusable(false);
		txtNombreGarante2.setFont(new Font("72", Font.PLAIN, 17));
		txtNombreGarante2.setColumns(10);
		txtNombreGarante2.setBounds(301, 193, 336, 23);
		mainPanel.add(txtNombreGarante2);
		
		txtNombreGarante3 = new JTextField();
		txtNombreGarante3.setFocusable(false);
		txtNombreGarante3.setFont(new Font("72", Font.PLAIN, 17));
		txtNombreGarante3.setColumns(10);
		txtNombreGarante3.setBounds(301, 220, 336, 23);
		mainPanel.add(txtNombreGarante3);
		cargarGarante(txtGarante1, txtGarante2, null, txtNombreGarante1, fechaVencimientoGarante1);
		cargarGarante(txtGarante2, txtGarante3, null, txtNombreGarante2, fechaVencimientoGarante2);
		cargarGarante(txtGarante3, null, ccbGarantias, txtNombreGarante3, fechaVencimientoGarante3);
	
		   DocumentListener netoACobrarListener = new DocumentListener() {
			    @Override
			    public void insertUpdate(DocumentEvent e) {
			    	if(txtGarante1.getText().isEmpty()) {
			    		txtNombreGarante1.setText("");
			    	}
			    	if(txtGarante2.getText().isEmpty()) {
			    		txtNombreGarante2.setText("");
			    	}
			    	if(txtGarante3.getText().isEmpty()) {
			    		txtNombreGarante3.setText("");
			    	}
			    }

			    @Override
			    public void removeUpdate(DocumentEvent e) {
			    	if(txtGarante1.getText().isEmpty()) {
			    		txtNombreGarante1.setText("");
			    	}
			    	if(txtGarante2.getText().isEmpty()) {
			    		txtNombreGarante2.setText("");
			    	}
			    	if(txtGarante3.getText().isEmpty()) {
			    		txtNombreGarante3.setText("");
			    	}
			    }
			    @Override
			    public void changedUpdate(DocumentEvent e) {
			    	if(txtGarante1.getText().isEmpty()) {
			    		txtNombreGarante1.setText("");
			    	}
			    	if(txtGarante2.getText().isEmpty()) {
			    		txtNombreGarante2.setText("");
			    	}
			    	if(txtGarante3.getText().isEmpty()) {
			    		txtNombreGarante3.setText("");
			    	}
			    }
			};
			
			txtGarante1.getDocument().addDocumentListener(netoACobrarListener);
			txtGarante2.getDocument().addDocumentListener(netoACobrarListener);
			txtGarante3.getDocument().addDocumentListener(netoACobrarListener);
    }
	public void setAltaAyudaPesosFormulario(AltaAyudaPesosFormulario altaAyudaPesosFormulario) {
		this.altaAyudaPesosFormulario = altaAyudaPesosFormulario;
	}
	
	public void setSocio(Socio socio) {
        this.socio = socio;
        if (this.socio != null) {
            txtSocio.setText(this.socio.getApellidoNombre());
    		txtIdSocio.setText(Integer.toString(this.socio.getNumeroSocio()));
    		
    		 DecimalFormatSymbols symbols = new DecimalFormatSymbols();
     		symbols.setGroupingSeparator('.');
     		symbols.setDecimalSeparator(',');
     		DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
     	
    		
    		txtSaldo.setText(df.format(calcularSaldoTotal()));
	    	 
	    	   if(txtNombreGarante1.getText().isEmpty()) {
	    		   txtGarante2.setEnabled(false);		    		   
	    	   }
	    	   if(txtNombreGarante2.getText().isEmpty()) {
	    		   txtGarante3.setEnabled(false);
	    		   
	    	   }
        
        } else {
            txtSocio.setText("");
            txtIdSocio.setText("");
        }

    }

	public Socio getSocio() {
		return this.socio;
	}
	private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
    }
	private BigDecimal calcularSaldoTotal() {
		 DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    		symbols.setGroupingSeparator('.');
    		symbols.setDecimalSeparator(',');
    		DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
    	
	    BigDecimal saldoTotal = BigDecimal.ZERO;
	    
	    // Sumar saldo del socio
	    if (this.socio != null) {
	        saldoTotal = saldoTotal.add(calcularSaldoPorNumero(this.socio.getNumeroSocio()));
	    }
	    
	    return saldoTotal;
	}

	
	private BigDecimal calcularSaldoPorNumero(int numeroSocio) {
	    BigDecimal saldo = BigDecimal.ZERO;
	    Date fechaActual = ConvertorFechas.convertLocalDateToDate(LocalDate.now());
	    List<MovimientoCajaAhorroComun> movimientos = movimientoCajaAhorroComunServicio.findByNumeroSocio(numeroSocio);
	    
	    if (movimientos != null) {
	        for (MovimientoCajaAhorroComun m : movimientos) {
	            Date fechaMovimiento = m.getFechaAcre();
	            if (fechaMovimiento.compareTo(fechaActual) <= 0) {
	                if (m.getCodigo() == 1) {
	                    if(m.getAnulado().trim().isEmpty() || m.getAnulado().trim() == ""){
	                    	saldo = saldo.add(m.getMonto());
	                    }
	                } else {
	                    saldo = saldo.subtract(m.getMonto());
	                }
	            }
	        }
	    }
	    return saldo;
	}

	private boolean GaranteIgualSocioActual(int numeroGarante) {
	    if (socio == null) {
	        mostrarMensaje("Información del socio no disponible. Por favor, reinicia el formulario.");
	        return false;
	    }
	    return numeroGarante == socio.getNumeroSocio();
	}
	
	
	private boolean validarGarantesSonDiferentes() {
	    Set<String> garantes = new HashSet<>();
	    if (!txtGarante1.getText().isEmpty()) garantes.add(txtGarante1.getText());
	    if (!txtGarante2.getText().isEmpty()) garantes.add(txtGarante2.getText());
	    if (!txtGarante3.getText().isEmpty()) garantes.add(txtGarante3.getText());

	    // Check if the size matches the number of non-empty garantes
	    int nonEmptyGarantes = 0;
	    if (!txtGarante1.getText().isEmpty()) nonEmptyGarantes++;
	    if (!txtGarante2.getText().isEmpty()) nonEmptyGarantes++;
	    if (!txtGarante3.getText().isEmpty()) nonEmptyGarantes++;

	    if (garantes.size() < nonEmptyGarantes) {
	       
	        return false;
	    }

	    return true;
	}
	private boolean asignarValores() {
		try {
		if(validarGarantesSonDiferentes()) {
            Garantia garantiaSeleccionada = (Garantia) ccbGarantias.getSelectedItem();
            String garantiaDescripcion = garantiaSeleccionada != null ? garantiaSeleccionada.getGarantiaNom() : "";
            int garantiaId = garantiaSeleccionada != null ? garantiaSeleccionada.getCodigo() : 0;
            
			
			var garantiaInfo = new  GarantiaInfoDTO();
			
			if(!txtNombreGarante1.getText().isEmpty()) {
				garantiaInfo.setNumeroGarante1(txtGarante1.getText());
				garantiaInfo.setNombreGarante1(txtNombreGarante1.getText());
				garantiaInfo.setFechaVencimientoGarante1(fechaVencimientoGarante1);
			}
			if(!txtNombreGarante2.getText().isEmpty()) {
				garantiaInfo.setNumeroGarante2(txtGarante2.getText());
				garantiaInfo.setNombreGarante2(txtNombreGarante2.getText());
				garantiaInfo.setFechaVencimientoGarante2(fechaVencimientoGarante2);
			}
			if(!txtNombreGarante3.getText().isEmpty()) {
				garantiaInfo.setNumeroGarante3(txtGarante3.getText());
				garantiaInfo.setNombreGarante3(txtNombreGarante3.getText());
				garantiaInfo.setFechaVencimientoGarante3(fechaVencimientoGarante3);
			}
			
			garantiaInfo.setNumeroSocio(txtIdSocio.getText());
			garantiaInfo.setNombreSocio(txtSocio.getText());
			garantiaInfo.setNumeroGarantia(Integer.toString(garantiaId));
			garantiaInfo.setTipoGarantia(garantiaDescripcion);
			garantiaInfo.setSaldoComun(txtSaldo.getText());
		
			
			garantiaInfo.setFechaVencimientoSocio(socio.getFechaVencimiento());
			
			altaAyudaPesosFormulario.setGarantiaDto(garantiaInfo);
			return true;
		} else {
			mostrarMensaje("No puedes seleccionar el mismo garante más de una vez.");
			return false;
		}
		
		}catch(Exception e ) {
			mostrarMensaje("Ocurrio un error al gargar datos");
			return false;
		}
	}
	
	
	
	
	
	private void cargarGarante(JTextField currentTxt,
            @Nullable JTextField nextTxt,
            @Nullable JComboBox<Garantia> nextCbb,
            JTextField currentNombreTxt,Date fechaVencimiento ) {
		currentTxt.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				boolean ok = asignarGarante(currentTxt, currentNombreTxt,fechaVencimiento);
				if (ok) {
					if (nextTxt != null) {
						nextTxt.setEnabled(true);
						nextTxt.requestFocusInWindow();
					} else if (nextCbb != null) {
						nextCbb.setEnabled(true);
						nextCbb.requestFocusInWindow();
					}
				}
			}
			}
		});
}
	
	private Socio validarGarante(JTextField txtGarante) {
		if(!txtGarante.getText().isEmpty()) {
			try {
	        	
	         	int numSocGarante = Integer.parseInt(txtGarante.getText());
	         	Socio socioGarante = socioPruebaServicio.buscarSocioPorNumeroSocio(numSocGarante);
	        	
	        	if(socioGarante != null) {
	            	if(!GaranteIgualSocioActual(socioGarante.getNumeroSocio())) {
	            		if(validarGarantesSonDiferentes()) {		           
	            		
	            			SocioValidator validador = new SocioValidator();
	            	      	
	            	      	
	            	      	if(!validador.validateSocio(socioGarante)) {
	            	      		mostrarMensaje("Garante con datos faltantes o inválidos");
	            	      		return null;
	            	      	}
	            			return socioGarante;
	            		}
	            		else {
	            			mostrarMensaje("Garante ya seleccionado.");
	            			 txtGarante.setText("");
	            				return null;
	            		}
	            		
	            	}else {
	            		 mostrarMensaje("El Garante Seleccionado, es igual al Socio Actual,"
	            				 + " selecciona otro por favor.");
	            		 txtGarante.setText("");
	            		 return null;
	            	}
	            }else {
	            	mostrarMensaje("Socio no encontrado.");
	            	 txtGarante.setText("");
	            	 return null;
	            }
	        } catch (NumberFormatException ex) {
	            mostrarMensaje("Por favor, ingrese un número de Socio válido.");
	            txtGarante.setText("");
	            return null;
	        }
		}
	    return null;
	}
	
	private boolean asignarGarante(JTextField txtGarante, JTextField txtNombreGarante, Date fechaVencimiento) {
		   DecimalFormatSymbols symbols = new DecimalFormatSymbols();
      		symbols.setGroupingSeparator('.');
      		symbols.setDecimalSeparator(',');
      		DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
      	
		var socio = validarGarante(txtGarante);
		txtSaldo.setText(df.format(calcularSaldoTotal()));
		if(socio != null) {
	        	txtNombreGarante.setText("");
	        	txtNombreGarante.setText(socio.getApellidoNombre());	
	        	fechaVencimiento = socio.getFechaVencimiento();
	        	return true;
		}
         return false;	
	}
	
	 public void limpiarCampos() {
	        this.txtSocio.setText("");
	        this.txtGarante1.setText("");
	        this.txtGarante2.setText("");
	        this.txtGarante3.setText("");
	        this.txtNombreGarante1.setText("");
	        this.txtNombreGarante2.setText("");
	        this.txtNombreGarante3.setText("");
	        this.txtSaldo.setText("");
	    }
}
