package entidades;

public class Conductor extends Persona {

	private double sueldo;

	public Conductor(String nombre, String apellido, int dni, double sueldo) {
		super(nombre, apellido, dni);
		this.sueldo = sueldo;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

}
