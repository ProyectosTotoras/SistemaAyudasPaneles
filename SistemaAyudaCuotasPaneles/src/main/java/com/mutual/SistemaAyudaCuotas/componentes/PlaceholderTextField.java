package com.mutual.SistemaAyudaCuotas.componentes;

import javax.swing.*;
import java.awt.*;

public class PlaceholderTextField extends JTextField {
    private String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0 || isFocusOwner()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setFont(getFont().deriveFont(Font.ITALIC));
        g2.setColor(Color.GRAY);
        Insets insets = getInsets();
        FontMetrics fm = g2.getFontMetrics();
        int x = insets.left + 2;
        int y = getHeight() / 2 + fm.getAscent() / 2 - 2;
        g2.drawString(placeholder, x, y);
        g2.dispose();
    }

    // Example usage
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Custom Placeholder JTextField");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(400, 100);
//            
//            PlaceholderTextField textField = new PlaceholderTextField("Enter your email");
//            textField.setColumns(20);
//            
//            frame.setLayout(new FlowLayout());
//            frame.add(textField);
//            frame.setVisible(true);
//        });
//    }
}
