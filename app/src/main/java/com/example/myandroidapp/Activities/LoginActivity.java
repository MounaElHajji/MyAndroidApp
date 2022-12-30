package com.example.myandroidapp.Activities;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myandroidapp.Api.AccountApi;
import com.example.myandroidapp.Models.Account;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.RetrofitS;

import java.util.logging.Level;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextTextPersonName)
    EditText username;

    @BindView(R.id.editTextNumberPassword)
    EditText pwd;

    @BindView(R.id.button2)
    Button loginButton;

    @BindView(R.id.pwdsh)
    CheckBox pwdsh;

    @BindView(R.id.signUp)
    TextView signUp;

    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        Boolean islogin = sharedPref.getBoolean("userlogin", false);
       /* if(islogin){
            Intent i= new Intent(this, EmployeelistActivity.class);
            startActivity(i);
        }else{*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);}


    @OnCheckedChanged(R.id.pwdsh)
    public void onCheckedChanged() {
        if (pwdsh.isChecked()) {
            // show password
            pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            pwdsh.setText("Cacher le mot de passe");
        } else {
            // hide password
            pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            pwdsh.setText("Afficher le mot de passe");
        }
    }
    @OnClick(R.id.signUp)
    public void  signUp(){
        Intent i= new Intent(this, inscription.class);
        startActivity(i);
    }

    @OnClick(R.id.button2)
    public  void loginClick(){

        RetrofitS retrofitS= new RetrofitS();
        AccountApi api=retrofitS.getRetrofit().create(AccountApi.class);
        String passwd=String.valueOf(pwd.getText());
        String usrname=String.valueOf(username.getText());
        Account account= new Account();
        account.setPassword(passwd);
        account.setUsername(usrname);
        Intent i= new Intent(this, listeServices.class);
        api.loginAccount(account) .enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()) {
                    /*logout*/
                    SharedPreferences.Editor edit =  sharedPref.edit();
                    edit.putBoolean("userlogin", true);
                    edit.commit();

                    Toast.makeText(LoginActivity.this, "login successful!", Toast.LENGTH_SHORT).show();
                    Account account1= response.body();
                    SharedPreferences.Editor myEdit = sharedPref.edit();
                    myEdit.putString("name", account1.getPerson().getFirstName() + " "+account1.getPerson().getLastName());
                    myEdit.putInt("id", Integer.parseInt(account1.getPerson().getId().toString()));
                    myEdit.commit();
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "login failed!!!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "login failed!!!", Toast.LENGTH_SHORT).show();
                Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });
        ;
    }
}