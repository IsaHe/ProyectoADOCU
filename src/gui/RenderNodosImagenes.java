package gui;

import java.awt.Component;
import java.awt.Image;
import java.io.Serial;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class RenderNodosImagenes extends DefaultTreeCellRenderer{
	 /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	private final ImageIcon iconoNodo;

    public RenderNodosImagenes() {
        // Cargar la imagen (ajusta la ruta según la ubicación de tu imagen)
        String imageURL = "src/resources/images/admin.png";
        iconoNodo = new ImageIcon(imageURL);
        // Escalar la imagen para que se ajuste al icono del nodo
        iconoNodo.setImage(iconoNodo.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
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
