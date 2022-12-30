package com.example.myandroidapp.Activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Models.Account;
import com.example.myandroidapp.Models.Person;
import com.example.myandroidapp.Models.Service;
import com.example.myandroidapp.Models.Ville;
import com.example.myandroidapp.R;
import com.example.myandroidapp.Api.AccountApi;
import com.example.myandroidapp.retrofit.RetrofitClient;
import com.example.myandroidapp.retrofit.RetrofitS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    ApiInterface apiInterface;
    AccountApi api=retrofitS.getRetrofit().create(AccountApi.class);
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        Boolean islogin = sharedPref.getBoolean("userlogin", false);
        if(islogin){
            Intent i= new Intent(this, EmployeelistActivity.class);
            startActivity(i);
        }else{
        setContentView(R.layout.inscription);
        ButterKnife.bind(this);
        desc.setVisibility(View.GONE);
        service.setVisibility(View.GONE);
        msg.setVisibility(View.GONE);
        msg.setText("");
        List fcts= new ArrayList();
        List LisVille= new ArrayList();
        List Services= new ArrayList();
        //LisVille.add("Ville");
        /*LisVille.add("Kénitra");
        LisVille.add("Rabat");*/
        getVilles();

        fcts.add("Fonction");
        fcts.add("Employé");
        fcts.add("Client");
        Services.add("Service");
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                fcts
        );
       /* ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, LisVille
        );*/

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fonction.setAdapter(adapter);
        //ville.setAdapter(adapter1);




        api.listServices().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
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
            service.setVisibility(View.GONE);
            msg.setText("Veuillez chosir une fonction");
        } else if(pos==1) {
            msg.setText("");
            desc.setVisibility(View.VISIBLE);
            service.setVisibility(View.VISIBLE);
        }else{
            desc.setVisibility(View.GONE);
            service.setVisibility(View.GONE);
        }
    }
    @OnItemSelected(R.id.ville)
    public void clickVille(Spinner s,int pos) {
        if (pos == 0) {
            msg.setVisibility(View.VISIBLE);
            msg.setText("Veuillez chosir une ville");
        }
        else {
            msg.setText("");
        }
    }
    @OnItemSelected(R.id.service)
    public void clickService(Spinner s,int pos) {
        if (pos == 0) {
            msg.setText("Veuillez chosir un service");
        } else {
            msg.setText("");
        }
    }
    @OnClick(R.id.authentification)
    public void clickLogin(){
        Intent i= new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
/********************************************* REGEX ************************************************/
private boolean isValidMail(String email) {

    String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    return Pattern.compile(EMAIL_STRING).matcher(email).matches();

}
    private boolean isValidMobile(String phone) {
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 6 && phone.length() <= 13;
        }
        return false;
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
    /********************** LOGIN EXISTS *********************/

    RetrofitS retrofit= new RetrofitS();
    ApiInterface api1 =retrofit.getRetrofit().create(ApiInterface.class);

    private void loginExists(String login){

        api1.checkLogin(login).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                // display response as string
                if(response.body()) {
                    msg.setText("Ce mail/tél est dèja utilisé");
                }
                else register();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(inscription.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });

    }


    /******************************************/
    @OnClick(R.id.btnRegister)
    public void clickRegister(){
        if(fonction.getSelectedItemPosition()==0 || ville.getSelectedItemPosition()==0 ||
        mail.getText().toString().matches("") || prenom.getText().toString().matches("") || nom.getText().toString().matches("")
        || cin.getText().toString().matches("") || pwd.getText().toString().matches("")) {
            msg.setText("Veuillez remplir tous les champs!");
        }else if(!isValidMail(mail.getText().toString()) && !isValidMobile(mail.getText().toString())){
            msg.setText("Email ou Tél n'est pas valide!");
        }else if(fonction.getSelectedItemPosition()==1 && service.getSelectedItemPosition()==0){
            msg.setText("Veuillez choisir un service!");
        }else if(!isValidPwd(pwd.getText().toString())){
            msg.setText("le mot de passe doit contenir des caractères majuscules, minuscules, des chiffres et" +
                    "des symboles et de longueur minimale 8!");
        }else { loginExists(mail.getText().toString()); }

    }

    private void register() {
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
        if(fonction.getSelectedItemPosition()==2){
            service1.setService_title("Client");
            service1.setService_id(7);
        }else {
            service1.setService_title(service.getSelectedItem().toString());
            service1.setService_id(service.getSelectedItemPosition());
        }
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
    private void getVilles() {
        List <String> Ville =new ArrayList<>();
        ArrayAdapter <String> villeAdapter= new ArrayAdapter<>(inscription.this, android.R.layout.simple_list_item_1,Ville);
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<Ville>> call = apiInterface.getCities();
        call.enqueue(new Callback<List<Ville>>() {
            @Override
            public void onResponse(Call<List<Ville>> call, Response<List<Ville>> response) {
                if(response.isSuccessful()) {
                    for (Ville villeList :response.body()){
                        String nom_ville= villeList.getNom_ville();
                        // Ville object = new Ville(nom_ville);
                        Ville.add(nom_ville);

                        villeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        ville.setAdapter(villeAdapter);
                    }
                }


            }
            @Override
            public void onFailure(Call<List<Ville>> call, Throwable t) {
                Toast.makeText(inscription.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}