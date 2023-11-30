package db;

import domain.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class BaseDeDatos {

	private static List<Usuario> usuarios;
	static Logger logger = Logger.getLogger(BaseDeDatos.class.getName());
	

	public static boolean comprobarUsuario(Usuario usuario) {
		return usuarios.contains(usuario);
	}

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}
	
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
			String cont = u.getContraseña();
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
	
	public static void borrarUsuarioEnBD(Connection conn, String usuario) {
		 try {     
				PreparedStatement prepSt = conn.prepareStatement("DELETE FROM usuarios WHERE usuario = ?");
				prepSt.setString(1, usuario);
				prepSt.executeUpdate();
	            prepSt.close();
		 }catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error al eliminar fila de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
	     }
		 try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	}
}
