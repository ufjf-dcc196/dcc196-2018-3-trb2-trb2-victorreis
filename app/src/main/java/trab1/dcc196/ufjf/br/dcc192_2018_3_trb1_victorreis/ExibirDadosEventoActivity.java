package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ExibirDadosEventoActivity extends AppCompatActivity {

    private TextView txtTitulo;
    private TextView txtDia;
    private TextView txtHora;
    private TextView txtFacilitador;
    private TextView txtDescricaoTextual;

    private int eventoIndice;
    private Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_dados_evento);

        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        eventoIndice = bundleResult.getInt(MainActivity.EVENTO_INDICE);
        evento = Persistencia.getInstanceEventos().get(eventoIndice);

        txtTitulo = (TextView) findViewById(R.id.txt_titulo);
        txtTitulo.setText(evento.getTitulo());

        txtDia = (TextView) findViewById(R.id.txt_dia);
        txtDia.setText(evento.getDia());

        txtHora = (TextView) findViewById(R.id.txt_hora);
        txtHora.setText(evento.getHora());

        txtFacilitador = (TextView) findViewById(R.id.txt_facilitador);
        txtFacilitador.setText(evento.getFacilitador());

        txtDescricaoTextual = (TextView) findViewById(R.id.txt_descricao_textual);
        txtDescricaoTextual.setText(evento.getDescricaoTextual());
    }
}
