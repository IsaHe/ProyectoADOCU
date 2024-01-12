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

/**
 * VentanaRegistro es una clase que extiende JFrame y representa la ventana de registro en la aplicación.
 * Esta ventana permite al usuario registrarse en la aplicación.
 * <p>
 * La clase contiene varios componentes como paneles, botones, etiquetas, campos de texto, un campo de contraseña y un JComboBox.
 * También mantiene una conexión con la base de datos y un Logger para registrar información y errores.
 * <p>
 * El constructor de la clase inicializa la ventana y sus componentes.
 * Establece las propiedades de la ventana, añade componentes a los paneles, y añade oyentes de acción a los botones.
 * También llena el JComboBox con los números del 18 al 100 para seleccionar la edad.
 * <p>
 * Los botones de la ventana tienen asignados varios eventos. El botón de registro comprueba si el usuario ya existe y, en caso contrario, registra al usuario en la base de datos y abre la ventana de inicio de sesión. El botón de volver cierra la ventana de registro y abre la ventana de inicio de sesión. El botón de salir cierra la aplicación.
 * <p>
 * Los campos de texto de nombre, apellidos y usuario tienen asignados KeyAdapters para limitar la longitud de los textos introducidos y permitir solo caracteres alfabéticos en el caso de nombre y apellidos.
 */
public class VentanaRegistro extends JFrame{
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS DE LA VENTANA

	private final Logger logger = Logger.getLogger(VentanaRegistro.class.getName());
    private final JTextField txtNombre;
    private final JTextField txtUsuario;
    private final JTextField txtApellidos;
    private final JPasswordField txtContra;
	private final JComboBox<Integer> cEdad;
    private final Connection con = BaseDeDatos.iniciarBaseDeDatos("src/db/usuarios.db");
		
	public VentanaRegistro(){
		super();
	
		
		
		//TAMAÑO Y FORMA DE LA VENTANA
		
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//PANELES Y SUS ASIGNACIONES EN LA VENTANA

        JPanel pCentro = new JPanel();
        JPanel pNorte = new JPanel();
        JPanel pSur = new JPanel();
        JPanel pNombre = new JPanel();
        JPanel pApellidos = new JPanel();
        JPanel pEdad = new JPanel();
        JPanel pContra = new JPanel();
        JPanel pUsuario = new JPanel();
		
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
        JLabel lblNombre = new JLabel("      - NOMBRE: ");
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
        JLabel lblApellidos = new JLabel("      - APELLIDOS: ");
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


        JLabel lblEdad = new JLabel("      - EDAD: ");
		lblEdad.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		lblEdad.setForeground(Color.BLACK);
	
		//USUARIO
		
		txtUsuario = new JTextField(20);
        JLabel lblUsuario = new JLabel("      - USUARIO: ");
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
        JLabel lblContra = new JLabel("      - CONTRASEÑA: ");
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

        JLabel lblTitulo = new JLabel("REGISTRO");
		lblTitulo.setFont(new Font(Font.SERIF, Font.PLAIN, 40));
		
		//BOTONES

        JButton btnRegistro = new JButton("REGISTRO");
		btnRegistro.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        JButton btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        JButton btnSalir = new JButton("SALIR");
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
