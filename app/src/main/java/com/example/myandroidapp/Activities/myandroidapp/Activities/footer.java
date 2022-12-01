package com.example.myandroidapp.Activities.myandroidapp.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myandroidapp.Activities.myandroidapp.Models.Settings;
import com.example.myandroidapp.R;
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
        Intent intent = new Intent(footer.this, EmployeelistActivity.class);
        startActivity(intent);
        binding.bottomNavigationView.setOnItemReselectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    Intent i1 = new Intent(footer.this, HomeActivity.class);
                    startActivity(i1);
                    break;
                case R.id.messages:

                    break;
                case R.id.settigns:
                    Intent i3 = new Intent(footer.this, Settings.class);
                    startActivity(i3);
                    break;
                case R.id.profile:
                    Intent i4 = new Intent(footer.this, CurrentProfile.class);
                    startActivity(i4);
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
