package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class VentanaTabla extends JFrame{

	private JFrame vActual, vAnterior;
	
	private JPanel pCentro, pSur;
	private JTable tabla;
	private JButton btnVolver, btnPagar;
	
	private JScrollPane scroll;
	private List<String> horas;
	
	private int fila;
	private  int columna;
	
	public VentanaTabla(JFrame vAnterior) {
		
		vActual = this;
		
		//CLASE DE LA TABLA
		class TablaActividades extends AbstractTableModel{
			private List<String> horas;
			private Object [] titulos = {"Horas", "Lunes", "Martes", "MIercoles", "Jueves", "Viernes"};
					
			public TablaActividades(List<String> horas) {
				this.horas = horas;
			}
			
			//Nombres de columnas
			@Override
			public String getColumnName(int column) {
				return (String) titulos[column];
			}
				
			//Numero de columna
			@Override
			public int getColumnCount() {
				return titulos.length;
			}
					
			//Numero de fila
			@Override
			public int getRowCount() {
				return horas.size();
			}
					
			//Valor de fila y columna
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				if (columnIndex == 0) {
					String hora = horas.get(rowIndex);
					return hora;
				}
				return "";
						
			}
					
			//Es la celda editable
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
					
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
				label.setOpaque(true);
				label.setText(value.toString());
				
				if (fila == row && columna == column) {
					label.setBackground(Color.BLACK);
				}else {
					label.setBackground(table.getBackground());
				}
				
				return label;
			}
		}
		
		
		//VENTANA
		horas = new ArrayList<>();
		horas.add("8:10 - 9:00");
		horas.add("9:10 - 10:00");
		horas.add("10:10 - 11:00");
		horas.add("11:10 - 12:00");
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		
		pSur = new JPanel();
		pCentro =  new JPanel();
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		btnPagar = new JButton("PAGAR");
		btnVolver = new JButton("VOLVER");
		
		tabla = new JTable(new TablaActividades(horas));
		scroll = new JScrollPane(tabla);
		
		tabla.setDefaultRenderer(Object.class, new RenderTabla() );
		
		pCentro.add(scroll);
		pSur.add(btnPagar);
		pSur.add(btnVolver);
		
		tabla.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
			
				Point p = e.getPoint();
				fila = tabla.rowAtPoint(p);
				columna = tabla.columnAtPoint(p);
				tabla.repaint();
				
			}
		
		});
		
		btnVolver.addActionListener((e)->{
			
			new VentanaInicioSesion();
			vActual.dispose();
			
		});
		
		setVisible(true);
	}
	
}