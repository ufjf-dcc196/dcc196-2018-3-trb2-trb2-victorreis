package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrarParticipante = (Button) findViewById(R.id.btn_cadastrar_participante);
        btnCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnCadastrarEvento = (Button) findViewById(R.id.btn_cadastrar_evento);
        btnCadastrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnListarEventos = (Button) findViewById(R.id.btn_listar_eventos);
        btnListarEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        rv = (RecyclerView) findViewById(R.id.main_recycle_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
