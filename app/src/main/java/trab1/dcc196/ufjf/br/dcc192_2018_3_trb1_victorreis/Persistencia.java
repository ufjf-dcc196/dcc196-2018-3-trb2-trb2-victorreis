package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Telephony;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static Persistencia instance;
    private Trabalho3DBHelper trabalho3DBHelper;
    private SQLiteDatabase db;

    private Persistencia(Context context) {
        trabalho3DBHelper = new Trabalho3DBHelper(context);
        db = trabalho3DBHelper.getWritableDatabase();
    }

    public static Persistencia getInstance(Context context){
        if (instance == null) {
            instance = new Persistencia(context);
        }
        return instance;
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

    public void inserirResetarDadosIniciais() {
        db.execSQL(Trabalho3Contract.SQL_DROP_PARTICIPANTE);
        db.execSQL(Trabalho3Contract.SQL_DROP_EVENTO);
        db.execSQL(Trabalho3Contract.SQL_DROP_PARTICIPANTE_EVENTO);
        db.execSQL(Trabalho3Contract.SQL_CREATE_PARTICIPANTE);
        db.execSQL(Trabalho3Contract.SQL_CREATE_EVENTO);
        db.execSQL(Trabalho3Contract.SQL_CREATE_PARTICIPANTE_EVENTO);
        inserirDadosIniciais();
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

    public Cursor selectAllParticipantesCursor() {
        String[] visao = {
                Trabalho3Contract.Participante._ID,
                Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO,
                Trabalho3Contract.Participante.COLUMN_NAME_EMAIL,
                Trabalho3Contract.Participante.COLUMN_NAME_CPF,
        };
        String sort = Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO + " DESC";
        String[] args = {};
        return db.query(Trabalho3Contract.Participante.TABLE_NAME, visao, "", args, null, null, sort);
    }

    public ArrayList<Participante> selectAllParticipantes() {
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

        int indiceParticipanteID = c.getColumnIndexOrThrow(Trabalho3Contract.Participante._ID);
        int indiceParticipanteNomeCompleto = c.getColumnIndexOrThrow(Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO);
        int indiceParticipanteEmail = c.getColumnIndexOrThrow(Trabalho3Contract.Participante.COLUMN_NAME_EMAIL);
        int indiceParticipanteCPF = c.getColumnIndexOrThrow(Trabalho3Contract.Participante.COLUMN_NAME_CPF);

        if (c != null && c.moveToFirst()) {
            do {
                participante = new Participante();
                participante.setId(c.getInt(indiceParticipanteID))
                        .setNomeCompleto(c.getString(indiceParticipanteNomeCompleto))
                        .setEmail(c.getString(indiceParticipanteEmail))
                        .setCpf(c.getString(indiceParticipanteCPF));

                participantes.add(participante);
            } while (c.moveToNext());
        }

        return participantes;
    }

    public Cursor selectAllEventosCursor() {
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
        return db.query(Trabalho3Contract.Evento.TABLE_NAME, visao, "", args, null, null, sort);
    }

    public Cursor selectEventosFromParticipanteCursor(Integer idParticipante) {
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
        return db.query(Trabalho3Contract.Evento.TABLE_NAME, visao, "", args, null, null, sort);
    }

    public Cursor selectEventosRestantesFromParticipanteCursor(Integer idParticipante) {
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
        return db.query(Trabalho3Contract.Evento.TABLE_NAME, visao, "", args, null, null, sort);
    }

    public ArrayList<Evento> selectAllEventos() {
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

        int indiceEventoID = c.getColumnIndexOrThrow(Trabalho3Contract.Evento._ID);
        int indiceEventoTitulo = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_TITULO);
        int indiceEventoDia = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_DIA);
        int indiceEventoHora = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_HORA);
        int indiceEventoFacilitador = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_FACILITADOR);
        int indiceEventoDescricaoTextual = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_DESCRICAO_TEXTUAL);

        if (c != null && c.moveToFirst()) {
            do {
                evento = new Evento();
                evento.setId(c.getInt(indiceEventoID))
                        .setTitulo(c.getString(indiceEventoTitulo))
                        .setDia(c.getString(indiceEventoDia))
                        .setHora(c.getString(indiceEventoHora))
                        .setFacilitador(c.getString(indiceEventoFacilitador))
                        .setDescricaoTextual(c.getString(indiceEventoDescricaoTextual));

                eventos.add(evento);
            } while (c.moveToNext());
        }

        return eventos;
    }

    public boolean deleteParticipante(String nomeCompleto) {
        String select = Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO + " = ?";
        String[] selectArgs = {nomeCompleto};
        db.delete(Trabalho3Contract.Participante.TABLE_NAME,select,selectArgs);
        Log.i("DBINFO", "DEL nome_completo: " + nomeCompleto);
        return true;
    }

    public boolean deleteEvento(String titulo) {
        String select = Trabalho3Contract.Evento.COLUMN_NAME_TITULO + " = ?";
        String[] selectArgs = {titulo};
        db.delete(Trabalho3Contract.Evento.TABLE_NAME,select,selectArgs);
        Log.i("DBINFO", "DEL titulo: " + titulo);
        return true;
    }

    public Participante selectParticipanteById(Integer id) {
        String[] visao = {
                Trabalho3Contract.Participante._ID,
                Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO,
                Trabalho3Contract.Participante.COLUMN_NAME_EMAIL,
                Trabalho3Contract.Participante.COLUMN_NAME_CPF,
        };
        String selecao = Trabalho3Contract.Participante._ID + " = ?";
        String[] args = {String.valueOf(id)};
        String sort = "";
        Cursor c = db.query(Trabalho3Contract.Participante.TABLE_NAME, visao, selecao, args, null, null, sort);

        Participante participante = new Participante();
        int indiceParticipanteID = c.getColumnIndexOrThrow(Trabalho3Contract.Participante._ID);
        int indiceParticipanteNomeCompleto = c.getColumnIndexOrThrow(Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO);
        int indiceParticipanteEmail = c.getColumnIndexOrThrow(Trabalho3Contract.Participante.COLUMN_NAME_EMAIL);
        int indiceParticipanteCPF = c.getColumnIndexOrThrow(Trabalho3Contract.Participante.COLUMN_NAME_CPF);

        if (c != null && c.moveToFirst()) {
            do {
                participante = new Participante();
                participante.setId(c.getInt(indiceParticipanteID))
                        .setNomeCompleto(c.getString(indiceParticipanteNomeCompleto))
                        .setEmail(c.getString(indiceParticipanteEmail))
                        .setCpf(c.getString(indiceParticipanteCPF));
            } while (c.moveToNext());
        }

        return participante;
    }

    public Evento selectEventoById(Integer id) {
        String[] visao = {
                Trabalho3Contract.Evento._ID,
                Trabalho3Contract.Evento.COLUMN_NAME_TITULO,
                Trabalho3Contract.Evento.COLUMN_NAME_DIA,
                Trabalho3Contract.Evento.COLUMN_NAME_HORA,
                Trabalho3Contract.Evento.COLUMN_NAME_FACILITADOR,
                Trabalho3Contract.Evento.COLUMN_NAME_DESCRICAO_TEXTUAL,
        };
        String selecao = Trabalho3Contract.Evento._ID + " = ?";
        String[] args = {String.valueOf(id)};
        String sort = "";
        Cursor c = db.query(Trabalho3Contract.Evento.TABLE_NAME, visao, selecao, args, null, null, sort);

        int indiceEventoID = c.getColumnIndexOrThrow(Trabalho3Contract.Evento._ID);
        int indiceEventoTitulo = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_TITULO);
        int indiceEventoDia = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_DIA);
        int indiceEventoHora = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_HORA);
        int indiceEventoFacilitador = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_FACILITADOR);
        int indiceEventoDescricaoTextual = c.getColumnIndexOrThrow(Trabalho3Contract.Evento.COLUMN_NAME_DESCRICAO_TEXTUAL);

        Evento evento = new Evento();
        if (c != null && c.moveToFirst()) {
            do {
                evento = new Evento();
                evento.setId(c.getInt(indiceEventoID))
                        .setTitulo(c.getString(indiceEventoTitulo))
                        .setDia(c.getString(indiceEventoDia))
                        .setHora(c.getString(indiceEventoHora))
                        .setFacilitador(c.getString(indiceEventoFacilitador))
                        .setDescricaoTextual(c.getString(indiceEventoDescricaoTextual));
            } while (c.moveToNext());
        }

        return evento;
    }
}
