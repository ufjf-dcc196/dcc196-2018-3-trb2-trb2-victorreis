package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        btnCadastrarEvento = (Button) findViewById(R.id.btn_cadastrar_participante);
        btnCadastrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
