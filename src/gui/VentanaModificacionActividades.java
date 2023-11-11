package gui;

import db.BaseDeDatos;
import domain.Actividad;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;

public class VentanaModificacionActividades extends JFrame {
    private static final Logger logger = Logger.getLogger(VentanaModificacionActividades.class.getName());
    Actividad[] listaActividades;
    int numeroMaxPersonas = 0;

    public VentanaModificacionActividades(int numeroPersonas, String horaActividad, LocalDate fechaActividad, VentanaTabla ventanaTabla) {    //Formato horas [hora, minutos, segundos]
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
        String dia = convertirDiaSemana(fechaActividad.getDayOfWeek().toString());
        listaActividades = BaseDeDatos.getActividades().get(dia).toArray(new Actividad[0]);

        JComboBox<Actividad> actividades = new JComboBox<>(listaActividades);
        actividades.addActionListener(e -> {
            Actividad actividad = (Actividad) actividades.getSelectedItem();
            assert actividad != null;
            numeroMaxPersonas = actividad.getNumeroParticipantes();
            lblNumeroPersonas.setText("Número de personas: " + numeroPersonas + "/" + numeroMaxPersonas);
        });

        actividades.addActionListener(e -> logger.info("Se ha seleccionado la actividad: " + Objects.requireNonNull(actividades.getSelectedItem())));

        JRadioButton btnSi = new JRadioButton("Si");
        JRadioButton btnNo = new JRadioButton("No");
        ButtonGroup aceptarRechazarRecordatorio = new ButtonGroup();
        aceptarRechazarRecordatorio.add(btnSi);
        aceptarRechazarRecordatorio.add(btnNo);

        btnNo.addActionListener(e -> logger.info("El usuario rechaza el recordatorio"));
        btnSi.addActionListener(e -> logger.info("El usuario acepta el recordatorio"));

        JButton btnRegistrarHora = new JButton("Registrar hora");
        JButton btnVolver = new JButton("Volver");

        btnVolver.addActionListener(e -> {
            logger.info("Se ha pulsado el botón Volver");
            this.dispose();
        });

        btnRegistrarHora.addActionListener(e -> {
            ventanaTabla.getTabla().setValueAt(actividades.getSelectedItem(), localizarHoraEnTabla(horaActividad), localizarFechaEnTabla(fechaActividad));
            VentanaInicioSesion.getUsuario().getlActividades().add((Actividad) actividades.getSelectedItem());
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
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String convertirDiaSemana(String dia) {
        return switch (dia) {
            case "MONDAY" -> "Lunes";
            case "TUESDAY" -> "Martes";
            case "WEDNESDAY" -> "Miercoles";
            case "THURSDAY" -> "Jueves";
            case "FRIDAY" -> "Viernes";
            case "SATURDAY" -> "Sabado";
            case "SUNDAY" -> "Domingo";
            default -> "";
        };
    }

    private int localizarHoraEnTabla(String hora) {
    	return switch (hora) {
    		case "8:10 - 9:00" -> 0;
    		case "9:10 - 10:00" -> 1;
    		case "10:10 - 11:00" -> 2;
    		case "11:10 - 12:00" -> 3;
    		case "12:10 - 13:00" -> 4;
    		case "13:10 - 14:00" -> 5;
    		case "17:10 - 18:00" -> 6;
    		case "18:10 - 19:00" -> 7;
    		case "19:10 - 20:00" -> 8;
    		case "20:10 - 21:00" -> 9;
            default -> -1;
    	};
    }

    private int localizarFechaEnTabla(LocalDate fecha) {
        if (Objects.equals(fecha, LocalDate.now())) {
            return 0;
        }
        if (Objects.equals(fecha, LocalDate.now().plusDays(1))) {
            return 1;
        }
        if (Objects.equals(fecha, LocalDate.now().plusDays(2))) {
            return 2;
        }
        if (Objects.equals(fecha, LocalDate.now().plusDays(3))) {
            return 3;
        }
        if (Objects.equals(fecha, LocalDate.now().plusDays(4))) {
            return 4;
        }
        return 5;
    }
}
