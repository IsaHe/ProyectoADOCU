package domain;

import java.util.ArrayList;

public class Usuario {
	
	private String nom;
	private String apellido;
	private int edad;
	private String usuario;
	private String contrasena;
	private ArrayList<Actividad> lActividades = new ArrayList<>();
	
	public Usuario() {
		super();
	}

	public Usuario(String nom, String apellido, int edad, String usuario, String contrasena, ArrayList<Actividad> lActividades) {
		super();
		this.nom = nom;
		this.apellido = apellido;
		this.edad = edad;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.lActividades = lActividades;
	}
	

	public Usuario(String usuario, String contrasena) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

	public ArrayList<Actividad> getlActividades() {
		return lActividades;
	}

	public void setlActividades(ArrayList<Actividad> lActividades) {
		this.lActividades = lActividades;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void eliminarActividadDeLista(Actividad actividad) {
		lActividades.remove(actividad);
	}

	@Override
	public String toString() {
		return nom + ";" + apellido + ";" + edad + ";" + usuario + ";" + contrasena;
	}
	
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
