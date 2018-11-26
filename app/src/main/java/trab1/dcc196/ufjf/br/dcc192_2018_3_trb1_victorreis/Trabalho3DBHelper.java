package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Trabalho3DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Trabalho3.db";

    public Trabalho3DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Trabalho3Contract.SQL_CREATE_PARTICIPANTE);
        db.execSQL(Trabalho3Contract.SQL_CREATE_EVENTO);
        db.execSQL(Trabalho3Contract.SQL_CREATE_PARTICIPANTE_EVENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Trabalho3Contract.SQL_DROP_PARTICIPANTE);
        db.execSQL(Trabalho3Contract.SQL_DROP_EVENTO);
        db.execSQL(Trabalho3Contract.SQL_DROP_PARTICIPANTE_EVENTO);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
