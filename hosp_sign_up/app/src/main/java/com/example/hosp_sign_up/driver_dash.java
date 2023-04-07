package com.example.hosp_sign_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class driver_dash extends AppCompatActivity {
    TextView t1;
    Switch s;
    Button b;
    Boolean isavail;
    DatabaseReference wdatabase,ww,w1,w2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dash);
        s=(Switch) findViewById(R.id.sw);
        t1=(TextView)findViewById(R.id.t1) ;
        b=(Button)findViewById(R.id.mapview) ;
        String user=getIntent().getStringExtra("uname").toString();

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                wdatabase= FirebaseDatabase.getInstance("https://hosp-db-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
                wdatabase.child("users").child("drivers").child(user).child("avail").setValue(s.isChecked());

                if(!ischecked){
                    b.setEnabled(false);
                }
                else
                    b.setEnabled(true);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driver_dash.this,driver_map.class);
                intent.putExtra("u_cor","13.1655064+77.5664645");
                startActivity(intent);
            }
        });

        ww= FirebaseDatabase.getInstance("https://hosp-db-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        ww.child("users").child("drivers").child(user).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                isavail=task.getResult().getValue(driver_reg_obj.class).checkavail();
                availfun();
            }
        });

    }
    public void availfun(){
        if(isavail)
        {
            w1=FirebaseDatabase.getInstance("https://hosp-db-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
            w1.child("users").child("bookings").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    t1.setText("You have new booking. Go fast.");
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else {
            t1.setText("");
        }
    }
}