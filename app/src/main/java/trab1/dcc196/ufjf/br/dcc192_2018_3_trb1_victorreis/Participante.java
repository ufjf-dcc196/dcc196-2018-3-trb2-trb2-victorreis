package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import java.util.ArrayList;
import java.util.List;

public class Participante {

    private Integer id;
    private String nomeCompleto;
    private String email;
    private String cpf;
    private List<Evento> eventos;

    public Participante() {
        this.eventos = new ArrayList<>();
    }

    public Participante(Integer id, String nomeCompleto, String email, String cpf) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.cpf = cpf;
        this.eventos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public Participante setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public Participante setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Participante setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Participante setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public Participante setEventos(List<Evento> eventos) {
        this.eventos = eventos;
        return this;
    }
}
