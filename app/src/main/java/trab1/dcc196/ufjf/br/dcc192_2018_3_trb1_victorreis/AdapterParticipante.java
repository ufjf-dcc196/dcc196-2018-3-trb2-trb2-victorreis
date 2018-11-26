package trab1.dcc196.ufjf.br.dcc192_2018_3_trb1_victorreis;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterParticipante extends RecyclerView.Adapter<AdapterParticipante.ViewHolder> {
    private OnAdapterParticipanteClickListener listener;
    private Cursor cursor;

    public AdapterParticipante(Cursor c){
        cursor = c;
    }

    public void setCursor(Cursor c){
        cursor = c;
        notifyDataSetChanged();
    }

    public interface OnAdapterParticipanteClickListener {
        void OnAdapterParticipanteClick(View view, int position);
        void OnAdapterParticipanteClickLong(View view, int position);
    }

    public void setOnAdapterParticipanteClickListener(OnAdapterParticipanteClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterParticipante.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.participante_layout_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterParticipante.ViewHolder viewHolder, int i) {
        int columnIndexNomeCompleto = cursor.getColumnIndexOrThrow(Trabalho3Contract.Participante.COLUMN_NAME_NOME_COMPLETO);
        cursor.moveToPosition(i);
        viewHolder.txtNomeCompleto.setText(cursor.getString(columnIndexNomeCompleto));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtNomeCompleto;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNomeCompleto = itemView.findViewById(R.id.txt_nome_completo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int adapterPosition = getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            listener.OnAdapterParticipanteClick(v, adapterPosition);
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
                            listener.OnAdapterParticipanteClickLong(v, adapterPosition);
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
                    listener.OnAdapterParticipanteClick(v, adapterPosition);
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (listener != null) {
                int adapterPosition = getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.OnAdapterParticipanteClickLong(v, adapterPosition);
                }
            }
            return false;
        }
    }
}
