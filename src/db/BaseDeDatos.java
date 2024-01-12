package db;

import domain.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Clase BaseDeDatos que se encarga de la gestión de los usuarios y las valoraciones en la base de datos.
 * Esta clase contiene métodos para iniciar la base de datos, obtener y cargar usuarios y valoraciones,
 * y realizar operaciones como comprobar, borrar y obtener usuarios.
 */
public class BaseDeDatos {

	private static List<Usuario> usuarios;
	private static List<Integer> valoraciones;
	static final Logger logger = Logger.getLogger(BaseDeDatos.class.getName());

	/**
	 * Comprueba si un usuario existe en la lista de usuarios.
	 * @param usuario El usuario a comprobar.
	 * @return true si el usuario existe, false en caso contrario.
	 */
	public static boolean comprobarUsuario(Usuario usuario) {
		return usuarios.contains(usuario);
	}

	/**
	 * Obtiene la lista de usuarios.
	 * @return La lista de usuarios.
	 */
	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * Obtiene la lista de usuarios SIN el admin incluido.
	 * @return La lista de usuarios.
	 */
	public static List<Usuario> getUsuariosSinAdmin() {
		List<Usuario> usuariosSinAdmin = usuarios;
		usuariosSinAdmin.removeIf(u -> u.getUsuario().equals("Admin"));
		return usuariosSinAdmin;
	}

	/**
	 * Obtiene la lista de valoraciones.
	 * @return La lista de valoraciones.
	 */
	public static List<Integer> getValoraciones() {
		return valoraciones;
	}

	/**
	 * Inicia la conexión con la base de datos.
	 * @param nomBaseDatos El nombre de la base de datos.
	 * @return La conexión con la base de datos.
	 */
	public static Connection iniciarBaseDeDatos (String nomBaseDatos) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nomBaseDatos);
		} catch (ClassNotFoundException | SQLException e) {
			logger.warning("No se ha podido establecer conexion con la base de datos");
		}
		
		return con;
	}

	/**
	 * Obtiene los usuarios de la base de datos y los carga en la lista de usuarios.
	 * @param con La conexión con la base de datos.
	 */
	public static void obtenerUsuariosDeBaseDeDatos (Connection con) {
		
		usuarios = new ArrayList<>();
		
		try {
			PreparedStatement prepSt = con.prepareStatement("SELECT * FROM usuarios");
			ResultSet rs = prepSt.executeQuery();
			
			while (rs.next()) {
				String nom = rs.getString("nombre");
				String apell = rs.getString("apellido");
				int edad = rs.getInt("edad");
				String usuario = rs.getString("usuario");
				String cont = rs.getString("contrasenia");
				Usuario u = new Usuario(nom, apell, edad, usuario, cont, null);
				if (!usuarios.contains(u)) {
					usuarios.add(u);
				}
			}
			
			rs.close();
			prepSt.close();
			con.close();
			
			
		} catch (SQLException e) {
			logger.warning("Error al obtener los usuarios de la Base De Datos");
		}
	}

	/**
	 * Carga los usuarios de la lista en la base de datos.
	 * @param con La conexión con la base de datos.
	 */
	public static void cargarUsuariosEnBaseDeDatos (Connection con) {
		
		try {
			PreparedStatement prpSt = con.prepareStatement("DELETE FROM usuarios");
			
			prpSt.executeUpdate();
			prpSt.close();
			
		} catch (SQLException e1) {
			logger.warning("No se ha podido eliminar la Base De Datos");
		}	
		
		for (Usuario u : usuarios) {
			String nombre = u.getNom();
			String apellido = u.getApellido();
			int edad = u.getEdad();
			String usuario = u.getUsuario();
			String cont = u.getContrasena();
			try {
				PreparedStatement prepSt = con.prepareStatement("INSERT INTO usuarios (nombre, apellido, edad, usuario, contrasenia) VALUES (?,?,?,?,?)");
				
				prepSt.setString(1, nombre);
				prepSt.setString(2, apellido);
				prepSt.setInt(3, edad);
				prepSt.setString(4, usuario);
				prepSt.setString(5, cont);
				
				prepSt.executeUpdate();
				
				prepSt.close();
				
			} catch (SQLException e) {
				logger.warning("No se ha podido guardar el usuario en la Base De Datos");
			}
			
		}
		try {
			con.close();
		} catch (SQLException e) {
			logger.warning("Error al cerrar conexion en Base De Datos");
		}
	}

	/**
	 * Borra un usuario en la base de datos.
	 * @param conn La conexión con la base de datos.
	 * @param usuario El nombre de usuario a borrar.
	 */
	public static void borrarUsuarioEnBD(Connection conn, String usuario) {
		try {
			PreparedStatement prepSt = conn.prepareStatement("DELETE FROM usuarios WHERE usuario = ?");
			prepSt.setString(1, usuario);
			prepSt.executeUpdate();
			prepSt.close();
			conn.close();
			usuarios.removeIf(u -> u.getUsuario().equals(usuario));
		} catch (SQLException e) {
			logger.warning("No se ha podido borrar el usuario en la Base De Datos");
			JOptionPane.showMessageDialog(null, "No se ha podido borrar el usuario en la Base De Datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Inicia la conexión con la base de datos de valoraciones.
	 * @param nomBD El nombre de la base de datos de valoraciones.
	 * @return La conexión con la base de datos de valoraciones.
	 */
	public static Connection iniciarBaseDeDatosValoraciones (String nomBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nomBD);
		} catch (ClassNotFoundException | SQLException e) {
			logger.warning("No se ha podido establecer conexion con la base de datos");
		}
		
		return con;
	}

	/**
	 * Carga las valoraciones en la base de datos de valoraciones.
	 * @param con La conexión con la base de datos de valoraciones.
	 */
	public static void cargarValoracionEnBaseDeDatos (Connection con) {
		try {
			PreparedStatement prepSt = con.prepareStatement("DELETE FROM valoraciones");
			prepSt.executeUpdate();
			prepSt.close();
			
			for (Integer val : valoraciones) {
				PreparedStatement prepStt = con.prepareStatement("INSERT INTO valoraciones (Valoracion) VALUES (?)");
				prepStt.setInt(1, val);
				prepStt.executeUpdate();
				prepStt.close();
			}
			
			con.close();
		} catch (SQLException e) {
			logger.warning("Error al cerrar la conexion con la Base De Datos");
		}
	}

	/**
	 * Obtiene las valoraciones de la base de datos de valoraciones y las carga en la lista de valoraciones.
	 * @param con La conexión con la base de datos de valoraciones.
	 */
	public static void obtenerValoracionesDeBaseDeDatos(Connection con){
		
		valoraciones = new ArrayList<>();
		
		try {
			PreparedStatement prepSt = con.prepareStatement("SELECT * FROM valoraciones");
			ResultSet rs = prepSt.executeQuery();
			while (rs.next()) {
				Integer valor = rs.getInt("Valoracion");
				valoraciones.add(valor);
			}
			rs.close();
			prepSt.close();
			con.close();
		} catch (SQLException e) {
			logger.warning("Error al cerrar la conexion con la Base De Datos");
		}
	}
		
}
