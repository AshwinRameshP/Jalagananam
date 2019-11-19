package com.example.first;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;



public class index extends AppCompatActivity {
    public static String pla,Season,soil,Name;
    public static int plares=0;
    TextView locationText;
    LocationManager locationManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        // LOCATION
     //   locationText = (TextView) findViewById(R.id.locationText);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        // CROP
        Spinner spinner = findViewById(R.id.crop);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("-- --");
        arrayList.add("WHEAT");
        arrayList.add("TOMATO");
        arrayList.add("PULSES");
        arrayList.add("GROUNDNUT");
        arrayList.add("MAIZE");
        arrayList.add("ONION");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String plantname = parent.getItemAtPosition(position).toString().toLowerCase();
                pla = plantname;

                // Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // STAGE
        Spinner spinner2 = findViewById(R.id.stage);
        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("-- --");
        //arrayList2.add("SOWING");
        arrayList2.add("INITIAL");
        arrayList2.add("DEVELOPMENT");
       // arrayList2.add("MID");
        arrayList2.add("FINAL");
        //arrayList2.add("POST-SEASON");
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 Name = parent.getItemAtPosition(position).toString();
              //  Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });


        // SEASON
        Spinner spinner3 = findViewById(R.id.season);
        ArrayList<String> arrayList3 = new ArrayList<>();
        arrayList3.add("-- --");
        arrayList3.add("JAN");
        arrayList3.add("APR");
        arrayList3.add("JUL");
        arrayList3.add("SEP");
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList3);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(arrayAdapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 Season = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        //SOIL TYPE
        Spinner spinner4 = findViewById(R.id.spinner8);
        ArrayList<String> arrayList4 = new ArrayList<>();
        arrayList4.add("-- --");
        arrayList4.add("DRY");
        arrayList4.add("WET");
        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList4);
        arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(arrayAdapter4);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                soil = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        final Button btn = (Button) findViewById(R.id.calc1);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (isOnline()) {


                    Intent intent = new Intent(index.this, ftos.class);
                    Bundle extra = new Bundle();
                  //  extra.putString("crop",pla);
                  //  extra.putString("season",season);
                 //   extra.putString("crop",pla);
                    intent.putExtra("crop",pla);
                    intent.putExtra("season",Season);
                    intent.putExtra("soil",soil);
                    intent.putExtra("stage",Name);
                    startActivity(intent);
                    finish();

                } else {
                    try {
                        AlertDialog alertDialog = new AlertDialog.Builder(index.this).create();

                        alertDialog.setTitle("Info");
                        alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ;


                            }
                        });
                        alertDialog.show();

                    } catch (Exception e) {
                        Log.d("error", "Show Dialog: " + e.getMessage());
                    }

                }
            }

        });


    }











    public boolean isOnline(){
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(getApplicationContext() ,"No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
