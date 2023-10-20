package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaPago extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel pCentro, pSur, pNorte, pNumTarjeta, pTitular, pCVV;
	private JLabel lblTitular, lblNumTarjeta, lblCVV, lblTitulo;
	private JTextField txtTitular, txtNumTarjeta, txtCVV;
	private JButton btnPagar, btnSalir;

	public VentanaPago(){
		super();
		
		setBounds(200, 200, 1000, 500);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		pCentro = new JPanel();
		pSur = new JPanel();
		pNorte = new JPanel();
		pTitular = new JPanel();
		pNumTarjeta = new JPanel();
		pCVV = new JPanel();
		
		pCentro.setLayout(new GridLayout(0,2));
		pSur.setLayout(new GridLayout(1,3));
		pTitular.setLayout(new GridLayout(3,1));
		pNumTarjeta.setLayout(new GridLayout(3,1));
		pCVV.setLayout(new GridLayout(3,1));
		
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		lblTitulo = new JLabel("PAGOS");
		lblTitulo.setFont(new Font(Font.SERIF, Font.PLAIN, 60));
		lblTitular = new JLabel("NOMBRE DEL TITULAR: ");
		lblTitular.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		lblNumTarjeta = new JLabel("NUMERO DE LA TARJETA: ");
		lblNumTarjeta.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		lblCVV = new JLabel("CVV: ");
		lblCVV.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		
		txtTitular = new JTextField(20);
		txtNumTarjeta = new JTextField(20);
		txtCVV = new JTextField(20);
		
		btnPagar = new JButton("PAGAR");
		btnPagar.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		btnSalir = new JButton("SALIR");
		btnSalir.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		
		pNorte.add(lblTitulo);
		pSur.add(btnPagar);
		pSur.add(btnSalir);
		pCentro.add(lblTitular);
		pCentro.add(pTitular);
		pTitular.add(new JPanel());
		pTitular.add(txtTitular);
		pTitular.add(new JPanel());
		pCentro.add(lblNumTarjeta);
		pCentro.add(pNumTarjeta);
		pNumTarjeta.add(new JPanel());
		pNumTarjeta.add(txtNumTarjeta);
		pNumTarjeta.add(new JPanel());
		pCentro.add(lblCVV);
		pCentro.add(pCVV);
		pCVV.add(new JPanel());
		pCVV.add(txtCVV);
		pCVV.add(new JPanel());
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		VentanaPago v = new VentanaPago();
		System.out.println(v.toString());
	}
	
	
	
	
	

}
