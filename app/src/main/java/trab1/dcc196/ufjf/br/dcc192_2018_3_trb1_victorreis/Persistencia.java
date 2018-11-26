package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
        ContentValues values;

        values = new ContentValues();
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO, "Victor Reis");
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_EMAIL, "v.crisostomo.reis@gmail.com");
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_CPF, "104.974.436-51");
        db.insert(Trabalho3Contract.Participante.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO, "Fulano de tal");
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_EMAIL, "fulano@gmail.com");
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_CPF, "999.999.999-99");
        db.insert(Trabalho3Contract.Participante.TABLE_NAME, null, values);

        values.put(Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO, "Ciclano Filho");
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_EMAIL, "ciclano@gmail.com");
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_CPF, "888.888.888-88");
        db.insert(Trabalho3Contract.Participante.TABLE_NAME, null, values);

        values.put(Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO, "Beltrano Júnior");
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_EMAIL, "beltrano@gmail.com");
        values.put(Trabalho3Contract.Participante.COLUMN_NAME_CPF, "777.777.777-77");
        db.insert(Trabalho3Contract.Participante.TABLE_NAME, null, values);
    }

    public static Persistencia getInstance(Context context){
        if (instance == null) {
            instance = new Persistencia(context);
        }
        return instance;
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
