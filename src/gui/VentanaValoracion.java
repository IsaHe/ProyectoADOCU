package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class VentanaValoracion extends JFrame {
    enum Valoracion {
        MUY_MAL, MAL, REGULAR, BIEN, MUY_BIEN
    }
    public VentanaValoracion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Valoración");
        setIconImage(new ImageIcon("C:\\Users\\PC\\IdeaProjects\\ProyectoADOCU\\src\\resources\\images\\ADOCU.png").getImage());

        JLabel etiqueta = new JLabel("Valoración: ");

        JComboBox<Valoracion> valoracion = new JComboBox<>(Valoracion.values());

        JLabel etiqueta2 = new JLabel("<html>Elige una valoración que creas adecuada<br>basandote en tu experiencia con el producto</html>");

        valoracion.addActionListener(e -> {
            etiqueta2.setText(
                    "<html>Elige una valoración que creas adecuada<br>" +
                    "basandote en tu experiencia con el producto<br>" +
                    "Teniendo en cuenta que 1 es la peor y 5 la mejor.<br>" +
                    "Actualmente la valoracion que has seleccionado equivale a:<br>"+
                    Objects.requireNonNull(valoracion.getSelectedItem()) +
                    "</html>");
            Valoracion v = (Valoracion) valoracion.getSelectedItem();
            switch (v) {
                case MUY_MAL:
                    etiqueta.setForeground(Color.RED);
                    break;
                case MAL:
                    etiqueta.setForeground(Color.ORANGE);
                    break;
                case REGULAR:
                    etiqueta.setForeground(Color.YELLOW);
                    break;
                case BIEN:
                    etiqueta.setForeground(Color.GREEN);
                    break;
                case MUY_BIEN:
                    etiqueta.setForeground(Color.BLUE);
                    break;
            }
        });
        add(etiqueta, BorderLayout.WEST);
        add(valoracion, BorderLayout.CENTER);
        add(etiqueta2, BorderLayout.EAST);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaValoracion();
    }
}
