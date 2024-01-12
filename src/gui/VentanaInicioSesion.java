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

public class VentanaInicioSesion extends JFrame{

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
		panelSur.setLayout(new GridLayout(1,3));
		panelConstrasena.setLayout(new GridLayout(3,1));
		panelUsuario.setLayout(new GridLayout(3,1));
		
		
		//FUNCIONES COMPONENTES
		lblTitulo.setFont(new Font(Font.DIALOG, Font.ITALIC, 65));
		lblUsuario.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
		lblContrasena.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
		txtUsuario.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		btnInicioSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		btnRegistrarse.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		btnSalir.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		
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

