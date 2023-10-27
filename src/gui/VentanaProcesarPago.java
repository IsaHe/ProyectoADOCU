package gui;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class VentanaProcesarPago extends JFrame{
	private JProgressBar pb;
	private JPanel pCentral,panel;
	private JLabel lblTitulo;
	
	public VentanaProcesarPago() {
		super();
		
		setBounds(200, 200, 200, 100);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(null);
		
		pb = new JProgressBar(1, 100);
		lblTitulo = new JLabel("Procesando el pago...");
		
		pCentral = new JPanel(new GridLayout(2, 1));
		pCentral.setAlignmentY(CENTER_ALIGNMENT);
		pCentral.add(lblTitulo);
		panel = new JPanel();
		panel.add(pb);
		pCentral.add(panel);
		getContentPane().add(pCentral);
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<100;i++) {
					pb.setValue(i);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, "Pago recibido correctamente");
				System.exit(0);
			}
		};
		Thread t = new Thread(r);
		t.start();
		
		
	
		setVisible(true);
	}
	
	public static void main(String[] args) {
		VentanaProcesarPago v = new VentanaProcesarPago();
	}
	
	
}
