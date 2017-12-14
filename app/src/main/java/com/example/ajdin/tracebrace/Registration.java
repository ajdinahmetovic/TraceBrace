package com.example.ajdin.tracebrace;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Registration extends Activity {

    Button reg;
    EditText name;
    EditText email;
    EditText password;
    EditText confirm;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
/*
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final User user = new User ();
        final Intent inten1 = new Intent(this, UserAgree.class);
        reg = findViewById(R.id.button2);
        name = findViewById(R.id.editText3);
        email = findViewById(R.id.editText4);
        password = findViewById(R.id.editText6);
        confirm = findViewById(R.id.editText7);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    user.setName(name.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setPassword(password.getText().toString());
                    if (!user.getPassword().equals(confirm.getText().toString())) {
                        throw new Exception("Passwords are not the same!\n");
                    }
                    if (!user.getEmail().isValid()) {
                        throw new Exception("Email is not valid\n");
                    }

                    if (user.getEmail().exists()) {
                        System.out.println("x");
                        throw new Exception("Email already exists\n");

                    }
                    user.writeToCSV("/storage/emulated/0/data_base.csv");
                    inten1.putExtra("ime", user.getName().fullName());
                    startActivity(inten1);
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
