package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import java.util.ArrayList;
import java.util.List;

public class Participante {

    private String nomeCompleto;
    private String email;
    private String cpf;
    private List<Evento> eventos;

    public Participante() {
        this.eventos = new ArrayList<>();
    }

    public Participante (String nomeCompleto, String email, String cpf) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.cpf = cpf;
        this.eventos = new ArrayList<>();
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
