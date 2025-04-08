package com.mutual.SistemaAyudaCuotas.filtros;

import java.awt.Toolkit;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DecimalFilter extends DocumentFilter {
    // Pattern: up to 8 digits, optionally followed by a dot and up to 2 decimal places
    private static final Pattern DECIMAL_PATTERN = Pattern.compile("^\\d{0,8}(\\.\\d{0,2})?$");

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
            throws BadLocationException {
        if (text == null) {
            return;
        }
        
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = new StringBuilder(currentText).insert(offset, text).toString();
        
        if (DECIMAL_PATTERN.matcher(newText).matches()) {
            super.insertString(fb, offset, text, attr);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (text == null) {
            text = "";
        }

        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        StringBuilder sb = new StringBuilder(currentText);
        sb.replace(offset, offset + length, text);
        
        if (DECIMAL_PATTERN.matcher(sb.toString()).matches()) {
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
        
        if (DECIMAL_PATTERN.matcher(sb.toString()).matches()) {
            super.remove(fb, offset, length);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
