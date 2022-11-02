package com.example.pokeapi;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokemonsViewModel extends AndroidViewModel {

    private final Application app;
    private MutableLiveData<List<Pokemon>> pokemons ;

    public PokemonsViewModel(@NonNull Application application) {
        super(application);
        this.app = application;
    }

    public MutableLiveData<List<Pokemon>> getPokemons(){
        if(pokemons == null){
            pokemons = new MutableLiveData<>();
        }
        return pokemons;
    }

    public void refresh() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());
        String tipo = preferences.getString("tipo", "");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            Pokeapi api = new Pokeapi();
            ArrayList<Pokemon> pokemons = api.getPokemons();

            this.pokemons.postValue(pokemons);
            });
    }
}
