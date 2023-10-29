package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.BaseDeDatos;
import domain.Usuario;

public class VentanaRegistro extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS DE LA VENTANA
	
	private JPanel pSur, pCentro, pNorte, pNombre, pUsuario, pApellidos, pContra, pEdad;
	private JTextField txtNombre, txtUsuario, txtApellidos;
	private JLabel lblNombre, lblUsuario, lblContra, lblApellidos, lblEdad, lblTitulo;
	private JPasswordField txtContra;
	private JComboBox<Integer> cEdad;
	private JButton btnRegistro, btnVolver, btnSalir;
	private Path ruta = Paths.get("src/io/UsuariosRegistrados.txt"); 
		
	public VentanaRegistro(){
		super();
	
		//TAMAÑO Y FORMA DE LA VENTANA
		
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//PANELES Y SUS ASIGNACIONES EN LA VENTANA
		
		pCentro = new JPanel();
		pNorte = new JPanel();
		pSur = new JPanel();
		pNombre = new JPanel();
		pApellidos = new JPanel();
		pEdad = new JPanel();
		pContra = new JPanel();
		pUsuario = new JPanel();
		
		pCentro.setLayout(new GridLayout(0,2));
		pSur.setLayout(new GridLayout(1,3));
		pNombre.setLayout(new GridLayout(3,1));
		pApellidos.setLayout(new GridLayout(3,1));
		pEdad.setLayout(new GridLayout(3,1));
		pUsuario.setLayout(new GridLayout(3,1));
		pContra.setLayout(new GridLayout(3,1));
		
		
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		//NOMBRE
		
		txtNombre = new JTextField(20);
		lblNombre = new JLabel("      - NOMBRE: ");
		lblNombre.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		lblNombre.setForeground(Color.BLACK);
		
		//KEYADAPTER PARA EL TEXTFIELD(NOMBRE)
		
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				  char j = e.getKeyChar();
				   if ((j < 'a' || j > 'z') &&(j < 'A' || j > 'Z') &&(j > ' ') || (txtNombre.getText().length() >= 15)) {
					   e.consume();
				}
			}
		}); 
		
		//APELLIDOS
		
		txtApellidos = new JTextField(20);
		lblApellidos = new JLabel("      - APELLIDOS: ");
		lblApellidos.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		lblApellidos.setForeground(Color.BLACK);
		
		//KEYADAPTER PARA EL TEXTFIELD(APELLIDOS)
		
		txtApellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				  char j = e.getKeyChar();
				   if ((j < 'a' || j > 'z') &&(j < 'A' || j > 'Z') &&(j > ' ') || (txtApellidos.getText().length() >= 30)) {
					   e.consume();
				}
			}
		}); 
		
		//EDAD
		
		Vector<Integer> numeros = new Vector<>();
		for (int i = 18; i<100; i++) {
			numeros.add(i);
		}
		cEdad = new JComboBox<Integer>(numeros);
		
		
		lblEdad = new JLabel("      - EDAD: ");
		lblEdad.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		lblEdad.setForeground(Color.BLACK);
	
		//USUARIO
		
		txtUsuario = new JTextField(20);
		lblUsuario = new JLabel("      - USUARIO: ");
		lblUsuario.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		lblUsuario.setForeground(Color.BLACK);
		
		//KEYADAPTER PARA EL TEXTFIELD(USUARIO)
		
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				   if ((txtUsuario.getText().length() >= 15)) {
					   e.consume();
				}
			}
		});
		
		//CONTRASEÑA
		
		txtContra = new JPasswordField(20);
		lblContra = new JLabel("      - CONTRASEÑA: ");
		lblContra.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		lblContra.setForeground(Color.BLACK);
		
		//KEYADAPTER PARA EL TEXTFIELD(CONTRASEÑA)
		
		txtContra.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent e) {
				   if ((txtContra.getText().length() >= 30)) {
					   e.consume();
				}
			}
		});
		
		//TITULO
		
		lblTitulo  = new JLabel("REGISTRO");
		lblTitulo.setFont(new Font(Font.SERIF, Font.PLAIN, 40));
		
		//BOTONES
		
		btnRegistro = new JButton("REGISTRO");
		btnRegistro.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
		btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
		btnSalir = new JButton("SALIR");
		btnSalir.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
		
		//ASIGNACIÓN DE LOS ATRIBUTOS A LA VENTANA
		
		pNorte.add(lblTitulo);
		
		pCentro.add(lblNombre);
		pCentro.add(pNombre);
		pNombre.add(new JPanel());
		pNombre.add(txtNombre);
		pNombre.add(new JPanel());
		pCentro.add(lblApellidos);
		pCentro.add(pApellidos);
		pApellidos.add(new JPanel());
		pApellidos.add(txtApellidos);
		pApellidos.add(new JPanel());
		pCentro.add(lblEdad);
		pCentro.add(pEdad);
		pEdad.add(new JPanel());
		pEdad.add(cEdad);
		pEdad.add(new JPanel());
		pCentro.add(lblUsuario);
		pCentro.add(pUsuario);
		pUsuario.add(new JPanel());
		pUsuario.add(txtUsuario);
		pUsuario.add(new JPanel());
		pCentro.add(lblContra);
		pCentro.add(pContra);
		pContra.add(new JPanel());
		pContra.add(txtContra);
		pContra.add(new JPanel());
		pSur.add(btnRegistro);
		pSur.add(btnVolver);
		pSur.add(btnSalir);
		
		
		//EVENTOS BOTONES
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		btnVolver.addActionListener((e)->{
			
			new VentanaInicioSesion();
			this.dispose();
			
		});
		
		btnRegistro.addActionListener(e->{
			
			Usuario usuario = new Usuario(txtNombre.getText(), txtApellidos.getText(), (int)cEdad.getSelectedItem(), txtUsuario.getText(), txtContra.getText());
			if (!BaseDeDatos.comprobarUsuario(usuario)) {
				BaseDeDatos.getUsuarios().add(usuario);
				BaseDeDatos.cargarUsuariosEnFichero(ruta);
				JOptionPane.showMessageDialog(null, "Usuario Registrado Correctamente");
				new VentanaInicioSesion();
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Este Usuario ya existe");
			}
		});
		
		setVisible(true);
	}

}
