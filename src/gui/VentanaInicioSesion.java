package gui;


import db.BaseDeDatos;
import domain.Usuario;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class VentanaInicioSesion extends JFrame{

	private final Logger logger = Logger.getLogger(VentanaInicioSesion.class.getName());
	private JPanel panelCentro, panelCentroIzq, panelSur, panelNorte, panelConstraseña, panelUsuario;
	private JLabel lblUsuario, lblContrasena, lblTitulo, lblFoto;
	private JTextField txtUsuario;
	private JPasswordField pasContrasena;
	private JButton btnInicioSesion, btnRegistrarse, btnSalir;
	private JFrame VentanaActual;
	private Path ruta = Paths.get("src/io/UsuariosRegistrados.txt"); 
	private static Usuario  usuario;
	
	public static Usuario getUsuario() {
		return usuario;
	}

	public VentanaInicioSesion () {
		
		VentanaActual = this;
		BaseDeDatos.obtenerUsuariosDeFichero(ruta);
		BaseDeDatos.obtenerActividadesDeFichero(Paths.get("src/io/ListaActividades.txt"));
		System.out.println(BaseDeDatos.getActividades());
		
		//FUNCIONES VENTANA
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("ADOCU");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//CREACION CONTENEDORES
		panelNorte = new JPanel();
		panelCentro = new JPanel();
		panelCentroIzq = new JPanel();
		panelSur = new JPanel();
		lblFoto = new JLabel(new ImageIcon(getClass().getResource("/resources/images//ADOCU.png")));
		panelConstraseña = new JPanel();
		panelUsuario = new JPanel();
		
		//CREACION COMPONENTES
		lblUsuario = new JLabel("    - Usuario: ");
		lblContrasena = new JLabel("    - Contraseña: ");
		lblTitulo = new JLabel(" - ADOCU - ");
		txtUsuario = new JTextField(20);
		pasContrasena = new JPasswordField(20);
		btnInicioSesion = new JButton("INICIAR SESION");
		btnRegistrarse = new JButton("REGISTRARSE");
		btnSalir = new JButton("SALIR");
		
		//FUNCIONES CONTENEDORES
		panelCentro.setLayout(new GridLayout(1,2));
		panelCentroIzq.setLayout(new GridLayout(2,2));
		panelSur.setLayout(new GridLayout(1,3));
		panelConstraseña.setLayout(new GridLayout(3,1));
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
		panelCentroIzq.add(panelConstraseña);
		panelConstraseña.add(new JPanel());
		panelConstraseña.add(pasContrasena);
		panelConstraseña.add(new JPanel());
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
			if(usuario1.equals("Admin") && contrasena.equals("111")) {
				usuario.setNom("Admin");
				usuario.setApellido("Admin");
				usuario.setEdad(20);
				new VentanaAdmin(VentanaActual);
				VentanaActual.setVisible(false);
			}else if (BaseDeDatos.comprobarUsuario(usuario) && BaseDeDatos.getUsuarios().get(pos).getContraseña().equals(usuario.getContraseña())) {
				usuario.setNom(BaseDeDatos.getUsuarios().get(pos).getNom());
				usuario.setApellido(BaseDeDatos.getUsuarios().get(pos).getApellido());
				usuario.setEdad(BaseDeDatos.getUsuarios().get(pos).getEdad());
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

