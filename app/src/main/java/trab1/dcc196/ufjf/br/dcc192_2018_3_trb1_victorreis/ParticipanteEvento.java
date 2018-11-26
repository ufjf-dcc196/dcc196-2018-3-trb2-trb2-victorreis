package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

public class ParticipanteEvento {
    private Integer id;
    private Integer participanteId;
    private Integer eventoId;

    public ParticipanteEvento() {
    }

    public ParticipanteEvento(Integer participanteId, Integer eventoId) {
        this.participanteId = participanteId;
        this.eventoId = eventoId;
    }

    public Integer getId() {
        return id;
    }

    public ParticipanteEvento setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getParticipanteId() {
        return participanteId;
    }

    public ParticipanteEvento setParticipanteId(Integer participanteId) {
        this.participanteId = participanteId;
        return this;
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public ParticipanteEvento setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
        return this;
    }
}
