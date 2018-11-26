package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExibirDadosParticipanteActivity extends AppCompatActivity {

    private TextView txtNomeCompleto;
    private TextView txtEmail;
    private TextView txtCPF;

    private Button btnEditarParticipante;

    private RecyclerView rvParticipandoDosEventos;
    private RecyclerView rvEventosRestantes;
    private AdapterEvento adapterParticipandoDosEventos;
    private AdapterEvento adapterEventosRestantes;

    private int participanteIndice;
    private Participante participante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_dados_participante);

        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        participanteIndice = bundleResult.getInt(MainActivity.PARTICIPANTE_INDICE);
        participante = Persistencia.getInstance(getApplicationContext()).selectParticipanteById(participanteIndice);

        txtNomeCompleto = (TextView) findViewById(R.id.txt_nome_completo);
        txtNomeCompleto.setText(participante.getNomeCompleto());

        txtEmail = (TextView) findViewById(R.id.txt_email);
        txtEmail.setText(participante.getEmail());

        txtCPF = (TextView) findViewById(R.id.txt_cpf);
        txtCPF.setText(participante.getCpf());

        btnEditarParticipante = (Button) findViewById(R.id.btn_editar_participante);
        btnEditarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExibirDadosParticipanteActivity.this, EditarParticipanteActivity.class);
                intent.putExtra(MainActivity.PARTICIPANTE_INDICE, participanteIndice);
                startActivity(intent);
            }
        });

        rvParticipandoDosEventos = (RecyclerView) findViewById(R.id.rv_participando_dos_eventos);
        rvParticipandoDosEventos.setLayoutManager(new LinearLayoutManager(this));
        adapterParticipandoDosEventos = new AdapterEvento(Persistencia.getInstance(getApplicationContext()).selectEventosFromParticipanteCursor(participante.getId()));
        rvParticipandoDosEventos.setAdapter(adapterParticipandoDosEventos);
        adapterParticipandoDosEventos.setOnAdapterEventoClickListener(new AdapterEvento.OnAdapterEventoClickListener() {
            @Override
            public void OnAdapterEventoClick(View view, int position) {

            }

            @Override
            public void OnAdapterEventoClickLong(View view, int position) {
                Cursor cursor = Persistencia.getInstance(getApplicationContext()).selectEventosFromParticipanteCursor(participante.getId());
                ArrayList<Evento> eventosDoParticipante = Persistencia.getInstance(getApplicationContext()).transformCursorInArrayListOfEventos(cursor);
                Evento evento = eventosDoParticipante.get(position);

                Persistencia.getInstance(getApplicationContext()).deleteParticipanteEvento(participante.getId(), evento);
                adapterParticipandoDosEventos.setCursor(Persistencia.getInstance(getApplicationContext()).selectEventosFromParticipanteCursor(participante.getId()));
                adapterEventosRestantes.setCursor(Persistencia.getInstance(getApplicationContext()).selectEventosRestantesFromParticipanteCursor(participante.getId()));

                Toast t = Toast.makeText(getApplicationContext(), "Desinscrito do evento.", Toast.LENGTH_LONG);
                t.show();
            }
        });

        rvEventosRestantes = (RecyclerView) findViewById(R.id.rv_eventos_restantes);
        rvEventosRestantes.setLayoutManager(new LinearLayoutManager(this));
        adapterEventosRestantes = new AdapterEvento(Persistencia.getInstance(getApplicationContext()).selectEventosRestantesFromParticipanteCursor(participante.getId()));
        rvEventosRestantes.setAdapter(adapterEventosRestantes);
        adapterEventosRestantes.setOnAdapterEventoClickListener(new AdapterEvento.OnAdapterEventoClickListener() {
            @Override
            public void OnAdapterEventoClick(View view, int position) {

            }

            @Override
            public void OnAdapterEventoClickLong(View view, int position) {
                Cursor cursor = Persistencia.getInstance(getApplicationContext()).selectEventosRestantesFromParticipanteCursor(participante.getId());
                ArrayList<Evento> eventosRestantes = Persistencia.getInstance(getApplicationContext()).transformCursorInArrayListOfEventos(cursor);
                Evento evento = eventosRestantes.get(position);

                Persistencia.getInstance(getApplicationContext()).insertParticipanteEvento(participante.getId(), evento);
                adapterParticipandoDosEventos.setCursor(Persistencia.getInstance(getApplicationContext()).selectEventosFromParticipanteCursor(participante.getId()));
                adapterEventosRestantes.setCursor(Persistencia.getInstance(getApplicationContext()).selectEventosRestantesFromParticipanteCursor(participante.getId()));

                Toast t = Toast.makeText(getApplicationContext(), "Inscrito no evento.", Toast.LENGTH_LONG);
                t.show();
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
        participante = Persistencia.getInstance(getApplicationContext()).selectParticipanteById(participanteIndice);
        txtNomeCompleto.setText(participante.getNomeCompleto());
        txtEmail.setText(participante.getEmail());
        txtCPF.setText(participante.getCpf());
    }
}
