package gui;

import db.BaseDeDatos;
import domain.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serial;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Logger;

public class VentanaRegistro extends JFrame{
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS DE LA VENTANA

	private final Logger logger = Logger.getLogger(VentanaRegistro.class.getName());
	private final JPanel pSur;
    private final JPanel pCentro;
    private final JPanel pNorte;
    private final JPanel pNombre;
    private final JPanel pUsuario;
    private final JPanel pApellidos;
    private final JPanel pContra;
    private final JPanel pEdad;
	private final JTextField txtNombre;
    private final JTextField txtUsuario;
    private final JTextField txtApellidos;
	private final JLabel lblNombre;
    private final JLabel lblUsuario;
    private final JLabel lblContra;
    private final JLabel lblApellidos;
    private final JLabel lblEdad;
    private final JLabel lblTitulo;
	private final JPasswordField txtContra;
	private final JComboBox<Integer> cEdad;
	private final JButton btnRegistro;
    private final JButton btnVolver;
    private final JButton btnSalir;
	private final Connection con = BaseDeDatos.iniciarBaseDeDatos("src/db/usuarios.db");
		
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
				logger.info("Se ha pulsado la tecla: " + e.getKeyChar());
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
				logger.info("Se ha pulsado la tecla: " + e.getKeyChar());
			}
		}); 
		
		//EDAD
		
		Vector<Integer> numeros = new Vector<>();
		for (int i = 18; i<100; i++) {
			numeros.add(i);
		}
		cEdad = new JComboBox<>(numeros);
		
		
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
				logger.info("Se ha pulsado la tecla: " + e.getKeyChar());
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
				logger.info("Se ha pulsado la tecla: " + e.getKeyChar());
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
		
		btnSalir.addActionListener(e -> {
            System.exit(0);
            logger.info("Se ha pulsado el botón Salir");
        });
		
		btnVolver.addActionListener((e)->{
			
			new VentanaInicioSesion();
			this.dispose();
			logger.info("Se ha pulsado el botón Volver");
			
		});
		
		btnRegistro.addActionListener(e->{
			assert cEdad.getSelectedItem() != null;
			Usuario usuario = new Usuario(txtNombre.getText(), txtApellidos.getText(), (int)cEdad.getSelectedItem(), txtUsuario.getText(), Arrays.toString(txtContra.getPassword()), null);
			if (!BaseDeDatos.comprobarUsuario(usuario)) {
				BaseDeDatos.getUsuarios().add(usuario);
				BaseDeDatos.cargarUsuariosEnBaseDeDatos(con);
				JOptionPane.showMessageDialog(null, "Usuario Registrado Correctamente");
				logger.info("Usuario Registrado Correctamente");
				new VentanaInicioSesion();
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Este Usuario ya existe");
				logger.info("Se ha intentado registrar un usuario que ya existe");
			}
		});
		
		setVisible(true);
	}

}
