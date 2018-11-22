package ENTIDADES;

public class Programa {

    private int codigo;
    private String nombre;
    private String horario;
    private Double valorSegundoAlAire;
    private Persona conductor;
    private Persona productor;
    private Auspiciante[] auspiciantes;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

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

    public Auspiciante[] getAuspiciantes() {
        return auspiciantes;
    }

    public void setAuspiciantes(Auspiciante[] auspiciantes) {
        this.auspiciantes = auspiciantes;
    }

    public Programa(int codigo, String nombre, String horario, Double valorSegundoAlAire, Conductor conductor, Productor productor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.horario = horario;
        this.valorSegundoAlAire = valorSegundoAlAire;
        this.conductor = conductor;
        this.productor = productor;
    }
    public Programa( String nombre, String horario, Double valorSegundoAlAire, Conductor conductor, Productor productor) {

        this.nombre = nombre;
        this.horario = horario;
        this.valorSegundoAlAire = valorSegundoAlAire;
        this.conductor = conductor;
        this.productor = productor;
    }
}
