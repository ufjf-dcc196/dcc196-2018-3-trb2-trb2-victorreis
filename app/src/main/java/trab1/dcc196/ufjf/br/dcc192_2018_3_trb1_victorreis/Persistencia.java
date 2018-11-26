package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Telephony;

import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static Persistencia instance;
    private Trabalho3DBHelper trabalho3DBHelper;
    private SQLiteDatabase db;

    private Persistencia(Context context) {
        trabalho3DBHelper = new Trabalho3DBHelper(context);
        db = trabalho3DBHelper.getWritableDatabase();

        inserirDadosIniciais();
    }

    private void inserirDadosIniciais() {
        Participante participante;
        Evento evento;

        participante = new Participante();
        participante.setNomeCompleto("Victor Reis")
                .setEmail("v.crisostomo.reis@gmail.com")
                .setCpf("104.974.436-51");
        insertParticipante(participante);

        participante = new Participante();
        participante.setNomeCompleto("Fulano de tal")
                .setEmail("fulano@gmail.com")
                .setCpf("999.999.999-99");
        insertParticipante(participante);

        participante = new Participante();
        participante.setNomeCompleto("Ciclano Filho")
                .setEmail("ciclano@gmail.com")
                .setCpf("888.888.888-88");
        insertParticipante(participante);

        participante = new Participante();
        participante.setNomeCompleto("Beltrano Júnior")
                .setEmail("beltrano@gmail.com")
                .setCpf("777.777.777-77");
        insertParticipante(participante);

        // ------------------------------------------------- //

        evento = new Evento();
        evento.setTitulo("Apresentação de TCC do Victor Reis")
                .setDia("04/12/2018")
                .setHora("17:00")
                .setFacilitador("Marcelo Caniato Renhe")
                .setDescricaoTextual("ALEA: Sistema de Gestão de Riscos Geotécnicos");
        insertEvento(evento);

        evento = new Evento();
        evento.setTitulo("Evento X")
                .setDia("12/12/2018")
                .setHora("12:12")
                .setFacilitador("Zé das Couves")
                .setDescricaoTextual("O Evento X bla bla bla bla bla bla.");
        insertEvento(evento);

        evento = new Evento();
        evento.setTitulo("Evento Y")
                .setDia("11/11/2018")
                .setHora("11:11")
                .setFacilitador("Fulaninho")
                .setDescricaoTextual("O Evento Y bla bla bla bla bla bla.");
        insertEvento(evento);
    }

    public static Persistencia getInstance(Context context){
        if (instance == null) {
            instance = new Persistencia(context);
        }
        return instance;
    }

    public Participante insertParticipante(Participante participante) {
        ContentValues values = new ContentValues();
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO, participante.getNomeCompleto());
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_EMAIL, participante.getEmail());
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_CPF, participante.getCpf());
        long id = db.insert(Trabalho3Contract.Participante.TABLE_NAME, null, values);

        participante.setId((int) id);
        return participante;
    }

    public Evento insertEvento(Evento evento) {
        ContentValues values = new ContentValues();
        values.put(Trabalho3Contract.Evento.COLUMN_NAME_TITULO, evento.getTitulo());
        values.put(Trabalho3Contract.Evento.COLUMN_NAME_DIA, evento.getDia());
        values.put(Trabalho3Contract.Evento.COLUMN_NAME_HORA, evento.getHora());
        values.put(Trabalho3Contract.Evento.COLUMN_NAME_FACILITADOR, evento.getFacilitador());
        values.put(Trabalho3Contract.Evento.COLUMN_NAME_DESCRICAO_TEXTUAL, evento.getDescricaoTextual());
        long id = db.insert(Trabalho3Contract.Evento.TABLE_NAME, null, values);

        evento.setId((int) id);
        return evento;
    }

    public List<Participante> selectAllParticipantes() {
        ArrayList<Participante> participantes = new ArrayList<>();
        Participante participante;

        String[] visao = {
                Trabalho3Contract.Participante._ID,
                Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO,
                Trabalho3Contract.Participante.COLUMN_NAME_EMAIL,
                Trabalho3Contract.Participante.COLUMN_NAME_CPF,
        };
        String sort = Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO + " DESC";
        String[] args = {};
        Cursor c = db.query(Trabalho3Contract.Participante.TABLE_NAME, visao, "", args, null, null, sort);

        int indiceParticipanteNomeCompleto = c.getColumnIndexOrThrow(Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO);
        int indiceParticipanteEmail = c.getColumnIndexOrThrow(Trabalho3Contract.Participante.COLUMN_NAME_EMAIL);
        int indiceParticipanteCPF = c.getColumnIndexOrThrow(Trabalho3Contract.Participante.COLUMN_NAME_CPF);

        if (!c.isNull(indiceParticipanteNomeCompleto)) {
            while (c.moveToNext()) {
                participante = new Participante();
                participante.setNomeCompleto(c.getString(indiceParticipanteNomeCompleto));
                participante.setEmail(c.getString(indiceParticipanteEmail));
                participante.setCpf(c.getString(indiceParticipanteCPF));

                participantes.add(participante);
            }
        }

        return participantes;
    }

    public List<Evento> selectAllEventos() {
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento evento;

        String[] visao = {
                Trabalho3Contract.Evento._ID,
                Trabalho3Contract.Evento.COLUMN_NAME_TITULO,
                Trabalho3Contract.Evento.COLUMN_NAME_DIA,
                Trabalho3Contract.Evento.COLUMN_NAME_HORA,
                Trabalho3Contract.Evento.COLUMN_NAME_FACILITADOR,
                Trabalho3Contract.Evento.COLUMN_NAME_DESCRICAO_TEXTUAL,
        };
        String sort = Trabalho3Contract.Evento.COLUMN_NAME_TITULO + " DESC";
        String[] args = {};
        Cursor c = db.query(Trabalho3Contract.Evento.TABLE_NAME, visao, "", args, null, null, sort);

        int indiceEventoTitulo = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_TITULO);
        int indiceEventoDia = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_DIA);
        int indiceEventoHora = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_HORA);
        int indiceEventoFacilitador = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_FACILITADOR);
        int indiceEventoDescricaoTextual = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_DESCRICAO_TEXTUAL);

        if (!c.isNull(indiceEventoTitulo)) {
            while (c.moveToNext()) {
                evento = new Evento();
                evento.setTitulo(c.getString(indiceEventoTitulo));
                evento.setDia(c.getString(indiceEventoDia));
                evento.setHora(c.getString(indiceEventoHora));
                evento.setFacilitador(c.getString(indiceEventoFacilitador));
                evento.setDescricaoTextual(c.getString(indiceEventoDescricaoTextual));

                eventos.add(evento);
            }
        }

        return eventos;
    }

    //private static List<Participante> participantes;
    //private static List<Evento> eventos;

//    public static List<Participante> getInstanceParticipantes() {
//        if (participantes == null) {
//            participantes = new ArrayList<>();
//
//            participantes.add(new Participante("Fulano de Bla bla bla", "fulaninho@uol.com.br", "123.234.345.56"));
//            participantes.add(new Participante("Beltrano de Ble ble ble", "bel.trano@bol.com.br", "089.987.867.76"));
//        }
//        return participantes;
//    }

//    public static List<Evento> getInstanceEventos() {
//        if (eventos == null) {
//            eventos = new ArrayList<>();
//
//            eventos.add(new Evento("Título 1", "11/11/2011", "23:23", "Fulano", "Bla bla bla bla bla bla bla bla bla"));
//            eventos.add(new Evento("Título 2", "22/11/2000", "11:11", "Beltrano", "Bla bla bla bla bla bla bla bla bla"));
//            eventos.add(new Evento("Título 3", "01/01/2001", "11:11", "Beltranovvdd", "Bla bla bla bla bla bla bla bla bla"));
//        }
//        return eventos;
//    }
}
