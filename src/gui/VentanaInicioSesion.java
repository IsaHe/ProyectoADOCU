package gui;


import db.BaseDeDatos;
import domain.Usuario;
import io.GestorFicheros;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * VentanaInicioSesion es una clase que extiende JFrame y representa la ventana de inicio de sesión en la aplicación.
 * Esta ventana permite al usuario iniciar sesión en la aplicación.
 * <p>
 * La clase contiene varios componentes como paneles, botones, etiquetas, campos de texto y un campo de contraseña.
 * También mantiene una conexión con la base de datos y un Logger para registrar información y errores.
 * <p>
 * El constructor de la clase inicializa la ventana y sus componentes.
 * Establece las propiedades de la ventana, añade componentes a los paneles, y añade oyentes de acción a los botones.
 * También llena la lista de usuarios con los usuarios de la base de datos y actualiza las actividades semanales.
 * <p>
 * La clase también contiene un método estático para obtener el usuario que ha iniciado sesión.
 * <p>
 * Los botones de la ventana tienen asignados varios eventos. El botón de inicio de sesión comprueba si el usuario y la contraseña introducidos son correctos y, en caso afirmativo, inicia la sesión y abre la ventana correspondiente. El botón de registro abre la ventana de registro. El botón de salir cierra la ventana de inicio de sesión y abre la ventana de valoración.
 */
public class VentanaInicioSesion extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(VentanaInicioSesion.class.getName());
    private final JTextField txtUsuario;
	private final JPasswordField pasContrasena;
    private final JFrame VentanaActual;
    private static Usuario  usuario;
	
	public static Usuario getUsuario() {
		return usuario;
	}

	public VentanaInicioSesion () {
		
		VentanaActual = this;
        Connection con = BaseDeDatos.iniciarBaseDeDatos("src/db/usuarios.db");
        BaseDeDatos.obtenerUsuariosDeBaseDeDatos(con);
        Connection conn = BaseDeDatos.iniciarBaseDeDatos("src/db/valoraciones.db");
        BaseDeDatos.obtenerValoracionesDeBaseDeDatos(conn);
		GestorFicheros.obtenerActividadesDeFichero(Paths.get("src/io/ListaActividades.txt"));
		GestorFicheros.obtenerActividadesSemanalesDeFichero(Paths.get("src/io/ActividadesSemanales.txt"));
		GestorFicheros.actualizarActividadesSemanales();
		GestorFicheros.setUltimoAccesoAtabla(LocalDate.now());

		//FUNCIONES VENTANA
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("ADOCU");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//CREACION CONTENEDORES
        JPanel panelNorte = new JPanel();
        JPanel panelCentro = new JPanel();
        JPanel panelCentroIzq = new JPanel();
        JPanel panelSur = new JPanel();
        JLabel lblFoto = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images//ADOCU.png"))));
        JPanel panelConstrasena = new JPanel();
        JPanel panelUsuario = new JPanel();
		
		//CREACION COMPONENTES
        JLabel lblUsuario = new JLabel("    - Usuario: ");
        JLabel lblContrasena = new JLabel("    - Contraseña: ");
        JLabel lblTitulo = new JLabel(" - ADOCU - ");
		txtUsuario = new JTextField(20);
		pasContrasena = new JPasswordField(20);
        JButton btnInicioSesion = new JButton("INICIAR SESION");
        JButton btnRegistrarse = new JButton("REGISTRARSE");
        JButton btnSalir = new JButton("SALIR");
		
		//FUNCIONES CONTENEDORES
		panelCentro.setLayout(new GridLayout(1,2));
		panelCentroIzq.setLayout(new GridLayout(2,2));
		panelConstrasena.setLayout(new GridLayout(3,1));
		panelUsuario.setLayout(new GridLayout(3,1));
		
		
		//FUNCIONES COMPONENTES
		lblTitulo.setFont(new Font(Font.SERIF, Font.BOLD, 65));
		lblUsuario.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
		lblContrasena.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
		txtUsuario.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		btnInicioSesion.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
		btnRegistrarse.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
		btnSalir.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
		
		
		
		
		//AÑADIR COMPONENTES A CONTENEDORES
		panelNorte.add(lblTitulo);
		panelCentro.add(panelCentroIzq);
		panelCentro.add(lblFoto);
		panelCentroIzq.add(lblUsuario);
		panelCentroIzq.add(panelUsuario);
		panelUsuario.add(new JPanel());
		panelUsuario.add(txtUsuario);
		panelUsuario.add(new JPanel());
		panelCentroIzq.add(lblContrasena);
		panelCentroIzq.add(panelConstrasena);
		panelConstrasena.add(new JPanel());
		panelConstrasena.add(pasContrasena);
		panelConstrasena.add(new JPanel());
		panelSur.add(btnInicioSesion);
		panelSur.add(btnRegistrarse);
		panelSur.add(btnSalir);
		
		//AÑADIR CONTENEDOR A VENTANA PRINCIPAL
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		setVisible(true);
		
		//EVENTOS BOTONES
		btnInicioSesion.addActionListener((e)->{
			
			usuario = new Usuario(txtUsuario.getText(), pasContrasena.getText());
			String contrasena = pasContrasena.getText();
			String usuario1 = txtUsuario.getText();
			int pos = BaseDeDatos.getUsuarios().indexOf(usuario);
			Path ruta = Paths.get("src/io/ActividadesUsuario.dat");
			if(usuario1.equals("Admin") && contrasena.equals("111")) {
				usuario.setNom("Admin");
				usuario.setApellido("Admin");
				usuario.setEdad(20);
				GestorFicheros.obtenerActividadesUsuarioEnFicheroBinario(usuario, ruta);
				new VentanaAdmin();
				VentanaActual.setVisible(false);
				logger.info("Se ha iniciado Sesion con el admin");
			}else if (BaseDeDatos.comprobarUsuario(usuario) && BaseDeDatos.getUsuarios().get(pos).getContrasena().equals(usuario.getContrasena())) {
				usuario.setNom(BaseDeDatos.getUsuarios().get(pos).getNom());
				usuario.setApellido(BaseDeDatos.getUsuarios().get(pos).getApellido());
				usuario.setEdad(BaseDeDatos.getUsuarios().get(pos).getEdad());
				GestorFicheros.obtenerActividadesUsuarioEnFicheroBinario(usuario, ruta);
				logger.info("Se ha iniciado sesion");
				new VentanaTabla();
				this.dispose();
			}else if (BaseDeDatos.comprobarUsuario(usuario)){
				JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
				logger.info("Contraseña incorrecta");
			}else {
				JOptionPane.showMessageDialog(null, "El usuario no esta registrado");
				logger.info("El usuario no esta registrado");
			}
			
			txtUsuario.setText("");
			pasContrasena.setText("");
		});
		
		btnSalir.addActionListener((e)->{	
			new VentanaValoracion();
			this.dispose();
			logger.info("Se ha pulsado el botón Salir");
		});
		
		btnRegistrarse.addActionListener((e)->{
			new VentanaRegistro();
			this.dispose();
			logger.info("Se ha pulsado el botón Registrarse");
		});
	}

}

