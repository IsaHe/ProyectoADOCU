package db;

import domain.Actividad;
import domain.Usuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

public class BaseDeDatos {

	private static List<Usuario> usuarios = new ArrayList<>();
	static Logger logger = Logger.getLogger(BaseDeDatos.class.getName());

	public static void cargarUsuariosEnFichero(Path ruta) {
		
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(ruta.toFile()));
			for (Usuario u : usuarios) {
				pw.write(u.toString() + "\n");
			}
			pw.close();
		} catch (FileNotFoundException e) {
			logger.warning("Error al cargar los usuarios al fichero");
		}
	}
	
	public static void obtenerUsuariosDeFichero (Path ruta) {


		try {
			Scanner sc = new Scanner(new FileInputStream(ruta.toFile()));
			while (sc.hasNext()) {
				String linea = sc.next();
				String[] partes = linea.split(";");
				Usuario usuario = new Usuario(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], partes[4],null);
				if (!usuarios.contains(usuario)) {
					usuarios.add(usuario);	
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			logger.warning("Error al obtener los usuarios del fichero");
		}
			
	}

	public static boolean comprobarUsuario(Usuario usuario) {
		return usuarios.contains(usuario);
	}

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}
}
