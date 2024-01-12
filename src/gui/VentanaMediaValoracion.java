package gui;

import javax.swing.*;

public class VentanaMediaValoracion extends JFrame {
    private int valoracionMedia;

    public VentanaMediaValoracion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JProgressBar barraValoracion = new JProgressBar();
        barraValoracion.setValue(valoracionMedia);
        barraValoracion.setStringPainted(true);
        barraValoracion.setString("Valoración media: " + valoracionMedia);
        barraValoracion.setMaximum(100);
        barraValoracion.setVisible(true);

        add(barraValoracion);
        setSize(200, 100);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public int getValoracionMedia() {
        return valoracionMedia;
    }

    public void setValoracionMedia(int valoracionMedia) {
        this.valoracionMedia = valoracionMedia;
    }
    
}
