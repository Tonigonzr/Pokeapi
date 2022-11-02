package com.example.pokeapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.pokeapi.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       Bundle datos = getArguments();

       if(datos != null){
           Pokemon pokemon = (Pokemon) datos.getSerializable("item");

           binding.txtnombre.setText(pokemon.getName());
           binding.textviewSecond.setText(String.valueOf(pokemon.getWeight()));
           binding.txtaltura.setText(String.valueOf(pokemon.getHeight()));
           Glide.with(getContext()).load(pokemon.getImage()).into(binding.imageView);

       }
        }

        public void Refresh(){

        }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}