package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaInicioSesion extends JFrame{
	
	private JPanel panelCentro, panelCentroIzq, panelSur, panelNorte, panelConstraseña, panelUsuario;
	private JLabel lblUsuario, lblContrasena, lblTitulo, lblFoto;
	private JTextField txtUsuario;
	private JPasswordField pasContrasena;
	private JButton btnInicioSesion, btnRegistrarse, btnSalir;
	
	public VentanaInicioSesion () {
		
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
		
		//EVENTOS
		
		btnInicioSesion.addActionListener((e)->{
			
			new VentanaTabla();
			this.dispose();
			
		});
		
		btnSalir.addActionListener((e)->{
			new VentanaValoracion();
			this.dispose();
		});
		
		btnRegistrarse.addActionListener((e)->{
			
			new VentanaRegistro();
			this.dispose();
			
		});
	}

}
