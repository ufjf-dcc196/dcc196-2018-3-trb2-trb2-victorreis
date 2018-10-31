package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

public class Evento {

    private String titulo;
    private String dia;
    private String hora;
    private String facilitador;
    private String descricaoTextual;

    public Evento() {
    }

    public Evento(String titulo, String dia, String hora, String facilitador, String descricaoTextual) {
        this.titulo = titulo;
        this.dia = dia;
        this.hora = hora;
        this.facilitador = facilitador;
        this.descricaoTextual = descricaoTextual;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador;
    }

    public String getDescricaoTextual() {
        return descricaoTextual;
    }

    public void setDescricaoTextual(String descricaoTextual) {
        this.descricaoTextual = descricaoTextual;
    }
}
