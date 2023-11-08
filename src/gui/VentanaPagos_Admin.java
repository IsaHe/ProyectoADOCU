package gui;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaPagos_Admin extends JFrame{
	
	private JPanel pCentro, pTitulo, pSur;
	private JTable tActividad;
	private DefaultTableModel modeloTabla;
	private ScrollPane scroll;
	private JButton btnVolver, btnSalir;
	private JFrame VentanaAntigua, VentanaActual;
	
	public VentanaPagos_Admin(JFrame va) {
		
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
		
		modeloTabla = new DefaultTableModel();
		tActividad = new JTable(modeloTabla);
		scroll = new ScrollPane();
		scroll.add(tActividad);
		
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
			}
		});
		setVisible(true);
	}
}
