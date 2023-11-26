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
						if (((Actividad)value).getNombre().equals("Gimnasia")) {
							label.setIcon(new ImageIcon("src/images/Gimnasia.png"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Airsoft")) {
							label.setIcon(new ImageIcon("src/images/Airsoft.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Ajedrez")) {
							label.setIcon(new ImageIcon("src/images/Ajedrez.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Alpinismo")) {
							label.setIcon(new ImageIcon("src/images/Alpinismo.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Atletismo")) {
							label.setIcon(new ImageIcon("src/images/Atletismo.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Baloncesto")) {
							label.setIcon(new ImageIcon("src/images/Baloncesto.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Balonvolea")) {
							label.setIcon(new ImageIcon("src/images/balonvolea.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Barranquismo")) {
							label.setIcon(new ImageIcon("src/images/Barranquismo.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Billar")) {
							label.setIcon(new ImageIcon("src/images/Billar.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Boxeo")) {
							label.setIcon(new ImageIcon("src/images/Boxeo.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Buceo")) {
							label.setIcon(new ImageIcon("src/images/Buceo.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Ciclismo")) {
							label.setIcon(new ImageIcon("src/images/Ciclismo.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Escalada")) {
							label.setIcon(new ImageIcon("src/images/Escalada.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Espeleologia")) {
							label.setIcon(new ImageIcon("src/images/Espeleologia.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Esqui")) {
							label.setIcon(new ImageIcon("src/images/Esqui.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Golf")) {
							label.setIcon(new ImageIcon("src/images/Golf.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Judo")) {
							label.setIcon(new ImageIcon("src/images/Judo.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Lucha")) {
							label.setIcon(new ImageIcon("src/images/Lucha.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Musculacion")) {
							label.setIcon(new ImageIcon("src/images/Musculacion.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Natacion")) {
							label.setIcon(new ImageIcon("src/images/Natacion.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Padel")) {
							label.setIcon(new ImageIcon("src/images/Padel.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Paintball")) {
							label.setIcon(new ImageIcon("src/images/Paintball.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Paracaidismo")) {
							label.setIcon(new ImageIcon("src/images/Paracaidismo.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Parapente")) {
							label.setIcon(new ImageIcon("src/images/Parapente.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Paseo")) {
							label.setIcon(new ImageIcon("src/images/Paseo.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Patinaje")) {
							label.setIcon(new ImageIcon("src/images/Patinaje.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Pesca")) {
							label.setIcon(new ImageIcon("src/images/Pesca.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Pilates")) {
							label.setIcon(new ImageIcon("src/images/Pilates.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Puenting")) {
							label.setIcon(new ImageIcon("src/images/Puenting.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Running")) {
							label.setIcon(new ImageIcon("src/images/Running.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Senderismo")) {
							label.setIcon(new ImageIcon("src/images/Senderismo.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Snowboard")) {
							label.setIcon(new ImageIcon("src/images/Snowboard.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Taekwondo")) {
							label.setIcon(new ImageIcon("src/images/Taekwondo.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Tenis")) {
							label.setIcon(new ImageIcon("src/images/Tenis.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Triatlon")) {
							label.setIcon(new ImageIcon("src/images/Triatlon.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Vela")) {
							label.setIcon(new ImageIcon("src/images/Vela.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Voleibol")) {
							label.setIcon(new ImageIcon("src/images/Voleibol.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Yoga")) {
							label.setIcon(new ImageIcon("src/images/Yoga.jpg"));
							label.setText("");
						}else if (((Actividad)value).getNombre().equals("Zumba")) {
							label.setIcon(new ImageIcon("src/images/Zumba.jpg"));
							label.setText("");
						}
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