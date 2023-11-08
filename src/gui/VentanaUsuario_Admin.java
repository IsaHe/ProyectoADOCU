package gui;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import domain.Usuario;

public class VentanaUsuario_Admin extends JFrame{
	
	private final Logger logger = Logger.getLogger(VentanaUsuario_Admin.class.getName());
	private JPanel pCentro, pTitulo, pSur;
	private JList<Usuario> lUsu;
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
		
		
		btnSalir = new JButton("SALIR");
		btnVolver = new JButton("VOLVER");
		
		scroll = new ScrollPane();
		lUsu = new JList<Usuario>();
		scroll.add(lUsu);
		
		pCentro.add(scroll);
		pSur.add(btnVolver);
		pSur.add(btnSalir);
		
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
				logger.info("SE ha pulsado el boton para volver a la VentanaAdmin");
			}
		});
		
		setVisible(true);
	}

}
