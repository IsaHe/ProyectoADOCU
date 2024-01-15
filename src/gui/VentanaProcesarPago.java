package gui;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.logging.Logger;

/**
 * VentanaProcesarPago es una clase que extiende JFrame y representa la ventana de procesamiento de pago en la aplicación.
 * Esta ventana muestra una barra de progreso mientras se procesa el pago.
 * <p>
 * La clase contiene un Logger para registrar información y errores, una JProgressBar para mostrar el progreso del pago, y una referencia a la ventana actual.
 * <p>
 * El constructor de la clase inicializa la ventana y sus componentes.
 * Establece las propiedades de la ventana, añade componentes a los paneles, y crea un hilo para simular el procesamiento del pago.
 * <p>
 * El hilo creado en el constructor incrementa el valor de la JProgressBar en un bucle hasta llegar a 100, simbolizando el final del procesamiento del pago. Cuando el procesamiento del pago se completa, se muestra un mensaje de confirmación y se abre la ventana de pago.
 */
public class VentanaProcesarPago extends JFrame{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(VentanaProcesarPago.class.getName());
	private final JProgressBar pb;
    private final JFrame vActual;
	
	public VentanaProcesarPago() {
		super();
		vActual = this;
		
		setBounds(200, 200, 200, 100);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(null);
		
		//CREACION DE CONTENEDORES
		pb = new JProgressBar(1, 100);
        JLabel lblTitulo = new JLabel("Procesando el pago...");

        JPanel pCentral = new JPanel(new GridLayout(2, 1));
		pCentral.setAlignmentY(CENTER_ALIGNMENT);
		pCentral.add(lblTitulo);
        JPanel panel = new JPanel();
		panel.add(pb);
		pCentral.add(panel);
		getContentPane().add(pCentral);
		
		Thread t = new Thread(() -> {
            for(int i=0;i<100;i++) {
				int finalI = i;
				SwingUtilities.invokeLater(() -> pb.setValue(finalI));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    logger.warning("Error al dormir el hilo");
                    logger.warning(e.getMessage());
                }
            }
            JOptionPane.showMessageDialog(null, "Pago recibido correctamente");
            new VentanaPago();
            vActual.dispose();
        });
		t.start();
		
		setVisible(true);
	}
}
