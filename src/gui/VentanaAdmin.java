package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import db.BaseDeDatos;
import domain.Actividad;
import domain.Usuario;
import io.GestorFicheros;

public class VentanaAdmin extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = Logger.getLogger(VentanaAdmin.class.getName());
	JFrame ventanaActual = this;
	private JPanel pSur,pCentro, pCentroC, pNorte, pOeste;
	private JLabel lblTitulo, lblFoto;
	private JButton btnVolver, btnSalir;
	private JTree arbol;
	private DefaultMutableTreeNode nRaiz;
	private DefaultTreeModel modeloArbol;
	private JList<Usuario> lUsu;
	private JList<Actividad> lAct;
	private JList<Integer> lPag;
	private DefaultListModel<Usuario> modeloListaUsu;
	private DefaultListModel<Actividad> modeloListaAct;
	private DefaultListModel<Integer> modeloListaPag;
	private ScrollPane scrollUsu, scrollAct, scrollPag;
	

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
		lblFoto = new JLabel(new ImageIcon(getClass().getResource("/resources/images//ADOCU.png")));
		
		String textoExplicacionValoracionHTML =
                "<h1>¡Bienvendido a la ventana de administrador!</h1>" +
                "<p>Esta ventana alverga un monton de interacciones como administrador, como por ejemplo:</p>" +
                "<p>Ver los diferentes usuarios en una lista, ver las actividades de cada usuario y los pagos realizados por el usuario.</p>" ;
		JEditorPane etiqueta = new JEditorPane("text/html", textoExplicacionValoracionHTML);
		etiqueta.setEditable(false);
        etiqueta.setOpaque(false);
        
        
        
		btnSalir = new JButton("SALIR");
		btnVolver = new JButton("VOLVER");
		
		nRaiz = new DefaultMutableTreeNode("Administrador");
		modeloArbol = new DefaultTreeModel(nRaiz);
		arbol = new JTree(nRaiz);
		modeloArbol.insertNodeInto(new DefaultMutableTreeNode("Ver Usuarios"), nRaiz, 0);
		modeloArbol.insertNodeInto(new DefaultMutableTreeNode("Ver Actividades"), nRaiz, 1);
		modeloArbol.insertNodeInto(new DefaultMutableTreeNode("Ver Pagos"), nRaiz, 2);
		
		
		pOeste.add(arbol);
		pOeste.add(etiqueta);
		pOeste.add(lblFoto);
		pNorte.add(lblTitulo);
		pSur.add(btnSalir);
		pSur.add(btnVolver);
		
		arbol.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath tp = e.getPath();
				String ultimo = tp.getLastPathComponent().toString();
				
				if (ultimo.equals("Ver Usuarios")){
					
					pCentro.removeAll();
					pCentroC.removeAll();
					
					modeloListaUsu = new DefaultListModel<Usuario>();
					modeloListaUsu.addAll(BaseDeDatos.getUsuarios());
					lUsu = new JList<Usuario>(modeloListaUsu);
					scrollUsu = new ScrollPane();
					scrollUsu.add(lUsu);
					
					pCentro.add(new JPanel());
					pCentro.add(scrollUsu);
					pCentro.add(new JPanel());
					
				}else if(ultimo.equals("Ver Actividades")) {
					
					pCentro.removeAll();
					pCentroC.removeAll();
					
					modeloListaAct = new DefaultListModel<Actividad>();
					lAct = new JList<Actividad>(modeloListaAct);
					scrollAct = new ScrollPane();
					scrollAct.add(lAct);
					
					pCentro.add(new JPanel());
					pCentro.add(pCentroC);
					pCentroC.add(scrollAct);
					pCentro.add(new JPanel());
					
				}else if(ultimo.equals("Ver Pagos")) {
					
					pCentro.removeAll();
					pCentroC.removeAll();
					
					modeloListaPag = new DefaultListModel<Integer>();
					lPag = new JList<Integer>(modeloListaPag);
					scrollPag = new ScrollPane();
					scrollPag.add(lPag);
					
					pCentro.add(new JPanel());
					pCentro.add(pCentroC);
					pCentroC.add(scrollPag);
					pCentro.add(new JPanel());
				}
				
			}
		});
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				logger.info("Se ha pulsado el boton para salir");
			}
		});
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new VentanaInicioSesion();
				logger.info("Se ha pulsado el boton para volver a la VentanaInicioSesion");
				
			}
		});
		
		setVisible(true);
	}	
	
}