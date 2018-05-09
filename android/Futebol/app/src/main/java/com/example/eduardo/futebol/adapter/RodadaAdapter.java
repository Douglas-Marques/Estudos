package com.example.eduardo.futebol.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eduardo.futebol.R;
import com.example.eduardo.futebol.model.Jogo;
import com.example.eduardo.futebol.model.Status;

import java.util.ArrayList;

public class RodadaAdapter extends RecyclerView.Adapter<RodadaViewHolder> {

    private Context mContext;
    private ArrayList<Jogo> mJogos;

    public RodadaAdapter(Context context, ArrayList<Jogo> jogos) {
        mContext = context;
        mJogos = jogos;
    }

    @NonNull
    @Override
    public RodadaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RodadaViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_rodada, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RodadaViewHolder holder, int position) {
        final Jogo jogoAtual = mJogos.get(position);

        holder.mandanteSigla.setText(jogoAtual.getTimeMandante().getSigla());
        holder.mandanteLogo.setImageResource(
                mContext.getResources().getIdentifier(
                        jogoAtual.getTimeMandante().getEscudo(), "drawable", mContext.getPackageName()));

        holder.visitanteSigla.setText(jogoAtual.getTimeVisitante().getSigla());
        holder.visitanteLogo.setImageResource(
                mContext.getResources().getIdentifier(
                        jogoAtual.getTimeVisitante().getEscudo(), "drawable", mContext.getPackageName()));

        if (jogoAtual.getStatus().getStatus().equals(Status.JOGO_FINALIZADO)) {
            holder.mandanteGols.setVisibility(View.VISIBLE);
            holder.mandanteGols.setText(String.valueOf(jogoAtual.getGolsMandante()));

            holder.visitanteGols.setVisibility(View.VISIBLE);
            holder.visitanteGols.setText(String.valueOf(jogoAtual.getGolsVisitante()));
        } else if (jogoAtual.getStatus().getStatus().equals(Status.JOGO_AGENDADO)) {
            holder.mandanteGols.setVisibility(View.INVISIBLE);
            holder.visitanteGols.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mJogos != null ? mJogos.size() : 0;
    }
}

class RodadaViewHolder extends RecyclerView.ViewHolder{

    TextView mandanteSigla;
    ImageView mandanteLogo;
    TextView mandanteGols;

    TextView visitanteSigla;
    ImageView visitanteLogo;
    TextView visitanteGols;

    public RodadaViewHolder(View itemView) {
        super(itemView);

        mandanteSigla = itemView.findViewById(R.id.text_time_mandante);
        mandanteLogo = itemView.findViewById(R.id.image_time_mandante);
        mandanteGols = itemView.findViewById(R.id.text_gols_mandante);

        visitanteSigla = itemView.findViewById(R.id.text_time_visitante);
        visitanteLogo = itemView.findViewById(R.id.image_time_visitante);
        visitanteGols = itemView.findViewById(R.id.text_gols_visitante);
    }
}