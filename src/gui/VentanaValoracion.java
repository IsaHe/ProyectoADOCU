package gui;

import javax.swing.*;

import db.BaseDeDatos;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * VentanaValoracion es una clase que extiende JFrame y representa la ventana de valoración en la aplicación.
 * Esta ventana permite al usuario valorar su experiencia con las actividades.
 * <p>
 * La clase contiene varios componentos como paneles, botones, etiquetas, un JComboBox y un JEditorPane.
 * También mantiene una conexión con la base de datos y un Logger para registrar información y errores.
 * <p>
 * El constructor de la clase inicializa la ventana y sus componentes.
 * Establece las propiedades de la ventana, añade componentes a los paneles, y añade oyentes de acción a los botones.
 * También llena el JComboBox con las opciones de valoración.
 * <p>
 * Los botones de la ventana tienen asignados varios eventos. El botón de enviar recoge la valoración seleccionada, la añade a la base de datos y cierra la aplicación.
 * <p>
 * La clase también contiene un método privado para crear el JEditorPane, que muestra un mensaje de agradecimiento y una explicación de la importancia de la valoración para la mejora del servicio.
 */
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

    /**
     * Metodo para crear el boton de enviar.
     *
     * @param valoracion El JComboBox con las opciones de valoración.
     * @return El boton de enviar.
     */
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

    /**
     * Metodo para crear el JEditorPane.
     *
     * @param urlLogo La URL del logo de la aplicación.
     * @return El JEditorPane con el mensaje de agradecimiento y la explicación de la importancia de la valoración.
     */
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