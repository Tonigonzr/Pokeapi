package com.example.pokeapi;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pokeapi {
    ArrayList<Pokemon> getPokemons() {
        String url = "https://pokeapi.co/api/v2/pokemon/";
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        Uri builtUri = Uri.parse(url)
                .buildUpon()
                .appendPath("pokemon")
                .appendPath("ability")
                .appendQueryParameter("region", "")
                .appendQueryParameter("api_key", "")
                .build();

        String url1 = builtUri.toString();
        try {
            String result = HttpUtils.get(url);
            Log.d("Debug", url);
            JSONObject jsonResult = new JSONObject(result);
            JSONArray results = jsonResult.getJSONArray("results");
            Log.e("Fallo", "Fallo");

            for (int i = 0; i < results.length(); i++) {
                JSONObject pokemonJson = results.getJSONObject(i);
                Pokemon pokemon = new Pokemon();

                pokemon.setName(pokemonJson.getString("name"));
                pokemon.setDetailsUrl(pokemonJson.getString("url"));

                String resultDetails = HttpUtils.get(pokemon.getDetailsUrl());
                JSONObject jsonDetails = new JSONObject(resultDetails);
                JSONObject sprites = jsonDetails.getJSONObject("sprites");
                String spriteDefault = sprites.getString("front_default");

                pokemon.setWeight(jsonDetails.getInt("height"));
                pokemon.setImage(spriteDefault);
                pokemons.add(pokemon);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return pokemons;
    }


}
