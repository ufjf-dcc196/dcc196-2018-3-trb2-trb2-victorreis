package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroParticipanteActivity extends AppCompatActivity {

    private EditText edtNomeCompleto;
    private EditText edtEmail;
    private EditText edtCPF;
    private Button btnCadastrarParticipante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);

        btnCadastrarParticipante = (Button) findViewById(R.id.btn_cadastrar_participante);
        btnCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        edtNomeCompleto = (EditText) findViewById(R.id.edt_nome_completo);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtCPF = (EditText) findViewById(R.id.edt_cpf);
    }
}
