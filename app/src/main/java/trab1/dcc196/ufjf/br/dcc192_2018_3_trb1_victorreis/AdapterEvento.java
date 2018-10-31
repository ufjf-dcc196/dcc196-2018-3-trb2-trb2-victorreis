package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterEvento extends RecyclerView.Adapter<AdapterEvento.ViewHolder> {
    private List<Evento> eventos = new ArrayList<>();
    private OnAdapterEventoClickListener listener;

    public AdapterEvento(List<Evento> eventos) {
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
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.evento_layout_item, viewGroup, false);
        AdapterEvento.ViewHolder viewHolder = new AdapterEvento.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEvento.ViewHolder viewHolder, int i) {
        viewHolder.txtTitulo.setText(eventos.get(i).getTitulo());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtTitulo;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTitulo = itemView.findViewById(R.id.txt_nome_completo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int adapterPosition = getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            listener.OnAdapterEventoClick(v, adapterPosition);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        int adapterPosition = getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            listener.OnAdapterEventoClickLong(v, adapterPosition);
                        }
                    }
                    return false;
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                int adapterPosition = getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.OnAdapterEventoClick(v, adapterPosition);
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (listener != null) {
                int adapterPosition = getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.OnAdapterEventoClickLong(v, adapterPosition);
                }
            }
            return false;
        }
    }
}
