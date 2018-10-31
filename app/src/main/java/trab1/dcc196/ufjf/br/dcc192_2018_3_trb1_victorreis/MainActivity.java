package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnCadastrarParticipante;
    private Button btnCadastrarEvento;
    private Button btnListarEventos;

    private AdapterParticipante adapterParticipante;

    private static final int REQUEST_CADASTRO_PARTICIPANTE = 1;
    private static final int REQUEST_CADASTRO_EVENTO = 2;

    public static final String PARTICIPANTE_INDICE = "PARTICIPANTE_INDICE";

    public static final String PARTICIPANTE_NOME_COMPLETO = "PARTICIPANTE_NOME_COMPLETO";
    public static final String PARTICIPANTE_EMAIL = "PARTICIPANTE_EMAIL";
    public static final String PARTICIPANTE_CPF = "PARTICIPANTE_CPF";

    public static final String EVENTO_TITULO = "EVENTO_TITULO";
    public static final String EVENTO_DIA = "EVENTO_DIA";
    public static final String EVENTO_HORA = "EVENTO_HORA";
    public static final String EVENTO_FACILITADOR = "EVENTO_FACILITADOR";
    public static final String EVENTO_DESCRICAO_TEXTUAL = "EVENTO_DESCRICAO_TEXTUAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrarParticipante = (Button) findViewById(R.id.btn_cadastrar_participante);
        btnCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CadastroParticipanteActivity.class);
                startActivityForResult(intent, REQUEST_CADASTRO_PARTICIPANTE);
            }
        });

        btnCadastrarEvento = (Button) findViewById(R.id.btn_cadastrar_evento);
        btnCadastrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CadastroEventoActivity.class);
                startActivityForResult(intent, REQUEST_CADASTRO_EVENTO);
            }
        });

        btnListarEventos = (Button) findViewById(R.id.btn_listar_eventos);
        btnListarEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListarEventosActivity.class);
                startActivity(intent);
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.main_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterParticipante = new AdapterParticipante(Persistencia.participantes);
        recyclerView.setAdapter(adapterParticipante);

        adapterParticipante.setOnAdapterParticipanteClickListener(new AdapterParticipante.OnAdapterParticipanteClickListener() {
            @Override
            public void OnAdapterParticipanteClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, ExibirDadosParticipanteActivity.class);
                intent.putExtra(MainActivity.PARTICIPANTE_INDICE, position);
                startActivity(intent);
            }

            @Override
            public void OnAdapterParticipanteClickLong(View view, int position) {
                Persistencia.participantes.get(position).getEventos().clear();
                Persistencia.participantes.remove(position);
                adapterParticipante.notifyItemRemoved(position);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MainActivity.REQUEST_CADASTRO_PARTICIPANTE) {
            Bundle bndResultado = data.getExtras();

            String nomeCompleto = bndResultado.getString(MainActivity.PARTICIPANTE_NOME_COMPLETO);
            String email = bndResultado.getString(MainActivity.PARTICIPANTE_EMAIL);
            String cpf = bndResultado.getString(MainActivity.PARTICIPANTE_CPF);

            Participante participante = new Participante(nomeCompleto, email, cpf);
            Persistencia.participantes.add(participante);
            adapterParticipante.notifyDataSetChanged();

        } else if (requestCode == MainActivity.REQUEST_CADASTRO_EVENTO) {
            Bundle bndResultado = data.getExtras();

            String titulo = bndResultado.getString(MainActivity.EVENTO_TITULO);
            String dia = bndResultado.getString(MainActivity.EVENTO_DIA);
            String hora = bndResultado.getString(MainActivity.EVENTO_HORA);
            String facilitador = bndResultado.getString(MainActivity.EVENTO_FACILITADOR);
            String descricaoTextual = bndResultado.getString(MainActivity.EVENTO_DESCRICAO_TEXTUAL);

            Evento evento = new Evento(titulo, dia, hora, facilitador, descricaoTextual);
            Persistencia.eventos.add(evento);

        }
    }
}
