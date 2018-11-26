package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.content.Intent;
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

    private static final int REQUEST_EDITAR_PARTICIPANTE = 1;

    public static final String PARTICIPANTE_NOME_COMPLETO = "PARTICIPANTE_NOME_COMPLETO";
    public static final String PARTICIPANTE_EMAIL = "PARTICIPANTE_EMAIL";
    public static final String PARTICIPANTE_CPF = "PARTICIPANTE_CPF";

    private int participanteIndice;
    private Participante participante;
    private List<Evento> eventosRestantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_dados_participante);

        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        participanteIndice = bundleResult.getInt(MainActivity.PARTICIPANTE_INDICE);
        //participante = Persistencia.getInstanceParticipantes().get(participanteIndice);
        participante = new Participante();

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
                startActivityForResult(intent, REQUEST_EDITAR_PARTICIPANTE);
            }
        });

        rvParticipandoDosEventos = (RecyclerView) findViewById(R.id.rv_participando_dos_eventos);
        rvParticipandoDosEventos.setLayoutManager(new LinearLayoutManager(this));
        adapterParticipandoDosEventos = new AdapterEvento(participante.getEventos());
        rvParticipandoDosEventos.setAdapter(adapterParticipandoDosEventos);
        adapterParticipandoDosEventos.setOnAdapterEventoClickListener(new AdapterEvento.OnAdapterEventoClickListener() {
            @Override
            public void OnAdapterEventoClick(View view, int position) {

            }

            @Override
            public void OnAdapterEventoClickLong(View view, int position) {
                Evento evento = participante.getEventos().get(position);
                //Persistencia.getInstanceParticipantes().get(participanteIndice).getEventos().remove(evento);
                eventosRestantes.add(evento);
                adapterParticipandoDosEventos.notifyDataSetChanged();
                adapterEventosRestantes.notifyDataSetChanged();

                Toast t = Toast.makeText(getApplicationContext(), "Desinscrito do evento.", Toast.LENGTH_LONG);
                t.show();
            }
        });

        rvEventosRestantes = (RecyclerView) findViewById(R.id.rv_eventos_restantes);
        rvEventosRestantes.setLayoutManager(new LinearLayoutManager(this));
        eventosRestantes = new ArrayList<>();
        for (Evento evento : Persistencia.getInstance(getApplicationContext()).selectAllEventos()) {
            if (!participante.getEventos().contains(evento)) {
                eventosRestantes.add(evento);
            }
        }
        adapterEventosRestantes = new AdapterEvento(eventosRestantes);
        rvEventosRestantes.setAdapter(adapterEventosRestantes);
        adapterEventosRestantes.setOnAdapterEventoClickListener(new AdapterEvento.OnAdapterEventoClickListener() {
            @Override
            public void OnAdapterEventoClick(View view, int position) {

            }

            @Override
            public void OnAdapterEventoClickLong(View view, int position) {
                Evento evento = eventosRestantes.get(position);
                eventosRestantes.remove(evento);
                //Persistencia.getInstanceParticipantes().get(participanteIndice).getEventos().add(evento);
                adapterParticipandoDosEventos.notifyDataSetChanged();
                adapterEventosRestantes.notifyDataSetChanged();

                Toast t = Toast.makeText(getApplicationContext(), "Inscrito no evento.", Toast.LENGTH_LONG);
                t.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ExibirDadosParticipanteActivity.REQUEST_EDITAR_PARTICIPANTE) {
            Bundle bndResultado = data.getExtras();

            String nomeCompleto = bndResultado.getString(ExibirDadosParticipanteActivity.PARTICIPANTE_NOME_COMPLETO);
            String email = bndResultado.getString(ExibirDadosParticipanteActivity.PARTICIPANTE_EMAIL);
            String cpf = bndResultado.getString(ExibirDadosParticipanteActivity.PARTICIPANTE_CPF);

            participante.setNomeCompleto(nomeCompleto);
            participante.setEmail(email);
            participante.setCpf(cpf);

            //Persistencia.getInstanceParticipantes().get(participanteIndice).setNomeCompleto(nomeCompleto);
            //Persistencia.getInstanceParticipantes().get(participanteIndice).setEmail(email);
            //Persistencia.getInstanceParticipantes().get(participanteIndice).setCpf(cpf);

            txtNomeCompleto.setText(nomeCompleto);
            txtEmail.setText(email);
            txtCPF.setText(cpf);
        }
    }
}
