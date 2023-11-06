package db;

import domain.Actividad;
import domain.Usuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;

public class BaseDeDatos {

	private static List<Usuario> usuarios = new ArrayList<>();
	private static Map<String, Set<Actividad>> actividades = new HashMap<>();
	static Logger logger = Logger.getLogger(BaseDeDatos.class.getName());

	static Random r = new Random();
	
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
				Usuario usuario = new Usuario(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], partes[4]);
				if (!usuarios.contains(usuario)) {
					usuarios.add(usuario);	
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			logger.warning("Error al obtener los usuarios del fichero");
		}
			
	}

	public static void obtenerActividadesDeFichero (Path ruta) {

		try {
			Scanner sc = new Scanner(new FileInputStream(ruta.toFile()));
			while (sc.hasNext()) {
				Set<Actividad> listaActividades = new HashSet<>();
				String linea = sc.next();
				String[] partes = linea.split(";");
				String dia = partes[0];
				for (int i = 1; i < partes.length; i++) {
					String[] partesActividad = partes[i].split(",");
					Actividad actividad = new Actividad(partesActividad[0], Integer.parseInt(partesActividad[1]));
					listaActividades.add(actividad);
				}
				actividades.put(dia, listaActividades);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			logger.warning("Error al obtener las actividades del fichero");
		}

	}
	
	public static boolean comprobarUsuario(Usuario usuario) {
		return usuarios.contains(usuario);	
	}

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	public static Map<String, Set<Actividad>> getActividades() {
		return actividades;
	}
	
}
