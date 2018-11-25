package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.provider.BaseColumns;

public class Trabalho3Contract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";

    public Trabalho3Contract() {
    }

    public static final class Evento implements BaseColumns {
        public static final String TABLE_NAME = "evento";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_DIA = "dia";
        public static final String COLUMN_NAME_HORA = "hora";
        public static final String COLUMN_NAME_FACILITADOR = "facilitador";
        public static final String COLUMN_NAME_DESCRICAO_TEXTUAL = "descricao_textual";
    }

    public static final class Participante implements BaseColumns {
        public static final String TABLE_NAME = "participante";
        public static final String COLUMN_NAME_NOME_COMPLETO = "nome_completo";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_CPF = "cpf";
    }

    public static final class ParticipanteEvento implements BaseColumns {
        public static final String TABLE_NAME = "participante_evento";
        public static final String COLUMN_NAME_PARTICIPANTE_ID = "participante_id";
        public static final String COLUMN_NAME_EVENTO_ID = "evento_id";
    }

    public static final String SQL_CREATE_PARTICIPANTE = "CREATE TABLE " + Participante.TABLE_NAME + " (" +
            Participante._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + SEP +
            Participante.COLUMN_NAME_NOME_COMPLETO + TEXT_TYPE + SEP +
            Participante.COLUMN_NAME_EMAIL + TEXT_TYPE + SEP +
            Participante.COLUMN_NAME_CPF + TEXT_TYPE + ")";
    public static final String SQL_DROP_PARTICIPANTE = "DROP TABLE IF EXISTS" + Participante.TABLE_NAME;

    public static final String SQL_CREATE_EVENTO = "CREATE TABLE " + Evento.TABLE_NAME + " (" +
            Evento._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + SEP +
            Evento.COLUMN_NAME_TITULO + TEXT_TYPE + SEP +
            Evento.COLUMN_NAME_DIA + TEXT_TYPE + SEP +
            Evento.COLUMN_NAME_HORA + TEXT_TYPE + SEP +
            Evento.COLUMN_NAME_FACILITADOR + TEXT_TYPE + SEP +
            Evento.COLUMN_NAME_DESCRICAO_TEXTUAL + TEXT_TYPE + ")";
    public static final String SQL_DROP_EVENTO = "DROP TABLE IF EXISTS" + Evento.TABLE_NAME;

    public static final String SQL_CREATE_PARTICIPANTE_EVENTO = "CREATE TABLE " + ParticipanteEvento.TABLE_NAME + " (" +
            ParticipanteEvento._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + SEP +
            ParticipanteEvento.COLUMN_NAME_PARTICIPANTE_ID + INT_TYPE + SEP +
            ParticipanteEvento.COLUMN_NAME_EVENTO_ID + INT_TYPE + ")";
    public static final String SQL_DROP_PARTICIPANTE_EVENTO = "DROP TABLE IF EXISTS" + ParticipanteEvento.TABLE_NAME;
}
