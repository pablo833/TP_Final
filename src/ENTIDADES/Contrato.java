package ENTIDADES;

public class Contrato {

    private Programa programa;
    private Auspiciante auspiciante;
    private int tiempoDePauta;

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Auspiciante getAuspiciante() {
        return auspiciante;
    }

    public void setAuspiciante(Auspiciante auspiciante) {
        this.auspiciante = auspiciante;
    }

    public int getTiempoDePauta() {
        return tiempoDePauta;
    }

    public void setTiempoDePauta(int tiempoDePauta) {
        this.tiempoDePauta = tiempoDePauta;
    }

    public Contrato(Programa programa, Auspiciante auspiciante, int tiempoDePauta) {
        this.programa = programa;
        this.auspiciante = auspiciante;
        this.tiempoDePauta = tiempoDePauta;
    }
}
