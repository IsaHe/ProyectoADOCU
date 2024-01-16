package gui;

import domain.Actividad;
import io.GestorFicheros;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serial;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * VentanaTabla es una clase que extiende JFrame y representa la ventana de tabla en la aplicación.
 * Esta ventana muestra una tabla con las actividades programadas para la semana.
 * <p>
 * La clase contiene un Logger para registrar información y errores, una JTable para mostrar las actividades, y dos variables enteras para almacenar la fila y columna seleccionadas en la tabla.
 * <p>
 * El constructor de la clase inicializa la ventana y sus componentes.
 * Establece las propiedades de la ventana, añade componentes a los paneles, y crea un modelo de tabla personalizado para mostrar las actividades.
 * También añade oyentes de eventos al ratón para detectar cuando se selecciona una celda de la tabla y cuando se mueve el ratón sobre la tabla.
 * <p>
 * La clase también contiene una clase interna para el modelo de la tabla, que extiende AbstractTableModel.
 * Esta clase interna define los nombres de las columnas, el número de filas y columnas, el valor de cada celda, y si una celda es editable.
 * También redefine el método fireTableCellUpdated para repintar la tabla cuando se actualiza una celda.
 * <p>
 * La clase también contiene una clase interna para el renderizado de la tabla, que implementa TableCellRenderer.
 * Esta clase interna define cómo se debe mostrar cada celda de la tabla, dependiendo de si la celda contiene una actividad o no.
 */
public class VentanaTabla extends JFrame{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(VentanaTabla.class.getName());
    private final JTable tabla;
	private int fila = -1;
	private  int columna = -1;
	private int filaRender = -1;
	private int columnaRender = -1;

	public VentanaTabla () {
		
		//CLASE DE LA TABLA
		class TablaActividades extends AbstractTableModel{
			/**
			 * 
			 */
			@Serial
			private static final long serialVersionUID = 1L;
			private final List<String> horas;
			private final Object [] fechas = {"HORAS",LocalDate.now(),LocalDate.now().plusDays(1),LocalDate.now().plusDays(2),LocalDate.now().plusDays(3),LocalDate.now().plusDays(4),LocalDate.now().plusDays(5)};
					
			public TablaActividades(List<String> horas) {
				this.horas = horas;
			}
			
			//Nombres de columnas
			@Override
			public String getColumnName(int column) {
				return  fechas[column].toString();
			}
				
			//Numero de columna
			@Override
			public int getColumnCount() {
				return fechas.length;
			}
					
			//Numero de fila
			@Override
			public int getRowCount() {
				return horas.size();
			}

			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				GestorFicheros.setActividad((Actividad) aValue, columnIndex, rowIndex);
				GestorFicheros.cargarActividadesSemanalesEnFichero(Paths.get("src/io/ActividadesSemanales.txt"));
				fireTableCellUpdated(rowIndex, columnIndex);
			}

			//Valor de fila y columna
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				if (columnIndex != 0) {
					if (GestorFicheros.getActividadesSemanales()[columnIndex-1][rowIndex] != null) {
						return GestorFicheros.getActividadesSemanales()[columnIndex-1][rowIndex];
					}
					return "";
				}
				return horas.get(rowIndex);
			}

			//Es la celda editable
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;

			}

			@Override
			public void fireTableCellUpdated(int row, int column) {
				super.fireTableCellUpdated(row, column);
				tabla.repaint();
			}
		}


				
		//CLASE DE RENDER
		class RenderTabla implements TableCellRenderer{

			public RenderTabla() {
				super();
			}

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
					
				JLabel label = new JLabel();
				label.setText(value.toString());
				label.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
				label.setOpaque(true);
				
				
				if (value instanceof Actividad) {
					if (filaRender == row && columnaRender == column) {
						if (((Actividad)value).isPagada()) {
							label.setText(value.toString().toUpperCase() + ": (" + ((Actividad)value).getUsuario() + ")");
							label.setBackground(Color.GREEN);
						}else {
							label.setText(value.toString().toUpperCase()+ ": (" + ((Actividad)value).getUsuario() + ")");
							label.setBackground(Color.RED);
						}
					}else {
						ImageIcon imagen = new ImageIcon("src/resources/images/" + ((Actividad) value).getNombre() + ".jpg");
						ImageIcon imagenConDimensiones = new ImageIcon(imagen.getImage().getScaledInstance(180, 100, Image.SCALE_DEFAULT));
                        label.setIcon(imagenConDimensiones);
                        label.setText("");
					}
					
				}
				
				return label;
			}
		}
		
		//VENTANA
		
		//Horas de la tabla
        List<String> horas = new ArrayList<>();
		horas.add("8:10 - 9:00");
		horas.add("9:10 - 10:00");
		horas.add("10:10 - 11:00");
		horas.add("11:10 - 12:00");
		horas.add("12:10 - 13:00");
		horas.add("13:10 - 14:00");
		horas.add("17:10 - 18:00");
		horas.add("18:10 - 19:00");
		horas.add("19:10 - 20:00");
		horas.add("20:10 - 21:00");
		
		//FUNCIONES VENTANA
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		
		//CREACION CONTENEDORES
        JPanel pSur = new JPanel();
        JPanel pCentro = new JPanel();
		
		//CREACION COMPONENTES
        JButton btnPagar = new JButton("PAGAR");
        JButton btnVolver = new JButton("VOLVER");
		tabla = new JTable(new TablaActividades(horas));
        JScrollPane scroll = new JScrollPane(tabla);
		
		//FUNCIONES COMPONENTES
		btnPagar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		btnVolver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		tabla.setDefaultRenderer(Object.class, new RenderTabla() );
		tabla.setRowHeight(100);
		
		//AÑADIR COMPONENTES A LOS CONTENEDORES
		pCentro.add(scroll);
		pSur.add(btnPagar);
		pSur.add(btnVolver);
		
		//AÑADIR CONTENEDORES A LA VENTANA
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(scroll, BorderLayout.CENTER);
		
		setVisible(true);
		
		//EVENTO CLICK RATON
		tabla.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {

				Point p = e.getPoint();
				fila = tabla.rowAtPoint(p);
				columna = tabla.columnAtPoint(p);
				if (columna != 0 && tabla.getValueAt(fila, columna) == "") {
					new VentanaModificacionActividades(
							10, (String) tabla.getValueAt(fila, 0),
							LocalDate.parse(tabla.getColumnName(columna)), VentanaTabla.this);
				} 
				e.consume();
				logger.info("Se ha pulsado la celda en la fila: " + fila + " y columna: " + columna);
			}
		});
		
		tabla.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
			
				Point p = e.getPoint();
				filaRender = tabla.rowAtPoint(p);
				columnaRender = tabla.columnAtPoint(p);
				tabla.repaint();
			}
			
		}); 
		
		//EVENTO BOTONES
		btnPagar.addActionListener((e)->{

			logger.info("Se ha pulsado el botón Pagar");
			new VentanaPago();
			this.dispose();
			
		});
		
		btnVolver.addActionListener((e)->{

			logger.info("Se ha pulsado el botón Volver");
			new VentanaInicioSesion();
			this.dispose();
			
		});
	}

	/**
	 * getTabla es un método que devuelve la tabla de la ventana.
	 * @return la tabla de la ventana.
	 */
	public JTable getTabla() {
		return tabla;
	}
}