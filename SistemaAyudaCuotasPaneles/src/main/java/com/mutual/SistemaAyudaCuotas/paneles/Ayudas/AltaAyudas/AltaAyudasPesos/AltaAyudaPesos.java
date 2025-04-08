package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos;

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

import javax.swing.border.BevelBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.entidades.Socio;
import com.mutual.SistemaAyudaCuotas.filtros.IntegerFilter;
import com.mutual.SistemaAyudaCuotas.servicio.ISocioServicio;
import com.mutual.SistemaAyudaCuotas.servicio.SocioServicio;
import com.mutual.SistemaAyudaCuotas.utilidades.SocioValidator;

@Component
@Scope("prototype")
public class AltaAyudaPesos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String panelAnterior = "AltaAyudaTipoAyuda";
	private JTextField txtSocio;
	private JTextField txtNombreSocio;
	private JTextField txtApoderado1;
	private JTextField txtApoderado2;
	private JTextField txtApoderado3;
	private JTextField txtRiesgo;
	
	private ISocioServicio socioServicio;
	
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JFrame mainFrame;

	private AltaAyudaPesosGarantes altaAyudaPesosGarantes;
	
	@Autowired
    public AltaAyudaPesos(
    		ISocioServicio socioServicio
    		) {
		
    	this.socioServicio = socioServicio;
    	
//    	iniciar(null,null,null);
    }
    
    public void iniciar(CardLayout cardLayout, JPanel cardPanel,JFrame mainFrame) {
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
    	        txtSocio.requestFocusInWindow();
    	    }
    	});
        
    	JPanel mainPanel = new JPanel((LayoutManager) null);
    	mainPanel.setPreferredSize(new Dimension(500, 400));
    	mainPanel.setMaximumSize(new Dimension(500, 400));
    	mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    	mainPanel.setBackground(UIManager.getColor("Button.background"));
    	add(mainPanel);
    	
    	txtSocio = new JTextField();
    	txtSocio.setFont(new Font("72", Font.PLAIN, 17));
    	txtSocio.setColumns(10);
    	txtSocio.setBounds(273, 34, 100, 23);
    	((javax.swing.text.AbstractDocument) txtSocio.getDocument()).setDocumentFilter(new IntegerFilter());
    	txtSocio.addKeyListener(new KeyAdapter() {
  			@Override
  			public void keyPressed(KeyEvent e) {
  				  if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
  					cargarSocioEnterListener();
  				 }
  				}
  			}); 
    	mainPanel.add(txtSocio);
    	
    	JLabel lblSocio = new JLabel("Introduce un Número de Socio: ");
    	lblSocio.setFont(new Font("72", Font.PLAIN, 16));
    	lblSocio.setBounds(29, 37, 234, 23);
    	mainPanel.add(lblSocio);
    	
    	JLabel lblSocioElegido = new JLabel("Socio Seleccionado:");
    	lblSocioElegido.setFont(new Font("72", Font.PLAIN, 16));
    	lblSocioElegido.setBounds(29, 65, 151, 23);
    	mainPanel.add(lblSocioElegido);
    	
    	txtNombreSocio = new JTextField();
    	txtNombreSocio.setFont(new Font("72", Font.PLAIN, 17));
    	txtNombreSocio.setFocusable(false);
    	txtNombreSocio.setBounds(190, 65, 283, 23);
    	mainPanel.add(txtNombreSocio);
    	
    	txtApoderado1 = new JTextField();
    	txtApoderado1.setFont(new Font("72", Font.PLAIN, 17));
    	txtApoderado1.setFocusable(false);
    	txtApoderado1.setBounds(157, 125, 305, 23);
    	mainPanel.add(txtApoderado1);
    	
    	JLabel lblApoderado1 = new JLabel("Apoderado 1:");
    	lblApoderado1.setFont(new Font("72", Font.PLAIN, 16));
    	lblApoderado1.setBounds(29, 131, 100, 23);
    	mainPanel.add(lblApoderado1);
    	
    	JLabel lblApoderado2 = new JLabel("Apoderado 2:");
    	lblApoderado2.setFont(new Font("72", Font.PLAIN, 16));
    	lblApoderado2.setBounds(29, 162, 100, 23);
    	mainPanel.add(lblApoderado2);
    	
    	txtApoderado2 = new JTextField();
    	txtApoderado2.setFont(new Font("72", Font.PLAIN, 17));
    	txtApoderado2.setFocusable(false);
    	txtApoderado2.setBounds(157, 156, 305, 23);
    	mainPanel.add(txtApoderado2);
    	
    	txtApoderado3 = new JTextField();
    	txtApoderado3.setFont(new Font("72", Font.PLAIN, 17));
    	txtApoderado3.setFocusable(false);
    	txtApoderado3.setBounds(157, 192, 305, 23);
    	mainPanel.add(txtApoderado3);
    	
    	JLabel lblApoderado3 = new JLabel("Apoderado 3:");
    	lblApoderado3.setFont(new Font("72", Font.PLAIN, 16));
    	lblApoderado3.setBounds(29, 195, 100, 23);
    	mainPanel.add(lblApoderado3);
    	
    	JLabel lblRiesgo = new JLabel("Riesgo: ");
    	lblRiesgo.setFont(new Font("72", Font.PLAIN, 16));
    	lblRiesgo.setBounds(29, 247, 60, 23);
    	mainPanel.add(lblRiesgo);
    	
    	txtRiesgo = new JTextField();
    	txtRiesgo.setFont(new Font("72", Font.PLAIN, 17));
    	txtRiesgo.setFocusable(false);
    	txtRiesgo.setBounds(110, 245, 151, 23);
    	mainPanel.add(txtRiesgo);
    	
    	JButton btnContinuar = new JButton("Continuar");
    	btnContinuar.setFont(new Font("72", Font.PLAIN, 17));
    	btnContinuar.setBounds(196, 340, 109, 23);
    	 btnContinuar.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
	                    continuar(cardLayout, cardPanel);
	                }
	            }
	        });
    	mainPanel.add(btnContinuar);
     
    }
    
    public void continuar(CardLayout cardLayout,JPanel cardPanel ) {
  	  String socioText = txtSocio.getText().trim();
  		var socio =  cargarSocio(socioText);
  		if(socio != null) {
//  			abrirAyudaPesosGarantes(socio);
  			altaAyudaPesosGarantes.setSocio(socio);
  			cardLayout.show(cardPanel, "AltaAyudaPesosGarantes");
  		}
    }
      
      private void cargarSocioEnterListener(){

			  String socioText = txtSocio.getText().trim();
			  var socio =  cargarSocio(socioText);
			  if(socio != null) {
				 
				  txtNombreSocio.setText(socio.getApellidoNombre());
				  
				  cargarApoderado(socio.getApodeSocio1(), txtApoderado1, "Apoderado 1");
				  cargarApoderado(socio.getApodeSocio2(), txtApoderado2, "Apoderado 2");
				  cargarApoderado(socio.getApodeSocio3(), txtApoderado3, "Apoderado 3");
				  
				  if(socio.getRiesgo() != null) txtRiesgo.setText(socio.getRiesgo()); 
				  
			  }				
      }
      
      private Socio cargarSocio(String socioText) {
      	
  		  	if (socioText.isEmpty()) {
  	            mostrarMensaje("Introduce un Número de Socio por favor.");
  	            return null;
  	        }
  		  	
  		  	limpiarDatos();
  		
  			int numeroSocio = 0;
  	      	try {
  	        	numeroSocio = Integer.parseInt(socioText);
  	      	} catch (NumberFormatException ex) {
  	        	mostrarMensaje("Por favor, ingrese un número de Socio válido.");
  	      	} 
  	        	
  	    
  	      	Socio socio = socioServicio.buscarSocioPorNumeroSocio(numeroSocio);
  	      	SocioValidator validador = new SocioValidator();
  	      	if (socio == null) {
  	      		mostrarMensaje("Socio no encontrado.");
  	      		limpiarDatos();
  	      		return null;
  	      	}
  	      	
  	      	if(!validador.validateSocio(socio)) {
  	      		mostrarMensaje("Datos de Socio faltantes o inválidos");
  	      		return null;
  	      	}
  	      	
  	      	
      	return socio;
      }
      
      private void cargarApoderado(Integer numeroApoderado, JTextField txtApoderado, String apoderadoLabel) {
          if (numeroApoderado != null) {
              Socio apoderado = socioServicio.buscarSocioPorNumeroSocio(numeroApoderado);
              if (apoderado != null) {
                  txtApoderado.setText(apoderado.getApellidoNombre());
              } else {
                  txtApoderado.setText("Apoderado no encontrado");
              }
          } else {
              txtApoderado.setText(""); // Clear the field if no Apoderado
          }
      }
      
      private void limpiarDatos() {
      	txtApoderado1.setText("");
      	txtApoderado2.setText("");
      	txtApoderado3.setText("");
      	txtRiesgo.setText("");
      	txtNombreSocio.setText("");
      }
      
  	private void mostrarMensaje(String mensaje){
          JOptionPane.showMessageDialog(this,mensaje);
      }
  	
  	public void borrarDatos() {
  		this.txtSocio.setText("");
  		this.txtApoderado1.setText("");
  		this.txtApoderado2.setText("");
  		this.txtApoderado3.setText("");
  		this.txtRiesgo.setText("");
  		this.txtNombreSocio.setText("");
  		
  	}

	public void setAltaAyudaPesosGarantes(AltaAyudaPesosGarantes altaAyudaPesosGarantes) {
		this.altaAyudaPesosGarantes = altaAyudaPesosGarantes;
	}
}
