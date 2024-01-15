package domain;

import java.io.Serial;
import java.io.Serializable;

/**
 * Clase Actividad que representa una actividad que puede ser realizada por un usuario.
 * Esta clase contiene información sobre el nombre de la actividad, el número de participantes, el precio, si está pagada y el usuario que la realiza.
 */
public class Actividad implements Serializable{
    @Serial
	private static final long serialVersionUID = 6010932527950163306L;
	private String nombre;
    private int numeroParticipantes;
    private float precio;
    private boolean pagada;
    private String usuario;

	/**
	 * Constructor de la clase Actividad.
	 * @param nombre El nombre de la actividad.
	 * @param numeroParticipantes El número de participantes en la actividad.
	 * @param precio El precio de la actividad.
	 * @param pagada Indica si la actividad está pagada.
	 * @param usuario El usuario que realiza la actividad.
	 */
	public Actividad(String nombre, int numeroParticipantes, float precio, boolean pagada, String usuario) {
		super();
		this.nombre = nombre;
		this.numeroParticipantes = numeroParticipantes;
		this.precio = precio;
		this.pagada = pagada;
		this.usuario = usuario;
	}

	/**
	 * Constructor de la clase Actividad sin usuario y pagada por defecto en false.
	 * @param nombre El nombre de la actividad.
	 * @param numeroParticipantes El número de participantes en la actividad.
	 * @param precio El precio de la actividad.
	 */
	public Actividad(String nombre, int numeroParticipantes, float precio) {
		super();
		this.nombre = nombre;
		this.numeroParticipantes = numeroParticipantes;
		this.precio = precio;
		this.pagada = false;
		this.usuario = null;
	}

	/**
	 * Constructor por defecto de la clase Actividad.
	 */
	public Actividad() {
		super();
	}

	/**
	 * Método para obtener si la actividad está pagada.
	 * @return true si la actividad está pagada, false en caso contrario.
	 */
	public boolean isPagada() {
		return pagada;
	}

	/**
	 * Método para establecer si la actividad está pagada.
	 * @param pagada El estado de pago de la actividad.
	 */
	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}

	/**
	 * Método para obtener el usuario que realiza la actividad.
	 * @return El usuario que realiza la actividad.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Método para establecer el usuario que realiza la actividad.
	 * @param usuario El usuario que realiza la actividad.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método para obtener el precio de la actividad.
	 * @return El precio de la actividad.
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * Método para establecer el precio de la actividad.
	 * @param precio El precio de la actividad.
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	/**
	 * Método para obtener el nombre de la actividad.
	 * @return El nombre de la actividad.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método para establecer el nombre de la actividad.
	 * @param nombre El nombre de la actividad.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método para obtener el número de participantes en la actividad.
	 * @return El número de participantes en la actividad.
	 */
	public int getNumeroParticipantes() {
		return numeroParticipantes;
	}

	/**
	 * Método para establecer el número de participantes en la actividad.
	 * @param numeroParticipantes El número de participantes en la actividad.
	 */
	public void setNumeroParticipantes(int numeroParticipantes) {
		this.numeroParticipantes = numeroParticipantes;
	}

	/**
	 * Método para obtener la representación en cadena de la actividad para la base de datos.
	 * @return La representación en cadena de la actividad para la base de datos.
	 */
	public String toStringBd() {
		return nombre + "," + numeroParticipantes + "," + precio + "," + pagada + "," + usuario;
	}

	/**
	 * Método para obtener la representación en cadena de la actividad para el administrador.
	 * @return La representación en cadena de la actividad para el administrador.
	 */
	public String toStringAdmin() {
		return "Actividad: " + nombre + " - Usuario: " + usuario;
	}

	/**
	 * Método para desplazar el array de actividades semanales.
	 * @param actividadesSemanales El array de actividades semanales.
	 * @return El array de actividades semanales desplazado.
	 */
	public static Actividad[][] shiftArray(Actividad[][] actividadesSemanales) {
		return shiftArrayRecursivo(actividadesSemanales, 0, new Actividad[6][10]);
	}

	/**
	 * Método para desplazar el array de actividades semanales de forma recursiva.
	 * @param actividadesSemanales El array de actividades semanales.
	 * @param i El índice del array.
	 * @param resultado El array de actividades semanales desplazado.
	 * @return El array de actividades semanales desplazado.
	 */
	public static Actividad[][] shiftArrayRecursivo(Actividad[][] actividadesSemanales, int i, Actividad[][] resultado) {
		if (i == actividadesSemanales.length-1) {
			resultado[i] = new Actividad[10];
			return resultado;
		}
		resultado[i] = getActividadesDiaSiguente(actividadesSemanales, i);
		return shiftArrayRecursivo(actividadesSemanales, i+1, resultado);
	}

	/**
	 * Método para obtener las actividades del día siguiente teniendo  en cuenta el desplazamiento del array.
	 * @param actividadesSemanales El array de actividades semanales.
	 * @param i El índice del array.
	 * @return Las actividades del día siguiente.
	 */
	private static Actividad[] getActividadesDiaSiguente(Actividad[][] actividadesSemanales, int i) {
		if (i == actividadesSemanales.length-1) {
			return new Actividad[10];
		}
		return actividadesSemanales[i+1];
	}

	/**
	 * Método para obtener la representación en cadena de la actividad.
	 * @return La representación en cadena de la actividad.
	 */
	@Override
	public String toString() {
		return nombre;
	}

	/**
	 * Método para obtener el código hash de la actividad.
	 * @return El código hash de la actividad.
	 */
	@Override
	public int hashCode() {
		return nombre.hashCode();
	}

	/**
	 * Método para comparar la igualdad de esta actividad con otro objeto.
	 * @param obj El objeto a comparar con esta actividad.
	 * @return true si el objeto es igual a esta actividad, false en caso contrario.
	 */
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
