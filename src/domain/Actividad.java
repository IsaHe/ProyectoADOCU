package domain;

public class Actividad {
    private String nombre;
    private int numeroParticipantes;
    private float precio;

	public Actividad(String nombre, int numeroParticipantes, float precio) {
		super();
		this.nombre = nombre;
		this.numeroParticipantes = numeroParticipantes;
		this.precio = precio;
	}

	public Actividad() {
		super();
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
		return nombre + "," + numeroParticipantes+","+precio;
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
		if (obj instanceof Actividad){
			return nombre.equals(((Actividad) obj).getNombre());
		}
		return false;
	}
}
