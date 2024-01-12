
package gui;

import domain.Actividad;
import domain.Usuario;
import io.GestorFicheros;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class VentanaPago extends JFrame{

	@Serial
	private static final long serialVersionUID = 1L;

	private final Logger logger = Logger.getLogger(VentanaPago.class.getName());
	private final DefaultListModel<Actividad> modeloPendiente;
    private final DefaultListModel<Actividad> modeloPagada;
	private final JList<Actividad> listaPendiente;
    private final JList<Actividad> listaPagada;
	private final JPanel pCentro;
    private final JPanel pSur;
    private final JPanel pNorte;
    private final JPanel pCentroArriba;
    private final JPanel pCentroAbajo;
    private final JPanel pUsuario;
    private final JPanel pContrasena;
    private final JPanel pEntreListas;
    private final JPanel pPagar;
    private final JPanel pTodosPagar;
    private final JPanel pPendiente;
    private final JPanel pTodosPendiente;
    private final JPanel pImporte;
	private final JLabel lblImporte;
    private final JLabel lblTitulo;
    private final JLabel lblUsuario;
    private final JLabel lblContrasena;
    private final JLabel lblImporteAPagar;
	private final JTextField txtUsuario;
	private final JButton btnPagar;
    private final JButton btnVolver;
    private final JButton btnPasarAPagada;
    private final JButton btnPasarAPendiente;
    private final JButton btnPasarTodosAPagar;
    private final JButton btnPasarTodosAPendiente;
	private JPasswordField pasContrasena;
	private float precio = 0;

	public VentanaPago(){
		super();
		
		
		
		//FUNCIONES VENTANA
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//CREACION CONTENEDORES
		pCentro = new JPanel();
		pCentroArriba = new JPanel();
		pEntreListas = new JPanel();
		pPagar = new JPanel();
		pTodosPagar = new JPanel();
		pPendiente = new JPanel();
		pTodosPendiente = new JPanel();
		pImporte = new JPanel();
		pCentroAbajo = new JPanel();
		pUsuario = new JPanel();
		pContrasena = new JPanel();
		pSur = new JPanel();
		pNorte = new JPanel();
		
		//FUNCION CONTENEDORES
		pCentro.setLayout(new GridLayout(2, 1));
		pCentroArriba.setLayout(new GridLayout(1,3));
		pEntreListas.setLayout(new GridLayout(5,1));
		pImporte.setLayout(new GridLayout(1,2));
		pCentroAbajo.setLayout(new GridLayout(1,4));
		pUsuario.setLayout(new GridLayout(5, 1));
		pContrasena.setLayout(new GridLayout(5, 1));
		pSur.setLayout(new GridLayout(1, 2));
		
		//AÑADIR CONTENEDOR A VENTANA PRINCIPAL
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		//CREACION COMPONENTES
		lblTitulo = new JLabel("PAGOS");
		lblImporte = new JLabel("    - Importe a Pagar: ");
		lblImporteAPagar = new JLabel("0€");
		lblUsuario = new JLabel("    - Usuario: ");
		txtUsuario = new JTextField(20);
		lblContrasena = new JLabel("    - Contraseña: ");
		pasContrasena = new JPasswordField(20);
		pasContrasena = new JPasswordField();
		btnPagar = new JButton("PAGAR");
		btnVolver = new JButton("VOLVER");
		modeloPendiente = new DefaultListModel<>();
		listaPendiente = new JList<>(modeloPendiente);
		modeloPagada = new DefaultListModel<>();
		listaPagada = new JList<>(modeloPagada);
		btnPasarAPagada = new JButton(">");
		btnPasarAPendiente = new JButton("<");
		btnPasarTodosAPagar = new JButton(">>");
		btnPasarTodosAPendiente = new JButton("<<");
		
		//FUNCION COMPONENTES
		lblTitulo.setFont(new Font(Font.SERIF, Font.PLAIN, 60));
		lblImporte.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		lblImporteAPagar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		lblUsuario.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		lblContrasena.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		btnPagar.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		btnVolver.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		
		//AÑADIR COMPONENTES A CONTENEDORES
		pNorte.add(lblTitulo);
		pCentro.add(pCentroArriba);
		pCentro.add(pCentroAbajo);
		pCentroArriba.add(listaPendiente);
		pCentroArriba.add(pEntreListas);
		pEntreListas.add(pPagar);
		pPagar.add(btnPasarAPagada);
		pEntreListas.add(pTodosPagar);
		pTodosPagar.add(btnPasarTodosAPagar);
		pEntreListas.add(pPendiente);
		pPendiente.add(btnPasarAPendiente);
		pEntreListas.add(pTodosPendiente);
		pTodosPendiente.add(btnPasarTodosAPendiente);
		pEntreListas.add(pImporte);
		pImporte.add(lblImporte);
		pImporte.add(lblImporteAPagar);
		pCentroArriba.add(listaPagada);
		pCentroAbajo.add(lblUsuario);
		pCentroAbajo.add(pUsuario);
		pUsuario.add(new JPanel());
		pUsuario.add(new JPanel());
		pUsuario.add(txtUsuario);
		pUsuario.add(new JPanel());
		pUsuario.add(new JPanel());
		pCentroAbajo.add(lblContrasena);
		pCentroAbajo.add(pContrasena);
		pContrasena.add(new JPanel());
		pContrasena.add(new JPanel());
		pContrasena.add(pasContrasena);
		pContrasena.add(new JPanel());
		pContrasena.add(new JPanel());
		pSur.add(btnPagar);
		pSur.add(btnVolver);
		
		//LLENAR LA JLIST PENDIENTES
		for (Actividad a : VentanaInicioSesion.getUsuario().getlActividades()) {
			modeloPendiente.addElement(a);
		}
		
		//EVENTOS BOTONES
		btnVolver.addActionListener((e)->{

			logger.info("Se ha pulsado el botón Volver");
			new VentanaTabla();
			this.dispose();
			
		});
		
		btnPagar.addActionListener(e ->{

			logger.info("Se ha pulsado el botón Pagar");
			Usuario usuario = VentanaInicioSesion.getUsuario();
			if (txtUsuario.getText().equals(usuario.getUsuario()) && pasContrasena.getText().equals(usuario.getContrasena()) && !modeloPagada.isEmpty()) {
				lblImporteAPagar.setText("0");
				for (int i = modeloPagada.size()-1; i>=0; i--) {
					usuario.getlActividades().remove(i);
					GestorFicheros.cambiarEstadoDePagoEnActividadSemanal(modeloPagada.get(i));
				}
				GestorFicheros.cargarActividadesSemanalesEnFichero(Paths.get("src/io/ActividadesSemanales.txt"));
				
				GestorFicheros.cargarActividadesUsuarioEnFicheroBinario(usuario, Paths.get("src/io/ActividadesUsuario.dat"));
				modeloPagada.removeAllElements();
				new VentanaProcesarPago();
				this.dispose();
			}else if (modeloPagada.isEmpty()){
				JOptionPane.showMessageDialog(null, "Seleccione actividades a pagar");
			}else {
				JOptionPane.showMessageDialog(null, "Error al pagar, inserte el usuario y contrasenia correctos");
			}
			
		});
		
		btnPasarAPagada.addActionListener(e -> {

            Actividad act = listaPendiente.getSelectedValue();
            if (act != null) {
                precio = precio + act.getPrecio();
                modeloPagada.addElement(act);
                modeloPendiente.removeElement(act);
                String precioStr = Float.toString(precio);
                lblImporteAPagar.setText(precioStr);
            }else {
                JOptionPane.showMessageDialog(null, "Seleccione una actividad");
            }
        });
		
		btnPasarAPendiente.addActionListener(e -> {
            Actividad act = listaPagada.getSelectedValue();
            if (act != null) {
                precio = precio - act.getPrecio();
                modeloPendiente.addElement(act);
                modeloPagada.removeElement(act);
                String precioStr = Float.toString(precio);
                lblImporteAPagar.setText(precioStr);
            }else {
                JOptionPane.showMessageDialog(null, "Seleccione una actividad");
            }
        });
		
		btnPasarTodosAPagar.addActionListener(e -> {

            for(int i = 0; i<modeloPendiente.getSize(); i++) {
                modeloPagada.addElement(modeloPendiente.get(i));
                precio = precio + modeloPendiente.get(i).getPrecio();
            }
            String precioStr = Float.toString(precio);
            lblImporteAPagar.setText(precioStr);
            modeloPendiente.removeAllElements();

        });
		
		btnPasarTodosAPendiente.addActionListener(e -> {

            for (int i = 0; i<modeloPagada.getSize(); i++) {
                modeloPendiente.addElement(modeloPagada.get(i));
            }
            precio = 0;
            String precioStr = Float.toString(precio);
            lblImporteAPagar.setText(precioStr);
            modeloPagada.removeAllElements();
        });
		
		setVisible(true);
	}

}
