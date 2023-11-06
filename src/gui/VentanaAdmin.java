package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaAdmin extends JFrame{
	
	private JFrame VentanaAntigua, VentanaActual;
	private JPanel pSur,pCentro, pNorte;
	private JLabel lblTitulo;
	private JButton btnVolver, btnVerUsuarios, btnVerActividades, btnVerPagos, btnSalir;

	public VentanaAdmin(JFrame va){
		super();
		VentanaAntigua = va;
		VentanaActual = this;
		
		setBounds(200, 200, 400, 600);
		
		pSur = new JPanel();
		pCentro = new JPanel();
		pNorte = new JPanel();
		
		pCentro.setLayout(new GridLayout(0, 1));
		
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
		pCentro.add(btnVerUsuarios);
		pCentro.add(btnVerActividades);
		pCentro.add(btnVerPagos);
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaInicioSesion();
				VentanaActual.setVisible(false);
				
			}
		});
		
		//TODO
		btnVerUsuarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
