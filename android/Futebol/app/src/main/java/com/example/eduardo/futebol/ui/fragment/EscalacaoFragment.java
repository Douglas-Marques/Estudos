package com.example.eduardo.futebol.ui.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eduardo.futebol.R;
import com.example.eduardo.futebol.adapter.RodadaAdapter;
import com.example.eduardo.futebol.dao.TimesDao;
import com.example.eduardo.futebol.model.Jogo;

import java.util.ArrayList;

public class EscalacaoFragment extends Fragment {

    private Context mContext;
    private RecyclerView mRecyclerRodadas;
    private RodadaAdapter mRodadasAdapter;
    private ArrayList<Jogo> mJogos = new ArrayList<>();

    public static EscalacaoFragment newInstance() {
        EscalacaoFragment fragment = new EscalacaoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_escalacao, container, false);
        mRodadasAdapter = new RodadaAdapter(mContext, mJogos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        mRecyclerRodadas = view.findViewById(R.id.rodadas);
        mRecyclerRodadas.setLayoutManager(layoutManager);
        mRecyclerRodadas.setHasFixedSize(true);
        mRecyclerRodadas.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRecyclerRodadas.setAdapter(mRodadasAdapter);

        carregarJogos();
        return view;
    }

    private void carregarJogos() {
        //mJogos.addAll(TimesDao.carregarJogos());
        mRodadasAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }
}
