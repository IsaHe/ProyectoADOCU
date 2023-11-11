package domain;

import java.util.ArrayList;

public class Usuario {
	
	private String nom;
	private String apellido;
	private int edad;
	private String usuario;
	private String contraseña;
	private ArrayList<Actividad> lActividades = new ArrayList<>();
	
	public Usuario() {
		super();
	}

	public Usuario(String nom, String apellido, int edad, String usuario, String contraseña, ArrayList<Actividad> lActividades) {
		super();
		this.nom = nom;
		this.apellido = apellido;
		this.edad = edad;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.lActividades = lActividades;
	}
	

	public Usuario(String usuario, String contraseña) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return nom + ";" + apellido + ";" + edad + ";" + usuario + ";" + contraseña;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Usuario)) {
			return false;
		}
		return this.getUsuario().equals(((Usuario) obj).getUsuario());
	}
	
}
