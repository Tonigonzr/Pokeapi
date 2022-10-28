package com.example.pokeapi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends ArrayAdapter<Pokemon> {


    public Adapter(@NonNull Context context, int lv_pokemon_row, int resource, List<Pokemon> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Pokemon pokemon = getItem(position);
        Log.w("XXXX", pokemon.toString());

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_pokemon_row, parent, false);
        }
        TextView txtPokemon = convertView.findViewById(R.id.txtListName);
        ImageView ivPosterImage = convertView.findViewById(R.id.imgPokemon);

        txtPokemon.setText(pokemon.getName());

        Glide.with(getContext()).load(pokemon.getImage()).into(ivPosterImage);
        return  convertView;
    }
}
