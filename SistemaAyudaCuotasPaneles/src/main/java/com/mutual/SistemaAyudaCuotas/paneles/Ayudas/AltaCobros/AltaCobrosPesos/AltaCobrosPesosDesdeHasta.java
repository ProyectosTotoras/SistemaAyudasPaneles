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
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.dto.CobroCuotasRangoFechaCuotasDto;
import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.filtros.IntegerFilter;
import com.mutual.SistemaAyudaCuotas.paneles.MenuAyudas;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos.AltaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.servicio.ICobroCuotaAyudaPesosServicio;
import com.mutual.SistemaAyudaCuotas.servicio.ICuotaAyudaPesosServicio;

@Component
public class AltaCobrosPesosDesdeHasta extends JPanel {
	
	private String panelAnterior = "AltaCobrosCobrarVariasOUna";
	private JButton btnBusquedaSocios;
  
    //SERVICIOS
    private ICuotaAyudaPesosServicio cuotasServicio;
    private ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio;
    
    //PANELES
    private AltaCobrosPesosCobrarVariasCuotas altaCobrosPesosCobrarVariasCuotas;
  
    
    //ATRIBUTOS
    private AyudaPesos ayudaPesos;
    private List<CuotaAyudaPesos> listaCuotasAdeudadas;
    
    //COMPONENTES
    private JPanel contentPane;
    private JTextField txtDesde;
    private JTextField txtHasta;
    private JLabel lblDesde;
    private JLabel lblHasta;

	private CardLayout cardLayout; 
	private JPanel cardPanel;
	private JFrame mainFrame;
	
	public AltaCobrosPesosDesdeHasta(
			  ICuotaAyudaPesosServicio cuotasServicio, 
	            ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio
	         ) {
		this.cuotasServicio = cuotasServicio;
	    this.cobroCuotaAyudaPesosServicio = cobroCuotaAyudaPesosServicio;
	      
	    this.ayudaPesos = new AyudaPesos();
//		iniciar(null,null,null);
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
    	      	cargarCuotasNoCobradas();
    	        txtHasta.requestFocusInWindow();
    	        if(listaCuotasAdeudadas == null || listaCuotasAdeudadas.isEmpty()) {
             		JOptionPane.showMessageDialog(contentPane, "No hay cuotas disponibles para cobrar.", "Información", JOptionPane.INFORMATION_MESSAGE);            	
             	  	cardLayout.show(cardPanel, panelAnterior);
             		return;}
    	    }
    	});
        
    	JPanel mainPanel = new JPanel((LayoutManager) null);
    	mainPanel.setPreferredSize(new Dimension(450, 270));
    	mainPanel.setMaximumSize(new Dimension(450, 270));
    	mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    	mainPanel.setBackground(new Color(138, 217, 247));
    	mainPanel.getLayout();
    	add(mainPanel);
    	  
        txtDesde = new JTextField();
        txtDesde.setFocusable(false);
        txtDesde.setFont(new Font("72", Font.PLAIN, 17));
        txtDesde.setBounds(276, 71, 166, 23);
        mainPanel.add(txtDesde);
        txtDesde.setColumns(10);
        
        txtHasta = new JTextField();
        txtHasta.setFont(new Font("72", Font.PLAIN, 17));
        txtHasta.setColumns(10);
        txtHasta.setBounds(275, 102, 167, 23);
        mainPanel.add(txtHasta);
        
        // Agregar KeyListener para manejar la tecla Enter en txtHasta
        txtHasta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e ) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    abrirCobrarVariasCuota();                    
                }
            }
        });
        ((AbstractDocument) txtHasta.getDocument()).setDocumentFilter(new IntegerFilter());
		
        lblDesde = new JLabel("Pagar desde Cuota:");
        lblDesde.setFont(new Font("72", Font.PLAIN, 17));
        lblDesde.setBounds(107, 77, 159, 23);
        mainPanel.add(lblDesde);
        
        lblHasta = new JLabel("Hasta Cuota:");
        lblHasta.setFont(new Font("72", Font.PLAIN, 17));
        lblHasta.setBounds(157, 105, 117, 23);
        mainPanel.add(lblHasta);
   
          
    }
    //--------------ATRIBUTOS-----------------
   	public List<CuotaAyudaPesos> getListaCuotasAdeudadas() {
   		return listaCuotasAdeudadas;
   	}
   	
   	public void setListaCuotasAdeudadas(List<CuotaAyudaPesos> listaCuotasAdeudadas) {
   	    if (listaCuotasAdeudadas != null) {
   	        this.listaCuotasAdeudadas = listaCuotasAdeudadas;
   	    } else {
   	        this.listaCuotasAdeudadas = new ArrayList<CuotaAyudaPesos>();
   	    } 
   	    
   	   
   	}
       
//       //-------------METODOS-------------
//       public void limpiarDatos() {
//       	this.cobrarVariasCuota.limpiarDatos();
//       }
       
       private void abrirCobrarVariasCuota() {
           // Obtener los valores ingresados
           String desdeStr = txtDesde.getText().trim();
           String hastaStr = txtHasta.getText().trim();
           
           int desde = 0;
           int hasta = 0;
           
           // Validar que los campos no estén vacíos
           if(desdeStr.isEmpty() || hastaStr.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Por favor, ingrese ambos números de cuota.", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
               return;
           }
           
           try {
               desde = Integer.parseInt(desdeStr);
               hasta = Integer.parseInt(hastaStr);
           } catch (NumberFormatException nfe) {
               JOptionPane.showMessageDialog(this, "Los números de cuota deben ser enteros válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
               return;
           }
           
           // Validar el rango
           if(desde < 1 || hasta < 1) {
               JOptionPane.showMessageDialog(this, "Los números de cuota deben ser mayores o iguales a 1.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
               return;
           }
           if(desde > hasta) {
               JOptionPane.showMessageDialog(this, "La cuota 'Desde' no puede ser mayor que la cuota 'Hasta'.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
               return;
           }
          
           
           // Obtener el número total de cuotas no cobradas
           int totalCuotasNoCobradas = contarCuotasNoCobradas();
           
           if(hasta > totalCuotasNoCobradas) {
               JOptionPane.showMessageDialog(this, "La cuota 'Hasta' excede el número total de cuotas no cobradas (" + totalCuotasNoCobradas + ").", "Error de Validación", JOptionPane.ERROR_MESSAGE);
               return;
           }
           
           // Crear el DTO con el rango
           CobroCuotasRangoFechaCuotasDto cobroCuotasRangoFechaCuotasDto = new CobroCuotasRangoFechaCuotasDto();
           cobroCuotasRangoFechaCuotasDto.setDesde(desde);
           cobroCuotasRangoFechaCuotasDto.setHasta(hasta);
      
           if(listaCuotasAdeudadas == null || listaCuotasAdeudadas.isEmpty()) {        	
       		JOptionPane.showMessageDialog(contentPane, "No hay cuotas disponibles para cobrar.", "Información", JOptionPane.INFORMATION_MESSAGE);            	
       		return;
           }
       
           altaCobrosPesosCobrarVariasCuotas.setCobroRangoFechasDto(cobroCuotasRangoFechaCuotasDto);	
           altaCobrosPesosCobrarVariasCuotas.setListaCuotasAdeudadas(listaCuotasAdeudadas);
           altaCobrosPesosCobrarVariasCuotas.setAyudaPesos(ayudaPesos);
         
           cardLayout.show(cardPanel, "AltaCobrosPesosCobrarVariasCuotas");
       }
       
       private int contarCuotasNoCobradas() {
           Integer nroAyuda = ayudaPesos.getNroAyuda();
           if(nroAyuda == null) {
               return 0;
           }
           List<CuotaAyudaPesos> cuotas = cuotasServicio.buscarCuotasPorNumeroAyuda(nroAyuda);
           if(cuotas == null || cuotas.isEmpty()) {
               return 0;
           }
           int count = 0;
           for(CuotaAyudaPesos cuota : cuotas) {
               boolean yaCobrada = cobroCuotaAyudaPesosServicio.existeCobro(nroAyuda, cuota.getNumeroCuota());
               if(!yaCobrada) {
                   count++;
               }
           }
           return cuotas.getLast().getNumeroCuota();
       }
       
       private void cargarCuotasNoCobradas() {
           Integer nroAyuda = ayudaPesos.getNroAyuda();
           if(nroAyuda == null) {
                  return;
           }
           List<CuotaAyudaPesos> cuotas = cuotasServicio.buscarCuotasPorNumeroAyuda(nroAyuda);
           if(cuotas == null || cuotas.isEmpty()) {
               return;
           }
           
           // Encontrar la primera y última cuota no cobrada
           int primeraCuotaNoCobrada = -1;
           int ultimaCuotaNoCobrada = -1;
           for(CuotaAyudaPesos cuota : cuotas) {
               boolean yaCobrada = cobroCuotaAyudaPesosServicio.existeCobro(nroAyuda, cuota.getNumeroCuota());
               if(!yaCobrada) {
                   if(primeraCuotaNoCobrada == -1) {
                       primeraCuotaNoCobrada = cuota.getNumeroCuota();
                   }
                   ultimaCuotaNoCobrada = cuota.getNumeroCuota();
               }
           }
           
           if(primeraCuotaNoCobrada == -1) {
               JOptionPane.showMessageDialog(this, "Todas las cuotas de esta ayuda ya han sido cobradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
               txtDesde.setText("");
               txtHasta.setText("");

               cardLayout.show(cardPanel, panelAnterior);
               return;
           }
           
           // Asignar los valores a los campos
           txtDesde.setText(String.valueOf(primeraCuotaNoCobrada));
           txtHasta.setText(String.valueOf(ultimaCuotaNoCobrada));
           
           // Opcional: limitar el rango de 'hasta' según el total de cuotas no cobradas
           // Podrías agregar una etiqueta o información adicional para el usuario
       }
       
       public AyudaPesos getAyudaPesos() {
           return ayudaPesos;
       }
       
       public void setAyudaPesos(AyudaPesos ayudaPesos) {
           if (ayudaPesos != null) {
               this.ayudaPesos = ayudaPesos;
              
           } else {
               this.ayudaPesos = new AyudaPesos();
               txtDesde.setText("");
               txtHasta.setText("");
           } 
       }

	public void setAltaCobrosPesosCobrarVariasCuotas(
			AltaCobrosPesosCobrarVariasCuotas altaCobrosPesosCobrarVariasCuotas) {
		this.altaCobrosPesosCobrarVariasCuotas = altaCobrosPesosCobrarVariasCuotas;
	}
  
}
