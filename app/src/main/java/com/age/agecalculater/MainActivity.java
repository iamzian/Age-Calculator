package com.age.agecalculater;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity{


    DatePicker datePicker;
    Button calAge;

    AdView ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ad = findViewById(R.id.adView);

        loadBanner();

        //Initialize
        init();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
        //For Current Date
        String strDate =  mdformat.format(cal.getTime());

        final int minage=1;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-minage);
        datePicker.setMaxDate(calendar.getTimeInMillis());

        calAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int dayPicked=datePicker.getDayOfMonth();
                int monthPicked= datePicker.getMonth()+1;
                int yearPicked = datePicker.getYear();
                final int selectedDates[] = {dayPicked, monthPicked, yearPicked};

                int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                if(currentYear-yearPicked<=minage)
                {
                    Toast.makeText(MainActivity.this, "Your Age should be more than One year", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this,Details.class);
                    intent.putExtra("pickDate",selectedDates);
                    startActivity(intent);
                }



            }
        });

    }

    public void init()
    {

        calAge = findViewById(R.id.calculate);
        datePicker=findViewById(R.id.datePicker);


    }

    private void loadBanner()
    {
        /*MobileAds.setRequestConfiguration(
                new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345"))
                        .build());*/

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        ad.loadAd(adRequest);
    }



}
