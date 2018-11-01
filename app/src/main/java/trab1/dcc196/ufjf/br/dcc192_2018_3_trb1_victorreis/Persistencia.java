package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static List<Participante> participantes;
    private static List<Evento> eventos;

    public static List<Participante> getInstanceParticipantes() {
        if (participantes == null) {
            participantes = new ArrayList<>();

            participantes.add(new Participante("Fulano de Bla bla bla", "fulaninho@uol.com.br", "123.234.345.56"));
            participantes.add(new Participante("Beltrano de Ble ble ble", "bel.trano@bol.com.br", "089.987.867.76"));
        }
        return participantes;
    }

    public static List<Evento> getInstanceEventos() {
        if (eventos == null) {
            eventos = new ArrayList<>();

            eventos.add(new Evento("Título 1", "11/11/2011", "23:23", "Fulano", "Bla bla bla bla bla bla bla bla bla"));
            eventos.add(new Evento("Título 2", "22/11/2000", "11:11", "Beltrano", "Bla bla bla bla bla bla bla bla bla"));
            eventos.add(new Evento("Título 3", "01/01/2001", "11:11", "Beltrano", "Bla bla bla bla bla bla bla bla bla"));
        }
        return eventos;
    }
}
