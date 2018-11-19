package ENTIDADES;

public class Radio {

	private String nombre;
	private String dial;
	private Programa[] programas;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDial() {
		return dial;
	}

	public void setDial(String dial) {
		this.dial = dial;
	}

	public Programa[] getProgramas() {
		return programas;
	}

	public void setProgramas(Programa[] programas) {
		this.programas = programas;
	}

}
