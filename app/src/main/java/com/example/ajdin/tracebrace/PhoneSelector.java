package com.example.ajdin.tracebrace;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.net.wifi.WifiInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class PhoneSelector extends AppCompatActivity {


    Button add;
    TextView connection;
    EditText number;
    EditText msg;
    Button next;
    String broj;
    String poruka;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_selector);
        final Intent intent = new Intent(this, Watching.class);
        connection = findViewById(R.id.textView6);
        number = findViewById(R.id.editText5);
        msg = findViewById(R.id.editText8);
        next = findViewById(R.id.next);

        WifiManager wifiMgr = (WifiManager) PhoneSelector.this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        String name = wifiInfo.getSSID();

        if(name.equals("\"traceBrace\"")){
            connection.setText("You are successsfully connected to TraceBrace");
            System.out.println(name);
        }
        System.out.println(name);
     //   System.out.println(name);
        add = findViewById(R.id.dgm);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number.getText().toString().length()!=0 && msg.getText().toString().length()!=0) {
                    Snackbar.make(findViewById(R.id.myLAy), "Broj telefona sacuvan, mozete dodati ponovo", Snackbar.LENGTH_LONG).show();
                    intent.putExtra("brojtel",number.getText().toString());
                    intent.putExtra("por", msg.getText().toString());
                }
                else {
                    Snackbar.make(findViewById(R.id.myLAy), "Pogresan unos, molimo ponovite",Snackbar.LENGTH_LONG).show();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
