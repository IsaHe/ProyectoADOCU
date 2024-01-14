package gui;

import db.BaseDeDatos;
import domain.Actividad;
import domain.Usuario;
import io.GestorFicheros;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.io.Serial;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Objects;
import java.util.logging.Logger;
/**
 * VentanaAdmin es una clase que extiende JFrame y representa la ventana de administrador en la aplicación.
 * Esta ventana permite al administrador ver y gestionar usuarios, actividades y calificaciones.
 * <p>
 * La clase contiene varios componentes como paneles, botones, etiquetas, tablas, listas y paneles de desplazamiento.
 * También mantiene una conexión con la base de datos.
 * <p>
 * El constructor de la clase inicializa la ventana y sus componentes.
 * Establece el diseño de los paneles, añade componentes a los paneles y añade oyentes de acción a los botones y al árbol.
 * <p>
 * La clase también contiene varios métodos privados para obtener JTextArea, JProgressBar y JEditorPane con configuraciones específicas.
 */
public class VentanaAdmin extends JFrame{
	
	@Serial
    private static final long serialVersionUID = 1L;
	
	private final Logger logger = Logger.getLogger(VentanaAdmin.class.getName());
	final JFrame ventanaActual = this;
    private final JPanel pCentro;
    private final JPanel pCentroC;
    private JButton btnBorrarAct;
    private final JButton btnBorrarUsu;
    private JTable tablaUsu;
	private JList<Actividad> lAct;
	private DefaultListModel<Actividad> modeloListaAct;
	private JScrollPane scrollUsu, scrollAct;
	private Connection conn = BaseDeDatos.iniciarBaseDeDatos("src/db/usuarios.db");


    /**
     * Constructor para VentanaAdmin.
     * Inicializa la ventana y sus componentes.
     */
	public VentanaAdmin(){
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images//ADOCU.png"))).getImage());

        JPanel pSur = new JPanel();
		pCentro = new JPanel();
        JPanel pNorte = new JPanel();
        JPanel pOeste = new JPanel();
		pCentroC = new JPanel();
		
		
		pCentro.setLayout(new GridLayout(3, 3));
		pOeste.setLayout(new GridLayout(3, 0));
		
		
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pOeste, BorderLayout.WEST);

        JLabel lblTitulo = new JLabel("ADMINISTRADOR");
		lblTitulo.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		lblTitulo.setForeground(Color.BLACK);
        JLabel lblFoto = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images//ADOCU.png"))));

        JEditorPane etiqueta = getjEditorPane();


        JButton btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        JButton btnSalir = new JButton("SALIR");
		btnSalir.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
		btnBorrarUsu = new JButton("Borrar Usuario");

        DefaultMutableTreeNode nRaiz = new DefaultMutableTreeNode("Administrador");
        DefaultTreeModel modeloArbol = new DefaultTreeModel(nRaiz);
        JTree arbol = new JTree(nRaiz);
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
                                case 4 -> BaseDeDatos.getUsuariosSinAdmin().get(rowIndex).getContrasena();
                                default -> null;
                            };
						}

						public void eliminarUsuario(int rowIndex) {
                            conn = BaseDeDatos.iniciarBaseDeDatos("src/db/usuarios.db");
                            Usuario usuario = BaseDeDatos.getUsuariosSinAdmin().get(rowIndex);
                            try {
                                GestorFicheros.obtenerActividadesSemanalesDeFichero(Paths.get("src/io/ActividadesSemanales.txt"));
                                Actividad[][] actividad = GestorFicheros.getActividadesSemanales();
                                for (int i = 0; i < 6; i++) {
                                    for (int j = 0; j < 10; j++) {
                                        if (actividad[i][j] != null && actividad[i][j].getUsuario().equals(usuario.getUsuario())) {
                                            GestorFicheros.eliminarYGuardarActividadesUsuarioDeMapa(usuario.getUsuario(), actividad[i][j]);
                                        }
                                    }
                                }
                                BaseDeDatos.borrarUsuarioEnBD(conn, usuario.getUsuario());
                                fireTableRowsDeleted(rowIndex, rowIndex);
                                fireTableDataChanged();
                            } catch (Exception e) {
                                logger.severe("Error al borrar el usuario " + usuario.getUsuario());
                                JOptionPane.showMessageDialog(null, "Error al borrar el usuario " + usuario.getUsuario(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
						}
					}

					CustomUserTableModel modeloTablaUsu = new CustomUserTableModel();
                    tablaUsu = new JTable(modeloTablaUsu);
                    scrollUsu = new JScrollPane(tablaUsu);

                    btnBorrarUsu.addActionListener(e1 -> {
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
                    JTextArea txtAct = getjTextArea();
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
                        JLabel label = new JLabel(value.toStringAdmin());
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
                            GestorFicheros.eliminarYGuardarActividadesUsuarioDeMapa(a.getUsuario(), a);
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
                    JProgressBar pb = getjProgressBar();

                    //TextArea De Explicación
                    JTextArea txtArea = getTextArea();

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

    /**
     * Método para obtener un JTextArea con texto de explicación.
     * @return Un JTextArea con texto de explicación.
     */
    private static JTextArea getTextArea() {
        JTextArea txtArea = new JTextArea("""
                *EN ESTA VENTANA ESTA DISPONIBLE LA MEDIA DE LAS VALORACIONES DE LOS CLIENTES*

                - Si la nota media esta entre [0 - 5) --> COLOR ROJO

                - Si la nota media esta entre [5 - 8) --> COLOR NARANJA

                - Si la nota media esta entre [8 - 10] --> COLOR VERDE""");

        txtArea.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
        txtArea.setEditable(false);
        return txtArea;
    }

    /**
     * Método para obtener un JProgressBar con la valoración media.
     * @return Un JProgressBar con la valoración media.
     */
    private static JProgressBar getjProgressBar() {
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
        return pb;
    }

    /**
     * Método para obtener un JTextArea con texto de explicación.
     * @return Un JTextArea con texto de explicación.
     */
    private static JTextArea getjTextArea() {
        JTextArea txtAct = new JTextArea("""
                - Si la actividad está en Rojo: NO ESTA PAGADA.

                - Si la actividad está en Verde: SI ESTA PAGADA.

                - Al seleccionar una actividad el fondo se pone Blanco.

                - Para borrar una actividad primero se debe seleccionar una.

                - El usuario que aparece es responsable de pagar las actividades.""");
        txtAct.setEditable(false);
        txtAct.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        return txtAct;
    }

    /**
     * Método para obtener un JEditorPane con texto de explicación.
     * @return Un JEditorPane con texto de explicación.
     */
    private static JEditorPane getjEditorPane() {
        String textoExplicacionValoracionHTML =
            "<h1>¡Bienvendido a la ventana de administrador!</h1>" +
            "<p>Esta ventana alverga un monton de interacciones como administrador, como por ejemplo:</p>" +
            "<p>Ver los diferentes usuarios en una lista, ver las actividades de cada usuario y los pagos realizados por el usuario.</p>";
        JEditorPane etiqueta = new JEditorPane("text/html", textoExplicacionValoracionHTML);
        etiqueta.setEditable(false);
        etiqueta.setOpaque(false);
        return etiqueta;
    }

}