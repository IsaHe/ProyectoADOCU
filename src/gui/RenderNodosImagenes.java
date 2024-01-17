package gui;

import java.awt.Component;
import java.awt.Image;
import java.io.Serial;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class RenderNodosImagenes extends DefaultTreeCellRenderer{
	 /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	private ImageIcon iconoNodo;

    public RenderNodosImagenes() {
        // Cargar la imagen (ajusta la ruta según la ubicación de tu imagen)
        URL imageURL = getClass().getResource("/resources/images//admin.png");
        if (imageURL != null) {
            iconoNodo = new ImageIcon(imageURL);
            // Escalar la imagen para que se ajuste al icono del nodo
            iconoNodo.setImage(iconoNodo.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
        }
    }

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean selected,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        // Asignar la imagen al nodo
        if (iconoNodo != null) {
            setIcon(iconoNodo);
        }

        return this;
    }


}
