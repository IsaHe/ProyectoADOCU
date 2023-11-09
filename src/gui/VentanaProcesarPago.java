package gui;

import domain.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class VentanaProcesarPago extends JFrame{

	private final Logger logger = Logger.getLogger(VentanaProcesarPago.class.getName());
	private JProgressBar pb;
	private JPanel pCentral,panel;
	private JLabel lblTitulo;
	private JFrame vActual;
	
	public VentanaProcesarPago(Usuario usuario) {
		super();
		vActual = this;
		
		setBounds(200, 200, 200, 100);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(null);
		
		//CREACION DE CONTENEDORES
		pb = new JProgressBar(1, 100);
		lblTitulo = new JLabel("Procesando el pago...");
		
		pCentral = new JPanel(new GridLayout(2, 1));
		pCentral.setAlignmentY(CENTER_ALIGNMENT);
		pCentral.add(lblTitulo);
		panel = new JPanel();
		panel.add(pb);
		pCentral.add(panel);
		getContentPane().add(pCentral);
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<100;i++) {
					pb.setValue(i);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						logger.warning("Error al dormir el hilo");
						logger.warning(e.getMessage());
					}
				}
				JOptionPane.showMessageDialog(null, "Pago recibido correctamente");
				new VentanaPago(usuario);
				vActual.dispose();
			}
		}); 
		t.start();
		
		setVisible(true);
	}
}
