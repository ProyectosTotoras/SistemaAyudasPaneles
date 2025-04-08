package com.mutual.SistemaAyudaCuotas.filtros;
import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class IntegerFilter extends DocumentFilter {
    
    // Esta expresión regular permite de 0 a 6 dígitos.
    private static final String INTEGER_REGEX = "^\\d{0,6}$";

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
            throws BadLocationException {
        if (text == null) {
            return;
        }
        // Obtiene el texto actual y lo concatena con el nuevo texto en la posición indicada
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = new StringBuilder(currentText).insert(offset, text).toString();
        if (newText.matches(INTEGER_REGEX)) {
            super.insertString(fb, offset, text, attr);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        StringBuilder sb = new StringBuilder(currentText);
        sb.replace(offset, offset + length, text == null ? "" : text);
        if (sb.toString().matches(INTEGER_REGEX)) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        StringBuilder sb = new StringBuilder(currentText);
        sb.delete(offset, offset + length);
        if (sb.toString().matches(INTEGER_REGEX)) {
            super.remove(fb, offset, length);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
