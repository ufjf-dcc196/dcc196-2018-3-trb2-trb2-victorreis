package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroEventoActivity extends AppCompatActivity {

    private EditText edtTitulo;
    private EditText edtDia;
    private EditText edtHora;
    private EditText edtFacilitador;
    private EditText edtDescricaoTextual;
    private Button btnCadastrarEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        edtTitulo = (EditText) findViewById(R.id.edt_titulo);
        edtDia = (EditText) findViewById(R.id.edt_dia);
        edtHora = (EditText) findViewById(R.id.edt_hora);
        edtFacilitador = (EditText) findViewById(R.id.edt_facilitador);
        edtDescricaoTextual = (EditText) findViewById(R.id.edt_descricao_textual);

        btnCadastrarEvento = (Button) findViewById(R.id.btn_cadastrar_evento);
        btnCadastrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtTitulo.getText() == null || "".equals(edtTitulo.getText().toString()) ||
                        edtDia.getText() == null || "".equals(edtDia.getText().toString()) ||
                        edtHora.getText() == null || "".equals(edtHora.getText().toString()) ||
                        edtFacilitador.getText() == null || "".equals(edtFacilitador.getText().toString()) ||
                        edtDescricaoTextual.getText() == null || "".equals(edtDescricaoTextual.getText().toString())
                        ) {
                    Toast t = Toast.makeText(getApplicationContext(), "ERRO: Preencher todos os campos.", Toast.LENGTH_LONG);
                    t.show();
                    return;
                }

                Evento evento = new Evento();
                evento.setTitulo(edtTitulo.getText().toString())
                        .setDia(edtDia.getText().toString())
                        .setHora(edtHora.getText().toString())
                        .setFacilitador(edtFacilitador.getText().toString())
                        .setDescricaoTextual(edtDescricaoTextual.getText().toString());
                Persistencia.getInstance(getApplicationContext()).insertEvento(evento);
                finish();
            }
        });
    }
}
