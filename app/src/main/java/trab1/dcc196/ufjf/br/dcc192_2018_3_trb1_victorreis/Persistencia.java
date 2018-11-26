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

    // -------------------------------------------------
    // -------------------------------------------------
    // -------------------------------------------------

    public ArrayList<Participante> transformCursorInArrayListOfParticipantes(Cursor c) {
        ArrayList<Participante> participantes = new ArrayList<>();
        Participante participante;

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

    public ArrayList<Evento> transformCursorInArrayListOfEventos(Cursor c) {
        ArrayList<Evento> eventos = new ArrayList<>();
        Evento evento;

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

    // -------------------------------------------------
    // -------------------------------------------------
    // -------------------------------------------------

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

    public ParticipanteEvento insertParticipanteEvento(Integer idParticipante, Evento evento) {
        ContentValues values = new ContentValues();
        values.put(Trabalho3Contract.ParticipanteEvento.COLUMN_NAME_PARTICIPANTE_ID, idParticipante);
        values.put(Trabalho3Contract.ParticipanteEvento.COLUMN_NAME_EVENTO_ID, evento.getId());
        long id = db.insert(Trabalho3Contract.ParticipanteEvento.TABLE_NAME, null, values);

        ParticipanteEvento participanteEvento = new ParticipanteEvento();
        participanteEvento.setId((int) id)
                .setParticipanteId(idParticipante)
                .setEventoId(evento.getId());
        return participanteEvento;
    }

    // -------------------------------------------------
    // -------------------------------------------------
    // -------------------------------------------------

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
//        String query = "SELECT * " +
//                "FROM " + Trabalho3Contract.ParticipanteEvento.TABLE_NAME + " AS pe, "
//                + Trabalho3Contract.Evento.TABLE_NAME + " AS e " +
//                "WHERE pe." + Trabalho3Contract.ParticipanteEvento.COLUMN_NAME_PARTICIPANTE_ID + " = ? "
//                + "AND e." + Trabalho3Contract.Evento._ID + " = pe." + Trabalho3Contract.ParticipanteEvento.COLUMN_NAME_EVENTO_ID;
        String query = "SELECT * FROM evento WHERE _id IN (SELECT pe.evento_id FROM participante_evento pe INNER JOIN evento e ON pe.evento_id = e._id WHERE pe.participante_id = ?)";
        String[] args = {String.valueOf(idParticipante)};
        return db.rawQuery(query, args);
    }

    public Cursor selectEventosRestantesFromParticipanteCursor(Integer idParticipante) {
//        String query = "SELECT * " +
//                "FROM " + Trabalho3Contract.ParticipanteEvento.TABLE_NAME + " AS pe, "
//                + Trabalho3Contract.Evento.TABLE_NAME + " AS e " +
//                "WHERE pe." + Trabalho3Contract.ParticipanteEvento.COLUMN_NAME_PARTICIPANTE_ID + " != ? "
//                + "AND e." + Trabalho3Contract.Evento._ID + " = pe." + Trabalho3Contract.ParticipanteEvento.COLUMN_NAME_EVENTO_ID;
        String query = "SELECT * FROM evento WHERE _id NOT IN (SELECT pe.evento_id FROM participante_evento pe INNER JOIN evento e ON pe.evento_id = e._id WHERE pe.participante_id = ?)";
        String[] args = {String.valueOf(idParticipante)};
        //String[] args = {};
        return db.rawQuery(query, args);
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

    // -------------------------------------------------
    // -------------------------------------------------
    // -------------------------------------------------

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

    public boolean deleteParticipanteEvento(Integer idParticipante, Evento evento) {
        String select = Trabalho3Contract.ParticipanteEvento.COLUMN_NAME_PARTICIPANTE_ID + " = ? " +
                " AND " + Trabalho3Contract.ParticipanteEvento.COLUMN_NAME_EVENTO_ID + " = ? ";
        String[] selectArgs = {String.valueOf(idParticipante), String.valueOf(evento.getId())};
        db.delete(Trabalho3Contract.ParticipanteEvento.TABLE_NAME,select,selectArgs);
        Log.i("DBINFO", "DEL deleteParticipanteEvento: id_part>" + idParticipante + " ||| id_evento>" + evento.getId());
        return true;
    }

    // -------------------------------------------------
    // -------------------------------------------------
    // -------------------------------------------------

    public Participante updateParticipante(Participante participante) {
        ContentValues values = new ContentValues();
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO, participante.getNomeCompleto());
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_EMAIL, participante.getEmail());
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_CPF, participante.getCpf());

        String where = Trabalho3Contract.Participante._ID + " = ?";
        String[] args = {String.valueOf(participante.getId())};
        db.update(Trabalho3Contract.Participante.TABLE_NAME, values, where, args);

        return participante;
    }
}
