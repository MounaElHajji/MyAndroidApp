package com.example.myandroidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myandroidapp.Api.AccountApi;
import com.example.myandroidapp.Models.Account;
import com.example.myandroidapp.R;
import com.example.myandroidapp.Retrofit.RetrofitS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inscription extends AppCompatActivity {
    @BindView(R.id.fonction)
    Spinner fonction;

    @BindView(R.id.ville)
    Spinner ville;

    @BindView(R.id.service)
    Spinner service;

    @BindView(R.id.checkBox)
    CheckBox c;

    @BindView(R.id.pwd)
    EditText pwd;

    @BindView(R.id.btnRegister)
    Button btnRegister;

    @BindView(R.id.msg)
    TextView msg;

    @BindView(R.id.desc)
    EditText desc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        ButterKnife.bind(this);
        desc.setVisibility(View.GONE);
        msg.setVisibility(View.GONE);
        msg.setText("");
        List fcts= new ArrayList();
        List LisVille= new ArrayList();
        List Services= new ArrayList();
        LisVille.add("Ville");
        LisVille.add("Kénitra");
        LisVille.add("Rabat");
        fcts.add("Fonction");
        fcts.add("Employé");
        fcts.add("Client");
        Services.add("Service");
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                fcts
        );
        ArrayAdapter adapter1 = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                LisVille
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        fonction.setAdapter(adapter);
        ville.setAdapter(adapter1);
        RetrofitS retrofitS= new RetrofitS();
        AccountApi api=retrofitS.getRetrofit().create(AccountApi.class);
        api.listServices().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> lis=response.body();
                for (String s:
                    lis ) {
                    Services.add(s);
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });
        ;
        ArrayAdapter adapter2 = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                Services
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        service.setAdapter(adapter2);

    }
    @OnCheckedChanged(R.id.checkBox)
    public void onCheckedChanged() {
        if (c.isChecked()) {
            // show password
            pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            c.setText("Cacher le mot de passe");
        } else {
            // hide password
            pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            c.setText("Afficher le mot de passe");
        }
    }
    @OnItemSelected(R.id.fonction)
    public void click(Spinner s,int pos) {
        System.out.println(pos);
        if (pos == 0) {
            desc.setVisibility(View.GONE);
            msg.setVisibility(View.VISIBLE);
            msg.setText("Veuillez chosir une fonction");
        } else if (pos == 1) {
            msg.setText("");
            desc.setVisibility(View.VISIBLE);
        } else {
            desc.setVisibility(View.GONE);
        }
    }
    @OnClick(R.id.btnRegister)
    public void clickRegister(){
        if(fonction.getSelectedItemPosition()==0) {
            msg.setText("Veuillez chosir une fonction");
        }
    }
}