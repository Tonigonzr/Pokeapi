package com.example.pokeapi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import com.example.pokeapi.databinding.FragmentFirstBinding;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private Adapter adapter;
    ArrayList<Pokemon> items;
    ArrayList<Pokemon> Pokemons;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items = new ArrayList<Pokemon>();
        Pokeapi api = new Pokeapi();
        adapter = new Adapter(
            getContext(),
            R.layout.lv_pokemon_row,
            R.id.txtListName,
            items


        );

        binding.lvPokemons.setAdapter(adapter);
        binding.lvPokemons.setOnItemClickListener(((adapterView, view1, i,l) ->{
            Pokemon item = (Pokemon)adapterView.getItemAtPosition(i);
            Bundle datos = new Bundle();
            datos.putSerializable("item",item);
            
                NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_SecondFragment,datos);
            }));



        PokemonsViewModel viewModel = new ViewModelProvider(getActivity()).get(PokemonsViewModel.class);
        viewModel.refresh();
        viewModel.getPokemons().observe(getActivity(), pokemons -> {
            adapter.clear();
            adapter.addAll(pokemons);
        });
        super.onViewCreated(view, savedInstanceState);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem Item){
        if(Item.getItemId() == R.id.Refresh){
        
        }
        return false;
    }

    public void refresh() {


    }



        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }



}