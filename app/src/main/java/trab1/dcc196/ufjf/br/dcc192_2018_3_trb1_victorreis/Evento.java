package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

public class Evento {

    private Integer id;
    private String titulo;
    private String dia;
    private String hora;
    private String facilitador;
    private String descricaoTextual;

    public Evento() {
    }

    public Evento(Integer id, String titulo, String dia, String hora, String facilitador, String descricaoTextual) {
        this.id = id;
        this.titulo = titulo;
        this.dia = dia;
        this.hora = hora;
        this.facilitador = facilitador;
        this.descricaoTextual = descricaoTextual;
    }

    public Integer getId() {
        return id;
    }

    public Evento setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Evento setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getDia() {
        return dia;
    }

    public Evento setDia(String dia) {
        this.dia = dia;
        return this;
    }

    public String getHora() {
        return hora;
    }

    public Evento setHora(String hora) {
        this.hora = hora;
        return this;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public Evento setFacilitador(String facilitador) {
        this.facilitador = facilitador;
        return this;
    }

    public String getDescricaoTextual() {
        return descricaoTextual;
    }

    public Evento setDescricaoTextual(String descricaoTextual) {
        this.descricaoTextual = descricaoTextual;
        return this;
    }
}
