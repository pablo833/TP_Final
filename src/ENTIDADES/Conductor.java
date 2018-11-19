package ENTIDADES;

public class Conductor extends Persona {

    private int codigo;

    private double sueldo;

    public int getCodigo() {
        return codigo;
    }

    public void serCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Conductor(int codigo, String nombre, String apellido, int dni, double sueldo) {
        super(nombre, apellido, dni);
        this.codigo = codigo;
        this.sueldo = sueldo;
    }

    public Conductor(String nombre, String apellido, int dni, double sueldo) {
        super(nombre, apellido, dni);
        this.codigo = codigo;
        this.sueldo = sueldo;
    }
}
