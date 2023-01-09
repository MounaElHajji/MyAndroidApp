package com.example.myandroidapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myandroidapp.Api.AccountApi;
import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.Models.Account;
import com.example.myandroidapp.Models.Employee;
import com.example.myandroidapp.Models.Person;
import com.example.myandroidapp.Models.Service;
import com.example.myandroidapp.Models.Ville;
import com.example.myandroidapp.R;
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
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {
    Person person = new Person();
    Account account = new Account();

    private final int GALLERY = 1000;
    String path;
    private ImageView iv;
    @BindView(R.id.ville)
    Spinner ville;
    TextView msg;
    EditText username ,firstname,lastname,password, description;
    EditText cin;
    ImageView imageP;
    Spinner service;

    List<String> Ville =new ArrayList<>();
    List<String> Servicelistt =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        getUserDetails();

        //  getVilles();
        iv = findViewById(R.id.imageView);
        ville=findViewById(R.id.ville);
        service=findViewById(R.id.service);
        firstname =findViewById(R.id.prenom);
        lastname= findViewById(R.id.nom);
        cin =findViewById(R.id.cin);
        username= findViewById(R.id.mail);
        password= findViewById(R.id.pwd);
        description =findViewById(R.id.desc);
        imageP= findViewById(R.id.imageView);
        msg = findViewById(R.id.msg);
        getServices();
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GALLERY);
            }
        });
        Button register= findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                long id= sh.getInt("id",0);
                person.setId((int) id);
                person.setDescription(description.getText().toString());
                person.setCin(cin.getText().toString());
                person.setFirstName(firstname.getText().toString());
                person.setCity(ville.getSelectedItem().toString());
                person.setImage(path);
                person.setLastName(lastname.getText().toString());
                person.setTel(username.getText().toString());
                Service serv= new Service();
                serv.setService_title(service.getSelectedItem().toString());
                serv.setService_id(service.getSelectedItemPosition());
                System.out.println("service id "+ serv.getService_id());
                person.setService(serv);
                account.setPassword ( password.getText().toString());
                account.setUsername(username.getText().toString());
                account.setPerson(person);


                if(ville.getSelectedItemPosition()==0 ||
                        username.getText().toString().matches("") || firstname.getText().toString().matches("") || lastname.getText().toString().matches("")
                        || cin.getText().toString().matches("") || password.getText().toString().matches("")) {
                    msg.setText("Veuillez remplir tous les champs!");
                }else if(!isValidMail(username.getText().toString()) && !isValidMobile(username.getText().toString())){
                    msg.setText("Email ou Tél n'est pas valide!");
                }else if(!isValidPwd(password.getText().toString())){
                    msg.setText("le mot de passe doit contenir des caractères majuscules, minuscules, des chiffres et" +
                            "des symboles et de longueur minimale 8!");
                }else{  EDIT();}



            }
        });
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
    private void EDIT() {

        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<Account> call = apiInterface.edit(account);

        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(EditProfile.this, "Data updated to API", Toast.LENGTH_SHORT).show();
                    System.out.println("person:" +person +"  "+ account);
                }
                Toast.makeText(EditProfile.this, "Mot de passe incorrect", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }
    ApiInterface apiInterface;
    List<String> Services = new ArrayList<>();
    RetrofitS retrofitS = new RetrofitS();
    AccountApi api = retrofitS.getRetrofit().create(AccountApi.class);

    private void  getServices(){
        ArrayAdapter<String> serviceAdapter= new ArrayAdapter<>(EditProfile.this, android.R.layout.simple_list_item_1,Services);

        api.listServices().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()) {
                    for (String servicelistt :response.body()){
                        // String nom_ville= servicelisttge();
                        // Ville object = new Ville(nom_ville);
                        Services.add(servicelistt);

                        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        service.setAdapter(serviceAdapter);

                    }

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
    private void getUserDetails() {
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int id= sh.getInt("id",0);
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<Employee> call = apiInterface.getProfilePersonne(id);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {


                //get the data from the response
                Employee reponseEmp = response.body();
                String empVille = reponseEmp.getCity();
                String empTel = response.body().getTel();
                String empNom = reponseEmp.getFirst_name();
                String empPrenom = reponseEmp.getLast_name();
                String empCin = reponseEmp.getCin();
                String empDesc = reponseEmp.getDescription();
                String empEmploie = reponseEmp.getType_profil();
                String imagep= reponseEmp.getImageP();
                final int[] i = {0};
                ArrayAdapter<String> villeAdapter= new ArrayAdapter<>(EditProfile.this, android.R.layout.simple_list_item_1,Ville);
                ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
                Call<List<com.example.myandroidapp.Models.Ville>> call1 = apiInterface.getCities();
                call1.enqueue(new Callback<List<Ville>>() {
                    @Override
                    public void onResponse(Call<List<Ville>> call, Response<List<Ville>> response) {
                        if(response.isSuccessful()) {
                            for (Ville villeList :response.body()){
                                String nom_ville= villeList.getNom_ville();
                                // Ville object = new Ville(nom_ville);
                                Ville.add(nom_ville);

                                villeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                ville.setAdapter(villeAdapter);
                                i[0] = villeAdapter.getPosition(empVille);
                                System.out.println(i[0] + " "+ empVille);
                            }

                        }


                    }
                    @Override
                    public void onFailure(Call<List<Ville>> call, Throwable t) {
                        Toast.makeText(EditProfile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                ville.setSelection(i[0]);
                username.setText(empTel);
                firstname.setText(empNom +" "+empPrenom);
                // emploiTxt.setText(empEmploie);
                cin.setText(empCin);
                description.setText(empDesc);

              /*  Picasso.get()
                        .load(Uri.parse(imagep))
                        .centerCrop()
                        .resize(150,150)
                        .into(imageP);*/
                if(empEmploie.equals("Client")){
                    description.setVisibility(View.GONE);
                    service.setVisibility(View.GONE);


                }else{
                    description.setVisibility(View.VISIBLE);
                    service.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

            }
        });

    }
    @Override
    protected void  onActivityResult(int requestCode ,int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode== RESULT_OK){
            if(requestCode==GALLERY){
                //for gallery
                iv.setImageURI(data.getData());
                path= String.valueOf(data.getData());


            }
        }
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

    public void onBackClick(View view) {
        finish();
    }

    // -------- Footer icons listeners :

    public void onMessageClick(View view) {
        Intent intent = new Intent(this, listeServices.class);
        startActivity(intent);
        finish();
    }

    public void onFavorisClick(View view) {
        Intent intent = new Intent(this, FavorisActivity.class);
        startActivity(intent);
        finish();
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(this, EmployeelistActivity.class);
        startActivity(intent);
        finish();
    }

    public void onSettingsClick(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        finish();

   /* public void onProfilClick(View view) {
        Intent intent = new Intent(this, CurrentProfile.class);
        startActivity(intent);
        finish();
    }*/

    // -------- Footer icons listeners /
}
}