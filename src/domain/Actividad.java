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
		return nombre + "," + numeroParticipantes + "," + precio;
	}

	public static Actividad[][] shiftArray(Actividad[][] actividadesSemanales) {
		Actividad[][] actividadesSemanalesAux = new Actividad[6][10];
		for (int i = 0; i < actividadesSemanales.length; i++) {
			actividadesSemanalesAux[i] = getActividadesDiaSiguente(actividadesSemanales, i);
		}
		return actividadesSemanalesAux;
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
		if (obj instanceof Actividad){
			if (((Actividad) obj).getNombre() == null) {
				return true;
			}
			return nombre.equals(((Actividad) obj).getNombre());
		}
		return false;
	}
}
