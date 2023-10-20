package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class VentanaValoracion extends JFrame {
    enum Valoracion {
        MUY_MAL, MAL, REGULAR, BIEN, MUY_BIEN
    }
    public VentanaValoracion() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Valoración");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images//ADOCU.png"))).getImage());

        //CREACION DE ELEMENTOS Y CONTENEDORES
        JComboBox<Valoracion> valoracion = new JComboBox<>(Valoracion.values());

        String htmlContent = "<html><body>" +
                "<div style='text-align: center; margin-top: 20px;'>" +
                "<img src='file:///C:/Users/Thega/IdeaProjects/ProyectoADOCU/src/resources/images/ADOCU.png' alt='Logo de ADOCU' width='150'><br><br>" +
                "<h2>¡Gracias por elegir nuestros espacios para tu actividad!</h2>" +
                "<p>En ADOCU, nos esforzamos por brindarte la mejor experiencia en nuestros espacios. Para seguir mejorando y ofrecerte un servicio excepcional, te pedimos que tomes un momento para valorar tu experiencia con nosotros.</p>" +
                "<p>Tu opinión es invaluable para nosotros. Nos ayuda a entender lo que estamos haciendo bien y a identificar áreas en las que podemos mejorar. Estamos comprometidos en proporcionarte un entorno cómodo y acogedor para tus actividades, y tu feedback nos guía en ese camino.</p>" +
                "<p>Por favor, haz clic en el desplegable para valorar nuestro servicio. Solo tomará unos segundos de tu tiempo y nos ayudará a seguir ofreciendo un servicio de calidad.</p>" +
                "<p>Gracias por tu colaboración y por confiar en ADOCU. Esperamos verte de nuevo pronto en nuestros espacios.</p>" +
                "<p>Atentamente,<br>El equipo de ADOCU</p>" +
                "</div></body></html>";
        JEditorPane etiqueta = new JEditorPane("text/html", htmlContent);
        etiqueta.setEditable(false);
        etiqueta.setOpaque(false);

        JPanel panelValoracion = new JPanel();
        panelValoracion.setLayout(new BorderLayout());

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(e -> dispose());

        //ADICION DE ELEMENTOS A LOS CONTENEDORES
        panelValoracion.add(valoracion, BorderLayout.CENTER);
        panelValoracion.add(btnEnviar, BorderLayout.SOUTH);

        add(panelValoracion, BorderLayout.SOUTH);
        add(etiqueta, BorderLayout.CENTER);

        //CONFIGURACION DE LA VENTANA
        setSize(1014, 607);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaValoracion();
    }
}