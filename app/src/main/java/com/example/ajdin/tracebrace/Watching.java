package com.example.ajdin.tracebrace;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Watching extends AppCompatActivity {

    double longi = 43.854970;
    double lat = 18.420411;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watching);
        Intent intent = getIntent();

        final String broj = intent.getExtras().getString("brojtel");
        final String poruka = intent.getExtras().getString("por")+" Lokacija: "+longi+" "+lat;
        System.out.println(broj+" "+poruka);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(broj, null, poruka, null, null);
        //THREAD DA PROVJERAVA WEB RADI
        new Thread(new Runnable() {
            public void run() {
               boolean b = true;

                while (true){
                    try{
                        URL url = new URL ("http://192.168.4.1/");
                        URLConnection konekcija = url.openConnection();
                        konekcija.connect();
                        BufferedReader in = new BufferedReader(new InputStreamReader(konekcija.getInputStream()));
                        String line;
                        while ((line = in.readLine()) != null) {
                            System.out.println(line);
                            if(line.equals("<html><p>B</p></html>")){

                                System.out.println("PRITISNUT TASTER");
                                if(b) {
                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage(broj, null, poruka, null, null);
                                    b = false;
                                    Snackbar.make(findViewById(R.id.myLAy), "Poruka je poslana ",Snackbar.LENGTH_LONG).show();
                                }
                            }
                            /*
                            //OVO JE ZA ZEZANJE SALJE MILION PORUKA
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(broj, null, poruka, null, null);
                            */
                        }
                        in.close();
                   }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}