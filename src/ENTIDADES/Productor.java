package ENTIDADES;

public class Productor extends Persona {

    private int codigo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Productor(String nombre, String apellido, int dni) {
        super(nombre, apellido, dni);
    }

    public Productor(int codigo, String nombre, String apellido, int dni) {
        super(nombre, apellido, dni);
        this.codigo = codigo;
    }
}
