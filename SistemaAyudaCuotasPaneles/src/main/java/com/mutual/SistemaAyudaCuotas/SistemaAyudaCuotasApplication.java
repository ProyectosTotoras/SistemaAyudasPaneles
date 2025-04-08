package com.mutual.SistemaAyudaCuotas;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SistemaAyudaCuotasApplication {
    public static void main(String[] args) {

        // Global exception handler
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            System.err.println("Uncaught exception in thread " + thread.getName());
            throwable.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,
                "An unexpected error occurred: " + throwable.getMessage(),
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        });
        
        ConfigurableApplicationContext contextSpring = new SpringApplicationBuilder(SistemaAyudaCuotasApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run();

        EventQueue.invokeLater(() -> {
        	SistemaAyudaCuotasCardLayout menuPrincipal = contextSpring.getBean(SistemaAyudaCuotasCardLayout.class);
        	menuPrincipal.iniciar();
            menuPrincipal.setVisible(true);
      
            
        });
    }
}
