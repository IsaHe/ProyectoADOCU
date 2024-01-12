package domain;

import java.util.ArrayList;

/**
 * Clase Usuario que representa un usuario de la aplicación.
 * Esta clase contiene información sobre el nombre, el apellido, la edad, el usuario y la contraseña del usuario.
 */
public class Usuario {
	
	private String nom;
	private String apellido;
	private int edad;
	private String usuario;
	private String contrasena;
	private ArrayList<Actividad> lActividades = new ArrayList<>();

	/**
	 * Constructor por defecto de la clase Usuario.
	 */
	public Usuario() {
		super();
	}

	/**
	 * Constructor de la clase Usuario.
	 * @param nom El nombre del usuario.
	 * @param apellido El apellido del usuario.
	 * @param edad La edad del usuario.
	 * @param usuario El usuario del usuario.
	 * @param contrasena La contraseña del usuario.
	 */
	public Usuario(String nom, String apellido, int edad, String usuario, String contrasena, ArrayList<Actividad> lActividades) {
		super();
		this.nom = nom;
		this.apellido = apellido;
		this.edad = edad;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.lActividades = lActividades;
	}

	/**
	 * Constructor de la clase Usuario con solo usuario y contraseña.
	 * @param usuario El usuario del usuario.
	 * @param contrasena La contraseña del usuario.
	 */
	public Usuario(String usuario, String contrasena) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

	/**
	 * Método para obtener la lista de actividades del usuario.
	 * @return La lista de actividades del usuario.
	 */
	public ArrayList<Actividad> getlActividades() {
		return lActividades;
	}

	/**
	 * Método para establecer la lista de actividades del usuario.
	 * @param lActividades La lista de actividades del usuario.
	 */
	public void setlActividades(ArrayList<Actividad> lActividades) {
		this.lActividades = lActividades;
	}

	/**
	 * Método para obtener el nombre del usuario.
	 * @return El nombre del usuario.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Método para establecer el nombre del usuario.
	 * @param nom El nombre del usuario.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Método para obtener el apellido del usuario.
	 * @return El apellido del usuario.
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Método para establecer el apellido del usuario.
	 * @param apellido El apellido del usuario.
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Método para obtener la edad del usuario.
	 * @return La edad del usuario.
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Método para establecer la edad del usuario.
	 * @param edad La edad del usuario.
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Método para obtener el usuario del usuario.
	 * @return El usuario del usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Método para establecer el usuario del usuario.
	 * @param usuario El usuario del usuario.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método para obtener la contraseña del usuario.
	 * @return La contraseña del usuario.
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Método para establecer la contraseña del usuario.
	 * @param contrasena La contraseña del usuario.
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Método para eliminar una actividad de la lista de actividades del usuario.
	 * @param actividad La actividad a eliminar.
	 */
	public void eliminarActividadDeLista(Actividad actividad) {
		lActividades.remove(actividad);
	}

	/**
	 * Método para obtener la representación en cadena del usuario.
	 * @return La representación en cadena del usuario.
	 */
	@Override
	public String toString() {
		return nom + ";" + apellido + ";" + edad + ";" + usuario + ";" + contrasena;
	}

	/**
	 * Método para comparar la igualdad de este usuario con otro objeto.
	 * @param obj El objeto a comparar con este usuario.
	 * @return true si el objeto es igual a este usuario, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Usuario)) {
			return false;
		}
		if (((Usuario) obj).getUsuario() == null){
			return true;
		}
		return this.getUsuario().equals(((Usuario) obj).getUsuario());
	}
	
}
