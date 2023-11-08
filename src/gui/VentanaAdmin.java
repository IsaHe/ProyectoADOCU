package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaAdmin extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = Logger.getLogger(VentanaAdmin.class.getName());
	JFrame ventanaActual = this;
	private JPanel pSur,pCentro, pNorte;
	private JLabel lblTitulo;
	private JButton btnVolver, btnVerUsuarios, btnVerActividades, btnVerPagos, btnSalir;
	private JFrame VentanaActual;

	public VentanaAdmin(){
		super();
		
		VentanaActual = this;
		
		setBounds(200, 200, 400, 550);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		pSur = new JPanel();
		pCentro = new JPanel();
		pNorte = new JPanel();
		
		
		pCentro.setLayout(new GridLayout(9, 1));
		
		
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		lblTitulo = new JLabel("ADMINISTRADOR");
		lblTitulo.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		lblTitulo.setForeground(Color.BLACK);
		
		btnSalir = new JButton("SALIR");
		btnVolver = new JButton("VOLVER");
		btnVerUsuarios = new JButton("VER USUARIOS");
		btnVerActividades = new JButton("VER ACTIVIDADES");
		btnVerPagos = new JButton("VER PAGOS");
		
		pNorte.add(lblTitulo);
		pSur.add(btnSalir);
		pSur.add(btnVolver);
		pCentro.add(new JPanel());
		pCentro.add(btnVerUsuarios);
		pCentro.add(new JPanel());
		pCentro.add(btnVerActividades);
		pCentro.add(new JPanel());
		pCentro.add(btnVerPagos);
		pCentro.add(new JPanel());
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				logger.info("Se ha pulsado el boton para salir");
			}
		});
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaInicioSesion();
				logger.info("Se ha pulsado el boton para volver a la VentanaInicioSesion");
				
			}
		});
		
		//TODO
		btnVerUsuarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaUsuario_Admin(VentanaActual);
				VentanaActual.dispose();
				logger.info("Se ha pulsado el boton para ver los usuarios");
				
			}
		});
		
		//TODO
		btnVerActividades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		
		//TODO
		btnVerPagos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		setVisible(true);
	}
	
}
