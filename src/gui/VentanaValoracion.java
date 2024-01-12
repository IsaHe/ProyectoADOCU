package gui;

import javax.swing.*;

import db.BaseDeDatos;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.util.Objects;
import java.util.logging.Logger;

public class VentanaValoracion extends JFrame {
	
    final Logger logger = Logger.getLogger(VentanaValoracion.class.getName());
    final Connection con = BaseDeDatos.iniciarBaseDeDatosValoraciones("src/db/valoraciones.db");
    enum Valoracion {
        MUY_MAL, MAL, REGULAR, BIEN, MUY_BIEN
    }
    
    public VentanaValoracion() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Valoración");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images//ADOCU.png"))).getImage());

        //CREACION DE ELEMENTOS Y CONTENEDORES
        JComboBox<Valoracion> valoracion = new JComboBox<>(Valoracion.values());
        valoracion.addActionListener(e -> logger.info("Se ha seleccionado la valoración: " + Objects.requireNonNull(valoracion.getSelectedItem())));

        URL urlLogo = getClass().getResource("/resources/images//ADOCU.png");
        JEditorPane etiqueta = getjEditorPane(urlLogo);

        JPanel panelValoracion = new JPanel();
        panelValoracion.setLayout(new BorderLayout());

        JButton btnEnviar = getBtnEnviar(valoracion);

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

    private JButton getBtnEnviar(JComboBox<Valoracion> valoracion) {
        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(e -> {
            logger.info("Se ha pulsado el botón Enviar");

            String valor = Objects.requireNonNull(valoracion.getSelectedItem()).toString();
            if (valor != null) {
            	if (valor.equals(Valoracion.MUY_MAL.toString())) {
            		Integer valorInt = 0;
            		BaseDeDatos.getValoraciones().add(valorInt);
            	}else if(valor.equals(Valoracion.MAL.toString())){
            		Integer valorInt = 2;
            		BaseDeDatos.getValoraciones().add(valorInt);
            	}else if (valor.equals(Valoracion.REGULAR.toString())) {
            		Integer valorInt = 5;
            		BaseDeDatos.getValoraciones().add(valorInt);
            	}else if (valor.equals(Valoracion.BIEN.toString())) {
            		Integer valorInt = 8;
            		BaseDeDatos.getValoraciones().add(valorInt);
            	}else {
            		Integer valorInt = 10;
            		BaseDeDatos.getValoraciones().add(valorInt);
            	}

            	BaseDeDatos.cargarValoracionEnBaseDeDatos(con);
            	System.exit(0);
            }else {
            	JOptionPane.showMessageDialog(null, "Seleccione una Valoracion");
            }

        });
        return btnEnviar;
    }

    private static JEditorPane getjEditorPane(URL urlLogo) {
        String textoExplicacionValoracionHTML = "<html><body>" +
                "<div style='text-align: center; margin-top: 20px;'>" +
                "<img src= " + urlLogo + " alt='Logo de ADOCU' width='150'><br><br>" +
                "<h1>¡Gracias por elegir nuestros espacios para tu actividad!</h1>" +
                "<p>En ADOCU, nos esforzamos por brindarte la mejor experiencia en nuestros espacios. Para seguir mejorando y ofrecerte un servicio excepcional, te pedimos que tomes un momento para valorar tu experiencia con nosotros.</p>" +
                "<p>Tu opinión es invaluable para nosotros. Nos ayuda a entender lo que estamos haciendo bien y a identificar áreas en las que podemos mejorar. Estamos comprometidos en proporcionarte un entorno cómodo y acogedor para tus actividades, y tu feedback nos guía en ese camino.</p>" +
                "<p>Por favor, haz clic en el desplegable para valorar nuestro servicio. Solo tomará unos segundos de tu tiempo y nos ayudará a seguir ofreciendo un servicio de calidad.</p>" +
                "<p>Gracias por tu colaboración y por confiar en ADOCU. Esperamos verte de nuevo pronto en nuestros espacios.</p>" +
                "<p>Atentamente,<br>El equipo de ADOCU</p>" +
                "</div></body></html>";
        JEditorPane etiqueta = new JEditorPane("text/html", textoExplicacionValoracionHTML);
        etiqueta.setEditable(false);
        etiqueta.setOpaque(false);
        return etiqueta;
    }

}