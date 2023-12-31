package gui;

import domain.Actividad;
import domain.Usuario;
import io.GestorFicheros;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class VentanaTabla extends JFrame{

	private final Logger logger = Logger.getLogger(VentanaTabla.class.getName());
	private JPanel pCentro, pSur;
	private JTable tabla;
	private JButton btnVolver, btnPagar;
	
	private JScrollPane scroll;
	private List<String> horas;

	private Usuario usuario = VentanaInicioSesion.getUsuario();
	
	private int fila = -1;
	private  int columna = -1;
	
	private int filaRender = -1;
	private int columnaRender = -1;
	
	public VentanaTabla () {
		
		//CLASE DE LA TABLA
		class TablaActividades extends AbstractTableModel{
			private List<String> horas;
			private Object [] fechas = {"HORAS",LocalDate.now(),LocalDate.now().plusDays(1),LocalDate.now().plusDays(2),LocalDate.now().plusDays(3),LocalDate.now().plusDays(4),LocalDate.now().plusDays(5)};
					
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
						ImageIcon imagen = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images//" + ((Actividad) value).getNombre() + ".jpg")));
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
		horas = new ArrayList<>();
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
		pSur = new JPanel();
		pCentro =  new JPanel();
		
		//CREACION COMPONENTES
		btnPagar = new JButton("PAGAR");
		btnVolver = new JButton("VOLVER");
		tabla = new JTable(new TablaActividades(horas));
		scroll = new JScrollPane(tabla);
		
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

	public JTable getTabla() {
		return tabla;
	}
}