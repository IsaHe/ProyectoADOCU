package gui;

import domain.Actividad;
import io.GestorFicheros;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * VentanaModificacionActividades es una clase que extiende JFrame y representa la ventana de modificación de actividades en la aplicación.
 * Esta ventana permite al usuario modificar las actividades.
 * <p>
 * La clase contiene varios componentes como etiquetas, botones, paneles y un JComboBox.
 * También mantiene una lista de actividades y el número máximo de personas para una actividad.
 * <p>
 * El constructor de la clase inicializa la ventana y sus componentes.
 * Establece las propiedades de la ventana, añade componentes a los paneles, y añade oyentes de acción a los botones.
 * También llena el JComboBox con las actividades del día seleccionado.
 * <p>
 * La clase también contiene varios métodos privados para convertir el día de la semana de inglés a español,
 * localizar la hora en la tabla y localizar la fecha en la tabla.
 */
public class VentanaModificacionActividades extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(VentanaModificacionActividades.class.getName());
    final Actividad[] listaActividades;
    int numeroMaxPersonas = 0;

    public VentanaModificacionActividades(int numeroPersonas, String horaActividad, LocalDate fechaActividad, VentanaTabla ventanaTabla) {    //Formato horas [hora, minutos, segundos]
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Modificación de actividades");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images//ADOCU.png"))).getImage());
        setLayout(new GridLayout(4, 2, 0, 0));

        //CREACION Y FORMATO DE LOS LABELS
        Vector<Integer> numeros = new Vector<>();
		for (int i = 0; i<11; i++) {
			numeros.add(i);
		}
        JComboBox<Integer> NumeroDePersonas = new JComboBox<>(numeros);
        JLabel lblHora = new JLabel(" Hora: " + horaActividad);
        JLabel lblFecha = new JLabel("Fecha: " + fechaActividad);
        JLabel lblNumeroPersonas = new JLabel("Número de personas: " + numeroPersonas + "/");
        JLabel lblActividad = new JLabel("Actividad: ");
        JLabel lblRecordatrio = new JLabel("Recordatorio: ");

        lblHora.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
        lblFecha.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
        lblRecordatrio.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
        lblActividad.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
        lblNumeroPersonas.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));

        //CREACION Y CONFIGURACION DE LOS ELEMENTOS CON LOS QUE PUEDE INTERACTUAR EL USUARIO
        String dia = convertirDiaSemana(fechaActividad.getDayOfWeek().toString());
        listaActividades = GestorFicheros.getActividades().get(dia).toArray(new Actividad[0]);

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
        btnRegistrarHora.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font(Font.SERIF, Font.PLAIN, 25));

        btnVolver.addActionListener(e -> {
            logger.info("Se ha pulsado el botón Volver");
            this.dispose();
        });

        btnRegistrarHora.addActionListener(e -> {
            ventanaTabla.getTabla().setValueAt(actividades.getSelectedItem(), localizarHoraEnTabla(horaActividad), localizarFechaEnTabla(fechaActividad));
            VentanaInicioSesion.getUsuario().getlActividades().add((Actividad) actividades.getSelectedItem());
            GestorFicheros.cargarActividadesUsuarioEnFicheroBinario(VentanaInicioSesion.getUsuario(), Paths.get("src/io/ActividadesUsuario.dat"));
            GestorFicheros.asignarUsuarioAActividadSemanal((Actividad) actividades.getSelectedItem(), VentanaInicioSesion.getUsuario().getUsuario());
            GestorFicheros.cargarActividadesSemanalesEnFichero(Paths.get("src/io/ActividadesSemanales.txt"));
            logger.info("Se ha pulsado el botón Registrar hora");
            this.dispose();
        });

        //CREACION DE LOS CONTENEDORES Y CONFIGURACION DE LOS MISMOS
        JPanel panelVacio = new JPanel();

        JPanel panelActividad = new JPanel();
        panelActividad.add(lblActividad);
        panelActividad.add(actividades);

        JPanel panelRadioBotones = new JPanel();
        
        panelRadioBotones.add(btnSi);
        panelRadioBotones.add(btnNo);

        JPanel panelRecordatorio = new JPanel();
        panelRecordatorio.setLayout(new GridLayout(1, 2));
        panelRecordatorio.add(lblRecordatrio);
        panelRecordatorio.add(panelRadioBotones);
        
        JPanel panelPersona = new JPanel();
        panelPersona.setLayout(new GridLayout(0, 3));
        //ADICION DE ELEMENTOS AL CONTENEDOR PRINCIPAL
        add(lblHora);
        add(lblFecha);
        add(panelActividad);
        add(lblNumeroPersonas);
        add(panelPersona);
        panelPersona.add(NumeroDePersonas);
        panelPersona.add(new JPanel());
        panelPersona.add(new JPanel());
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

    /**
     * Metodo para convertir el dia de la semana en ingles a español.
     *
     * @param dia El dia de la semana en ingles.
     * @return El dia de la semana en español.
     */
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

    /**
     * Metodo para localizar la hora en la tabla.
     *
     * @param hora La hora a localizar.
     * @return La posicion de la hora en la tabla.
     */
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

    /**
     * Metodo para localizar la fecha en la tabla.
     *
     * @param fecha La fecha a localizar.
     * @return La posicion de la fecha en la tabla.
     */
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
