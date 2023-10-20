package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaRegistro extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS DE LA VENTANA
	
	private JPanel pSur, pCentro, pDerecha;
	private JTextField txtNombre, txtUsuario, txtApellidos;
	private JLabel lblNombre, lblUsuario, lblContra, lblApellidos, lblEdad;
	private JPasswordField txtContra;
	private JComboBox<Integer> cEdad;
	private JButton btnRegistro, btnVolver, btnSalir;
		
	public VentanaRegistro(){
		super();
	
		//TAMAÑO Y FORMA DE LA VENTANA
		
		setBounds(200, 200, 500, 200);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//PANELES Y SUS ASIGNACIONES EN LA VENTANA
		
		pCentro = new JPanel();
		pDerecha = new JPanel(new GridLayout(0, 2));
		pSur = new JPanel();
		
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		//NOMBRE
		
		txtNombre = new JTextField(20);
		lblNombre = new JLabel("NOMBRE: ");
		lblNombre.setFont(new Font(Font.SERIF, Font.BOLD, 14));
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
		lblApellidos = new JLabel("APELLIDOS: ");
		lblApellidos.setFont(new Font(Font.SERIF, Font.BOLD, 14));
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
		
		lblEdad = new JLabel("EDAD: ");
		lblEdad.setFont(new Font(Font.SERIF, Font.BOLD, 14));
		lblEdad.setForeground(Color.BLACK);
	
		//USUARIO
		
		txtUsuario = new JTextField(20);
		lblUsuario = new JLabel("USUARIO: ");
		lblUsuario.setFont(new Font(Font.SERIF, Font.BOLD, 14));
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
		lblContra = new JLabel("CONTRASEÑA: ");
		lblContra.setFont(new Font(Font.SERIF, Font.BOLD, 14));
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
		
		//BOTONES
		
		btnRegistro = new JButton("REGISTRO");
		btnVolver = new JButton("VOLVER");
		btnSalir = new JButton("SALIR");
		
		//ASIGNACIÓN DE LOS ATRIBUTOS A LA VENTANA
		
		pCentro.add(pDerecha);
		pDerecha.add(lblNombre);
		pDerecha.add(txtNombre);
		pDerecha.add(lblApellidos);
		pDerecha.add(txtApellidos);
		pDerecha.add(lblEdad);
		pDerecha.add(cEdad);
		pDerecha.add(lblUsuario);
		pDerecha.add(txtUsuario);
		pDerecha.add(lblContra);
		pDerecha.add(txtContra);
		pSur.add(btnRegistro);
		pSur.add(btnVolver);
		pSur.add(btnSalir);
		
		
		//ACTIONS
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		setVisible(true);
	}
	
	//MAIN
	
	public static void main(String[] args) {
		VentanaRegistro v = new VentanaRegistro();
		
		System.out.println(v.toString());
		
	}
	
	
	

}
