package com.example.ajdin.tracebrace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserAgree extends AppCompatActivity {

    TextView pro;
    TextView roop;
    Button dalje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agree);
        roop = findViewById(R.id.textView9);
        pro = findViewById(R.id.prozor);
        Intent intente  = getIntent();

        pro.setText("Dobro dosli u TraceBrace "+ intente.getExtras().getString("ime")+", hvala sto ste nas odabrali, molimo da pazljivo procitate uslove koristenja");
        roop.setText(R.string.EULA);

        dalje = findViewById(R.id.button4);

        System.out.println((dalje==null)+"  DALJE");
        final Intent intent = new Intent(this, PhoneSelector.class);
        dalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });



    }
}
