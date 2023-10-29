package gui;

import domain.Actividad;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;

public class VentanaModificacionActividades extends JFrame {
    private static final Logger logger = Logger.getLogger(VentanaModificacionActividades.class.getName());
    Actividad[] listaActividades = {
            new Actividad(),
            new Actividad(),
            new Actividad(),
            new Actividad()
    }; // Variable temporal hasta la creacion del objeto Actividad

    public VentanaModificacionActividades(int numeroPersonas, String horaActividad, LocalDate fechaActividad) {    //Formato horas [hora, minutos, segundos]
        int numeroMaxPersonas = 10; // Variable temporal hasta la creacion del objeto Actividad
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Modificación de actividades");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images//ADOCU.png"))).getImage());
        setLayout(new GridLayout(4, 2, 0, 0));

        //CREACION Y FORMATO DE LOS LABELS
        JLabel lblHora = new JLabel(" Hora: " + horaActividad);
        JLabel lblFecha = new JLabel("Fecha: " + fechaActividad);
        JLabel lblNumeroPersonas = new JLabel("Número de personas: " + numeroPersonas + "/" + numeroMaxPersonas);
        JLabel lblActividad = new JLabel("Actividad: ");
        JLabel lblRecordatrio = new JLabel("Recordatorio: ");

        lblHora.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
        lblFecha.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
        lblRecordatrio.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
        lblActividad.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
        lblNumeroPersonas.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));

        //CREACION Y CONFIGURACION DE LOS ELEMENTOS CON LOS QUE PUEDE INTERACTUAR EL USUARIO
        JComboBox<Actividad> actividades = new JComboBox<>(listaActividades);

        actividades.addActionListener(e -> logger.info("Se ha seleccionado la actividad: " + Objects.requireNonNull(actividades.getSelectedItem())));

        JRadioButton btnSi = new JRadioButton("Si");
        JRadioButton btnNo = new JRadioButton("No");
        ButtonGroup aceptarRechazarRecordatorio = new ButtonGroup();
        aceptarRechazarRecordatorio.add(btnSi);
        aceptarRechazarRecordatorio.add(btnNo);

        JButton btnRegistrarHora = new JButton("Registrar hora");
        JButton btnVolver = new JButton("Volver");

        btnVolver.addActionListener(e -> {
            logger.info("Se ha pulsado el botón Volver");
            this.dispose();
        });

        btnRegistrarHora.addActionListener(e -> {
            logger.info("Se ha pulsado el botón Registrar hora");
            this.dispose();
        });

        //CREACION DE LOS CONTENEDORES Y CONFIGURACION DE LOS MISMOS
        JPanel panelVacio = new JPanel();

        JPanel panelActividad = new JPanel();
        panelActividad.add(lblActividad);
        panelActividad.add(actividades);

        JPanel panelRadioBotones = new JPanel();
        panelRadioBotones.setLayout(new GridLayout(2, 1));
        panelRadioBotones.add(btnSi);
        panelRadioBotones.add(btnNo);

        JPanel panelRecordatorio = new JPanel();
        panelRecordatorio.setLayout(new GridLayout(1, 2));
        panelRecordatorio.add(lblRecordatrio);
        panelRecordatorio.add(panelRadioBotones);

        //ADICION DE ELEMENTOS AL CONTENEDOR PRINCIPAL
        add(lblHora);
        add(lblFecha);
        add(panelActividad);
        add(lblNumeroPersonas);
        add(panelRecordatorio);
        add(panelVacio);
        add(btnVolver);
        add(btnRegistrarHora);

        //CONFIGURACION DE LA VENTANA
//        setExtendedState(MAXIMIZED_BOTH);
        pack(); //No se pone en pantalla completa que esta pensado para salir en pequeño pequeño
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
