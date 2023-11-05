package domain;

import java.time.LocalDate;
import java.util.Arrays;

public class Actividad {
    private String nombre;
    private int numeroParticipantes;
    private String[] diasDeLaSemana;
    private LocalDate fecha;
    
	public Actividad(String nombre, int numeroParticipantes, String[] diasDeLaSemana, LocalDate fecha) {
		super();
		this.nombre = nombre;
		this.numeroParticipantes = numeroParticipantes;
		this.diasDeLaSemana = diasDeLaSemana;
		this.fecha = fecha;
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
	public String[] getDiasDeLaSemana() {
		return diasDeLaSemana;
	}
	public void setDiasDeLaSemana(String[] diasDeLaSemana) {
		this.diasDeLaSemana = diasDeLaSemana;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "Actividad [nombre=" + nombre + ", numeroParticipantes=" + numeroParticipantes + ", diasDeLaSemana="
				+ Arrays.toString(diasDeLaSemana) + ", fecha=" + fecha + "]";
	}
    
    
    
    
}
