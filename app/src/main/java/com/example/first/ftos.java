package com.example.first;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ftos extends AppCompatActivity {
IndexDatabase id;
TextView text,a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftos);
        id=new IndexDatabase(this);
      //  id.addcrop();
        String croppass=getIntent().getStringExtra("crop").toLowerCase();
        String Seasonp=getIntent().getStringExtra("season");
        String Soilp=getIntent().getStringExtra("soil");
        String Name=getIntent().getStringExtra("stage");
        a=(TextView)findViewById(R.id.crop);
        a.setText(croppass);
        b=(TextView)findViewById(R.id.stage);
        b.setText(Name);

        double c=id.checkdata(croppass,Seasonp,Soilp,Name);
       text=(TextView)findViewById(R.id.season);

            text.setText(""+c+"mm " + "/" +"season" +"/"+"acre");



    }
}
