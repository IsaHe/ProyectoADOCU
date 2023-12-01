package domain;

import java.io.Serializable;

public class Actividad implements Serializable{
    private String nombre;
    private int numeroParticipantes;
    private float precio;
    private boolean pagada;
    private String usuario;

	

	public Actividad(String nombre, int numeroParticipantes, float precio, boolean pagada, String usuario) {
		super();
		this.nombre = nombre;
		this.numeroParticipantes = numeroParticipantes;
		this.precio = precio;
		this.pagada = pagada;
		this.usuario = usuario;
	}

	public Actividad(String nombre, int numeroParticipantes, float precio) {
		super();
		this.nombre = nombre;
		this.numeroParticipantes = numeroParticipantes;
		this.precio = precio;
		this.pagada = false;
		this.usuario = null;
	}

	public Actividad() {
		super();
	}
	
	public boolean isPagada() {
		return pagada;
	}

	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumeroParticipantes() {
		return numeroParticipantes;
	}
	public void setNumeroParticipantes(int numeroParticipantes) {
		this.numeroParticipantes = numeroParticipantes;
	}

	public String toStringBd() {
		return nombre + "," + numeroParticipantes + "," + precio + "," + pagada + "," + usuario;
	}

	public String toStringAdmin() {
		return "Actividad: " + nombre + " - Usuario: " + usuario;
	}

	
	public static Actividad[][] shiftArray(Actividad[][] actividadesSemanales) {
		return shiftArrayRecursivo(actividadesSemanales, 0, new Actividad[6][10]);
	}

	public static Actividad[][] shiftArrayRecursivo(Actividad[][] actividadesSemanales, int i, Actividad[][] resultado) {
		if (i == actividadesSemanales.length-1) {
			resultado[i] = new Actividad[10];
			return resultado;
		}
		resultado[i] = getActividadesDiaSiguente(actividadesSemanales, i);
		return shiftArrayRecursivo(actividadesSemanales, i+1, resultado);
	}

	private static Actividad[] getActividadesDiaSiguente(Actividad[][] actividadesSemanales, int i) {
		if (i == actividadesSemanales.length-1) {
			return new Actividad[10];
		}
		return actividadesSemanales[i+1];
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return nombre.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Actividad)){
			return false;
		}
		if (((Actividad) obj).getNombre() == null) {
			return true;
		}
		return nombre.equals(((Actividad) obj).getNombre());
	}
}
