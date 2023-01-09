package com.example.myandroidapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myandroidapp.Api.ApiInterface;
import com.example.myandroidapp.R;
import com.example.myandroidapp.retrofit.RetrofitS;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity  extends AppCompatActivity {

EditText txt_mail;
Button send;
    EditText txt_code;
    Button send_code;
    String messageTosend;
    private String EMAIL= "email";
    public static final String SHARED_PREFS = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdp_oubliee);
        send = findViewById(R.id.valider);
        txt_mail = findViewById(R.id.email);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String username = "oumamimaryam49@gmail.com";
                final String password = "goxixrivbozbrgzx";
                messageTosend = RandGeneratedStr(5);


                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                //beacuse we are sending from gmail
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                //session  pour se connecter à l email avec qui on va envoyer msg
                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }

                        });




                RetrofitS retrofitS= new RetrofitS();
               ApiInterface api=retrofitS.getRetrofit().create(ApiInterface.class);
                api.checkLogin(txt_mail.getText().toString()).enqueue(new Callback<Boolean>() {

                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.body()) {
                        try {
                           System.out.println(response.body());
                            Log.i("SUCCES","send");
                            Toast.makeText(ForgotPasswordActivity.this, "email send!!!", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString(EMAIL, String.valueOf(txt_mail.getText()));
                            editor.apply();
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(username));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.valueOf(txt_mail.getText())));

                            message.setSubject("Verification Code");
                            message.setText(messageTosend);
                            Transport.send(message);

                        } catch (MessagingException e) {

                            Log.i("failed","failed to send ");
                            Toast.makeText(ForgotPasswordActivity.this, "email not sended!!!", Toast.LENGTH_SHORT).show();
                        }


                    }
                        else {
                            Log.i("SUCCES","not found");
                            System.out.println(response.body());
                            Toast.makeText(ForgotPasswordActivity.this, "email not found!!!", Toast.LENGTH_SHORT).show();}
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(ForgotPasswordActivity.this, "failed!!!", Toast.LENGTH_SHORT).show();
                    }

                });
            };
        });

            StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(tp);

                //sending verfication code
                send_code = findViewById(R.id.valider_code);
                txt_code = findViewById(R.id.code_verif);
                send_code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {
                        String code = (txt_code.getText().toString());
                        String tag = "Message";
                        String tag2 = "Code verif";
                        if (messageTosend.equals(code)) {
                            Log.i(tag, messageTosend);
                            Log.i(tag2, code);
                            Intent intent = new Intent(ForgotPasswordActivity.this, setPassword.class);
                            startActivity(intent);
                        } else {
                   /* Log.i(tag,messageTosend);
                    Log.i(tag2,code);*/

                            Toast.makeText(getApplicationContext(), "Code Erronee !!", Toast.LENGTH_LONG);


                        }
                    }
                });


                // defining a function to generate a random string of length l


            }






    public static String RandGeneratedStr(int l) {

        // a list of characters to choose from in form of a string
        String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        // creating a StringBuffer size of AlphaNumericStr
        StringBuilder s = new StringBuilder(l);
        int i;
        for (i = 0; i < l; i++) {
            //generating a random number using math.random()
            int ch = (int) (AlphaNumericStr.length() * Math.random());
            //adding Random character one by one at the end of s
            s.append(AlphaNumericStr.charAt(ch));
        }
        return s.toString();

    }


}

