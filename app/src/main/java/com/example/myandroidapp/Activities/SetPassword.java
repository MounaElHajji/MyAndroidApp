package com.example.myandroidapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Models.Account;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.RetrofitClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetPassword extends AppCompatActivity {
    private String EMAIL= "email";
    public static final String SHARED_PREFS = "sharedPrefs";
    ApiInterface apiInterface;
    EditText Text;
    Button button;
    TextView msg;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        Text =findViewById(R.id.password);
        button=findViewById(R.id.valider_password);
        msg= findViewById(R.id.msg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                String Password = (Text.getText().toString());
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                String text = sharedPreferences.getString(EMAIL, "");
                String tag2= "Email";
                Log.i(tag2,text);
                Log.i(tag2,Password);

                if( Password.matches("")) {
                    msg.setText("Veuillez remplir le champ!");

                }else if(!isValidPwd(Text.getText().toString())){
                    msg.setText("le mot de passe doit contenir des caractères majuscules, minuscules, des chiffres et" +
                            "des symboles et de longueur minimale 8!");}
                else{
                    UpdateAccount(Password, text);
                    Intent i= new Intent(SetPassword.this,LoginActivity.class);
                    startActivity(i);
                }
            }
        });

    }
    private  boolean isValidPwd(String pwd){
        String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
   /*String PASSWORD_PATTERN =
            "[a-zA-Z]+";*/
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(pwd);
        return matcher.matches();
    }
    private void UpdateAccount(String password,String email) {
        Account account = new Account(password,email);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<Account> call = apiInterface.updateAccount(account);

        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Toast.makeText(SetPassword.this, "Data updated to API", Toast.LENGTH_SHORT).show();
                Account responseFromAPI = response.body();
                String responseString = "Response Code : " +password + "\nName : " + email;
                String tag="response";
                Log.i(tag,responseString);



            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }
}