package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AdapterParticipante extends RecyclerView.Adapter<AdapterParticipante.ViewHolder> {
    private List<Participante> participantes = new ArrayList<>();
    private OnAdapterParticipanteClickListener listener;

    public AdapterParticipante(ArrayList<Participante> participantes) {
        this.participantes = participantes;

    }

    public interface OnAdapterParticipanteClickListener {
        void OnAdapterParticipanteClick(View view, int position);
        void OnAdapterParticipanteClickLong(View view, int position);
    }

    public void setOnAdapterParticipanteClickListener(OnAdapterParticipanteClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterParticipante.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterParticipante.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
