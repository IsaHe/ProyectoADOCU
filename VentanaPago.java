
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaPago extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel pCentro, pSur, pNorte, pNumTarjeta, pImporte, pCVV, pAbajoUsuario, pAbajoContraseña, pUsuario, pContra;
	private JLabel lblImporte, lblNumTarjeta, lblCVV, lblTitulo, lblUsuario, lblContrasena;
	private JTextField txtTitular, txtNumTarjeta, txtCVV, txtUsuario;
	private JButton btnPagar, btnVolver;
	private JPasswordField pasContrasena;

	public VentanaPago(){
		super();
		
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		pCentro = new JPanel();
		pSur = new JPanel();
		pNorte = new JPanel();
		pImporte = new JPanel();
		pNumTarjeta = new JPanel();
		pCVV = new JPanel();
		pAbajoUsuario = new JPanel();
		pAbajoContraseña = new JPanel();
		pUsuario = new JPanel();
		pContra = new JPanel();
		
		pCentro.setLayout(new GridLayout(0,2));
		pSur.setLayout(new GridLayout(0,2));
		pImporte.setLayout(new GridLayout(3,1));
		pNumTarjeta.setLayout(new GridLayout(3,1));
		pCVV.setLayout(new GridLayout(3,1));
		pAbajoUsuario.setLayout(new GridLayout(0,2));
		pAbajoContraseña.setLayout(new GridLayout(0,2));
		pUsuario.setLayout(new GridLayout(3,0));
		pContra.setLayout(new GridLayout(3,0));
		
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		lblTitulo = new JLabel("PAGOS");
		lblTitulo.setFont(new Font(Font.SERIF, Font.PLAIN, 60));
		lblImporte = new JLabel("IMPORTE A PAGAR: ");
		lblImporte.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		lblNumTarjeta = new JLabel("NUMERO DE LA TARJETA: ");
		lblNumTarjeta.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		lblCVV = new JLabel("CVV: ");
		lblCVV.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		lblUsuario = new JLabel("USUARIO: ");
		lblUsuario.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		lblContrasena = new JLabel("CONTRASEÑA: ");
		lblContrasena.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		
		txtTitular = new JTextField(20);
		txtNumTarjeta = new JTextField(20);
		txtCVV = new JTextField(20);
		txtUsuario = new JTextField(20);
		pasContrasena = new JPasswordField(20);
		
		btnPagar = new JButton("PAGAR");
		btnPagar.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		
		pNorte.add(lblTitulo);
		pSur.add(btnVolver);
		pSur.add(btnPagar);
		pCentro.add(lblImporte);
		pCentro.add(pImporte);
		pImporte.add(new JPanel());
		pImporte.add(txtTitular);
		pImporte.add(new JPanel());
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
		pCentro.add(pAbajoUsuario);
		pCentro.add(pAbajoContraseña);
		pAbajoUsuario.add(lblUsuario);
		pAbajoContraseña.add(lblContrasena);
		pAbajoUsuario.add(pUsuario);
		pAbajoContraseña.add(pContra);
		pUsuario.add(new JPanel());
		pUsuario.add(txtUsuario);
		pUsuario.add(new JPanel());
		pContra.add(new JPanel());
		pContra.add(pasContrasena);
		pContra.add(new JPanel());
		
		btnVolver.addActionListener((e)->{
			
			new VentanaTabla();
			this.dispose();
			
		});
		
		
		setVisible(true);
	}

}
