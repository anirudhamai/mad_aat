package com.example.hosp_sign_up;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class hosp_dash extends AppCompatActivity {

RadioGroup r;
RadioButton r1,r2;
TextView t1,t2,qno;
int i;
FirebaseDatabase database;
DatabaseReference wdatabase,wdatabase1;
ArrayList<String> qs=new ArrayList<String>();
RadioButton rc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        qs.add("Q1. Is ICU available ? ");
        qs.add("Q2. Is OT available ? ");
        qs.add("Q3. Are beds in ward available ? ");
        qs.add("Q4. Is treatment for Fire accident available (along with doctors and medicine)? ");
        qs.add("Q5. Is treatment for Road accident available (along with doctors and medicine)? ");
        qs.add("Q6. Is treatment for Water accident available (along with doctors and medicine) ? ");
        qs.add("Q7. Is treatment for Heart attack/Cardiac arrest available (along with doctors and medicine) ? ");
        qs.add("Q8. Is treatment for Deadly/Major Injuries available (along with doctors and medicine)");
        qs.add("Q9. Is treatment for obstetric emergencies / Child birth available (along with doctors and medicine)?");

        super.onCreate(savedInstanceState);
        String uname=getIntent().getStringExtra("uname");
        Bundle bundle = new Bundle();
        bundle.putString("uname", uname);
        setContentView(R.layout.activity_hosp_dash);
        r=(RadioGroup) findViewById(R.id.rg1);
        r1=(RadioButton) findViewById(R.id.r1);
        r2=(RadioButton) findViewById(R.id.r2);
        t1=(TextView)findViewById(R.id.t1);
        qno=(TextView)findViewById(R.id.qno);
        i=0;
        t1.setText(qs.get(i));
        qno.setText("Question attampted : "+i+"/9");
        wdatabase= FirebaseDatabase.getInstance("https://hosp-db-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int j) {
                Boolean ans;
                if(radioGroup.getCheckedRadioButtonId()==R.id.r1){
                    ans=true;
                }
                else{
                    ans=false;
                }

                wdatabase.child("users").child("hosp_dets").child(uname).child(String.valueOf(i+1)).setValue(ans);
                r1.setChecked(false);
                r2.setChecked(false);
                i+=1;
                qno.setText("Question attampted : "+i+"/9");

                if (i > 8) {
                    finish();
//                    i = 0;
//                    t1.setText(qs.get(i));
                }
                else{
                    t1.setText(qs.get(i));
                }
            }
        });
    }

}