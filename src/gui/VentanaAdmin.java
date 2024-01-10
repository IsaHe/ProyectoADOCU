package gui;

import db.BaseDeDatos;
import domain.Actividad;
import io.GestorFicheros;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Objects;
import java.util.logging.Logger;

public class VentanaAdmin extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = Logger.getLogger(VentanaAdmin.class.getName());
	JFrame ventanaActual = this;
	private JPanel pSur,pCentro, pCentroC, pNorte, pOeste;
	private JLabel lblTitulo, lblFoto;
	private JButton btnVolver, btnSalir, btnBorrarAct, btnBorrarUsu;
	private JTree arbol;
	private DefaultMutableTreeNode nRaiz;
	private DefaultTreeModel modeloArbol;
	private JTable tablaUsu;
	private JList<Actividad> lAct;
	private DefaultListModel<Actividad> modeloListaAct;
	private JScrollPane scrollUsu, scrollAct;
	private Connection conn = BaseDeDatos.iniciarBaseDeDatos("src/db/usuarios.db");
	

	public VentanaAdmin(){
		
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images//ADOCU.png"))).getImage());
		
		pSur = new JPanel();
		pCentro = new JPanel();
		pNorte = new JPanel();
		pOeste = new JPanel();
		pCentroC = new JPanel();
		
		
		pCentro.setLayout(new GridLayout(3, 3));
		pOeste.setLayout(new GridLayout(3, 0));
		
		
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pOeste, BorderLayout.WEST);
		
		lblTitulo = new JLabel("ADMINISTRADOR");
		lblTitulo.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		lblTitulo.setForeground(Color.BLACK);
		lblFoto = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images//ADOCU.png"))));
		
		String textoExplicacionValoracionHTML =
                "<h1>¡Bienvendido a la ventana de administrador!</h1>" +
                "<p>Esta ventana alverga un monton de interacciones como administrador, como por ejemplo:</p>" +
                "<p>Ver los diferentes usuarios en una lista, ver las actividades de cada usuario y los pagos realizados por el usuario.</p>" ;
		JEditorPane etiqueta = new JEditorPane("text/html", textoExplicacionValoracionHTML);
		etiqueta.setEditable(false);
        etiqueta.setOpaque(false);
        
        
        
		btnSalir = new JButton("SALIR");
		btnVolver = new JButton("VOLVER");
		btnBorrarUsu = new JButton("Borrar Usuario");
		
		nRaiz = new DefaultMutableTreeNode("Administrador");
		modeloArbol = new DefaultTreeModel(nRaiz);
		arbol = new JTree(nRaiz);
		modeloArbol.insertNodeInto(new DefaultMutableTreeNode("Ver Usuarios"), nRaiz, 0);
		modeloArbol.insertNodeInto(new DefaultMutableTreeNode("Ver Actividades"), nRaiz, 1);
		modeloArbol.insertNodeInto(new DefaultMutableTreeNode("Ver Valoraciones"), nRaiz, 2);
		
		
		pOeste.add(arbol);
		pOeste.add(etiqueta);
		pOeste.add(lblFoto);
		pNorte.add(lblTitulo);
		pSur.add(btnSalir);
		pSur.add(btnVolver);
		
		arbol.addTreeSelectionListener(e -> {
            TreePath tp = e.getPath();
            String ultimo = tp.getLastPathComponent().toString();

            switch (ultimo) {
                case "Ver Usuarios" -> {

                    pCentro.removeAll();
                    pCentroC.removeAll();

					class CustomUserTableModel extends AbstractTableModel {

						private final String[] columnNames = {"Nombre", "Apellido", "Edad", "Usuario", "Contraseña"};

						@Override
						public int getRowCount() {
							return BaseDeDatos.getUsuariosSinAdmin().size();
						}

						@Override
						public int getColumnCount() {
							return columnNames.length;
						}

						@Override
						public String getColumnName(int column) {
							return columnNames[column];
						}

						@Override
						public Object getValueAt(int rowIndex, int columnIndex) {
                            return switch (columnIndex) {
                                case 0 -> BaseDeDatos.getUsuariosSinAdmin().get(rowIndex).getNom();
                                case 1 -> BaseDeDatos.getUsuariosSinAdmin().get(rowIndex).getApellido();
                                case 2 -> BaseDeDatos.getUsuariosSinAdmin().get(rowIndex).getEdad();
                                case 3 -> BaseDeDatos.getUsuariosSinAdmin().get(rowIndex).getUsuario();
                                case 4 -> BaseDeDatos.getUsuariosSinAdmin().get(rowIndex).getContraseña();
                                default -> null;
                            };
						}

						public void eliminarUsuario(int rowIndex) {
							BaseDeDatos.borrarUsuarioEnBD(conn, BaseDeDatos.getUsuariosSinAdmin().get(rowIndex).getUsuario());
							fireTableRowsDeleted(rowIndex, rowIndex);
						}
					}

					CustomUserTableModel modeloTablaUsu = new CustomUserTableModel();
                    tablaUsu = new JTable(modeloTablaUsu);
                    scrollUsu = new JScrollPane(tablaUsu);

                    btnBorrarUsu.addActionListener(e1 -> {
                        conn = BaseDeDatos.iniciarBaseDeDatos("src/db/usuarios.db");
                        int fila = tablaUsu.getSelectedRow();

                        if (fila != -1) {
                            modeloTablaUsu.eliminarUsuario(fila);
                        } else {
                            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    });

                    pCentro.add(new JPanel());
                    pCentro.add(scrollUsu);
                    pCentro.add(pCentroC);
                    pCentroC.add(btnBorrarUsu);
                }
                case "Ver Actividades" -> {

                    pCentro.removeAll();
                    pCentroC.removeAll();

                    JPanel panelTxt = new JPanel();
                    JPanel panelBtn = new JPanel();
                    JTextArea txtAct = new JTextArea("- Si la actividad está en Rojo: NO ESTA PAGADA." + "\n"
                            + "\n" + "- Si la actividad está en Verde: SI ESTA PAGADA." + "\n"
                            + "\n" + "- Al seleccionar una actividad el fondo se pone Blanco." + "\n"
                            + "\n" + "- Para borrar una actividad primero se debe seleccionar una." + "\n"
                            + "\n" + "- El usuario que aparece es responsable de pagar las actividades.");

                    txtAct.setEditable(false);
                    txtAct.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
                    panelTxt.add(txtAct, BorderLayout.CENTER);

                    btnBorrarAct = new JButton("Borrar Actividad");
                    panelBtn.add(btnBorrarAct);
                    GestorFicheros.obtenerActividadesSemanalesDeFichero(Paths.get("src/io/ActividadesSemanales.txt"));
                    Actividad[][] actividad = GestorFicheros.getActividadesSemanales();

                    modeloListaAct = new DefaultListModel<>();
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (actividad[i][j] != null) {
                                modeloListaAct.addElement(actividad[i][j]);
                            }

                        }

                    }
                    lAct = new JList<>(modeloListaAct);
                    scrollAct = new JScrollPane(lAct);

                    lAct.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
                        JLabel label = new JLabel(((Actividad) value).toStringAdmin());
                        label.setOpaque(true);
                        label.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));

                        if (value.isPagada()) {
                            label.setBackground(Color.green);
                        } else {
                            label.setBackground(Color.red);
                        }

                        if (isSelected) {
                            label.setBackground(list.getBackground());
                        }

                        return label;
                    });

                    btnBorrarAct.addActionListener(e12 -> {

                        Actividad a = lAct.getSelectedValue();
                        if (a == null) {
                            JOptionPane.showMessageDialog(null, "Debes selecionar una actividad antes de borrarla");
                        } else {
                            GestorFicheros.eliminarActividadDeActividadSemanal(a);
                            GestorFicheros.cargarActividadesSemanalesEnFichero(Paths.get("src/io/ActividadesSemanales.txt"));
                            GestorFicheros.eliminarActividadUsuarioDeMapa(a.getUsuario(), a);
                            GestorFicheros.cargarActividadesUsuarioEnFicheroBinario2(a.getUsuario(), Paths.get("src/io/ActividadesUsuario.dat"));
                            modeloListaAct.removeElement(a);
                        }
                    });

                    pCentro.add(panelTxt);
                    pCentro.add(scrollAct);
                    pCentro.add(panelBtn);
                }
                case "Ver Valoraciones" -> {

                    pCentro.removeAll();
                    pCentroC.removeAll();

                    //Progress Bar Valoracion
                    JProgressBar pb = new JProgressBar(0, 10);
                    float valorTotal = 0;

                    for (Integer val : BaseDeDatos.getValoraciones()) {
                        valorTotal = valorTotal + val;
                    }

                    valorTotal = valorTotal / BaseDeDatos.getValoraciones().size();
                    String valorStr = String.valueOf(valorTotal);
                    String primerNum = String.valueOf(valorStr.charAt(0));
                    pb.setValue(Integer.parseInt(primerNum));
                    pb.setString(valorStr);
                    pb.setStringPainted(true);
                    pb.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
                    pb.setBackground(Color.BLACK);

                    if (valorTotal < 5) {
                        pb.setForeground(Color.RED);
                    } else if (valorTotal < 8) {
                        pb.setForeground(Color.ORANGE);
                    } else {
                        pb.setForeground(Color.GREEN);
                    }

                    //TextArea De Explicación
                    JTextArea txtArea = new JTextArea("*EN ESTA VENTANA ESTA DISPONIBLE LA MEDIA DE LAS VALORACIONES DE LOS CLIENTES*" + "\n"
                            + "\n" + "- Si la nota media esta entre [0 - 5) --> COLOR ROJO" + "\n"
                            + "\n" + "- Si la nota media esta entre [5 - 8) --> COLOR NARANJA" + "\n"
                            + "\n" + "- Si la nota media esta entre [8 - 10] --> COLOR VERDE");

                    txtArea.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
                    txtArea.setEditable(false);

                    pCentro.add(txtArea);
                    pCentro.add(pb);
                    pCentro.add(new JPanel());
                }
            }

        });
		
		btnSalir.addActionListener(e -> {
            System.exit(0);
            logger.info("Se ha pulsado el boton para salir");
        });
		
		btnVolver.addActionListener(e -> {
            ventanaActual.dispose();
            new VentanaInicioSesion();
            logger.info("Se ha pulsado el boton para volver a la VentanaInicioSesion");

        });
		
		setVisible(true);
	}	
	
}