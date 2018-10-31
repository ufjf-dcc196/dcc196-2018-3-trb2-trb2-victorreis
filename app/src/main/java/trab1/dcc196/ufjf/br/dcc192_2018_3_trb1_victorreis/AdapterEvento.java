package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AdapterEvento extends RecyclerView.Adapter<AdapterEvento.ViewHolder> {
    private List<Evento> eventos = new ArrayList<>();
    private OnAdapterEventoClickListener listener;

    public AdapterEvento(ArrayList<Evento> eventos) {
        this.eventos = eventos;

    }

    public interface OnAdapterEventoClickListener {
        void OnAdapterEventoClick(View view, int position);
        void OnAdapterEventoClickLong(View view, int position);
    }

    public void setOnAdapterEventoClickListener(OnAdapterEventoClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterEvento.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEvento.ViewHolder viewHolder, int i) {

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
