package com.example.jogoprapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Questoes.class}, version = 1)
public abstract class BancoDeDados extends RoomDatabase {

    //Através deste metodo é possivel recuperar o objeto DAO

    public abstract MeuDAO meuDAO();
    //instancia unica para o BD

    private static BancoDeDados INSTANCE;

    //modelo Singleton

    public static BancoDeDados getBancoDeDados(final Context context){
        if (INSTANCE == null){
            synchronized (BancoDeDados.class){
                if (INSTANCE == null){
                    //cria o BD
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BancoDeDados.class,"bd_questoes").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

}


