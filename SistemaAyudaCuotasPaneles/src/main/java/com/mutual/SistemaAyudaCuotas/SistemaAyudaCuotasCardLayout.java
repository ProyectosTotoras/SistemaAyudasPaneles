package com.mutual.SistemaAyudaCuotas;
import org.springframework.beans.factory.ObjectFactory;

import javax.swing.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mutual.SistemaAyudaCuotas.paneles.MenuPrincipal;
import com.mutual.SistemaAyudaCuotas.entidades.Socio;
import com.mutual.SistemaAyudaCuotas.paneles.MenuAyudas;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudaTipoAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos.AltaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos.AltaAyudaPesosFormulario;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos.AltaAyudaPesosGarantes;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaCobros.AltaCobrosTipoAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaCobros.AltaCobrosPesos.AltaCobrosPesosAyudaPorAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaCobros.AltaCobrosPesos.AltaCobrosPesosAyudaPorSocio;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaCobros.AltaCobrosPesos.AltaCobrosPesosCobrarUnaCuota;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaCobros.AltaCobrosPesos.AltaCobrosPesosCobrarVariasCuotas;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaCobros.AltaCobrosPesos.AltaCobrosPesosCobrarVariasOUna;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaCobros.AltaCobrosPesos.AltaCobrosPesosDesdeHasta;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaCobros.AltaCobrosPesos.AltaCobrosPesosPantallaAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaCobros.AltaCobrosPesos.AltaCobrosPesosTipoBusqueda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AnularAyudasALegales.AnularAyudasALegalesTipoAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AnularAyudasALegales.AnularAyudasALegalesPesos.AnularAyudasALegalesPesosPantAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AnularAyudasALegales.AnularAyudasALegalesPesos.AnularAyudasALegalesPesosPantallaSocio;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AnularAyudasALegales.AnularAyudasALegalesPesos.AnularAyudasALegalesPesosPorAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AnularAyudasALegales.AnularAyudasALegalesPesos.AnularAyudasALegalesPesosPorSocio;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AnularAyudasALegales.AnularAyudasALegalesPesos.AnularAyudasALegalesPesosTipoBusqueda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AyudasALegales.AyudasALegalesTipoAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AyudasALegales.AyudasALegalesPesos.AyudasALegalesPesosPantallaAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AyudasALegales.AyudasALegalesPesos.AyudasALegalesPesosPantallaSocio;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AyudasALegales.AyudasALegalesPesos.AyudasALegalesPesosPorAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AyudasALegales.AyudasALegalesPesos.AyudasALegalesPesosPorSocio;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AyudasALegales.AyudasALegalesPesos.AyudasALegalesPesosTipoBusqueda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaAyudas.ConsultaBajaAyudaTipoAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaAyudas.ConsultaBajaAyudasPesos.ConsultaBajaAyudaPantallaAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaAyudas.ConsultaBajaAyudasPesos.ConsultaBajaAyudaPantallaSocio;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaAyudas.ConsultaBajaAyudasPesos.ConsultaBajaAyudaPorAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaAyudas.ConsultaBajaAyudasPesos.ConsultaBajaAyudaPorSocio;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaAyudas.ConsultaBajaAyudasPesos.ConsultaBajaAyudaTipoBusqueda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaCobros.ConsultaBajaCobrosTipoAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaCobros.ConsultaBajaCobrosPesos.ConsultaBajaCobrosConsultaCobro;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaCobros.ConsultaBajaCobrosPesos.ConsultaBajaCobrosPantallaAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaCobros.ConsultaBajaCobrosPesos.ConsultaBajaCobrosPantallaSocio;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaCobros.ConsultaBajaCobrosPesos.ConsultaBajaCobrosPorAyuda;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaCobros.ConsultaBajaCobrosPesos.ConsultaBajaCobrosPorSocio;
import com.mutual.SistemaAyudaCuotas.paneles.Ayudas.ConsultaBajaCobros.ConsultaBajaCobrosPesos.ConsultaBajaCobrosTipoBusqueda;
import com.mutual.SistemaAyudaCuotas.paneles.ListadosVarios.ListadosVarios;
import com.mutual.SistemaAyudaCuotas.servicio.ISocioServicio;
import com.mutual.SistemaAyudaCuotas.servicio.SocioServicio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Stack;

@Component
@Scope("prototype")
public class SistemaAyudaCuotasCardLayout extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
    private JPanel cardPanel;
    
    //LISTADOS VARIOS
    private final ObjectFactory<ListadosVarios> listadosVariosFactory;
    private final ListadosVarios listadosVarios;
   
    //ALTAS
    private final ObjectFactory<AltaAyudaTipoAyuda> altaAyudaTipoAyudaFactory;
    private final AltaAyudaTipoAyuda altaAyudaTipoAyuda;
    
    private final ObjectFactory<AltaAyudaPesos> altaAyudaPesosFactory;
    private final AltaAyudaPesos altaAyudaPesos;
    
    private final ObjectFactory<AltaAyudaPesosGarantes> altaAyudaPesosGarantesFactory;
    private final AltaAyudaPesosGarantes altaAyudaPesosGarantes;
    
    private final ObjectFactory<AltaAyudaPesosFormulario> altaAyudaPesosFormularioFactory;
    private final AltaAyudaPesosFormulario altaAyudaPesosFormulario;
    
    //CONSULTA
    private final ObjectFactory<ConsultaBajaAyudaTipoAyuda> consultaBajaAyudaTipoAyudaFactory;
    private final ConsultaBajaAyudaTipoAyuda consultaBajaAyudaTipoAyuda;
    
    private final ObjectFactory<ConsultaBajaAyudaTipoBusqueda> consultaBajaAyudaTipoBusquedaFactory;
    private final ConsultaBajaAyudaTipoBusqueda consultaBajaAyudaTipoBusqueda;
    
    private final ObjectFactory<ConsultaBajaAyudaPorSocio> consultaBajaAyudaPorSocioFactory;
    private final ConsultaBajaAyudaPorSocio consultaBajaAyudaPorSocio;
    
    private final ObjectFactory<ConsultaBajaAyudaPorAyuda> consultaBajaAyudaPorAyudaFactory;
    private final ConsultaBajaAyudaPorAyuda consultaBajaAyudaPorAyuda;
    
    private final ObjectFactory<ConsultaBajaAyudaPantallaSocio> consultaBajaAyudaPantallaSocioFactory;
    private final ConsultaBajaAyudaPantallaSocio consultaBajaAyudaPantallaSocio;
    
    private final ObjectFactory<ConsultaBajaAyudaPantallaAyuda> consultaBajaAyudaPantallaAyudaFactory;
    private final ConsultaBajaAyudaPantallaAyuda consultaBajaAyudaPantallaAyuda;
    
    //COBROS
    private final ObjectFactory<AltaCobrosTipoAyuda> altaCobrosTipoAyudaFactory;
    private final AltaCobrosTipoAyuda altaCobrosTipoAyuda;
    
    private final ObjectFactory<AltaCobrosPesosTipoBusqueda> altaCobrosPesosTipoBusquedaFactory;
    private final AltaCobrosPesosTipoBusqueda altaCobrosPesosTipoBusqueda;
    
    private final ObjectFactory<AltaCobrosPesosAyudaPorSocio> altaCobrosPesosAyudaPorSocioFactory;
    private final AltaCobrosPesosAyudaPorSocio altaCobrosPesosAyudaPorSocio;
    
    private final ObjectFactory<AltaCobrosPesosAyudaPorAyuda> altaCobrosPesosAyudaPorAyudaFactory;
    private final AltaCobrosPesosAyudaPorAyuda altaCobrosPesosAyudaPorAyuda;
    
    private final ObjectFactory<AltaCobrosPesosPantallaAyuda> altaCobrosPesosPantallaAyudaFactory;
    private final AltaCobrosPesosPantallaAyuda altaCobrosPesosPantallaAyuda;
    
    private final ObjectFactory<AltaCobrosPesosCobrarVariasOUna> altaCobrosPesosCobrarVariasOUnaFactory;
    private final AltaCobrosPesosCobrarVariasOUna altaCobrosPesosCobrarVariasOUna;
    
    private final ObjectFactory<AltaCobrosPesosCobrarUnaCuota> altaCobrosPesosCobrarUnaCuotaFactory;
    private final AltaCobrosPesosCobrarUnaCuota altaCobrosPesosCobrarUnaCuota;
    
    private final ObjectFactory<AltaCobrosPesosCobrarVariasCuotas> altaCobrosPesosCobrarVariasCuotasFactory;
    private final AltaCobrosPesosCobrarVariasCuotas altaCobrosPesosCobrarVariasCuotas;
    
    private final ObjectFactory<AltaCobrosPesosDesdeHasta> altaCobrosPesosDesdeHastaFactory;
    private final AltaCobrosPesosDesdeHasta altaCobrosPesosDesdeHasta;
    
    //CONSULTA BAJA COBROS
    private final ObjectFactory<ConsultaBajaCobrosTipoAyuda> consultaBajaCobrosTipoAyudaFactory;
    private final ConsultaBajaCobrosTipoAyuda consultaBajaCobrosTipoAyuda;
    
    private final ObjectFactory<ConsultaBajaCobrosTipoBusqueda> consultaBajaCobrosTipoBusquedaFactory;
    private final ConsultaBajaCobrosTipoBusqueda consultaBajaCobrosTipoBusqueda;
    
    private final ObjectFactory<ConsultaBajaCobrosPorSocio> consultaBajaCobrosPorSocioFactory;
    private final ConsultaBajaCobrosPorSocio consultaBajaCobrosPorSocio;
    
    private final ObjectFactory<ConsultaBajaCobrosPorAyuda> consultaBajaCobrosPorAyudaFactory;
    private final ConsultaBajaCobrosPorAyuda consultaBajaCobrosPorAyuda;
    
    private final ObjectFactory<ConsultaBajaCobrosPantallaSocio> consultaBajaCobrosPantallaSocioFactory;
    private final ConsultaBajaCobrosPantallaSocio consultaBajaCobrosPantallaSocio;
    
    private final ObjectFactory<ConsultaBajaCobrosPantallaAyuda> consultaBajaCobrosPantallaAyudaFactory;
    private final ConsultaBajaCobrosPantallaAyuda consultaBajaCobrosPantallaAyuda;
    
    private final ObjectFactory<ConsultaBajaCobrosConsultaCobro> consultaBajaCobrosConsultaCobroFactory;
    private final ConsultaBajaCobrosConsultaCobro consultaBajaCobrosConsultaCobro;
    
    
  //AYUDAS A LEGALES
    private final ObjectFactory<AyudasALegalesTipoAyuda> ayudasALegalesTipoAyudaFactory;
    private final  AyudasALegalesTipoAyuda ayudasALegalesTipoAyuda;
    
    private final ObjectFactory<AyudasALegalesPesosTipoBusqueda> ayudasALegalesPesosTipoBusquedaFactory;
    private final AyudasALegalesPesosTipoBusqueda ayudasALegalesPesosTipoBusqueda;
    
    private final ObjectFactory<AyudasALegalesPesosPorAyuda> ayudasALegalesPesosPorAyudaFactory;
    private final AyudasALegalesPesosPorAyuda ayudasALegalesPesosPorAyuda;
    
    private final ObjectFactory<AyudasALegalesPesosPorSocio> ayudasALegalesPesosPorSocioFactory;
    private final AyudasALegalesPesosPorSocio ayudasALegalesPesosPorSocio;
    
    private final ObjectFactory<AyudasALegalesPesosPantallaSocio> ayudasALegalesPesosPantallaSocioFactory;
    private final AyudasALegalesPesosPantallaSocio ayudasALegalesPesosPantallaSocio;
    
    private final ObjectFactory<AyudasALegalesPesosPantallaAyuda> ayudasALegalesPesosPantallaAyudaFactory;
    private final AyudasALegalesPesosPantallaAyuda ayudasALegalesPesosPantallaAyuda;
 
    //AYUDAS A LEGALES
    private final ObjectFactory<AnularAyudasALegalesTipoAyuda> anularAyudasALegalesTipoAyudaFactory;
    private final  AnularAyudasALegalesTipoAyuda anularAyudasALegalesTipoAyuda;
    
    private final ObjectFactory<AnularAyudasALegalesPesosTipoBusqueda> anularAyudasALegalesPesosTipoBusquedaFactory;
    private final AnularAyudasALegalesPesosTipoBusqueda anularAyudasALegalesPesosTipoBusqueda;
    
    private final ObjectFactory<AnularAyudasALegalesPesosPorAyuda> anularAyudasALegalesPesosPorAyudaFactory;
    private final AnularAyudasALegalesPesosPorAyuda anularAyudasALegalesPesosPorAyuda;
    
    private final ObjectFactory<AnularAyudasALegalesPesosPorSocio> anularAyudasALegalesPesosPorSocioFactory;
    private final AnularAyudasALegalesPesosPorSocio anularAyudasALegalesPesosPorSocio;
    
    private final ObjectFactory<AnularAyudasALegalesPesosPantallaSocio> anularAyudasALegalesPesosPantallaSocioFactory;
    private final AnularAyudasALegalesPesosPantallaSocio anularAyudasALegalesPesosPantallaSocio;
    
    private final ObjectFactory<AnularAyudasALegalesPesosPantAyuda> anularAyudasALegalesPesosPantallaAyudaFactory;
    private final AnularAyudasALegalesPesosPantAyuda anularAyudasALegalesPesosPantallaAyuda;
    
    public SistemaAyudaCuotasCardLayout(

    		ObjectFactory<ListadosVarios> listadosVariosFactory,
    		 ListadosVarios listadosVarios,
    		
        	//-------------------------- ALTA AYUDAS
        	
    		ObjectFactory<AltaAyudaTipoAyuda> altaAyudaTipoAyudaFactory,
    		AltaAyudaTipoAyuda altaAyudaTipoAyuda,
    		
    		ObjectFactory<AltaAyudaPesos> altaAyudaPesosFactory,
    		AltaAyudaPesos altaAyudaPesos,
    	    
    		ObjectFactory<AltaAyudaPesosGarantes> altaAyudaPesosGarantesFactory,
    	    AltaAyudaPesosGarantes altaAyudaPesosGarantes,
    	    
    	    ObjectFactory<AltaAyudaPesosFormulario> altaAyudaPesosFormularioFactory,
    	    AltaAyudaPesosFormulario altaAyudaPesosFormulario,

        	//-------------------------- CONSULTA BAJA AYUDAS
        	
    	    ObjectFactory<ConsultaBajaAyudaTipoAyuda> consultaBajaAyudaTipoAyudaFactory,
    	    ConsultaBajaAyudaTipoAyuda consultaBajaAyudaTipoAyuda,
    	    
    	    ObjectFactory<ConsultaBajaAyudaTipoBusqueda> consultaBajaAyudaTipoBusquedaFactory,
    	    ConsultaBajaAyudaTipoBusqueda consultaBajaAyudaTipoBusqueda,
    	    
    	    ObjectFactory<ConsultaBajaAyudaPorSocio> consultaBajaAyudaPorSocioFactory,
    	    ConsultaBajaAyudaPorSocio consultaBajaAyudaPorSocio,
    	    
    	    ObjectFactory<ConsultaBajaAyudaPorAyuda> consultaBajaAyudaPorAyudaFactory,
    	    ConsultaBajaAyudaPorAyuda consultaBajaAyudaPorAyuda,
    	    
    	    ObjectFactory<ConsultaBajaAyudaPantallaSocio> consultaBajaAyudaPantallaSocioFactory,
    	    ConsultaBajaAyudaPantallaSocio consultaBajaAyudaPantallaSocio,
    	    
    	    ObjectFactory<ConsultaBajaAyudaPantallaAyuda> consultaBajaAyudaPantallaAyudaFactory,
    	    ConsultaBajaAyudaPantallaAyuda consultaBajaAyudaPantallaAyuda,

        	//-------------------------- ALTA COBROS CUOTAS
        	
    	    ObjectFactory<AltaCobrosTipoAyuda> altaCobrosTipoAyudaFactory,
    	    AltaCobrosTipoAyuda altaCobrosTipoAyuda,
    	    
    	    ObjectFactory<AltaCobrosPesosTipoBusqueda> altaCobrosPesosTipoBusquedaFactory,
    	    AltaCobrosPesosTipoBusqueda altaCobrosPesosTipoBusqueda,
    	    
    	    ObjectFactory<AltaCobrosPesosAyudaPorSocio> altaCobrosPesosAyudaPorSocioFactory,
    	    AltaCobrosPesosAyudaPorSocio altaCobrosPesosAyudaPorSocio,
    	    
    	    ObjectFactory<AltaCobrosPesosAyudaPorAyuda> altaCobrosPesosAyudaPorAyudaFactory,
    	    AltaCobrosPesosAyudaPorAyuda altaCobrosPesosAyudaPorAyuda,
    	    
    	    ObjectFactory<AltaCobrosPesosPantallaAyuda> altaCobrosPesosPantallaAyudaFactory,
    	    AltaCobrosPesosPantallaAyuda altaCobrosPesosPantallaAyuda,
    	    
    	    ObjectFactory<AltaCobrosPesosCobrarVariasOUna> altaCobrosPesosCobrarVariasOUnaFactory,
    	    AltaCobrosPesosCobrarVariasOUna altaCobrosPesosCobrarVariasOUna,
    	    
    	    ObjectFactory<AltaCobrosPesosCobrarUnaCuota> altaCobrosPesosCobrarUnaCuotaFactory,
    	    AltaCobrosPesosCobrarUnaCuota altaCobrosPesosCobrarUnaCuota,
    	    
    	    ObjectFactory<AltaCobrosPesosCobrarVariasCuotas> altaCobrosPesosCobrarVariasCuotasFactory,
    	    AltaCobrosPesosCobrarVariasCuotas altaCobrosPesosCobrarVariasCuotas,
    	    
    	    ObjectFactory<AltaCobrosPesosDesdeHasta> altaCobrosPesosDesdeHastaFactory,
    	    AltaCobrosPesosDesdeHasta altaCobrosPesosDesdeHasta,

        	//-------------------------- CONSULTA BAJA COBROS
        	
    	    ObjectFactory<ConsultaBajaCobrosTipoAyuda> consultaBajaCobrosTipoAyudaFactory,
    	    ConsultaBajaCobrosTipoAyuda consultaBajaCobrosTipoAyuda,
    	    
    	    ObjectFactory<ConsultaBajaCobrosTipoBusqueda> consultaBajaCobrosTipoBusquedaFactory,
    	    ConsultaBajaCobrosTipoBusqueda consultaBajaCobrosTipoBusqueda,
    	    
    	    ObjectFactory<ConsultaBajaCobrosPorSocio> consultaBajaCobrosPorSocioFactory,
    	    ConsultaBajaCobrosPorSocio consultaBajaCobrosPorSocio,
    	    
    	    ObjectFactory<ConsultaBajaCobrosPorAyuda> consultaBajaCobrosPorAyudaFactory,
    	    ConsultaBajaCobrosPorAyuda consultaBajaCobrosPorAyuda,
    	    
    	    ObjectFactory<ConsultaBajaCobrosPantallaSocio> consultaBajaCobrosPantallaSocioFactory,
    	    ConsultaBajaCobrosPantallaSocio consultaBajaCobrosPantallaSocio,
    	    
    	    ObjectFactory<ConsultaBajaCobrosPantallaAyuda> consultaBajaCobrosPantallaAyudaFactory,
    	    ConsultaBajaCobrosPantallaAyuda consultaBajaCobrosPantallaAyuda,
    	    
    	    ObjectFactory<ConsultaBajaCobrosConsultaCobro> consultaBajaCobrosConsultaCobroFactory,
    	    ConsultaBajaCobrosConsultaCobro consultaBajaCobrosConsultaCobro,
    	    
        	//-------------------------- AYUDAS A LEGALES

    	    ObjectFactory<AyudasALegalesTipoAyuda> ayudasALegalesTipoAyudaFactory,
    	    AyudasALegalesTipoAyuda ayudasALegalesTipoAyuda,
    	    
    	    ObjectFactory<AyudasALegalesPesosTipoBusqueda> ayudasALegalesPesosTipoBusquedaFactory,
    	    AyudasALegalesPesosTipoBusqueda ayudasALegalesPesosTipoBusqueda,
    	    
    	    ObjectFactory<AyudasALegalesPesosPorAyuda> ayudasALegalesPesosPorAyudaFactory,
    	    AyudasALegalesPesosPorAyuda ayudasALegalesPesosPorAyuda,
    	    
    	    ObjectFactory<AyudasALegalesPesosPorSocio> ayudasALegalesPesosPorSocioFactory,
    	    AyudasALegalesPesosPorSocio ayudasALegalesPesosPorSocio,
    	    
    	    ObjectFactory<AyudasALegalesPesosPantallaSocio> ayudasALegalesPesosPantallaSocioFactory,
    	    AyudasALegalesPesosPantallaSocio ayudasALegalesPesosPantallaSocio,
    	    
    	    ObjectFactory<AyudasALegalesPesosPantallaAyuda> ayudasALegalesPesosPantallaAyudaFactory,
    	    AyudasALegalesPesosPantallaAyuda ayudasALegalesPesosPantallaAyuda,

        	//-------------------------- ANULAR AYUDAS A LEGALES
    	    
    	    ObjectFactory<AnularAyudasALegalesTipoAyuda> anularAyudasALegalesTipoAyudaFactory,
    	    AnularAyudasALegalesTipoAyuda anularAyudasALegalesTipoAyuda,
    	    
    	    ObjectFactory<AnularAyudasALegalesPesosTipoBusqueda> anularAyudasALegalesPesosTipoBusquedaFactory,
    	    AnularAyudasALegalesPesosTipoBusqueda anularAyudasALegalesPesosTipoBusqueda,
    	    
    	    ObjectFactory<AnularAyudasALegalesPesosPorAyuda> anularAyudasALegalesPesosPorAyudaFactory,
    	    AnularAyudasALegalesPesosPorAyuda anularAyudasALegalesPesosPorAyuda,
    	    
    	    ObjectFactory<AnularAyudasALegalesPesosPorSocio> anularAyudasALegalesPesosPorSocioFactory,
    	    AnularAyudasALegalesPesosPorSocio anularAyudasALegalesPesosPorSocio,
    	    
    	    ObjectFactory<AnularAyudasALegalesPesosPantallaSocio> anularAyudasALegalesPesosPantallaSocioFactory,
    	    AnularAyudasALegalesPesosPantallaSocio anularAyudasALegalesPesosPantallaSocio,
    	    
    	    ObjectFactory<AnularAyudasALegalesPesosPantAyuda> anularAyudasALegalesPesosPantallaAyudaFactory,
    	    AnularAyudasALegalesPesosPantAyuda anularAyudasALegalesPesosPantallaAyuda
    	    
    	    
    		){

    	this.listadosVariosFactory = listadosVariosFactory;
    	this.listadosVarios = listadosVariosFactory.getObject();
    	
    	//--------------------------
    	
    	this.altaAyudaTipoAyudaFactory = altaAyudaTipoAyudaFactory;
    	this.altaAyudaTipoAyuda = altaAyudaTipoAyudaFactory.getObject();

    	this.altaAyudaPesosFactory = altaAyudaPesosFactory;
    	this.altaAyudaPesos = altaAyudaPesosFactory.getObject();
     
    	this.altaAyudaPesosGarantesFactory = altaAyudaPesosGarantesFactory;
    	this.altaAyudaPesosGarantes = altaAyudaPesosGarantesFactory.getObject();
       
    	this.altaAyudaPesosFormularioFactory = altaAyudaPesosFormularioFactory;
    	this.altaAyudaPesosFormulario = altaAyudaPesosFormularioFactory.getObject();

    	//--------------------------
    	
    	this.consultaBajaAyudaTipoAyudaFactory = consultaBajaAyudaTipoAyudaFactory;
    	this.consultaBajaAyudaTipoAyuda = consultaBajaAyudaTipoAyudaFactory.getObject();
    	
    	this.consultaBajaAyudaTipoBusquedaFactory = consultaBajaAyudaTipoBusquedaFactory;
    	this.consultaBajaAyudaTipoBusqueda = consultaBajaAyudaTipoBusquedaFactory.getObject();
    	
    	this.consultaBajaAyudaPorSocioFactory = consultaBajaAyudaPorSocioFactory;
    	this.consultaBajaAyudaPorSocio = consultaBajaAyudaPorSocioFactory.getObject();
    	
    	this.consultaBajaAyudaPorAyudaFactory = consultaBajaAyudaPorAyudaFactory;
    	this.consultaBajaAyudaPorAyuda = consultaBajaAyudaPorAyudaFactory.getObject();
    	
    	this.consultaBajaAyudaPantallaSocioFactory = consultaBajaAyudaPantallaSocioFactory;
    	this.consultaBajaAyudaPantallaSocio = consultaBajaAyudaPantallaSocioFactory.getObject();
    	
    	this.consultaBajaAyudaPantallaAyudaFactory = consultaBajaAyudaPantallaAyudaFactory;
    	this.consultaBajaAyudaPantallaAyuda = consultaBajaAyudaPantallaAyudaFactory.getObject();

    	//--------------------------
    	
    	this.altaCobrosTipoAyudaFactory = altaCobrosTipoAyudaFactory;
    	this.altaCobrosTipoAyuda = altaCobrosTipoAyudaFactory.getObject();
        
    	this.altaCobrosPesosTipoBusquedaFactory = altaCobrosPesosTipoBusquedaFactory;
    	this.altaCobrosPesosTipoBusqueda = altaCobrosPesosTipoBusquedaFactory.getObject();
        
    	this.altaCobrosPesosAyudaPorSocioFactory = altaCobrosPesosAyudaPorSocioFactory;
    	this.altaCobrosPesosAyudaPorSocio = altaCobrosPesosAyudaPorSocioFactory.getObject();
        
    	this.altaCobrosPesosAyudaPorAyudaFactory = altaCobrosPesosAyudaPorAyudaFactory;
    	this.altaCobrosPesosAyudaPorAyuda = altaCobrosPesosAyudaPorAyudaFactory.getObject();
        
    	this.altaCobrosPesosPantallaAyudaFactory = altaCobrosPesosPantallaAyudaFactory;
    	this.altaCobrosPesosPantallaAyuda = altaCobrosPesosPantallaAyudaFactory.getObject();
        
    	this.altaCobrosPesosCobrarVariasOUnaFactory = altaCobrosPesosCobrarVariasOUnaFactory;
    	this.altaCobrosPesosCobrarVariasOUna = altaCobrosPesosCobrarVariasOUnaFactory.getObject();
        
    	this.altaCobrosPesosCobrarUnaCuotaFactory = altaCobrosPesosCobrarUnaCuotaFactory;
    	this.altaCobrosPesosCobrarUnaCuota = altaCobrosPesosCobrarUnaCuotaFactory.getObject();
        
    	this.altaCobrosPesosCobrarVariasCuotasFactory = altaCobrosPesosCobrarVariasCuotasFactory;
    	this.altaCobrosPesosCobrarVariasCuotas = altaCobrosPesosCobrarVariasCuotasFactory.getObject();
    
    	this.altaCobrosPesosDesdeHastaFactory = altaCobrosPesosDesdeHastaFactory;
    	this.altaCobrosPesosDesdeHasta = altaCobrosPesosDesdeHastaFactory.getObject();
    	
    	//--------------------------
    	
    	this.consultaBajaCobrosTipoAyudaFactory = consultaBajaCobrosTipoAyudaFactory;
    	this.consultaBajaCobrosTipoAyuda = consultaBajaCobrosTipoAyudaFactory.getObject();
        
    	this.consultaBajaCobrosTipoBusquedaFactory = consultaBajaCobrosTipoBusquedaFactory;
    	this.consultaBajaCobrosTipoBusqueda =consultaBajaCobrosTipoBusquedaFactory.getObject() ;
        
    	this.consultaBajaCobrosPorSocioFactory = consultaBajaCobrosPorSocioFactory;
    	this.consultaBajaCobrosPorSocio = consultaBajaCobrosPorSocioFactory.getObject();
        
    	this.consultaBajaCobrosPorAyudaFactory = consultaBajaCobrosPorAyudaFactory;
    	this.consultaBajaCobrosPorAyuda = consultaBajaCobrosPorAyudaFactory.getObject();
        
    	this.consultaBajaCobrosPantallaSocioFactory = consultaBajaCobrosPantallaSocioFactory;
    	this.consultaBajaCobrosPantallaSocio = consultaBajaCobrosPantallaSocioFactory.getObject();
        
    	this.consultaBajaCobrosPantallaAyudaFactory = consultaBajaCobrosPantallaAyudaFactory;
    	this.consultaBajaCobrosPantallaAyuda = consultaBajaCobrosPantallaAyudaFactory.getObject();
        
    	this.consultaBajaCobrosConsultaCobroFactory = consultaBajaCobrosConsultaCobroFactory;
    	this.consultaBajaCobrosConsultaCobro = consultaBajaCobrosConsultaCobroFactory.getObject();
      	
    	//--------------------------
    	
    	this.ayudasALegalesTipoAyudaFactory = ayudasALegalesTipoAyudaFactory;
		this.ayudasALegalesTipoAyuda = ayudasALegalesTipoAyudaFactory.getObject();
		
		this.ayudasALegalesPesosTipoBusquedaFactory = ayudasALegalesPesosTipoBusquedaFactory;
		this.ayudasALegalesPesosTipoBusqueda = ayudasALegalesPesosTipoBusquedaFactory.getObject();
		
		this.ayudasALegalesPesosPorAyudaFactory = ayudasALegalesPesosPorAyudaFactory;
		this.ayudasALegalesPesosPorAyuda = ayudasALegalesPesosPorAyudaFactory.getObject();
		
		this.ayudasALegalesPesosPorSocioFactory = ayudasALegalesPesosPorSocioFactory;
		this.ayudasALegalesPesosPorSocio = ayudasALegalesPesosPorSocioFactory.getObject();
		
		this.ayudasALegalesPesosPantallaSocioFactory = ayudasALegalesPesosPantallaSocioFactory;
		this.ayudasALegalesPesosPantallaSocio = ayudasALegalesPesosPantallaSocioFactory.getObject();
		
		this.ayudasALegalesPesosPantallaAyudaFactory = ayudasALegalesPesosPantallaAyudaFactory;
		this.ayudasALegalesPesosPantallaAyuda = ayudasALegalesPesosPantallaAyudaFactory.getObject();
		
		//--------------------------
		
		this.anularAyudasALegalesTipoAyudaFactory = anularAyudasALegalesTipoAyudaFactory;
		this.anularAyudasALegalesTipoAyuda = anularAyudasALegalesTipoAyudaFactory.getObject();
		
		this.anularAyudasALegalesPesosTipoBusquedaFactory = anularAyudasALegalesPesosTipoBusquedaFactory;
		this.anularAyudasALegalesPesosTipoBusqueda = anularAyudasALegalesPesosTipoBusquedaFactory.getObject();
		
		this.anularAyudasALegalesPesosPorAyudaFactory = anularAyudasALegalesPesosPorAyudaFactory;
		this.anularAyudasALegalesPesosPorAyuda = anularAyudasALegalesPesosPorAyudaFactory.getObject();
		
		this.anularAyudasALegalesPesosPorSocioFactory = anularAyudasALegalesPesosPorSocioFactory;
		this.anularAyudasALegalesPesosPorSocio = anularAyudasALegalesPesosPorSocioFactory.getObject();
		
		this.anularAyudasALegalesPesosPantallaSocioFactory = anularAyudasALegalesPesosPantallaSocioFactory;
		this.anularAyudasALegalesPesosPantallaSocio = anularAyudasALegalesPesosPantallaSocioFactory.getObject();
		
		this.anularAyudasALegalesPesosPantallaAyudaFactory = anularAyudasALegalesPesosPantallaAyudaFactory;
		this.anularAyudasALegalesPesosPantallaAyuda = anularAyudasALegalesPesosPantallaAyudaFactory.getObject();
	
    }
    
    public void iniciar() {
    	 setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/static/imagenes/ufc_icon.jpg"))); 
    	 setTitle("CardLayout Example");
         setLocationRelativeTo(this);
         setExtendedState(JFrame.MAXIMIZED_BOTH);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
         //LAYOUT
         cardLayout = new CardLayout();
         cardPanel = new JPanel(cardLayout);
         cardPanel.setPreferredSize(new Dimension(1200, 740));
         
         //-----------MENUS-----------------
         //MENU PRINCIPAL
         MenuPrincipal menuPrincipal = new MenuPrincipal(cardLayout, cardPanel, this);
         FlowLayout flowLayout = (FlowLayout) menuPrincipal.getLayout();
         flowLayout.setVgap(100);
         flowLayout.setAlignOnBaseline(true);
         cardPanel.add(menuPrincipal, "MenuPrincipal");
         //MENU AYUDAS
         MenuAyudas menuAyudas = new MenuAyudas(cardLayout, cardPanel, this);
         FlowLayout flowLayout_1 = (FlowLayout) menuAyudas.getLayout();
         flowLayout_1.setVgap(100);
         cardPanel.add(menuAyudas, "MenuAyudas");
         
         //-----------ALTAAYUDAS-------------
         
         listadosVarios.iniciar(cardLayout, cardPanel, this);        
         FlowLayout listadosVariosLayout = (FlowLayout) listadosVarios.getLayout();
         listadosVariosLayout.setVgap(100);
         cardPanel.add(listadosVarios, "ListadosVarios");
         
         
         altaAyudaTipoAyuda.iniciar(cardLayout, cardPanel, this);
         altaAyudaTipoAyuda.setMenuAyudas(menuAyudas);
         altaAyudaTipoAyuda.setAltaAyudaPesos(altaAyudaPesos);
         FlowLayout altaAyudaTipoAyudaLayout = (FlowLayout) altaAyudaTipoAyuda.getLayout();
         altaAyudaTipoAyudaLayout.setVgap(100);
         cardPanel.add(altaAyudaTipoAyuda, "AltaAyudaTipoAyuda");
         
         altaAyudaPesos.iniciar(cardLayout, cardPanel, this);
         altaAyudaPesos.setAltaAyudaPesosGarantes(altaAyudaPesosGarantes); 
         FlowLayout altaAyudaPesosLayout = (FlowLayout) altaAyudaTipoAyuda.getLayout();
         altaAyudaPesosLayout.setVgap(100);
         cardPanel.add(altaAyudaPesos, "AltaAyudaPesos");
         
         altaAyudaPesosGarantes.setAltaAyudaPesosFormulario(altaAyudaPesosFormulario);
         altaAyudaPesosGarantes.iniciar(cardLayout, cardPanel, this);
         FlowLayout altaAyudaPesosGarantesLayout = (FlowLayout) altaAyudaTipoAyuda.getLayout();
         altaAyudaPesosGarantesLayout.setVgap(100);
         cardPanel.add(altaAyudaPesosGarantes, "AltaAyudaPesosGarantes");
         
         altaAyudaPesosFormulario.iniciar(cardLayout, cardPanel, this);
         FlowLayout altaAyudaPesosFormularioLayout = (FlowLayout) altaAyudaTipoAyuda.getLayout();
         altaAyudaPesosFormularioLayout.setVgap(100);
         altaAyudaPesosFormulario.setAltaAyudaPesosGarantes(altaAyudaPesosGarantes);
         altaAyudaPesosFormulario.setAltaAyudaPesos(altaAyudaPesos);
         cardPanel.add(altaAyudaPesosFormulario, "AltaAyudaPesosFormulario");
          
         //----------CONSULTA/ANULACION AYUDAS
         consultaBajaAyudaTipoAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaAyudaTipoAyudaLayout = (FlowLayout) consultaBajaAyudaTipoAyuda.getLayout();
         consultaBajaAyudaTipoAyudaLayout.setVgap(100);
         cardPanel.add(consultaBajaAyudaTipoAyuda, "ConsultaBajaAyudaTipoAyuda");
         
         consultaBajaAyudaTipoBusqueda.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaAyudaTipoBusquedaLayout = (FlowLayout) consultaBajaAyudaTipoBusqueda.getLayout();
         consultaBajaAyudaTipoBusquedaLayout.setVgap(100);
         cardPanel.add(consultaBajaAyudaTipoBusqueda, "ConsultaBajaAyudaTipoBusqueda");
        
         consultaBajaAyudaPorSocio.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaAyudaPorSocioLayout = (FlowLayout) consultaBajaAyudaPorSocio.getLayout();
         consultaBajaAyudaPorSocioLayout.setVgap(60);
         consultaBajaAyudaPorSocio.setConsultaBajaAyudaPantallaSocio(consultaBajaAyudaPantallaSocio);
         cardPanel.add(consultaBajaAyudaPorSocio, "ConsultaBajaAyudaPorSocio");
        
         consultaBajaAyudaPorAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaAyudaPorAyudaLayout = (FlowLayout) consultaBajaAyudaPorAyuda.getLayout();
         consultaBajaAyudaPorAyudaLayout.setVgap(100);
         consultaBajaAyudaPorAyuda.setConsultaBajaAyudaPantallaAyuda(consultaBajaAyudaPantallaAyuda);
         cardPanel.add(consultaBajaAyudaPorAyuda, "ConsultaBajaAyudaPorAyuda");
         
         consultaBajaAyudaPantallaSocio.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaAyudaPantallaSocioLayout = (FlowLayout) consultaBajaAyudaPantallaSocio.getLayout();
         consultaBajaAyudaPantallaSocioLayout.setVgap(60);
         cardPanel.add(consultaBajaAyudaPantallaSocio, "ConsultaBajaAyudaPantallaSocio");
         
         consultaBajaAyudaPantallaAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaAyudaPantallaAyudaLayout = (FlowLayout) consultaBajaAyudaPantallaAyuda.getLayout();
         consultaBajaAyudaPantallaAyudaLayout.setVgap(60);
         cardPanel.add(consultaBajaAyudaPantallaAyuda, "ConsultaBajaAyudaPantallaAyuda");
       
         //ALTA DE COBROS
         
         altaCobrosTipoAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout altaCobrosTipoAyudaLayout = (FlowLayout) altaCobrosTipoAyuda.getLayout();
         altaCobrosTipoAyudaLayout.setVgap(100);
         cardPanel.add(altaCobrosTipoAyuda, "AltaCobrosTipoAyuda");
         
         altaCobrosPesosTipoBusqueda.iniciar(cardLayout, cardPanel, this);
         FlowLayout altaCobrosPesosTipoBusquedaLayout = (FlowLayout) altaCobrosPesosTipoBusqueda.getLayout();
         altaCobrosPesosTipoBusquedaLayout.setVgap(100);
         cardPanel.add(altaCobrosPesosTipoBusqueda, "AltaCobrosPesosTipoBusqueda");
        
         altaCobrosPesosAyudaPorAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout altaCobrosPesosAyudaPorAyudaLayout = (FlowLayout) altaCobrosPesosAyudaPorAyuda.getLayout();
         altaCobrosPesosAyudaPorAyudaLayout.setVgap(60);
         altaCobrosPesosAyudaPorAyuda.setAltaCobrosPesosPantallaAyuda(altaCobrosPesosPantallaAyuda);
         cardPanel.add(altaCobrosPesosAyudaPorAyuda, "AltaCobrosPesosAyudaPorAyuda");
        
         altaCobrosPesosAyudaPorSocio.iniciar(cardLayout, cardPanel, this);
         FlowLayout altaCobrosPesosAyudaPorSocioLayout = (FlowLayout) altaCobrosPesosAyudaPorSocio.getLayout();
         altaCobrosPesosAyudaPorSocioLayout.setVgap(100);
         altaCobrosPesosAyudaPorSocio.setAltaCobrosPesosPantallaAyuda(altaCobrosPesosPantallaAyuda);
         cardPanel.add(altaCobrosPesosAyudaPorSocio, "AltaCobrosPesosAyudaPorSocio");
         
         altaCobrosPesosPantallaAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout altaCobrosPesosPantallaAyudaLayout = (FlowLayout) altaCobrosPesosPantallaAyuda.getLayout();
         altaCobrosPesosPantallaAyudaLayout.setVgap(60);
         altaCobrosPesosPantallaAyuda.setAltaCobrosPesosCobrarVariasOUna(altaCobrosPesosCobrarVariasOUna); 
         cardPanel.add(altaCobrosPesosPantallaAyuda, "AltaCobrosPesosPantallaAyuda");
         
         altaCobrosPesosCobrarVariasOUna.iniciar(cardLayout, cardPanel, this);
         FlowLayout altaCobrosPesosCobrarVariasOUnaLayout = (FlowLayout) altaCobrosPesosCobrarVariasOUna.getLayout();
         altaCobrosPesosCobrarVariasOUnaLayout.setVgap(60);
         altaCobrosPesosCobrarVariasOUna.setAltaCobrosPesosDesdeHasta(altaCobrosPesosDesdeHasta);
         altaCobrosPesosCobrarVariasOUna.setAltaCobrosPesosCobrarUnaCuota(altaCobrosPesosCobrarUnaCuota);
         cardPanel.add(altaCobrosPesosCobrarVariasOUna, "AltaCobrosPesosCobrarVariasOUna");
        
         altaCobrosPesosDesdeHasta.iniciar(cardLayout, cardPanel, this);
         FlowLayout altaCobrosPesosDesdeHastaLayout = (FlowLayout) altaCobrosPesosDesdeHasta.getLayout();
         altaCobrosPesosDesdeHastaLayout.setVgap(60);
         altaCobrosPesosDesdeHasta.setAltaCobrosPesosCobrarVariasCuotas(altaCobrosPesosCobrarVariasCuotas);
         cardPanel.add(altaCobrosPesosDesdeHasta, "AltaCobrosPesosDesdeHasta");
        
         altaCobrosPesosCobrarUnaCuota.iniciar(cardLayout, cardPanel, this);
         FlowLayout altaCobrosPesosCobrarUnaCuotaLayout = (FlowLayout) altaCobrosPesosCobrarUnaCuota.getLayout();
         altaCobrosPesosCobrarUnaCuotaLayout.setVgap(60);
         cardPanel.add(altaCobrosPesosCobrarUnaCuota, "AltaCobrosPesosCobrarUnaCuota");
         
         altaCobrosPesosCobrarVariasCuotas.iniciar(cardLayout, cardPanel, this);
         FlowLayout altaCobrosPesosCobrarVariasCuotasLayout = (FlowLayout) altaCobrosPesosCobrarVariasCuotas.getLayout();
         altaCobrosPesosCobrarVariasCuotasLayout.setVgap(60);
         cardPanel.add(altaCobrosPesosCobrarVariasCuotas, "AltaCobrosPesosCobrarVariasCuotas");
      
         //CONSULTA BAJA COBROS
         consultaBajaCobrosTipoAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaCobrosTipoAyudaLayout = (FlowLayout) consultaBajaCobrosTipoAyuda.getLayout();
         consultaBajaCobrosTipoAyudaLayout.setVgap(100);
         cardPanel.add(consultaBajaCobrosTipoAyuda, "ConsultaBajaCobrosTipoAyuda");
         
         consultaBajaCobrosTipoBusqueda.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaCobrosTipoBusquedaLayout = (FlowLayout) consultaBajaCobrosTipoBusqueda.getLayout();
         consultaBajaCobrosTipoBusquedaLayout.setVgap(100);
         cardPanel.add(consultaBajaCobrosTipoBusqueda, "ConsultaBajaCobrosTipoBusqueda");
        
         consultaBajaCobrosPorSocio.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaCobrosPorSocioLayout = (FlowLayout) consultaBajaAyudaPorSocio.getLayout();
         consultaBajaCobrosPorSocioLayout.setVgap(60);
         consultaBajaCobrosPorSocio.setConsultaBajaCobrosPantallaSocio(consultaBajaCobrosPantallaSocio);
         cardPanel.add(consultaBajaCobrosPorSocio, "ConsultaBajaCobrosPorSocio");
        
         consultaBajaCobrosPorAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaCobrosPorAyudaLayout = (FlowLayout) consultaBajaCobrosPorAyuda.getLayout();
         consultaBajaCobrosPorAyudaLayout.setVgap(100);
         consultaBajaCobrosPorAyuda.setConsultaBajaCobrosPantallaAyuda(consultaBajaCobrosPantallaAyuda);
         cardPanel.add(consultaBajaCobrosPorAyuda, "ConsultaBajaCobrosPorAyuda");
         
         consultaBajaCobrosPantallaSocio.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaCobrosPantallaSocioLayout = (FlowLayout) consultaBajaCobrosPantallaSocio.getLayout();
         consultaBajaCobrosPantallaSocioLayout.setVgap(60);
         consultaBajaCobrosPantallaSocio.setConsultaBajaCobrosConsultaCobro(consultaBajaCobrosConsultaCobro);
         cardPanel.add(consultaBajaCobrosPantallaSocio, "ConsultaBajaCobrosPantallaSocio");
         
         consultaBajaCobrosPantallaAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaCobrosPantallaAyudaLayout = (FlowLayout) consultaBajaCobrosPantallaAyuda.getLayout();
         consultaBajaCobrosPantallaAyudaLayout.setVgap(60);
         consultaBajaCobrosPantallaAyuda.setConsultaBajaCobrosConsultaCobro(consultaBajaCobrosConsultaCobro);
         cardPanel.add(consultaBajaCobrosPantallaAyuda, "ConsultaBajaCobrosPantallaAyuda");
       
         consultaBajaCobrosConsultaCobro.iniciar(cardLayout, cardPanel, this);
         FlowLayout consultaBajaCobrosConsultaCobroLayout = (FlowLayout) consultaBajaCobrosConsultaCobro.getLayout();
         consultaBajaCobrosConsultaCobroLayout.setVgap(60);
         cardPanel.add(consultaBajaCobrosConsultaCobro, "ConsultaBajaCobrosConsultaCobro");

         //-------------------
         ayudasALegalesTipoAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout ayudasALegalesTipoAyudaLayout = (FlowLayout) ayudasALegalesTipoAyuda.getLayout();
         ayudasALegalesTipoAyudaLayout.setVgap(100);
         cardPanel.add(ayudasALegalesTipoAyuda, "AyudasALegalesTipoAyuda");
         
         ayudasALegalesPesosTipoBusqueda.iniciar(cardLayout, cardPanel, this);
         FlowLayout ayudasALegalesPesosTipoBusquedaLayout = (FlowLayout) ayudasALegalesPesosTipoBusqueda.getLayout();
         ayudasALegalesPesosTipoBusquedaLayout.setVgap(100);
         cardPanel.add(ayudasALegalesPesosTipoBusqueda, "AyudasALegalesPesosTipoBusqueda");
        
         ayudasALegalesPesosPorAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout ayudasALegalesPesosPorAyudaLayout = (FlowLayout) ayudasALegalesPesosPorAyuda.getLayout();
         ayudasALegalesPesosPorAyudaLayout.setVgap(60);
         ayudasALegalesPesosPorAyuda.setAyudasALegalesPesosPantallaAyuda(ayudasALegalesPesosPantallaAyuda);
         cardPanel.add(ayudasALegalesPesosPorAyuda, "AyudasALegalesPesosPorAyuda");
        
         ayudasALegalesPesosPorSocio.iniciar(cardLayout, cardPanel, this);
         FlowLayout ayudasALegalesPesosPorSocioLayout = (FlowLayout) ayudasALegalesPesosPorSocio.getLayout();
         ayudasALegalesPesosPorSocioLayout.setVgap(100);
         ayudasALegalesPesosPorSocio.setAyudasALegalesPesosPantallaSocio(ayudasALegalesPesosPantallaSocio);
         cardPanel.add(ayudasALegalesPesosPorSocio, "AyudasALegalesPesosPorSocio");
         
         ayudasALegalesPesosPantallaSocio.iniciar(cardLayout, cardPanel, this);
         FlowLayout ayudasALegalesPesosPantallaSocioLayout = (FlowLayout) ayudasALegalesPesosPantallaSocio.getLayout();
         ayudasALegalesPesosPantallaSocioLayout.setVgap(60);
         cardPanel.add(ayudasALegalesPesosPantallaSocio, "AyudasALegalesPesosPantallaSocio");
         
         ayudasALegalesPesosPantallaAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout ayudasALegalesPesosPantallaAyudaLayout = (FlowLayout) ayudasALegalesPesosPantallaAyuda.getLayout();
         ayudasALegalesPesosPantallaAyudaLayout.setVgap(60);
         cardPanel.add(ayudasALegalesPesosPantallaAyuda, "AyudasALegalesPesosPantallaAyuda");

         //-------------------
         anularAyudasALegalesTipoAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout anularAyudasALegalesTipoAyudaLayout = (FlowLayout) anularAyudasALegalesTipoAyuda.getLayout();
         anularAyudasALegalesTipoAyudaLayout.setVgap(100);
         cardPanel.add(anularAyudasALegalesTipoAyuda, "AnularAyudasALegalesTipoAyuda");
         
         anularAyudasALegalesPesosTipoBusqueda.iniciar(cardLayout, cardPanel, this);
         FlowLayout anularAyudasALegalesPesosTipoBusquedaLayout = (FlowLayout) anularAyudasALegalesPesosTipoBusqueda.getLayout();
         anularAyudasALegalesPesosTipoBusquedaLayout.setVgap(100);
         cardPanel.add(anularAyudasALegalesPesosTipoBusqueda, "AnularAyudasALegalesPesosTipoBusqueda");
        
         anularAyudasALegalesPesosPorAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout anularAyudasALegalesPesosPorAyudaLayout = (FlowLayout) anularAyudasALegalesPesosPorAyuda.getLayout();
         anularAyudasALegalesPesosPorAyudaLayout.setVgap(60);
         anularAyudasALegalesPesosPorAyuda.setAnularAyudasALegalesPesosPantallaAyuda(anularAyudasALegalesPesosPantallaAyuda);
         cardPanel.add(anularAyudasALegalesPesosPorAyuda, "AnularAyudasALegalesPesosPorAyuda");
        
         anularAyudasALegalesPesosPorSocio.iniciar(cardLayout, cardPanel, this);
         FlowLayout anularAyudasALegalesPesosPorSocioLayout = (FlowLayout) anularAyudasALegalesPesosPorSocio.getLayout();
         anularAyudasALegalesPesosPorSocioLayout.setVgap(100);
         anularAyudasALegalesPesosPorSocio.setAnularAyudasALegalesPesosPantallaSocio(anularAyudasALegalesPesosPantallaSocio);
         cardPanel.add(anularAyudasALegalesPesosPorSocio, "AnularAyudasALegalesPesosPorSocio");
         
         anularAyudasALegalesPesosPantallaSocio.iniciar(cardLayout, cardPanel, this);
         FlowLayout anularAyudasALegalesPesosPantallaSocioLayout = (FlowLayout) anularAyudasALegalesPesosPantallaSocio.getLayout();
         anularAyudasALegalesPesosPantallaSocioLayout.setVgap(60);
         cardPanel.add(anularAyudasALegalesPesosPantallaSocio, "AnularAyudasALegalesPesosPantallaSocio");
         
         anularAyudasALegalesPesosPantallaAyuda.iniciar(cardLayout, cardPanel, this);
         FlowLayout anularAyudasALegalesPesosPantallaAyudaLayout = (FlowLayout) anularAyudasALegalesPesosPantallaAyuda.getLayout();
         anularAyudasALegalesPesosPantallaAyudaLayout.setVgap(60);
         cardPanel.add(anularAyudasALegalesPesosPantallaAyuda, "AnularAyudasALegalesPesosPantallaAyuda");
     
         JPanel centerPanel = new JPanel(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 0;
         // Use weight so the cell expands and centers the component within it
         gbc.weightx = 1.0;
         gbc.weighty = 1.0;
         gbc.anchor = GridBagConstraints.CENTER;
         centerPanel.add(cardPanel, gbc);

         // Add the centerPanel to the frame's center
         getContentPane().add(centerPanel, BorderLayout.CENTER);
         pack();
         setVisible(true);
         setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

    }
  
}
