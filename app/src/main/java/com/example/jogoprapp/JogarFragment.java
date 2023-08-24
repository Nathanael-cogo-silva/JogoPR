package com.example.jogoprapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;


public class JogarFragment extends Fragment {

    Button mbotaoCadastarPr;

    Button mbotaoPular;

    Button mbotaoResposta;

    TextView mtextViewPergunta;

    TextView mTextViewResposta;

    List<Questoes> mListQuestoes;


    public JogarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jogar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mbotaoPular = getActivity().findViewById(R.id.botaoPular);
        mbotaoResposta= getActivity().findViewById(R.id.botaoResposta);
        mtextViewPergunta= getActivity().findViewById(R.id.pergunta);
        mTextViewResposta=getActivity().findViewById(R.id.resposta);

        //recupera todas as questoes cadastradas no banco de dados
        mListQuestoes = BancoDeDados.getBancoDeDados(getActivity())
                .meuDAO().pesquisarTodasQuestoes();

        proximaQuestão();


        mbotaoCadastarPr = getActivity().findViewById(R.id.botaoCadastrarPR);

        mbotaoCadastarPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new CadastrarFragment()).commit();
            }
        });

        mbotaoPular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { proximaQuestão(); }
        });

        mbotaoResposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewResposta.setVisibility(View.VISIBLE);
            }
        });

    }

    private void proximaQuestão() {
        if (!mListQuestoes.isEmpty()){
            int totalDeQuestoes = mListQuestoes.size();
            int indexAleatorio = new Random().nextInt(totalDeQuestoes);
            Questoes questoes = mListQuestoes.get(indexAleatorio);
            mtextViewPergunta.setText(questoes.getPergunta());
            mTextViewResposta.setText(questoes.getResposta());
            mTextViewResposta.setVisibility(View.GONE);
        }
    }
}