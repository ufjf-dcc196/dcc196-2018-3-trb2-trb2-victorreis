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
    private RecyclerView rv;
    private Button btnCadastrarParticipante;
    private Button btnCadastrarEvento;
    private Button btnListarEventos;

    private static final int REQUEST_CADASTRO_PARTICIPANTE = 1;
    private static final int REQUEST_CADASTRO_EVENTO = 2;
    private static final int REQUEST_LISTAR_EVENTOS = 3;

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
                startActivityForResult(intent, REQUEST_LISTAR_EVENTOS);
            }
        });


        rv = (RecyclerView) findViewById(R.id.main_recycle_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MainActivity.REQUEST_CADASTRO_PARTICIPANTE) {

        } else if (requestCode == MainActivity.REQUEST_CADASTRO_EVENTO) {

        } else if (requestCode == MainActivity.REQUEST_LISTAR_EVENTOS) {
            
        }
    }
}
