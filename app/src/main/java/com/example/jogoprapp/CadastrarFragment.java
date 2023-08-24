package com.example.jogoprapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CadastrarFragment extends Fragment {

    Button mbotaoJogar;

    Button mbotaoCadastrar;

    EditText mEditTextPerg;

    EditText mEditTextResp;



    public CadastrarFragment() {
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
        return inflater.inflate(R.layout.fragment_cadastrar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mbotaoCadastrar = getActivity().findViewById(R.id.botaoCadastrar);
        mEditTextPerg = getActivity().findViewById(R.id.editPergunta);
        mEditTextResp = getActivity().findViewById(R.id.editResposta);
        mbotaoJogar = getActivity().findViewById(R.id.botaoJogar);

        mbotaoJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new JogarFragment()).commit();
            }
        });

        mbotaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pergunta = mEditTextPerg.getText().toString();
                String resposta = mEditTextResp.getText().toString();

                if (!pergunta.isEmpty() && !resposta.isEmpty()){
                    //cria um objeto do tipo questoes com os valores  digitados pelo usuario
                    Questoes questoes = new Questoes(pergunta, resposta);

                    //atraves da classe Dao Insere questoes no BD
                    BancoDeDados.getBancoDeDados(getActivity())
                            .meuDAO().inserirQuestoes(questoes);

                    mEditTextPerg.setText("");
                    mEditTextResp.setText("");

                    Toast.makeText(getActivity() , "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}