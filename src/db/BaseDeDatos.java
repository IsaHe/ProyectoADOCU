package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.Usuario;

public class BaseDeDatos {

	private static List<Usuario> usuarios = new ArrayList<>();
	
	public static void cargarUsuariosEnFichero(Path ruta) {
		
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(ruta.toFile()));
			for (Usuario u : usuarios) {
				pw.write(u.toString() + "\n");
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
			
	}
	
	public static boolean comprobarUsuario(Usuario usuario) {
		return usuarios.contains(usuario);	
	}

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}
	
}
