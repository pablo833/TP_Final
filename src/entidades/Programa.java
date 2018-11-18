package entidades;

public class Programa {

	private Persona conductor;
	private Persona productor;
	private Double valorSegundoAlAire;
	private String horario;
	private String nombre;
	private Auspiciante[] auspiciantes;
	
	public Conductor getConductor() {
		return (Conductor) conductor;
	}
	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}
	public Productor getProductor() {
		return (Productor) productor;
	}
	public void setProductor(Productor productor) {
		this.productor = productor;
	}
	public Double getValorSegundoAlAire() {
		return valorSegundoAlAire;
	}
	public void setValorSegundoAlAire(Double valorSegundoAlAire) {
		this.valorSegundoAlAire = valorSegundoAlAire;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Programa(String nombre, String horario, Double valorSegundoAlAire, Conductor conductor, Productor productor) {
		this.nombre = nombre;
		this.horario = horario;
		this.valorSegundoAlAire = valorSegundoAlAire;
		this.conductor = conductor;
		this.productor = productor;
	}
	public Auspiciante[] getAuspiciantes() {
		return auspiciantes;
	}
	public void setAuspiciantes(Auspiciante[] auspiciantes) {
		this.auspiciantes = auspiciantes;
	}
}
