package com.example.filmapi.ui.activityes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.example.filmapi.R;
import com.example.filmapi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.basket.setOnClickListener(v ->{NavController navController = Navigation.findNavController(this,R.id.filmListFragment);
        navController.navigate(R.id.roomFragment);
        });


    }
}