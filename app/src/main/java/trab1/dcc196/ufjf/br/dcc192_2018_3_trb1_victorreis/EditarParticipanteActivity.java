package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditarParticipanteActivity extends AppCompatActivity {

    private EditText edtNomeCompleto;
    private EditText edtEmail;
    private EditText edtCPF;

    private Button btnEditarParticipante;

    private int participanteIndice;
    private Participante participante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_participante);

        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        participanteIndice = bundleResult.getInt(MainActivity.PARTICIPANTE_INDICE);
        participante = Persistencia.getInstance(getApplicationContext()).selectParticipanteById(participanteIndice);

        edtNomeCompleto = (EditText) findViewById(R.id.edt_nome_completo);
        edtNomeCompleto.setText(participante.getNomeCompleto());

        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtEmail.setText(participante.getEmail());

        edtCPF = (EditText) findViewById(R.id.edt_cpf);
        edtCPF.setText(participante.getCpf());

        btnEditarParticipante = (Button) findViewById(R.id.btn_editar_participante);
        btnEditarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNomeCompleto.getText() == null || "".equals(edtNomeCompleto.getText().toString()) ||
                        edtEmail.getText() == null || "".equals(edtEmail.getText().toString()) ||
                        edtCPF.getText() == null || "".equals(edtCPF.getText().toString())
                        ) {
                    Toast t = Toast.makeText(getApplicationContext(), "ERRO: Preencher todos os campos.", Toast.LENGTH_LONG);
                    t.show();
                    return;
                }

                participante.setNomeCompleto(edtNomeCompleto.getText().toString())
                        .setEmail(edtEmail.getText().toString())
                        .setCpf(edtCPF.getText().toString());

                Persistencia.getInstance(getApplicationContext()).updateParticipante(participante);

                finish();
            }
        });
    }
}
