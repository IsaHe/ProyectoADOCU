package domain;

public class Actividad {
    private String nombre;
    private int numeroParticipantes;

	public Actividad(String nombre, int numeroParticipantes) {
		super();
		this.nombre = nombre;
		this.numeroParticipantes = numeroParticipantes;
	}

	public Actividad() {
		super();
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
		return nombre + "," + numeroParticipantes;
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
