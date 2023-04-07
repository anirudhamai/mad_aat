package com.example.hosp_sign_up;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class mapipop extends AppCompatActivity implements  GeoTask.Geo{
    FirebaseDatabase database;
    DatabaseReference wdatabase,w2,w3;
    TextView t1,name,no;
    Button b1;
    List<String> durs = new ArrayList<String>();
    boolean isavail;
    String finalloc,fincom,hname,sourcecom,desti;

    long count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapipop);
        t1=(TextView) findViewById(R.id.t1);
        name=(TextView) findViewById(R.id.dname);
        no=(TextView) findViewById(R.id.dno);
        b1=(Button) findViewById(R.id.button2);
        String sourcecom=getIntent().getStringExtra("cord");
        String desti=getIntent().getStringExtra("dest");
        String hname=getIntent().getStringExtra("name");
        w2=FirebaseDatabase.getInstance("https://hosp-db-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        w2.child("users").child("drivers").child("nanu").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                driver_reg_obj driver=task.getResult().getValue(driver_reg_obj.class);
                isavail=driver.checkavail();
                if(isavail){
                    name.setText(driver.getname());
                    no.setText(driver.getno());
                }
            }
        });

        Log.e("s",sourcecom);
        Log.e("hname",hname);
        Log.e("descom",desti);
        w3=FirebaseDatabase.getInstance("https://hosp-db-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(mapipop.this, maps1.class);
                intent.putExtra("dest_cor",desti);
                intent.putExtra("hospname",hname);
                startActivity(intent);
            }
        });
//        if(desti!=null){
//            Log.e("s",sourcecom);
//            Log.e("hname",hname);
//            Log.e("descom",desti);
//        }
    }

//    public void getduration(View view)  {
//        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + sourcecom + "&destinations=" + desti+ "&mode=driving&avoid=ferries&key=AIzaSyDUkdwhKB4gHn06NmQnPwtl7Vui29QnpHw";
//        Log.e("url",url);
//        new GeoTask(mapipop.this).execute(url);
//    }

    @Override
    public void setDouble(String result) {
        String res[]=result.split(",");
        t1.setText("ETA : "+res[0]);
    }
}