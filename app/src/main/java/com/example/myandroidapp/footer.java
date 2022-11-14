package com.example.myandroidapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myandroidapp.databinding.FooterBinding;

public class footer extends AppCompatActivity {
  FooterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.footer);
        binding= FooterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //whenever the app opens for the first time the home fragment should be shown on the screen
        Intent intent = new Intent(footer.this,Otheractivity.class);
        startActivity(intent);
        binding.bottomNavigationView.setOnItemReselectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    Intent i = new Intent(footer.this, Otheractivity.class);
                    startActivity(i);
                    break;
                case R.id.messages:


                    break;
                case R.id.settigns:

                    break;
                case R.id.profile:

                    break;
                case R.id.favoris:

                    break;
            }


        });

    }
    //methode that will replace our frame layout in main.xml with the fragments
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

}
