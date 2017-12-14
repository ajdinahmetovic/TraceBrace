package com.example.ajdin.tracebrace;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {
    TextView reg;
    EditText email;
    EditText pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg = findViewById(R.id.register);
        email = findViewById(R.id.editText);
        pass = findViewById(R.id.editText2);
        login = findViewById(R.id.button);

        final Intent intent = new Intent(this, Registration.class);
        final Intent intent1 = new Intent (this, PhoneSelector.class );

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                User zero = new User("00 00","0","0");
                user.setEmail(email.getText().toString());
                user.setPassword(pass.getText().toString());
                try {
                    user = user.find("/storage/emulated/0/data_base.csv",(User.Info.EMAIL.getValue()|User.Info.PASSWORD.getValue()));
                    System.out.println(user.getName().fullName()+" "+user.getEmail().getEmail()+" "+user.getPassword());
                if(user.equals(zero)){
                    System.out.println("a");
                        throw new Exception("User does not exist");

                }
                    startActivity(intent1);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_LONG).show();
            }

           //     }
            }
        });




    }


    public void onClick(View view) {
        final Intent intent = new Intent(this, Registration.class);
        startActivity(intent);

    }
}
