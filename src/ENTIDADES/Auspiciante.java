package ENTIDADES;

public class Auspiciante {

    private int code;
    private String razonSocial;

    public Auspiciante(int code, String razonSocial) {
        this.razonSocial = razonSocial;
        this.code = code;
    }

    public Auspiciante(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

}
