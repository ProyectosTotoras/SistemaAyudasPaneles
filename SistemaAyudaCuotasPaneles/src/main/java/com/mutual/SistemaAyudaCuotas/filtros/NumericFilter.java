package com.mutual.SistemaAyudaCuotas.filtros;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericFilter extends DocumentFilter {
    private static final String REGEX = "\\d*(\\.\\d{0,2})?";

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.insert(offset, string);

        if (isValid(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        } else {
            // Opcional: reproducir un sonido o mostrar una alerta
            java.awt.Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.replace(offset, offset + length, text);

        if (isValid(sb.toString())) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            // Opcional: reproducir un sonido o mostrar una alerta
            java.awt.Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.delete(offset, offset + length);

        if (isValid(sb.toString())) {
            super.remove(fb, offset, length);
        } else {
            // Opcional: reproducir un sonido o mostrar una alerta
            java.awt.Toolkit.getDefaultToolkit().beep();
        }
    }

    private boolean isValid(String text) {
        if (text.isEmpty()) {
            return true; // Permitir campo vacío, se validará posteriormente
        }
        return text.matches(REGEX);
    }
}
