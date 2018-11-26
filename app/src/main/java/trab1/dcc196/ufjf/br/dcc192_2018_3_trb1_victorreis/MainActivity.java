package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvTodosParticipantes;
    private RecyclerView rvTodosEventos;

    private Button btnCadastrarParticipante;
    private Button btnCadastrarEvento;
    private Button btnInserirResetarDadosIniciais;

    private AdapterParticipante adapterParticipante;
    private AdapterEvento adapterEvento;

    public static final String PARTICIPANTE_INDICE = "PARTICIPANTE_INDICE";
    public static final String EVENTO_INDICE = "EVENTO_INDICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrarParticipante = (Button) findViewById(R.id.btn_cadastrar_participante);
        btnCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroParticipanteActivity.class);
                startActivity(intent);
            }
        });

        btnCadastrarEvento = (Button) findViewById(R.id.btn_cadastrar_evento);
        btnCadastrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                startActivity(intent);
            }
        });

        btnInserirResetarDadosIniciais = (Button) findViewById(R.id.btn_inserir_resetar_dados_iniciais);
        btnInserirResetarDadosIniciais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Persistencia.getInstance(getApplicationContext()).inserirResetarDadosIniciais();
                adapterParticipante.setCursor(Persistencia.getInstance(getApplicationContext()).selectAllParticipantesCursor());
                adapterEvento.setCursor(Persistencia.getInstance(getApplicationContext()).selectAllEventosCursor());
            }
        });

        rvTodosParticipantes = (RecyclerView) findViewById(R.id.rv_todos_participantes);
        rvTodosParticipantes.setLayoutManager(new LinearLayoutManager(this));
        adapterParticipante = new AdapterParticipante(Persistencia.getInstance(getApplicationContext()).selectAllParticipantesCursor());
        rvTodosParticipantes.setAdapter(adapterParticipante);
        adapterParticipante.setOnAdapterParticipanteClickListener(new AdapterParticipante.OnAdapterParticipanteClickListener() {
            @Override
            public void OnAdapterParticipanteClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, ExibirDadosParticipanteActivity.class);

                Cursor cursor = Persistencia.getInstance(getApplicationContext()).selectAllParticipantesCursor();
                ArrayList<Participante> participantes = Persistencia.getInstance(getApplicationContext()).transformCursorInArrayListOfParticipantes(cursor);
                Participante participante = participantes.get(position);

                intent.putExtra(MainActivity.PARTICIPANTE_INDICE, participante.getId());
                startActivity(intent);
            }

            @Override
            public void OnAdapterParticipanteClickLong(View view, int position) {
                TextView txtNomeCompleto = (TextView) view.findViewById(R.id.txt_nome_completo);
                Persistencia.getInstance(getApplicationContext()).deleteParticipante(txtNomeCompleto.getText().toString());

                adapterParticipante.setCursor(Persistencia.getInstance(getApplicationContext()).selectAllParticipantesCursor());
            }
        });

        rvTodosEventos = (RecyclerView) findViewById(R.id.rv_todos_eventos);
        rvTodosEventos.setLayoutManager(new LinearLayoutManager(this));
        adapterEvento = new AdapterEvento(Persistencia.getInstance(getApplicationContext()).selectAllEventosCursor());
        rvTodosEventos.setAdapter(adapterEvento);
        adapterEvento.setOnAdapterEventoClickListener(new AdapterEvento.OnAdapterEventoClickListener() {
            @Override
            public void OnAdapterEventoClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, ExibirDadosEventoActivity.class);

                Cursor cursor = Persistencia.getInstance(getApplicationContext()).selectAllEventosCursor();
                ArrayList<Evento> eventos = Persistencia.getInstance(getApplicationContext()).transformCursorInArrayListOfEventos(cursor);
                Evento evento = eventos.get(position);

                intent.putExtra(MainActivity.EVENTO_INDICE, evento.getId());
                startActivity(intent);
            }

            @Override
            public void OnAdapterEventoClickLong(View view, int position) {
                TextView txtTitulo = (TextView) view.findViewById(R.id.txt_titulo);
                Persistencia.getInstance(getApplicationContext()).deleteEvento(txtTitulo.getText().toString());

                adapterEvento.setCursor(Persistencia.getInstance(getApplicationContext()).selectAllEventosCursor());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterParticipante.setCursor(Persistencia.getInstance(getApplicationContext()).selectAllParticipantesCursor());
        adapterEvento.setCursor(Persistencia.getInstance(getApplicationContext()).selectAllEventosCursor());
    }
}
