package domain;

public class Usuario {
	
	private String nom;
	private String apellido;
	private int edad;
	private String usuario;
	private String contraseña;
	
	public Usuario() {
		super();
	}

	public Usuario(String nom, String apellido, int edad, String usuario, String contraseña) {
		super();
		this.nom = nom;
		this.apellido = apellido;
		this.edad = edad;
		this.usuario = usuario;
		this.contraseña = contraseña;
	}
	
	

	public Usuario(String usuario, String contraseña) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
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
		return this.getUsuario().equals(((Usuario) obj).getUsuario());
	}
	
}
