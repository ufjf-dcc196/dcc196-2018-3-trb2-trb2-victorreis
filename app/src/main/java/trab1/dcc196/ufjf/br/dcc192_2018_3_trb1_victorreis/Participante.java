package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

public class Participante {

    private String nomeCompleto;
    private String email;
    private String cpf;

    public Participante() {
    }

    public Participante (String nomeCompleto, String email, String cpf) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.cpf = cpf;
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
}
