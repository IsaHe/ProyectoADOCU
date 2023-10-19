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

        JComboBox<Valoracion> valoracion = new JComboBox<>(Valoracion.values());

        String htmlContent = "<html><body>" +
                "<div style='text-align: center; margin-top: 20px;'>" +
                "<img src='file:///C:/Users/Thega/IdeaProjects/ProyectoADOCU/src/resources/images/ADOCU.png' alt='Logo de ADOCU' width='150'><br><br>" +
                "<h2>¡Gracias por elegir nuestros espacios para tu actividad!</h2>" +
                "<p>En [Nombre de la Empresa], nos esforzamos por brindarte la mejor experiencia en nuestros espacios. Para seguir mejorando y ofrecerte un servicio excepcional, te pedimos que tomes un momento para valorar tu experiencia con nosotros.</p>" +
                "<p>Tu opinión es invaluable para nosotros. Nos ayuda a entender lo que estamos haciendo bien y a identificar áreas en las que podemos mejorar. Estamos comprometidos en proporcionarte un entorno cómodo y acogedor para tus actividades, y tu feedback nos guía en ese camino.</p>" +
                "<p>Por favor, haz clic en el enlace a continuación para completar nuestra breve encuesta de valoración. Solo tomará unos minutos de tu tiempo y nos ayudará a seguir ofreciendo un servicio de calidad.</p>" +
                "<a href='enlace_a_encuesta' style='text-decoration: none; background-color: #4CAF50; color: white; padding: 10px 20px; border-radius: 5px;'>Completar la Encuesta</a>" +
                "<p>Gracias por tu colaboración y por confiar en [Nombre de la Empresa]. Esperamos verte de nuevo pronto en nuestros espacios.</p>" +
                "<p>Atentamente,<br>El equipo de [Nombre de la Empresa]</p>" +
                "</div></body></html>";
        JEditorPane etiqueta = new JEditorPane("text/html", htmlContent);
        etiqueta.setEditable(false);
        etiqueta.setOpaque(false);

        JPanel panelValoracion = new JPanel();
        panelValoracion.setLayout(new BorderLayout());

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(e -> dispose());

        panelValoracion.add(valoracion, BorderLayout.CENTER);
        panelValoracion.add(btnEnviar, BorderLayout.SOUTH);

        add(panelValoracion, BorderLayout.SOUTH);
        add(etiqueta, BorderLayout.CENTER);

        setSize(1014, 607);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaValoracion();
    }
}
