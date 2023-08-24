package com.example.jogoprapp;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MeuDAO {

    @Insert
    long inserirQuestoes(Questoes questoes);

    @Query("SELECT * FROM Questoes")
    List<Questoes> pesquisarTodasQuestoes();

    @Query("DELETE FROM Questoes")
    void apagarTabela();
}
