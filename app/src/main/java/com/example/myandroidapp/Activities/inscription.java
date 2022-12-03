package com.example.myandroidapp.Activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myandroidapp.Models.Account;
import com.example.myandroidapp.Models.Person;
import com.example.myandroidapp.Models.Service;
import com.example.myandroidapp.R;
import com.example.myandroidapp.Api.AccountApi;
import com.example.myandroidapp.retrofit.RetrofitS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
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

    @BindView(R.id.prenom)
    EditText prenom;
    @BindView(R.id.nom)
    EditText nom;
    @BindView(R.id.mail)
    EditText mail;

    @BindView(R.id.cin)
    EditText cin;


    //Retrofit
    RetrofitS retrofitS= new RetrofitS();
    AccountApi api=retrofitS.getRetrofit().create(AccountApi.class);

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
        fonction.setAdapter(adapter);
        ville.setAdapter(adapter1);

        api.listServices().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Toast.makeText(inscription.this, "services successfully!", Toast.LENGTH_SHORT).show();
                List<String> lis=response.body();
                for (String s:
                        lis ) {
                    Services.add(s);
                    System.out.println(s);
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                Toast.makeText(inscription.this, "services failed!", Toast.LENGTH_SHORT).show();

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
        if (pos == 0) {
            desc.setVisibility(View.GONE);
            msg.setVisibility(View.VISIBLE);
            msg.setText("Veuillez chosir une fonction");
        } else if (pos == 1) {
            msg.setText("");
            desc.setVisibility(View.VISIBLE);
            service.setVisibility(View.VISIBLE);
        } else {
            desc.setVisibility(View.GONE);
            service.setVisibility(View.GONE);
        }
    }
    @OnItemSelected(R.id.ville)
    public void clickVille(Spinner s,int pos) {
        if (pos == 0) {

            desc.setVisibility(View.GONE);
            msg.setVisibility(View.VISIBLE);
            msg.setText("Veuillez chosir une ville");
        } else if (pos == 1) {
            msg.setText("");
            desc.setVisibility(View.VISIBLE);
        } else {
            msg.setText("");
            desc.setVisibility(View.GONE);
        }
    }
    @OnItemSelected(R.id.service)
    public void clickService(Spinner s,int pos) {
        if (pos == 0) {
            desc.setVisibility(View.GONE);
            msg.setVisibility(View.VISIBLE);
            msg.setText("Veuillez chosir un service");
        } else if (pos == 1) {
            msg.setText("");
            desc.setVisibility(View.VISIBLE);
        } else {
            msg.setText("");
            desc.setVisibility(View.GONE);
        }
    }
    @OnClick(R.id.btnRegister)
    public void clickRegister(){
        if(fonction.getSelectedItemPosition()==0 || ville.getSelectedItemPosition()==0 || service.getSelectedItemPosition()==0 ||
        mail.getText().equals("") || prenom.getText().equals("") || nom.getText().equals("")
        || cin.getText().equals("") || pwd.getText().equals("")) {
            msg.setText("Veuillez remplir tous les champs!");
        }else {
            Person person = new Person();
            Account account = new Account();
            Service service1 = new Service();
            person.setDescription(desc.getText().toString());
            person.setCin(cin.getText().toString());
            person.setFirstName(prenom.getText().toString());
            person.setCity(ville.getSelectedItem().toString());
            person.setImage("");
            person.setLastName(nom.getText().toString());
            person.setTel(mail.getText().toString());
            person.setTypeProfil(fonction.getSelectedItem().toString());
            account.setPassword(pwd.getText().toString());
            account.setUsername(mail.getText().toString());
            service1.setService_title(service.getSelectedItem().toString());
            service1.setService_id(service.getSelectedItemPosition());
            person.setService(service1);
            account.setPerson(person);
            Intent i= new Intent(this, LoginActivity.class);
            api.signup(account).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    Toast.makeText(inscription.this, "signed up successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Toast.makeText(inscription.this, "signed up failed!!!", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                }
            });
        }

    }
}