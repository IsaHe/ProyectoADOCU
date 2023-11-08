package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import domain.Usuario;

public class VentanaUsuario_Admin extends JFrame{
	
	private JPanel pCentro, pTitulo, pSur;
	private JLabel lblTitulo;
	private JList<Usuario> lUsu;
	private DefaultListModel<Usuario> modeloLista;
	private ScrollPane scroll;
	private JButton btnVolver, btnSalir;
	private JFrame VentanaAntigua, VentanaActual;
	
	public VentanaUsuario_Admin(JFrame va) {
		
		VentanaActual = this;
		VentanaAntigua = va;
		
		setBounds(200, 200, 500, 500);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		pCentro = new JPanel();
		pTitulo = new JPanel();
		pSur = new JPanel();
		
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pTitulo, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		lblTitulo = new JLabel("USUARIOS REGISTRADOS");
		lblTitulo.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		lblTitulo.setForeground(Color.BLACK);
		
		btnSalir = new JButton("SALIR");
		btnVolver = new JButton("VOLVER");
		
		modeloLista = new DefaultListModel<>();
		scroll = new ScrollPane();
		lUsu = new JList<Usuario>(modeloLista);
		scroll.add(lUsu);
		
		pCentro.add(scroll);
		pSur.add(btnVolver);
		pSur.add(btnSalir);
		pTitulo.add(lblTitulo);
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaAdmin();
				VentanaActual.dispose();
			}
		});
		
		setVisible(true);
	}

}
